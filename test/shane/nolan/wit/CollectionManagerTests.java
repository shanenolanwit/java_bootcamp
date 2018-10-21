package shane.nolan.wit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CollectionManagerTests {
	
	private final CollectionManager manager = new CollectionManager();
	private List<String> strings;
	
	

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	public CollectionManager getCollectionManager() {
		return manager;
	}

	@Before
	public void setUp() throws Exception {
		strings = new ArrayList<>(Arrays.asList(
				"abc","xyz",
				"abcd","wxyz",
				"email","hello","world",
				"number",
				"seventy","se7enty","grouped",
				"ambulance"				
				));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCollectionManager() {
	}

	@Test
	public void testCountShortStrings() {
		assertEquals(2, getCollectionManager().countShortStrings(strings, 4));
		assertEquals(4, getCollectionManager().countShortStrings(strings, 5));
	}

	@Test
	public void testCountLongStrings() {
		assertEquals(5, getCollectionManager().countLongStrings(strings, 5));
		assertEquals(1, getCollectionManager().countLongStrings(strings, 8));
	}

	@Test
	public void testRemoveShortStrings() {
		List<String> filteredList = getCollectionManager().removeShortStrings(strings, 4);
		assertEquals(10, filteredList.size());
		assertTrue(filteredList.contains("number"));
		assertFalse(filteredList.contains("xyz"));
	}

	@Test
	public void testRemoveLongStrings() {
		List<String> filteredList = getCollectionManager().removeLongStrings(strings, 4);
		assertEquals(4, filteredList.size());
		assertFalse(filteredList.contains("ambulance"));
		assertTrue(filteredList.contains("xyz"));
	}

	@Test
	public void testMapStringToStringLength() {
		Map<String,Integer> map = getCollectionManager().mapStringToStringLength(strings);
		assertEquals(12,map.size());
		assertEquals(5,map.get("email").intValue());
		assertEquals(3,map.get("abc").intValue());
		assertEquals(9,map.get("ambulance").intValue());
	}

	@Test
	public void testMapLengthToMatchingStrings() {
		Map<Integer, List<String>> map = getCollectionManager().mapLengthToMatchingStrings(strings);
		assertTrue(map.get(5).contains("email"));
		assertTrue(map.get(5).contains("world"));
		assertTrue(map.get(7).contains("se7enty"));
		assertFalse(map.get(7).contains("ambulance"));
		assertFalse(map.get(3).contains("world"));
		assertEquals(6, map.keySet().size());
	}

	@Test
	public void testReverseList() {
		List<String> reversedList = getCollectionManager().reverseList(strings);
		assertEquals(strings.size(), reversedList.size());
		assertEquals("ambulance", reversedList.get(0));
		assertEquals("abc", reversedList.get(11));
	}

	@Test
	public void testListContainsDuplicates() {
		List<String> dupes = Arrays.asList("hello","world","blah","hello","emoji");
		assertFalse(getCollectionManager().listContainsDuplicates(strings));
		assertTrue(getCollectionManager().listContainsDuplicates(dupes));
	}
	
	@Test
	public void testRemoveDuplicates() {
		List<String> dupes = Arrays.asList("hello","world","blah","hello","emoji");
		assertEquals(strings.size(),getCollectionManager().removeDuplicates(strings).size());
		assertEquals(12,getCollectionManager().removeDuplicates(strings).size());
		assertEquals(4,getCollectionManager().removeDuplicates(dupes).size());
	}

	@Test
	public void testGetFirstElements() {
		List<String> filteredList = getCollectionManager().getFirstElements(strings, 3);
		assertEquals(3, filteredList.size());
		assertTrue(filteredList.contains("abc"));
		assertTrue(filteredList.contains("xyz"));
		assertTrue(filteredList.contains("abcd"));
		assertFalse(filteredList.contains("hello"));
	}

	@Test
	public void testGetLastElements() {
		List<String> filteredList = getCollectionManager().getLastElements(strings, 3);
		assertEquals(3, filteredList.size());
		assertTrue(filteredList.contains("se7enty"));
		assertTrue(filteredList.contains("grouped"));
		assertTrue(filteredList.contains("ambulance"));
		assertFalse(filteredList.contains("hello"));
	}
	
	@Test
	public void testExtractElementsMachingPattern() {
		List<String> elements = Arrays.asList("US123","IRL456","UK987","RUS4419","224B2","BATMAN","44557799","batman@gothambank.com");
		//GET USERNAMES STARTING WITH AN UPPERCASE TWO TO THREE CHARACTER COUNTRY CODE AND ENDING IN A NUMBER
		assertEquals(4, getCollectionManager().extractElementsMatchingPattern(elements, "^[A-Z]{2,3}[0-9]+$").size());
		assertTrue(getCollectionManager().extractElementsMatchingPattern(elements, "^[A-Z]{2,3}[0-9]+$")
				.containsAll(Arrays.asList("US123","IRL456","UK987","RUS4419")));
		//GET STRINGS WITH NO NUMBERS
		assertEquals(2, getCollectionManager().extractElementsMatchingPattern(elements, "[^0-9]+").size());
		assertTrue(getCollectionManager().extractElementsMatchingPattern(elements, "[^0-9]+")
				.containsAll(Arrays.asList("BATMAN","batman@gothambank.com")));
		//FIND STRINGS STARTING WITH BAT or bat
		assertEquals(2, getCollectionManager().extractElementsMatchingPattern(elements, "^(BAT.+|bat.+)").size());
		assertTrue(getCollectionManager().extractElementsMatchingPattern(elements, "^(BAT.+|bat.+)")
				.containsAll(Arrays.asList("BATMAN","batman@gothambank.com")));
		//FIND STRINGS ENDING IN A SINGLE DIGIT - ABC1 is valid as 1 is a single digit, XYZ54 is not valid as its two digits
		assertEquals(1, getCollectionManager().extractElementsMatchingPattern(elements, "^.+[^0-9][0-9]{1}$").size());
		assertTrue(getCollectionManager().extractElementsMatchingPattern(elements, "^.+[^0-9][0-9]{1}$")
				.containsAll(Arrays.asList("224B2")));		
	}
	
	@Test
	public void testExtractValidEmailAddresses() {
		List<String> elements = Arrays.asList("spiderman.com","support@onaware.com","@yahoo.net","abc@.abc",
				"trump@us.gov","34252","cafe_babe@java.com","8872what!","batman22@gothambank.ie","frank@gov.");
		System.out.println(getCollectionManager().extractValidEmailAddresses(elements));
		assertEquals(4, getCollectionManager().extractValidEmailAddresses(elements).size());
		assertTrue(getCollectionManager().extractValidEmailAddresses(elements)
				.containsAll(Arrays.asList("support@onaware.com","trump@us.gov","cafe_babe@java.com","batman22@gothambank.ie")));	
	}
	
	@Test
	public void testPickStringAtIndex(){
		List<String> names = Arrays.asList("batman","superman","spiderman","ironman","wonder woman","santa claus");
		assertEquals("spiderman", getCollectionManager().pickStringAtIndex(names, 2));
		assertEquals("spiderman", getCollectionManager().pickStringAtIndex(names, 8));
		assertEquals("batman", getCollectionManager().pickStringAtIndex(names, 0));
		assertEquals("batman", getCollectionManager().pickStringAtIndex(names, 6));
		assertEquals("wonder woman", getCollectionManager().pickStringAtIndex(names, 4));
		assertEquals("wonder woman", getCollectionManager().pickStringAtIndex(names, 820));
		assertEquals("superman", getCollectionManager().pickStringAtIndex(names, 1));
		assertEquals("superman", getCollectionManager().pickStringAtIndex(names, 406699));
		assertEquals("santa claus", getCollectionManager().pickStringAtIndex(names, -1));
		assertEquals("santa claus", getCollectionManager().pickStringAtIndex(names, -7));
		assertEquals("wonder woman", getCollectionManager().pickStringAtIndex(names, -8));
		assertEquals("wonder woman", getCollectionManager().pickStringAtIndex(names, -404));
		assertEquals("spiderman", getCollectionManager().pickStringAtIndex(names, -4));
		assertEquals("batman", getCollectionManager().pickStringAtIndex(names, -600));		
	}
	
	@Test
	public void testFindSequences(){
		List<Integer> groupA = Arrays.asList(1,2,3,4,6,7,9,10,15,206,207,208);
		//[[1, 2, 3, 4], [6, 7], [9, 10], [15], [206, 207, 208]]
		List<List<Integer>> subGroupsA = getCollectionManager().findSequences(groupA);		
		
		assertEquals(5,subGroupsA.size());
		assertEquals(4,subGroupsA.get(0).size());
		assertEquals(2,subGroupsA.get(1).size());
		assertEquals(2,subGroupsA.get(2).size());
		assertEquals(1,subGroupsA.get(3).size());
		assertEquals(3,subGroupsA.get(4).size());
		assertTrue(subGroupsA.get(0).containsAll(Arrays.asList(1,2,3,4)));
		assertTrue(subGroupsA.get(1).containsAll(Arrays.asList(6,7)));
		assertTrue(subGroupsA.get(2).containsAll(Arrays.asList(9,10)));
		assertTrue(subGroupsA.get(3).containsAll(Arrays.asList(15)));
		assertTrue(subGroupsA.get(4).containsAll(Arrays.asList(206,207,208)));		
		
		List<Integer> groupB = Arrays.asList(3,4,5,8,11,101,102,200);
		//[[3, 4, 5], [8], [11], [101, 102], [200]]
		List<List<Integer>> subGroupsB = getCollectionManager().findSequences(groupB);
		
		assertEquals(5,subGroupsB.size());
		assertEquals(3,subGroupsB.get(0).size());
		assertEquals(1,subGroupsB.get(1).size());
		assertEquals(1,subGroupsB.get(2).size());
		assertEquals(2,subGroupsB.get(3).size());
		assertEquals(1,subGroupsB.get(4).size());
		assertTrue(subGroupsB.get(0).containsAll(Arrays.asList(3,4,5)));
		assertTrue(subGroupsB.get(1).containsAll(Arrays.asList(8)));
		assertTrue(subGroupsB.get(2).containsAll(Arrays.asList(11)));
		assertTrue(subGroupsB.get(3).containsAll(Arrays.asList(101,102)));
		assertTrue(subGroupsB.get(4).containsAll(Arrays.asList(200)));
		
	}
	
	@Test
	public void testForcedExceptions(){
		assertTrue(getCollectionManager().forceIndexOutOfBoundsException() instanceof IndexOutOfBoundsException);
		assertTrue(getCollectionManager().forceUnsupportedOperationException() instanceof UnsupportedOperationException);
	}
	
	@Test
	public void testGetMostFrequentWord(){
		assertEquals("bird",getCollectionManager().getMostFrequentWord(Arrays.asList("bird","dog","bird","cat")));
		assertEquals("jam",getCollectionManager().getMostFrequentWord(Arrays.asList("butter","jam","bread","jam")));
	}
	
	@Test
	public void testGetLeastFrequentWord(){
		assertEquals("bird",getCollectionManager().getLeastFrequentWord(Arrays.asList("cat","bird","dog","dog","cat")));
		assertEquals("bread",getCollectionManager().getLeastFrequentWord(Arrays.asList("butter","jam","bread","jam","butter")));
	}
	
	@Test
	public void testEvenlySpaced(){
		assertTrue(getCollectionManager().evenlySpaced(Arrays.asList(2,4,6,8,10)));
		assertTrue(getCollectionManager().evenlySpaced(Arrays.asList(15,30,45,60)));
		assertFalse(getCollectionManager().evenlySpaced(Arrays.asList(100,101,103)));
		assertFalse(getCollectionManager().evenlySpaced(Arrays.asList(1,2,3,5,6,7)));		
	}
	
	@Test
	public void testSplitCanBalance(){
		assertTrue(getCollectionManager().splitCanBalance(Arrays.asList(1,2,3)));
		assertTrue(getCollectionManager().splitCanBalance(Arrays.asList(1,2,3,4,5,5)));
		assertTrue(getCollectionManager().splitCanBalance(Arrays.asList(1,1,1,1,1,2,3)));
		assertTrue(getCollectionManager().splitCanBalance(Arrays.asList(20,20,5,5,30)));
		assertFalse(getCollectionManager().splitCanBalance(Arrays.asList(3,6,7)));
		assertFalse(getCollectionManager().splitCanBalance(Arrays.asList(10,11,12)));
	}

}

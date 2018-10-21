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

}

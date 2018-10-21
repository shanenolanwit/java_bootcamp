package shane.nolan.wit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringManagerTests {
	
	private final StringManager stringManager = new StringManager();	

	public StringManager getStringManager() {
		return stringManager;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testRemoveVowels() {
		assertEquals("hll",getStringManager().removeVowels("hello"));
		assertEquals("",getStringManager().removeVowels("aaaiii"));
		assertEquals("rhythm",getStringManager().removeVowels("rhythm"));
	}

	@Test
	public void testRemoveConsonants() {
		assertEquals("eo",getStringManager().removeConsonants("hello"));
		assertEquals("aaaiii",getStringManager().removeConsonants("aaaiii"));
		assertEquals("",getStringManager().removeConsonants("rhythm"));
	}

	@Test
	public void testGetFirstCharacter() {
		assertEquals('h', getStringManager().getFirstCharacter("hello"));
		assertEquals('4', getStringManager().getFirstCharacter("4worlds"));
	}

	@Test
	public void testGetLastCharacter() {
		assertEquals('o', getStringManager().getLastCharacter("hello"));
		assertEquals('4', getStringManager().getLastCharacter("world4"));
	}

	@Test
	public void testContainsNumbers() {
		assertTrue(getStringManager().containsNumbers("h3llo w0rld"));
		assertTrue(getStringManager().containsNumbers("2 x 2 = 4"));
		assertTrue(getStringManager().containsNumbers("101"));
		assertTrue(getStringManager().containsNumbers("abcdefghijklmnopqrstuvwxyz8abcdefghijklmnopqrstuvwxyz"));
		
		assertFalse(getStringManager().containsNumbers("abcdefghijklmnopqrstuvwxyz"));
		assertFalse(getStringManager().containsNumbers("$dollars"));
		assertFalse(getStringManager().containsNumbers("bruce@wayne.com"));
	}

	@Test
	public void testRemoveNumbers() {
		assertEquals("hello",getStringManager().removeNumbers("hell4o"));
		assertEquals("",getStringManager().removeNumbers("123"));
		assertEquals("world",getStringManager().removeNumbers("world"));
	}

	@Test
	public void testCountCharacters() {
		assertEquals(5, getStringManager().countCharacters("world"));
		assertEquals(0, getStringManager().countCharacters(""));
		assertEquals(1, getStringManager().countCharacters("s"));
	}

	@Test
	public void testReverseString() {
		assertEquals("dlrow", getStringManager().reverseString("world"));
		assertEquals("abcd", getStringManager().reverseString("dcba"));
		assertEquals("1881", getStringManager().reverseString("1881"));
		assertEquals("moc.liamg@anna", getStringManager().reverseString("anna@gmail.com"));
	}

	@Test
	public void testIsPalindrome() {
		assertTrue(getStringManager().isPalindrome("wow"));
		assertTrue(getStringManager().isPalindrome("8448"));
		assertTrue(getStringManager().isPalindrome("redTder"));
		assertFalse(getStringManager().isPalindrome("admin@microsoft.com"));
		assertFalse(getStringManager().isPalindrome("blue45eulb"));
	}

	@Test
	public void testDuplicate() {
		String[] strings = getStringManager().duplicate("java", 5);
		assertEquals(5, strings.length);
		assertEquals("java", strings[0]);
		assertEquals("java", strings[4]);
		
		strings = getStringManager().duplicate("bruce@wayne.com", 100);
		assertEquals(100, strings.length);
		assertEquals("bruce@wayne.com", strings[0]);
		assertEquals("bruce@wayne.com", strings[47]);
		assertEquals("bruce@wayne.com", strings[99]);
		
		strings = getStringManager().duplicate("apple3", 1);
		assertEquals(1, strings.length);
		assertEquals("apple3", strings[0]);
		
		strings = getStringManager().duplicate("hurrah", 0);
		assertEquals(0, strings.length);
		
	}

	@Test
	public void testStringsAreEqual() {
		assertTrue(getStringManager().stringsAreEqual("hellO", "hELLo"));
	}

}

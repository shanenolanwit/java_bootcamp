package shane.nolan.wit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringManager {
	
	public StringManager(){}
	
	/**
	 * Given a string, return the same string but without the vowels
	 * @param str
	 * @return
	 */
	public String removeVowels(String str){
		return str.replaceAll("[aeiouAEIOU]", "");
	}
	
	/**
	 * Given a string, return the same string but without the consonants
	 * @param str
	 * @return
	 */
	public String removeConsonants(String str){
		return str.replaceAll("[^aeiouAEIOU]", "");
	}
	
	/**
	 * Given a string, return the first character
	 * @param str
	 * @return
	 */
	public char getFirstCharacter(String str){		
		return str.charAt(0);
	}
	
	/**
	 * Given a string, return the last character
	 * @param str
	 * @return
	 */
	public char getLastCharacter(String str){
		return str.charAt(str.length()-1);
	}
	
	/**
	 * Given a string, return true if the string contains numbers, otherwise return false
	 * @param str
	 * @return
	 */
	public boolean containsNumbers(String str){
		return str.matches("^.+?[0-9].+$");
	}
	
	/**
	 * Given a string, returns the string without the numbers
	 * @param str
	 * @return
	 */
	public String removeNumbers(String str){
		return str.replaceAll("[0-9]","");
	}
	
	/**
	 * Given a string, return the number of chars
	 * @param str
	 * @return
	 */
	public int countCharacters(String str){
		return str.length();
	}
	
	/**
	 * Given a string, returns the same string but reversed
	 * @param str
	 * @return
	 */
	public String reverseString(String str){
		return new StringBuilder(str).reverse().toString();
	}
	
	/**
	 * Returns a boolean representing if this string is spelt the same forwards and backwards
	 * @param str
	 * @return
	 */
	public boolean isPalindrome(String str){
		return str.equals(reverseString(str));
	}	
	
	/**
	 * Given a string, returns an array with a given number of copies of that string
	 * (batman,3) would return [batman,batman,batman]
	 * @param str
	 * @param numberOfDuplicates
	 * @return
	 */
	public String[] duplicate(String str, int numberOfDuplicates){
		String[] duplicates = new String[numberOfDuplicates];
		for(int i = 0; i < numberOfDuplicates; i++){
			duplicates[i] = str;
		}
		return duplicates;
	}
	
	/**
	 * Returns true if these two strings are the same (not case sensitive)
	 * hello would be equal to HeLLO
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean stringsAreEqual(String a, String b){
		return a.equalsIgnoreCase(b);
	}
	
	/**
	 * Counts how many words in a given sentence start with a given string s
	 * @param sentence
	 * @param s
	 * @param caseSensitive
	 * @return
	 */
	public int countStartsWith(String sentence, String s, boolean caseSensitive){
		int count = 0;
		String[] words = sentence.split(" ");
		for(int i=0; i<words.length; i++){
			boolean match = (caseSensitive) ? words[i].startsWith(s) : words[i].toLowerCase().startsWith(s.toLowerCase());
			if(match){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Counts how many words in a given sentence end with a given string s
	 * @param sentence
	 * @param s
	 * @param caseSensitive
	 * @return
	 */
	public int countEndsWith(String sentence, String s, boolean caseSensitive){
		int count = 0;
		String[] words = sentence.split(" ");
		for(int i=0; i<words.length; i++){
			boolean match = (caseSensitive) ? words[i].endsWith(s) : words[i].toLowerCase().endsWith(s.toLowerCase());
			if(match){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Given a string composed of all integer characters, return the sum of those integers
	 * 123 would return 6 (1+2+3) and 9909 would return 27 (9+9+0+9)
	 * @param str
	 * @return
	 */
	public int addLetterValues(String str){
		String[] strArr = str.split("");
		int total = 0;
		for(int i=0; i< strArr.length; i++){
			total += Integer.parseInt(strArr[i]);
		}
		return total;
	}
	
	
	/**
	 * Given a date in the format: Year-Month-Day Hour:Minute:Second
	 * Return the date in the format Day-Month-Year Hour:Minute AM|PM
	 * Assume the input is always the same format and uses the 24 hour clock
	 * 1999-05-29 15:10:02 represents 10 minutes and 2 seconds past 3PM on the 29th of May in the year 1999
	 * The result we want back in this instance is 29-05-1999 3:10 PM
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public String reformatDateString(String dateString) throws ParseException {
		return new SimpleDateFormat("dd-MM-yyyy h:mm a")
				.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(dateString));
	}

}

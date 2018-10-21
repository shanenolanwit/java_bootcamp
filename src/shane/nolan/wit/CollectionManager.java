package shane.nolan.wit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionManager {

	public CollectionManager() {
	}

	/**
	 * Counts how many strings in a given list are shorter than a given length
	 * 
	 * @param strings
	 * @param length
	 * @return
	 */
	public int countShortStrings(List<String> strings, int length) {
		List<String> shortStrings = strings.stream()
				.filter(str -> str.length() < length)
				.collect(Collectors.toList());
		return shortStrings.size();
	}

	/**
	 * Counts how many strings in a given list are longer than a given length
	 * 
	 * @param strings
	 * @param length
	 * @return
	 */
	public int countLongStrings(List<String> strings, int length) {
		List<String> longStrings = strings.stream()
				.filter(str -> str.length() > length)
				.collect(Collectors.toList());
		return longStrings.size();
	}

	/**
	 * Finds strings in a given list which are shorter than a given length and
	 * removes them
	 * 
	 * @param strings
	 * @param length
	 * @return
	 */
	public List<String> removeShortStrings(List<String> strings, int length) {
		List<String> longStrings = strings.stream()
				.filter(str -> str.length() >= length)
				.collect(Collectors.toList());
		return longStrings;
	}

	/**
	 * Finds strings in a given list are longer than a given length and removes
	 * them
	 * 
	 * @param strings
	 * @param length
	 * @return
	 */
	public List<String> removeLongStrings(List<String> strings, int length) {
		List<String> shortStrings = strings.stream()
				.filter(str -> str.length() <= length)
				.collect(Collectors.toList());
		return shortStrings;
	}

	/**
	 * Given a list of strings, returns a map where the strings are the keys and
	 * the values are the string length [hello,world,cat,ambulance] would return
	 * {hello -> 5, world -> 5, cat -> 3, ambulance -> 9}
	 * 
	 * @param strings
	 * @return
	 */
	public Map<String, Integer> mapStringToStringLength(List<String> strings) {
		return strings.stream().collect(
				Collectors.toMap(str -> str, str -> str.length()));
	}

	/**
	 * Given a list of strings, returns a map where the keys are the unique
	 * strings lengths found in the list and the values are the strings matching
	 * each length [hello,world,cat,ambulance] would return {5 -> [hello,
	 * world], 3 -> [cat] -> 3, 9 -> [ambulance]}
	 * 
	 * @param strings
	 * @return
	 */
	public Map<Integer, List<String>> mapLengthToMatchingStrings(List<String> strings) {
		return strings.stream().collect(
				Collectors.groupingBy(str -> str.length()));
	}

	/**
	 * Given a list of strings, returns the list reversed
	 * 
	 * @param strings
	 * @return
	 */
	public List<String> reverseList(List<String> strings) {
		List<String> reversed = new ArrayList<String>(strings.size());
		for(int i=strings.size()-1; i >= 0; i--){
			reversed.add(strings.get(i));
		}
		return reversed;
	}

	/**
	 * Returns true if a list contains duplicates
	 * 
	 * @param strings
	 * @return
	 */
	public boolean listContainsDuplicates(List<String> strings) {
		return strings.stream().count() != strings.stream().distinct().count();
	}

	/**
	 * Removes duplicate items from the given list of strings
	 * 
	 * @param strings
	 * @return
	 */
	public Collection<String> removeDuplicates(List<String> strings) {
		return strings.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * Given a list of strings, returns the first n items of that list
	 * 
	 * @param strings
	 * @param numberOfElements
	 * @return
	 */
	public List<String> getFirstElements(List<String> strings, int numberOfElements) {
		List<String> firstN = new ArrayList<String>(numberOfElements);
		for(int i=0; i< numberOfElements; i++){
			firstN.add(strings.get(i));
		}
		return firstN;
	}

	/**
	 * Given a list of strings, returns the last n items of that list
	 * 
	 * @param strings
	 * @param numberOfElements
	 * @return
	 */
	public List<String> getLastElements(List<String> strings, int numberOfElements) {
		List<String> lastN = new ArrayList<String>(numberOfElements);
		for(int i=1; i<= numberOfElements; i++){
			lastN.add(strings.get(strings.size()-i));
		}
		return lastN;
	}

	/**
	 * Return a list of strings taken from a provided list, that match a
	 * provided pattern
	 * 
	 * @param strings
	 * @param pattern
	 * @return
	 */
	public List<String> extractElementsMatchingPattern(List<String> strings, String pattern) {
		return strings
				.stream()
				.filter(str -> str.matches(pattern))
				.collect(Collectors.toList());
	}

	/**
	 * Return a list of valid email addresses taken from the provided list NB:
	 * This does not need to be a comprehensive solution that matches all
	 * official RFCs
	 * 
	 * @param strings
	 * @return
	 */
	public List<String> extractValidEmailAddresses(List<String> strings) {
		return strings
				.stream()
				.filter(str -> str
						.matches("^[a-zA-Z0-9_]{1,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z]{1,}$"))
				.collect(Collectors.toList());
	}

	/**
	 * Returns a string at a given index, if the index is greater than the size
	 * of the list, then the search wraps around A negative index counts from
	 * the end of the list backward. Using -1 as index gets the last entry, -2
	 * as an index gets the next-to-last entry [a,b,c,d] index 0 returns a,
	 * index 3 returns d, index 4 returns a, index 6 returns c Given a minus
	 * number, the search goes backwards [a,b,c,d] index -1 returns d, -3
	 * returns b, -5 returns d, -8 returns a
	 * 
	 * @param strings
	 * @param index
	 * @return
	 */
	public String pickStringAtIndex(List<String> strings, int index) {
		// [a,b,c] @ position -1 becomes [c,b,a] @ position 0
		return (index >= 0) ? strings.get(index % strings.size()) : pickStringAtIndex(reverseList(strings), (index*-1)-1);	
	}

	/**
	 * Given a list of integers, break up the list into subgroups of sequential
	 * integers [1,2,3,7,8,9] would return [[1,2,3],[7,8,9]] [1,2,50,101,102]
	 * would return [[1,2],[50],[101,102]]
	 * 
	 * @param integers
	 * @return
	 */
	public List<List<Integer>> findSequences(List<Integer> integers) {
		List<List<Integer>> groupList = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		for(int i=0; i < integers.size(); i++) {
			//If the list has no elements or the current item is 1 greater than the last
			if(subList.isEmpty() || subList.get(subList.size() -1) + 1 == integers.get(i) ) {
				subList.add(integers.get(i));
			} else {
				//We have reached the end of a sequence, this number does not follow the last, add it to a new sequence
				groupList.add(subList);
				subList = new ArrayList<Integer>();
				subList.add(integers.get(i));
			}
		}
		groupList.add(subList);
		return groupList;
	}

	/**
	 * Encountering an exception can happen often when working with collections
	 * Understanding the exception is the first step towards building a solution
	 * Research IndexOutOfBoundsException in relation to collections Create a
	 * new collection and perform some action which causes an
	 * IndexOutOfBoundsException Capture the exception and return it safely
	 * 
	 * @return
	 */
	public IndexOutOfBoundsException forceIndexOutOfBoundsException() {
		IndexOutOfBoundsException e = null;
		try {
			Arrays.asList("dog", "ball", "hat").get(9000);
		} catch (IndexOutOfBoundsException ioobe) {
			e = ioobe;
		}
		return e;
	}

	/**
	 * Encountering an exception can happen often when working with collections
	 * Understanding the exception is the first step towards building a solution
	 * Research UnsupportedOperationException in relation to collections Create
	 * a new collection and perform some action which causes an
	 * IndexOutOfBoundsException Capture the exception and return it safely
	 * 
	 * @return
	 */
	public UnsupportedOperationException forceUnsupportedOperationException() {
		UnsupportedOperationException e = null;
		try {
			Arrays.asList("dog", "ball", "hat").add("lady");
		} catch (UnsupportedOperationException uoe) {
			e = uoe;
		}
		return e;
	}

	/**
	 * Finds the word which appears most frequently in a given list
	 * 
	 * @param words
	 * @return
	 */
	public String getMostFrequentWord(List<String> words) {
		String mostFrequentWord = words.get(0);
		int max = 0;		
		for(String word : words) {
			int wordCount = Collections.frequency(words, word);
			if(wordCount > max) {
				mostFrequentWord = word;
				max = wordCount;
			}
		}
		
		return mostFrequentWord;
	}

	/**
	 * Finds the word which appears least frequently in a given list
	 * 
	 * @param words
	 * @return
	 */
	public String getLeastFrequentWord(List<String> words) {
		String leastFrequentWord = words.get(0);
		int least = words.size();		
		for(String word : words) {
			int wordCount = Collections.frequency(words, word);
			if(wordCount < least) {
				leastFrequentWord = word;
				least = wordCount;
			}
		}
		
		return leastFrequentWord;
	}

	/**
	 * Returns true if the space between each number is identical
	 * 
	 * @param numbers
	 * @return
	 */
	public boolean evenlySpaced(List<Integer> numbers) {
		boolean result = true;
		if(numbers.size() > 2) {
			int interval = numbers.get(1) - numbers.get(0);			
			for(int i = 0; i < numbers.size() - 1; i++) {
				if(numbers.get(i + 1) - numbers.get(i) != interval){
					result = false;
				}
			}
		}
		return result;

	}

	/**
	 * Determines if the list can be split into two groups at a point where the
	 * sum of the two groups is even [2,2,4] can be split into [2,2] and [4]
	 * [10,5,5,5,5] can be split into [10,5] and [5,5,5] [1,10,7,9] can not be
	 * split into two equal groups
	 * 
	 * @param numbers
	 * @return
	 */
	public boolean splitCanBalance(List<Integer> numbers) {
		boolean canSplit = false;

		for(int i = 0; i < numbers.size(); i++) {
			if(sum(numbers.subList(0, i)) == sum(numbers.subList(i, numbers.size()))) {
				canSplit = true;
				break;
			}
		}
		return canSplit;
	}
	
	private int sum(List<Integer> numbers){
		return numbers.stream().mapToInt(x -> x).sum();
	}

}

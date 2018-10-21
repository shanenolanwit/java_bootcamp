package shane.nolan.wit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CollectionManager {
	
	public CollectionManager(){}
	
	/**
	 * Counts how many strings in a given list are shorter than a given length
	 * @param strings
	 * @param length
	 * @return
	 */
	public int countShortStrings(List<String> strings, int length){
		return new Long(strings.stream()
						.filter(str -> str.length() < length)
						.count())
					.intValue();
	}
	
	/**
	 * Counts how many strings in a given list are longer than a given length
	 * @param strings
	 * @param length
	 * @return
	 */
	public int countLongStrings(List<String> strings, int length){
		return new Long(strings.stream()
							.filter(str -> str.length() > length)
							.count())
					.intValue();
	}
	
	/**
	 * Finds strings in a given list which are shorter than a given length and removes them
	 * @param strings
	 * @param length
	 * @return
	 */
	public List<String> removeShortStrings(List<String> strings, int length){
		return strings.stream()
				.filter(str -> str.length() >= length)
				.collect(Collectors.toList());
	}
	
	/**
	 * Finds strings in a given list are longer than a given length and removes them
	 * @param strings
	 * @param length
	 * @return
	 */
	public List<String> removeLongStrings(List<String> strings, int length){
		return strings.stream()
				.filter(str -> str.length() <= length)
				.collect(Collectors.toList());
	}
	
	/**
	 * Given a list of strings, returns a map where the strings are the keys and the values are the string length
	 * [hello,world,cat,ambulance] would return {hello -> 5, world -> 5, cat -> 3, ambulance -> 9}
	 * @param strings
	 * @return
	 */
	public Map<String,Integer> mapStringToStringLength(List<String> strings){		
		return strings.stream()				
				.collect(Collectors.toMap(str -> str, str -> str.length()));
	}
	
	/**
	 * Given a list of strings, returns a map where the keys are the unique strings lengths found in the list and the values are the strings matching each length
	 * [hello,world,cat,ambulance] would return {5 -> [hello, world], 3 -> [cat] -> 3, 9 -> [ambulance]}
	 * @param strings
	 * @return
	 */
	public Map<Integer,List<String>> mapLengthToMatchingStrings(List<String> strings){
		return strings.stream()
				.collect(Collectors.groupingBy(str -> str.length()));
	}
	
	/**
	 * Given a list of strings, returns the list reversed
	 * @param strings
	 * @return
	 */
	public List<String> reverseList(List<String> strings){
		return IntStream.range(0, strings.size())
				.boxed()
				.sorted(Collections.reverseOrder())
				.map(i -> strings.get(i))
				.collect(Collectors.toList());
	}
	
	/**
	 * Returns true if a list contains duplicates
	 * @param strings
	 * @return
	 */
	public boolean listContainsDuplicates(List<String> strings){
		return strings.stream().count() != strings.stream().distinct().count();
	}
	
	/**
	 * Removes duplicate items from the given list of strings
	 * @param strings
	 * @return
	 */
	public Collection<String> removeDuplicates(List<String> strings){
		return strings.stream()
				.distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Given a list of strings, returns the first n items of that list
	 * @param strings
	 * @param numberOfElements
	 * @return
	 */
	public List<String> getFirstElements(List<String> strings, int numberOfElements){
		return IntStream.range(0, numberOfElements)
				.boxed()
				.map(i -> strings.get(i))
				.collect(Collectors.toList());
	}
	
	/**
	 * Given a list of strings, returns the last n items of that list
	 * @param strings
	 * @param numberOfElements
	 * @return
	 */
	public List<String> getLastElements(List<String> strings, int numberOfElements){
		return IntStream.range(strings.size()-numberOfElements, strings.size())
				.boxed()
				.map(i -> strings.get(i))
				.collect(Collectors.toList());
	}
	
	/**
	 * Return a list of strings taken from a provided list, that match a provided pattern
	 * @param strings
	 * @param pattern
	 * @return
	 */
	public List<String> extractElementsMatchingPattern(List<String> strings, String pattern){
		return strings.stream()
				.filter(str -> str.matches(pattern))
				.collect(Collectors.toList());
	}
	
	/**
	 * Return a list of valid email addresses taken from the provided list
	 * NB: This does not need to be a comprehensive solution that matches all official RFCs
	 * @param strings
	 * @return
	 */
	public List<String> extractValidEmailAddresses(List<String> strings){
		return strings.stream()
//				.filter(str -> str.matches("^[a-zA-Z0-9_]{1,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z0-9]{1,}$"))
				.filter(str -> str.matches("^[a-zA-Z0-9_]{1,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z]{1,}$"))
				.collect(Collectors.toList());
	}
	
	/**
	 * Returns a string at a given index, if the index is greater than the size of the list, then the search wraps around
	 * A negative index counts from the end of the list backward. Using -1 as index gets the last entry, -2 as an index gets the next-to-last entry
	 * [a,b,c,d] index 0 returns a, index 3 returns d, index 4 returns a, index 6 returns c 
	 * Given a minus number, the search goes backwards
	 * [a,b,c,d] index -1 returns d, -3 returns b, -5 returns d, -8 returns a
	 * @param strings
	 * @param index
	 * @return
	 */
	public String pickStringAtIndex(List<String> strings, int index){
		//[a,b,c] @ position -1 becomes [c,b,a] @ position 0
		return (index >= 0) ? strings.get(index % strings.size()) : pickStringAtIndex(reverseList(strings), (index*-1)-1);					
	}
	
	/**
	 * Given a list of integers, break up the list into subgroups of sequential integers
	 * [1,2,3,7,8,9] would return [[1,2,3],[7,8,9]]
	 * [1,2,50,101,102] would return [[1,2],[50],[101,102]]
	 * @param integers
	 * @return
	 */
	public List<List<Integer>> findSequences(List<Integer> integers){
		//comments using example [1,2,50,101,102]
		return IntStream.range(0,integers.size()) //[0,1,2,3,4]
				.boxed() //(0,1,2,3,4)
				.filter(i -> i ==0 || integers.get(i)-integers.get(i-1) != 1)	//find where the sequence breaks	[1,50,101]		
				.map(i -> integers.subList(i, integers.size())) //create new sequences from breaking points to the end of the list [[1,2,50,101,102],[50,101,102],[101,102]]
				.map(sublist -> { //first example is [1,2,50,101,102]
					return IntStream.range(0,sublist.size()) //[0,1,2,3,4]
							.boxed() //(0,1,2,3,4)
							.filter(i -> i ==0 || sublist.get(i)-sublist.get(i-1) != 1)			//find where the sequence breaks [1,50,101]
							.map(i -> (i == 0 && evenlySpaced(sublist)) ? sublist : sublist.subList(0, i)) //create new sequences from 0 to breaking points [],[1,2],[1,2,50]
							.filter(i -> i.size() > 0)				//don't care about empty sub sequences		 [1,2],[1,2,50]		
							.collect(Collectors.toList());				//([1,2],[1,2,50])			
				})
				.filter(i -> i.size() > 0) // don't care about empty sequences //([[1,2],[1,2,50]],[[50],[50,101,102]],[[101,102]])
				.map(i -> i.get(0)) //get the first entry of each sub sequence list (ie 0 to the first breaking point) [1,2],[50],[101,102]
				.collect(Collectors.toList());				
	}
	
	
	
	/**
	 * Encountering an exception can happen often when working with collections
	 * Understanding the exception is the first step towards building a solution
	 * Research IndexOutOfBoundsException in relation to collections
	 * Create a new collection and perform some action which causes an IndexOutOfBoundsException
	 * Capture the exception and return it safely
	 * @return
	 */
	public IndexOutOfBoundsException forceIndexOutOfBoundsException(){
		IndexOutOfBoundsException e = null;
		try{
			Arrays.asList("dog","ball","hat").get(9000);
		}catch(IndexOutOfBoundsException ioobe){
			e = ioobe;
		}
		return e;
	}
	
	/**
	 * Encountering an exception can happen often when working with collections
	 * Understanding the exception is the first step towards building a solution
	 * Research UnsupportedOperationException in relation to collections
	 * Create a new collection and perform some action which causes an IndexOutOfBoundsException
	 * Capture the exception and return it safely
	 * @return
	 */
	public UnsupportedOperationException forceUnsupportedOperationException(){		
		UnsupportedOperationException e = null;
		try{
			Arrays.asList("dog","ball","hat").add("lady");
		}catch(UnsupportedOperationException uoe){
			e = uoe;
		}
		return e;
	}
	
	/**
	 * Finds the word which appears most frequently in a given list
	 * @param words
	 * @return
	 */
	public String getMostFrequentWord(List<String> words){
		return words.stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting())) //map input to count
				.entrySet()
				.stream()
				.sorted((a,b) -> b.getValue().compareTo(a.getValue())) //sort by counts descending
				.map(es -> es.getKey()) //just take the keys
				.collect(Collectors.toList())
				.get(0);
	}
	
	/**
	 * Finds the word which appears least frequently in a given list
	 * @param words
	 * @return
	 */
	public String getLeastFrequentWord(List<String> words){
		return words.stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting())) //map input to count
				.entrySet()
				.stream()
				.sorted((a,b) -> a.getValue().compareTo(b.getValue())) //sort by counts ascending
				.map(es -> es.getKey()) //just take the keys
				.collect(Collectors.toList())
				.get(0);
	}
	
	/**
	 * Returns true if the space between each number is identical
	 * @param numbers
	 * @return
	 */
	public boolean evenlySpaced(List<Integer> numbers){		
		return (numbers.size() <= 2
				|| numbers.get(1)-numbers.get(0) == numbers.get(2)-numbers.get(1)
				&& evenlySpaced(numbers.subList(1, numbers.size())));

	}
	
	/**
	 * Determines if the list can be split into two groups at a point where the sum of the two groups is even
	 * [2,2,4] can be split into [2,2] and [4]
	 * [10,5,5,5,5] can be split into [10,5] and [5,5,5]
	 * [1,10,7,9] can not be split into two equal groups
	 * @param numbers
	 * @return
	 */
	public boolean splitCanBalance(List<Integer> numbers){
		return IntStream.range(0, numbers.size()-1)
				.boxed()
				.anyMatch((i) -> {
					int lhs = numbers.subList(0, i+1).stream().mapToInt(j->j.intValue()).sum();
					int rhs = numbers.subList(i+1, numbers.size()).stream().mapToInt(j->j.intValue()).sum();
					return ( lhs == rhs );
				});
	}
	
	

}

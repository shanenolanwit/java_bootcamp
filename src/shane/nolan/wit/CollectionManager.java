package shane.nolan.wit;

/**
 * Look into the imports marked as unused
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;


public class CollectionManager {
	
	public CollectionManager(){}
	
	/**
	 * Counts how many strings in a given list are shorter than a given min length
	 * @param strings
	 * @param minLength
	 * @return
	 */
	public int countShortStrings(List<String> strings, int minLength){
		return -1;
	}
	
	/**
	 * Counts how many strings in a given list are longer than a given max length
	 * @param strings
	 * @param maxLength
	 * @return
	 */
	public int countLongStrings(List<String> strings, int maxLength){
		return -1;
	}
	
	/**
	 * Finds strings in a given list are shorter than a given length and removes them
	 * @param strings
	 * @param length
	 * @return
	 */
	public List<String> removeShortStrings(List<String> strings, int length){
		return null;
	}
	
	/**
	 * Finds strings in a given list are longer than a given max length and removes them
	 * @param strings
	 * @param minLength
	 * @return
	 */
	public List<String> removeLongStrings(List<String> strings, int maxLength){
		return null;
	}
	
	/**
	 * Given a list of strings, returns a map where the strings are the keys and the values are the string length
	 * [hello,world,cat,ambulance] would return {hello -> 5, world -> 5, cat -> 3, ambulance -> 9}
	 * @param strings
	 * @return
	 */
	public Map<String,Integer> mapStringToStringLength(List<String> strings){	
		return null;
	}
	
	/**
	 * Given a list of strings, returns a map where the keys are the unique strings lengths found in the list and the values are the strings matching each length
	 * [hello,world,cat,ambulance] would return {5 -> [hello, world], 3 -> [cat] -> 3, 9 -> [ambulance]}
	 * @param strings
	 * @return
	 */
	public Map<Integer,List<String>> mapLengthToMatchingStrings(List<String> strings){
		return null;
	}
	
	/**
	 * Given a list of strings, returns the list reversed
	 * @param strings
	 * @return
	 */
	public List<String> reverseList(List<String> strings){
		return null;
	}
	
	/**
	 * Returns true if a list contains duplicates
	 * @param strings
	 * @return
	 */
	public boolean listContainsDuplicates(List<String> strings){
		return false;
	}
	
	/**
	 * Given a list of strings, returns the first n items of that list
	 * @param strings
	 * @param numberOfElements
	 * @return
	 */
	public List<String> getFirstElements(List<String> strings, int numberOfElements){
		return null;
	}
	
	/**
	 * Given a list of strings, returns the last n items of that list
	 * @param strings
	 * @param numberOfElements
	 * @return
	 */
	public List<String> getLastElements(List<String> strings, int numberOfElements){
		return null;
	}

}

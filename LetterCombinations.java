package javaConcepts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classic back tracking problem 
 * Lexicographical Letter Combinations of Phone
 * Digits Given a string of digits where '2'-'9' map to letters (like on a phone
 * keypad) and '0','1' map to themselves, return all possible letter
 * combinations in lexicographical order.
 * 
 * Examples Example 1
 * 
 * Input:
 * 
 * digits = 23 Output:
 * 
 * ['ad', 'ae', 'af', 'bd', 'be', 'bf', 'cd', 'ce', 'cf']
 */
class LetterCombinations {

	/*
	 * Classic Backtracking problem
	 */

	static Map<Character, String> map = Map.of('0', "0", '1', "1", '2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6',
			"mno", '7', "pqrs", '8', "tuv", '9', "wxyz");

	static List<String> ans = new ArrayList<>();

	public static List<String> minTasksToCancelForNoConflict(String digits) {

		StringBuilder sb = new StringBuilder("");
		recur(digits, sb, 0);
		return ans;

	}

	static void recur(String digits, StringBuilder sb, int index) {

		if (index >= digits.length()) {
			ans.add(sb.toString());
			return;
		}
		
		/**
		 *  This code however will never execute since it will return from the base case above
		 *   as we are always deleting the last character and we are only going upto indexes i.e length of digits
		 */
		if(sb.length() > digits.length()) {
			return; 
		}

		for (char ch : map.get(digits.charAt(index)).toCharArray()) {
			sb.append(ch);
			recur(digits, sb, index + 1);
			sb.deleteCharAt(sb.length() - 1);
		}

	}

}

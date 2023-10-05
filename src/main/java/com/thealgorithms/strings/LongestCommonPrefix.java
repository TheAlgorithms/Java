package com.thealgorithms.strings;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Imran Ansari
 * @note This class is free from sonar lint issues
 */
public class LongestCommonPrefix {
	/**
	 * Using a private constructor is to prevent the instantiation of a class
	 */
	private LongestCommonPrefix() {
	}

	/**
	 * @param strs: an array of string to check if its elements has common prefix
	 * @return: a string which is a common prefix for all the Strings present in
	 *          input array
	 */
	public static String getLongestCommonPrefix(String[] strs) {
		String longestCommonPrefix = StringUtils.EMPTY;

		// Length of input array
		final int len = strs.length;

		if (len != 0) {
			longestCommonPrefix = strs[0];

			for (int i = 0; i < len; i++) {

				// Iterate till string has longestCommonPrefix as substring
				while (strs[i].indexOf(longestCommonPrefix) != 0) {

					// Reduce the length of longestCommonPrefix by 1
					longestCommonPrefix = longestCommonPrefix.substring(0, longestCommonPrefix.length() - 1);
					if (longestCommonPrefix.isEmpty())
						return longestCommonPrefix;
				}
			}
		}
		return longestCommonPrefix;
	}
}

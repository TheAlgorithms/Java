package com.dataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecursiveReverseLinkedListTest {

	private static final String REVERSED_LIST_VALUES = "096";

	@Test
	void testReverseLinkedList() {
		RecursiveReverseLinkedList<String> list = new RecursiveReverseLinkedList<String>();
		list.add("6");
		list.add("9");
		list.add("0");

		String originalList = list.toString(list.getHead());
		Node<String> node = list.reverseList(list.getHead());
		String reverseList = list.toString(node);

		Assertions.assertNotEquals(originalList, reverseList);
		Assertions.assertEquals(REVERSED_LIST_VALUES, reverseList);
	}
}

// C++ program to delete a node in
// singly linked list recursively

#include <bits/stdc++.h>
using namespace std;

struct node {
	int info;
	node* link = NULL;
	node() {}
	node(int a)
		: info(a)
	{
	}
};

/*
Deletes the node containing 'info' part as val and
alter the head of the linked list (recursive method)
*/
void deleteNode(node*& head, int val)
{
	
	// Check if list is empty or we
	// reach at the end of the
	// list.
	if (head == NULL) {
		cout << "Element not present in the list\n";
		return;
	}
	// If current node is the node to be deleted
	if (head->info == val) {
		node* t = head;
		head = head->link; // If it's start of the node head
						// node points to second node
		delete (t); // Else changes previous node's link to
					// current node's link
		return;
	}
	deleteNode(head->link, val);
}

// Utility function to add a
// node in the linked list
// Here we are passing head by
// reference thus no need to
// return it to the main function
void push(node*& head, int data)
{
	node* newNode = new node(data);
	newNode->link = head;
	head = newNode;
}

// Utility function to print
// the linked list (recursive
// method)
void print(node* head)
{
	
	// cout<<endl gets implicitly
	// typecasted to bool value
	// 'true'
	if (head == NULL and cout << endl)
		return;
	cout << head->info << ' ';
	print(head->link);
}

int main()
{
	// Starting with an empty linked list
	node* head = NULL;

	// Adds new element at the
	// beginning of the list
	push(head, 10);
	push(head, 12);
	push(head, 14);
	push(head, 15);

	// original list
	print(head);

	deleteNode(head, 20); // Call to delete function
	print(head); // 20 is not present thus no change in the
				// list

	deleteNode(head, 10);
	print(head);

	deleteNode(head, 14);
	print(head);

	return 0;
}

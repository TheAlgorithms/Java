Linked List

In case of linked list elements can be added both at the
beginning and end of the list. Linked lists are useful
when the size of a list is unknown and changes
frequently. The linked list data structure uses node
Initialization: node is a struct that contains the data
at the position and the next node address.
It has various functions like Insert at the beginning,
Inserts at the end, Delete from the end, Delete from the
beginning.

Insert at Beginning: a new node is created. A new node
created using the given value. If the start node is empty
then this new node is set as start. Or else the current
start is linked at the end of the new node and the new
node becomes start node.

Insert at End: a new node is created. A new node created
using the givenvalue. If the start node is empty then this
new node is set as start. Else start from start node
and go on till the node with the address of the next
element is null Replace the null value with the address
of the new node.

Insert at Pos: a new node is created. A new node created
using the given value. Start from start node keeping a
count of node number and go on till you reach (pos – 1)th
node. Set the next address for the new node as the
current next address of (pos-1)th node. And set the next
address of (pos-1)th node as the address of the new node.
If the position is found to exceed the size of the listed
list a suitable message is displayed.

Delete from the start: Check if the Start node is null
then no element is present so display a suitable message.
Else Display the start element and make the node linked
to the start node.

Delete from the end: Check if the Start node is null then
no element is present so display a suitable message.
Else start from start node and Go through the list to
find the second last node and set its next node address
as null.

Delete from position: Start from start node keeping a
count of node number and go on till you reach (pos – 1)th
node. The next node address of (pos – 1)th node is
replaced with that of p the node.

Display: Check if the Start node is null then no element
is present so display a suitable message. Else start from
start node and Go through the list printing the value at
the current node. Go on till you reach a null node.
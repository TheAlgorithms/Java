Queue

The queue is a First in First Out (LIFO) data structure.
Queue works using insert, delete, display. Element
addition and removal takes place from a different side
Initialization: there are three variables initialized.
They are the array storing the Queue and the rear and 
front position of the Queue. Rear and front are initialized
as minus one.

INSERT: This is the method of adding data to a Queue. The
function checks if the rear is at the last position. If
yes then there is no more space to add more variables.
Thus it has to display a suitable message and end the
function. Or else it takes a number as input. Then
increments the value of the rear. Then adds the value at
the rear position. In case the value of front is minus
one it is also incremented.

DELETE: This is a method used to remove an element from a
Queue. The function first checks if the rear element is
minus one or front is greater than the rear. This means
that there is no data in the Queue. Thus displays an
error message and ends the function. Or else It first
stores the value at the front position in a variable and
then prints it. The data is deleted by incrementing the
value of the front. This doesn’t remove the data but
makes it invisible with respect to the Queue. This
element gets replaced when a new value is added to the
Queue.

DISPLAY: This is a method used to display the elements in a Queue
The function first checks if the front element is minus
one. This means that there is no data in the stack. Or
else there is a loop from position front to rear positon
printing all the elements in the Queue.
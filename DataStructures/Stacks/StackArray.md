Stack
The stack is a Last In First Out (LIFO) data structure.
Stack works using push, pop, display.

Initialization: there are two variables initialized. They
are the array storing the stack and the top position of
the stack. The top is initialized as zero.

Push: This is the method of adding data to a stack. The
function checks if the top is in the last position. If
yes then there is no more space to add more variables.
Thus it has to display a suitable message and end the
function. Or else it takes a number as input. Then
increments the value of the top. Then adds the value at
the top position.

POP: This is a method used to delete an element from a
stack. The function first checks if the top element is
minus one. This means that there is no data in the stack.
Thus displays an error message and ends the function. It
first stores the value at the top position in a variable
and then prints it. The data is deleted by decreasing the
value of the top. This doesn’t remove the data but makes
it invisible in respect of the stack. This element gets
replaced when a new value is added to the stack.

DISPLAY: This is a method used to display the elements in
a stack. The function first checks if the top element is
minus one. This means that there is no data in the
stack.Or else there is a loop from position zero to top
printing all the elements in the stack.
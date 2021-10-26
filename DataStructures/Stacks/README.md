# STACK

Stack is an ADT (abstract data type) that acts like a list of objects but there is a difference.

Stack works on the principle of _LIFO_ (Last In First Out), it means that the last item added to the stack will be the first item to be removed.

Stack is based on two methods (functions)-

## push(element)

It adds "element" to the top of the stack.

For example: If we have `1, 3, 5` in stack, and we call push(9),

`9` will be added to last index of stack -> `1, 3, 5 , 9`.

## peek() or top()

It returns element at the top of the stack.

For example: If we have `1, 3, 5` in stack, and we call peek(),

`5` will be returned (without removing it from the stack).

## pop()

It removes the last element (i.e. top of stack) from stack.

For example: If we have `1, 3, 5 , 9` in stack, and we call pop(),

the function will return `9` and the stack will change to `1, 3, 5`.

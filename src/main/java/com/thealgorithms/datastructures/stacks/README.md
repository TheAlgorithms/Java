# STACK

- Stack is an ADT (abstract data type) that is a collection of elements where items are added and removed from the end, known as the "top" of the stack.

- Stack works on the principle of _LIFO_ (Last In First Out), it means that the last item added to the stack will be the first item to be removed.

## Declaration
 `Stack<Obj> stack=new Stack<Obj>();`

# Functionalities
Stack is based on two functions (methods)-

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

# Real Life Applications 
  - `Undo mechanisms:`
     Many software applications use stacks to implement an "undo" feature.

  - `Browser history:`
    The "back" button in a web browser is implemented using a stack, allowing users to navigate through previously visited pages.

  - `Function calls and recursion:` 
    The computer's call stack keeps track of function calls, allowing programs to remember where to return after a function finishes execution.

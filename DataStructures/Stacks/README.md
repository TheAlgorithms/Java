# STACK

stack is an ADT (abstract data type ) that act like list of objects but there is a diffrents.

stack act is _LIFO_ (Last In First Out), it means that when we want to get an element from the stack we get the last element in the stack.

stack is based on two methods (functions)

## push(element)

add "element" to the top of the stack.

for example: we have `1, 3, 5` in stack, then we call push(9),

`9` will add to last index of stack -> `1, 3, 5 , 9`

## peek() or top()

return element at the top of the stack.

for example: we have `1, 3, 5` in stack, then we call peek(),

`5` will be returned (without removing it from the stack)

## pop()

remove the last element (i.e. top of stack) from stack.
for example: we have `1, 3, 5 , 9` in stack, then we call pop(),

the function will return `9` and the stack will change to `1, 3, 5`.

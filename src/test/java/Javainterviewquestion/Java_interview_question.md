---
title: Java Interview Questions
description: Java Interview Questions that can be helpfull & handy while revising java Concepts.
created: 2022-10-14
updated: 2022-10-14
---

## 1. What is Java ?
 A high-level, object-oriented, robust, secure programming language, platform-independent, high performance, Multithreaded, and portable programming language. Java was developed by James Gosling in June 1991.

## 2. Features of Java ?
*Secured*
*Robust*
*Portable*
*Object-Oriented*
*Simple*
*Platform independent*
*Dynamic*
*Multithreaded*

## 3. Basic Concepts of OOPS ?
*Polymorphism* - Polymorphism refers to many forms, or it is a process that performs a single action in different ways.
*Abstraction*  -  process which displays only the information needed and hides the unnecessary information. We can say that the main purpose of abstraction is data hiding. 
*Inheritence*  - Inheritance is a method in which one object acquires/inherits another object’s properties, and inheritance also supports hierarchical classification.
*Encapsulation*
*Object*      - An entity that has state and behavior is known as an object.
*Java Class*  - A Java class file is a file containing Java bytecode that can be executed on the Java Virtual Machine.1

## 4. What is an Object ?
Object is the real-time entity having some state and behavior.Object is an instance of the class having the instance variables as the state of the object and the methods as the behavior of the object. The object of a class can be created by using the new keyword.

## 5.Difference between C++ & Java ? 
| C++                                              | Java                                                                                           |
| -------------                                    | -------------                                                                                  |
| 1.C++ is platform-dependent.                     | 1 .Java is platform-independent.                                                               |
| 2.C++ supports multiple inheritance.             | 2.Java doesn't support multiple inheritance through class. Can be achieved by using Interface. |
| 3.C++ supports operator overloading.             | 3.Java doesn't support operator overloading.                                                   |

## 6.How many types of constructors are used in Java? 
 **Default Constructor**: *default constructor is the one which does not accept any value. The default constructor is mainly used to initialize the instance variable with the default values.* 

 **Parameterized Constructor** : *The parameterized constructor is the one which can initialize the instance variables with the given values*

## 7.Can we override the static methods?
No, we can't override static methods.

## 8.What is the static block?
 *Static block is used to initialize the static data member. It is executed before the main method, at the time of classloading.*
```
class Static_Block{  
  static
  {
    System.out.println("static block is invoked");
    }  
    public static void main(String args[]){  
    System.out.println("Static Block Demo");  
  }  
}  
```
## 9. What is this keyword in java?
The this keyword is a reference variable that refers to the current object. There are the various uses of this keyword in Java. It can be used to refer to current class properties such as instance methods, variable, constructors, etc.
_____________                 _____________________
|            |                |State  & Behavior  |
|This        |________________|___________________|_
|____________|

## 10. What are the main uses of the super keyword?
*There are the following uses of super keyword.*

super can be used to refer to the immediate parent class instance variable.
super can be used to invoke the immediate parent class method.
super() can be used to invoke immediate parent class constructor.

## 11. Can we initialize the final blank variable?
Yes, if it is not static, we can initialize it in the constructor. If it is static blank final variable, it can be initialized only in the static block.
## 12 . Can you declare the main method as final?
Yes, We can declare the main method as public static final void main(String[] args){}.
 
 ## 13.Can we declare a constructor as final?
The constructor can never be declared as final because it is never inherited. Constructors are not ordinary methods; therefore, there is no sense to declare constructors as final. However, if you try to do so, The compiler will throw an error.

## 14. What is the difference between the final method and abstract method?
The main difference between the final method and abstract method is that the abstract method cannot be final as we need to override them in the subclass to give its definition.



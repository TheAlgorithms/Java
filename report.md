# Report for assignment 3

This is a template for your report. You are free to modify it as needed.
It is not required to use markdown for your report either, but the report
has to be delivered in a standard, cross-platform format.

## Project

Name: TheAlgorithms Java

URL: [Group 29 repository](https://github.com/a-kbd/g29_assignment3),
[Original repository](https://github.com/TheAlgorithms/Java)

One or two sentences describing it

A repo gathering many different algorithms common in computer science and software engineering.

## Onboarding experience

### Did it build and run as documented?

See the assignment for details; if everything works out of the box,
there is no need to write much here. If the first project(s) you picked
ended up being unsuitable, you can describe the "onboarding experience"
for each project, along with reason(s) why you changed to a different one.

### **1. How easily can you build the project? Briefly describe if everything worked as documented or not:**

- Did you have to install a lot of additional tools to build the software?

  Other than the obvious of having to have a Java runtime installed, we had to install Maven to build the project.

- Were those tools well documented?

  Yes, Maven is well documented, besides building it only needed a simple command that also is built in to many IDEs and text editors.

- Were other components installed automatically by the build script?

  Dependencies like JUnit and the maven-surefire-plugin were installed automatically, however, only when running the tests as they are not required otherwise. All tests pass.

- Did the build conclude automatically without errors?

  Yes.

- How well do examples and tests run on your system(s)?

  They run perfectly.

### **2. Do you plan to continue or choose another project?**

We plan to continue using this project as it builds fine on our systems and it fulfills the requirements.

## Complexity

### **1. What are your results for eight complex functions? Did everyone get the same result? Is there something that is unclear? If you have a tool, is its result the same as yours?**

We used Lizard which put the most complex functions as follows.

The functions are MyAtoi, LongDivision, CRCAlgorithm, BinaryTree, BinaryTree, RegexMatching, MaximumMinimumWindow, RedBlackBST, ValidParentheses.

#### Manual calculation of the complexity

- LongDivision had 17 branches and 5 exit points. Based on the formula, the cyclomatic complecity was manually calculated to be 17-5+2=14. This was not coherent with the data we got from Lizard, which told the CC was  19. 

- CRCAlgorithm had 21 branches and 5 exit points, based on the formula we have the complexity 21-5+2=18. This is the same value that Lizard finds.

### **2. Are the functions just complex, or also long?**

They are also long. The median LOC is 2 whereas the median for the top eight is 16.5. Thus they are longer.

### **3. What is the purpose of the functions? Is it related to the high CC?**

- **MyAtoi:** Converts a string to a 32bit signed integer. The high CC stems from all the case distinctions to handle different numerals as well as some edge cases. I.e. yes it is related to the high CC.

- **LongDivision:** Is implements integer long division “by hand”, i.e. without using prebuilt division. The high CC also seems to come from all the necessary case distinctions and thus related to its purpose.

- **CRCAlgorithm** is for checking the integrity of data streams, it finds the bit error rate of a received message which could be useful in e.g. networking equipment to know whether to drop a packet or not. The high CC stems from the fact that you have to go through a list of binary numbers and there lots of different conditions checked to determine whether there are any data errors or not.

- **BinaryTree** is a representation of the data structure binary tree where there are nodes containing some data, each with two successor nodes, children, also containing data. The tree structure enables the use of various different algorithms for searching etc. These algorithms are included in the class such as BFS and others in order to add a node at the right place, remove a node etc. Lots of conditional statements (branches) are needed in that case to see whether the node you are at is not a leaf etc and this explains the high CC.

- **ValidParentheses:** Given a string of exclusively brackets, it checks whether the brackets are closed properly in the right order.

- **DeleteFixUp:** It ensures for a red-black-tree that the red-black properties are not violated.

- **RegexMatching:** The function takes in a string and a series of characters that are matched against the string (so called wildcard pattern) and returns whether the pattern matches the string. The function is related to high CC since it contains a recursion containing a lot of conditional statements to see if the string (or substring) fulfills different conditions. LOC is 181.

- **MaximumMinimumWindow:** The function takes in an integer array from which it finds the maximum of the minimum of every window size in the given array. The window size refers to a sub-array of a certain size of the original array. The high CC of the function comes from the fact that it has multiple loops and conditions in order to be able to go through the array of integers and check which one is bigger/smaller than the other one(s). LOC is 105.

### **4. Are exceptions taken into account in the given measurements?**

Lizard does not seem to count exceptions. This was tested by checking a small file with two functions, one of them throwing one exception. It CC was the same as the one that did not throw an exception.

### **5. Is the documentation clear w.r.t. all the possible outcomes?**

- **MyAtoi:** No. It does not document the inner workings at all.

- **LongDivision:** Not really. It partially explains how fractional cases are handled, i.e. always round towards zero, but otherwise the actual code is not documented at all, i.e. its if statements are bare.

- **CRCAlgorithm** has really good documentation explaining what the algorithm is doing, it explains different cases that could lead to it determine a message either contain errors or not, however, it does not touch upon the possibility of invalid input etc.

- **BinaryTree** sometimes explains what happens in different cases but mostly does not, however, the documentation regarding the correct functionality of the program is satisfactory.

- **ValidParentheses:**

- **DeleteFixUp:**

- **RegexMatching:** The documentation regarding the functionality of the function is rather good. The documentation also contains comments regarding the complexity of the function but lacks examples of input and how it would work. Every method has short documentation of how it works.

- **MaximumMinimumWindow:** The initial documentation of how the class works overall is good. Even though the name can throw you off with how it works, the author has provided an example of exactly how the class/function operates. It is however somewhat difficult to read through the actual code in the function and understand what’s happening due to the relatively high number of loops and conditions that are uncommented.

## Refactoring

Plan for refactoring complex code:

Estimated impact of refactoring (lower CC, but other drawbacks?).

Carried out refactoring (optional, P+):

git diff ...

## Coverage

### Tools

Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?

### Your own coverage tool

Show a patch (or link to a branch) that shows the instrumented code to
gather coverage measurements.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

1. How detailed is your coverage measurement?

2. What are the limitations of your own tool?

3. Are the results of your tool consistent with existing coverage tools?

## Coverage improvement

Show the comments that describe the requirements for the coverage.

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...

Number of test cases added: two per team member (P) or at least four (P+).

## Self-assessment: Way of working

Current state according to the Essence standard: ...

Was the self-assessment unanimous? Any doubts about certain items?

How have you improved so far?

Where is potential for improvement?

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?

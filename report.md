# Report for assignment 3

David Cederström, Simon Grunwald, Robert Scholz, Arasp Keighobad

## Project

Name: TheAlgorithms Java

A repo gathering many different algorithms common in computer science and software engineering.

URL: [Group 29 repository](https://github.com/a-kbd/g29_assignment3),
[Original repository](https://github.com/TheAlgorithms/Java)


## Onboarding experience

### Did it build and run as documented?

Everything worked out of the box, however, the onboarding with JaCoCo was a little bit rough in the beginning but it sorted itself out pretty quickly. We all had experience with JUnit prior to starting so that part went smoothly. We did not end up changing projects since the first one we picked turned out to be suitable and reasonably straight-forward to get started with.

### **1. How easily can you build the project? Briefly describe if everything worked as documented or not:**

- Did you have to install a lot of additional tools to build the software?

  Other than the obvious of having to have a Java runtime installed, we had to install Maven to build the project. Moreover, we had to add the JaCoCo plugin to the maven dependencies. 

- Were those tools well documented?

  Yes, Maven is well documented. Besides building, it only needed a simple command that also is built into many IDEs and text editors.

- Were other components installed automatically by the build script?

  >Dependencies like JUnit and the maven-surefire-plugin were installed automatically, however, only when running the tests as they are not required otherwise. All tests pass.

- Did the build conclude automatically without errors?

  Yes, it concluded automatically without errors.

- How well do examples and tests run on your system(s)?

  >They run perfectly.

### **2. Do you plan to continue or choose another project?**

We plan to continue using this project as it builds fine on our systems and it fulfills the requirements. Also it is an interesting repo to work with.

## Complexity

### **1. What are your results for eight complex functions? Did everyone get the same result? Is there something that is unclear? If you have a tool, is its result the same as yours?**

>We used Lizard which put the most complex functions as follows.

>The functions are: MyAtoi, LongDivision, CRCAlgorithm, BinaryTree, BinaryTree, RegexMatching, MaximumMinimumWindow, RedBlackBST, ValidParentheses.

Everyone got the same result when running Lizard, doing the manual calculation we got results that were a bit different, see below:

### **Manual calculation of the complexity**

- **MyAtoi** had 32 branches and 8 exit points, based on the formula we have the complexity is 32-8+2=26. This is contrary to what Lizard finds.

- **LongDivision** had 17 branches and 5 exit points. Based on the formula, the cyclomatic complecity was manually calculated to be 17-5+2=14. This was not coherent with the data we got from Lizard, which told the CC was  19. 

- **CRCAlgorithm** had 21 branches and 5 exit points, based on the formula we have the complexity is 21-5+2=18. This is the same value that Lizard finds.

- **BinaryTree** had 30 branches and 17 exit points, based on the formula we have the complexity 30-17+2=15. This is _not_ the same value that Lizard finds.

### **2. Are the functions just complex, or also long?**

>They are also long. The median LOC is 2 whereas the median for the top eight is 16.5. Thus they are longer.

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

> Lizard does not seem to count exceptions. This was tested by checking a small file with two functions, one of them throwing one exception. Its CC was the same as the one that did not throw an exception.

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

**Plan for refactoring complex code:**

**Estimated impact of refactoring (lower CC, but other drawbacks?).**

**Carried out refactoring (optional, P+):**

**git diff ...**

> **MyAtoi**
The complexity of MyAtoi is mostly due to the different cases of numbers can be embedded in strings. Some of it, especially the edge cases, can be factored out into a specific function. Especially the MAX_INT handling could be split of into its own function. The switch statement would be difficult to factor out so I would leave it in place.

> **CRCAlgorithm**
CRCAlgorithm's complexity is due to the usage of ArrayLists, nested while and/or for loops and also because of the amount of condiional statements. One way to improve the complexity is to combine two nested loops into one combined. Also, it would be good to split the three biggest functions to smaller functions.

> **BinaryTree**  
Much of the complexity of BinaryTree is attributed to the fact that it includes code to print the tree in pre- post- and infix order as well as a BFS implementation in the same class. One refactoring idea could be to remove these parts of the class and instead have them in separate classes that only handle functionality regarding printing or searching in a tree, that way the BinaryTree class would only be for the representation of the data structure as well as putting and removing values from it. The impact of carrying out refoctoring in the way described would lower the CC and also perhaps make the code of the whole repo easier to understand and more encapsulated. There are not really any drawbacks, another benefit would be that other tree datastructures such as red-black trees or KD-trees could also use the BFS functions (assuming the nodes have similar pointers to their successors etc) 

> **LongDivision**\
The complexity of the divide function in the given code appears to be reasonable and necessary for implementing a long division algorithm. However, there is scope for improving the code's readability and maintainability by splitting it into smaller units.\
One possible way to reduce complexity and improve the code's structure would be to split the divide function into smaller functions, each performing a specific task within the long division algorithm. For example, one function could be responsible for converting the dividend and divisor to positive numbers if either or both are negative, another function could be responsible for calculating the next quotient digit, and so on. 


## Coverage

### Tools

**Document your experience in using a "new"/different coverage tool.**

Using JaCoCo was surprisingly a nice experience as it allowed you to test the coverage of the code by simply writing JUnit tests and not have to think about it too much. Then it provided you with a nice looking HTML report that one could open in the browser and easily see the coverage of all the different functions and classes in the repo.


**How well was the tool documented? Was it possible/easy/difficult to integrate it with your build environment?**

There was some friction in integrating it with the build environment as it was hard to find a clear walkthrough on how to integrate it with Maven. All the different answers found on knowledge-sharing websites such as StackOverflow assumed different prerequisites and plugins installed etc. At first when we managed to integrate it, it only gave reports stating 0% coverage although we were fairly certain that the hundreds of tests in the test suite should at least provide some coverage. It turned out that the maven-surefire-plugin and JaCoCo were not communicating properly and a necessary file for JaCoCo to create its report was missing after maven-surefire had run.

### Your own coverage tool

Our own coverage tool is pretty simple and straight-forward to work with since it only consists of a field containing a boolean array in the class being tested where each of the relevant elements gets set to `true` when the corresponding branch is reached (i.e. covered). Then the proportion of the values that were set to true would be used to calculate the coverage percentage.

**Show a patch (or link to a branch) that shows the instrumented code to gather coverage measurements.**

>The covergae tool implemented by the group can be seen in the [diy-coverage branch](https://github.com/a-kbd/g29_assignment3/tree/diy-coverage).

**The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:**

**git diff ...**

### Evaluation

**What kinds of constructs does your tool support, and how accurate is
its output? How is the quality of your coverage measurement?**\
The quality of our own coverage measurement is not bad, however, there are some constructs in the language that it does not check, e.g. the ternary operator as that does not allow one to write full code blocks for each branch, rather only a single statement that should be returned. This means that you could not put a "if(reached): true" type of statement to see if the ternary operator was reached.

>The quality of the coverage is measured by creating a boolean array with the size of the number of branches in the function. If a branch is taken, the corresponding elemnent in the array is set to true (initially false). The percentage of true vs false is then calculated and used as a coverage percentage. 

**What are the limitations of your own tool?**\
It only works for the specific program as it is entirely manual. It does not take exceptions into account as none were used in the code. Besides constructs like the ternary operator and the new syntax for the switch statement in Java are not supported as discussed above.

**Are the results of your tool consistent with existing coverage tools?**

>**MyAtoi**\
 No. The manual coverage JaCoCo reports is 59% whereas the manual tool reports roughly 33%.

> **CRCAlgorithm**\
There were no test cases implemented by default. A manual coverage of 68%, 81%, 88% was shown, however it differs since there are random values inside the function.

> **BinaryTree**\
There was no test suite included in the repo for the BinaryTree. However, looking at the comments of the class itself, one could see the documentation and through that deduce the required functionality. E.g. in the test later we are testing assertEquals(remove(9), true) or because the remove function is supposed to return a boolean based on whether the specified node was found and removed from the tree or not. \
In CoverageTest.java, separate functions are created to test the function, printing the coverage percentage of the different branches after each run. Branch coverage is increased in each test case 13%→20%→40%. The simple diy test data structure is set up as a field consisting of an array of boolean values in the class being tested, each branch changes its corresponding entry in the array if it was reached and the class being tested then has a method to return the field of the particular object instance of the test. \
JaCoCo going through the added JUnit tests gave a 44% coverage which is roughly consistent with the 40% reported by our own simple coverage tool.

>**LongDivision**\
 Yes. JaCoco showed a coverage of 71% before the group implemented further tests. The manual tool reported 70% percent for two of the test cases that was previously implemented in the original repository while one reported a coverage of roughly 54%.

## Coverage improvement

**Show the comments that describe the requirements for the coverage.**

**Report of old coverage: [link]**\
[Old coverage](https://github.com/a-kbd/g29_assignment3/tree/documentation/coverage_reports/old_coverage)


**Report of new coverage: [link]**\
[New coverage](https://github.com/a-kbd/g29_assignment3/tree/documentation/coverage_reports/new_coverage)

**Test cases added:**
All test cases added are in [this branch](https://github.com/a-kbd/g29_assignment3/tree/19-feature-request-improve-coverage-with-additional-test-cases) (see commits)

git diff ...

Number of test cases added: two per team member (P) or at least four (P+).
> **MyAtoi**
After implementing the tests JaCoCo showed an improvement to 73%. The main issue was that multiple switch statements for different numerals were not covered. The tests cases improved on that. Additionally, the case of a plus sign was added to add coverage for the case of a redundant plus sign.
[Tests added for MyAtoi](https://github.com/a-kbd/g29_assignment3/tree/20-feature-request-improve-coverage-for-myatoi)
> **BinaryTree**
[Tests added for BinaryTree](https://github.com/a-kbd/g29_assignment3/tree/23-feature-request-improve-coverage-for-binarytree)

> **CRCAlgorithm**
[Tests added for CRCAlgorithm](https://github.com/a-kbd/g29_assignment3/blob/7-feature-request-implement-manual-test-coverage-for-the-crcalgorithm/src/test/java/com/thealgorithms/others/CRCAlgorithmTest.java)

>**LongDivision**\
After implementing new tests, JaCoCo showed a coverage of 88% which is an improvement of around 18%. This is primarily because the previous test cases missed to test some of the conditions implemented in the LongDivision function. One test especially, was added to test the edge case of when the divisor is zero. The original code did not have functionality for handling this case and was therefore added.\
The test cases that were added were: 
```java
  // Requirement: Dividend (negative), divisor (positive), returns correct integer after division
  // Tests the case where the dividend is less than 0.
  @Test
  void testNegativeDividend() {
      assertEquals(-1, LongDivision.divide(-5,3));
  }

  // Requirement: Dividend (positive), divisor (positive), returns correct integer after division
  // Tests the case where the dividend is less than the divisor. The test should return 0 in this case. 
  @Test
  void testDividendLessThanDivisor() {
      assertEquals(0, LongDivision.divide(3,5));
  }

  // Requirement: Dividend (neither), divisor (positive), returns correct integer after division
  // Tests the case where the dividend is 0. This should return a 0.
  @Test
  void testDividendIsZero() {
      assertEquals(0, LongDivision.divide(0,5));
  }

  // Requirement: Dividend (positive), divisor (neither), returns correct integer after division
  // Tests the case where the divisor is 0. This should return a 0.
  @Test
  void testDivisionByZero() {
      assertEquals(0, LongDivision.divide(5,0));  
  }
```
>Comments were added to the tests that were already implemented to identify the requirements that were tested. The requirements were identified as follows:
```java
  // Requirement: Dividend (positive) is  greater than divisor (positive), returns correct integer after division
  @Test
  void testOne() {
      assertEquals(3, LongDivision.divide(10,3));
  }

  // Requirement: Dividend (positive) is  greater than divisor (negative), returns correct integer after division
  @Test
  void testTwo() {
      assertEquals(-2, LongDivision.divide(7,-3));
  }

  // Requirement: Dividend (positive) is  greater than divisor (negative), returns correct integer after division (basically the same as the first test)
  @Test
  void testThree() {
      assertEquals(10, LongDivision.divide(105,10));
  }

```

## Self-assessment: Way of working

**Current state according to the Essence standard: ...**

The current state according to the Essence standard seems to be "in-place" since all the team members are using the way-of-working to accomplish their tasks.

**Was the self-assessment unanimous? Any doubts about certain items?**

Yes, we were all in agreement on the points of the self-assessment. There were some uncertainty about the fact that we don't have any real "stakeholders" but one could see the TA assessing our work filling that role.

**How have you improved so far?**

We have learned things about the things touched upon by the task such as branch coverage and code complexity. Our way of working has improved during the assignment (not to mention during the whole course) in the way that we are now better at working in a team as well as picking suitable tools for the given task.

**Where is potential for improvement?**

Communication can always be improved and that is also true for our project. It does not necessarily mean that the communication was bad, however, in the future perhaps we could have had planned the times for the meetings more in advance ensuring that nobody in the group missed the memo. 

*Assessment according to the essence standard:* \
[x]  Principles and constraints are committed to by the team.
→ Yes, we agreed on a way of writing commit messages, we settled on what tools to use like JaCoCo and Java, as well as JUnit which was already used by the repo.\
[-] Principles and constraints are agreed to by the stakeholders.\
[-] The tool needs of the work and its stakeholders are agreed.\
[x] A recommendation for the approach to be taken is available.
→ The assignment description.\
[x] The context within which the team will operate is understood.
→ A school assignment where some criteria need to be met to pass.\
[x] The constraints that apply to the selection, acquisition, and use of practices and tools are known.
→ Yes, such constraints were discussed before the start of the project.\
[x] The key practices and tools that form the foundation of the way-of-working are selected.
→ Yes, tools were selected at the start of the project.\
[x] Enough practices for work to start are agreed to by the team.
→ Yes, deciding to use Github and the particular repo was enough to start making issues and start work.\
[x] All non-negotiable practices and tools have been identified.
→ Git was non-negotiable for the group since the course recommends it and is very centered around it. The repo we chose used Github which made using that too convenient.\
[x] The gaps that exist between the practices and tools that are needed and the practices and tools that are available have been analyzed and understood.
→ No gaps could be identified through thorough analysis.\
[x] The capability gaps that exist between what is needed to execute the desired way of working and the capability levels of the team have been analyzed and understood.
→ No gaps identified through discussion at the start of the project.\
[x] The selected practices and tools have been integrated to form a usable way-of-working.
→ Yes.\
[x] The practices and tools are being used to do real work.
→ Yes the tools used are common in industry and were useful for the work in this project.\
[x] The use of the practices and tools selected are regularly inspected.
→ Yes inspected regularly during code review and approvals when doing pull requests.\
[x] The practices and tools are being adapted to the team’s context.
→ We added a plugin via maven, JaCoCo.\
[x] The use of the practices and tools is supported by the team.
→ Yes, after some negative opinions on using Java were noted in the initial discussions, the whole team agreed on and supported the tools.\
[x] Procedures are in place to handle feedback on the team’s way of working.
→ Regular meetings where feedback could be given and discussed, the chat on Discord could also be used for this.\
[x] The practices and tools support team communication and collaboration.
→ Yes, the disussion forum on discord provided good communication and the voice chat with screen sharing could be used for live collaboration.\
[x] The practices and tools are being used by the whole team to perform their work.
→ Yes, everybody adhered to the practices and tools decided upon.\
[x] All team members have access to the practices and tools required to do their work.
→ Yes, everybody had access to git and Java on their machines.\
[x] The whole team is involved in the inspection and adaptation of the way-of-working.
→ Yes, the whole team was involved in this through the frequent meetings and discussions.\
[x] Team members are making progress as planned by using and adapting the way-of-working to suit their current context.
→ Yes, as seen in the progress tracker on Github.\
[x] The team naturally applies the practices without thinking about them.
→ Yes, at this point of the course things like git comes naturally to the group members, practices such as linking each commit with an issue in the issue tracker and so on were adhered to.\
[x] The tools naturally support the way that the team works.
→ Git feels very suited for software development so it comes naturally. Java and JaCoCo became transparent to the workflow and naturally after some time.\
[-] The team continually tunes their use of the practices and tools.
→ No, it was not really needed for the short timespan of the project.\
[-] The team's way of working is no longer being used.
→ It was used until the end of the project.\
[x] Lessons learned are shared for future use.
→ Yes, any lessons learned were shared on Discord for the others to have a kind of reference manual for the tools that were figured out by only part of the team.\

## Overall experience

**What are your main take-aways from this project? What did you learn?**

That perhaps in the future it is worth to explore other build tools other than Maven.




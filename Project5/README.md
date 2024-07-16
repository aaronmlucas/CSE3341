# Project 5 Garbage Collector
Projects from Principles of Programming Languages

## Name: Aaron Lucas

This zip contains the fifth project for CSE 3341. In this project, I was tasked with adding a "Garbage Collector" which just keeps track of the number of reachable objects. This also required modifications to the CoreObject class to include a reference count.

## These files include:
Correct (Directory)
    - #.code - The code read by the built Scanner, Parsed, and then executed.
    - #.data - The data read in by a second Scanner. It is used during the execution of the code.
    - #.expected - The expected output.
    - #.student - The actual output.

Error (Directory)
    - #.code - Code containing errors meant to be caught by the interpreter.

**'3341 Project 5'.pdf** - The instructions for this Project.

**Core.java** - Includes the Core enum containing all possible tokens in the Core language. Borrowed from Project 1.

**Scanner.java** - The file containing the Scanner class and all functions pertaining to it. Borrowed from Project 1.


**README.md** - Congratulations, you found it.

**Main.java** - The file containing the main method for the Project. It is what initializes the Scanner, Parser, and calls the necessary methods.

**tester.sh** - A file provided by the instructor to make testing a bit easier.

**Parser.java** - The file containing the Parser class and all functions pertaining to it. This is the protagonist of this story.

**ProgramStack.java** - The file containing the ProgramStack data structure designed for this project. This is what allows for (mostly functional) variable scoping. This is the fan-favorite character of this story.

**ProgramBlock.java** - Along with ProgramStack, this class is used to help with variable scoping. This is the fan-favorite character's bestfriend.

**CoreVariable.java** - The file containing the CoreVariable data structure designed for this project. It is an abstract class meant to be extended by CoreInteger and CoreObject.

**CoreInteger.java** - The file extending CoreVariable. It represents an integer.

**CoreObject.java** - The file extending CoreVariable. It represents an object.

**Procedure.java** - The file containing the Procedure class representing the Procedures/programs in the Core language. This is the protagonist's bestfriend.

**DeclSeq.java** - The file containing the DeclSeq class representing the declaration sequences in the Core language.

**StmtSeq.java** - The file containing the StmtSeq class representing the statement sequences in the Core language.

**Decl.java** - The class representing the variable declarations in the Core language.

**Stmt.java** - The class representing the various different statements in the Core language. Including assignments, if-statements, loops, output-statements, and declarations. This is the underrated character.

**Assignment.java** - The class representing assignments in the Core language.

**IfStmt.java** - The class representing if-statements in the Core language.

**Loop.java** - The class representing loops in the Core language.

**Out.java** - The class representing output statements in the Core language.

**Cond.java** - The class representing conditionals in the Core language.

**Cmpr.java** -  The class representing the comparisons in the Core language.

**Expr.java** - The class representing expressions in the Core language.

**Term.java** - The class representing terms in the Core language.

**Factor.java** - The class representing factors in the Core language.

**Function.java** - The class representing functions in the Core language.

**Call.java** - The class used to execute functions and pass in parameters.

**Parameters.java** - The class used to represent parameters in the Core language. Note: I use this for both the passed in values and the formal parameters.

**GarbageCollector.java** - The file containing the GarbageCollector class. This is the new, and final, protagonist of this story.

*For more info on the Core language, please see the final page of the "3341 Project 5.pdf" file.*

## Author Comments:
I sound like a broken record at this point, but I could've done better here. Many of the issues I faced during this project (which was meant to be an easier project) were due to the complications and failures of the previous projects.

### Known Bugs:
Since I was unable to get Project 4 to work correctly, recursion does not function in the correct way and the Garbage Collector is unable to properly keep track of the number of reachable objects in that case.
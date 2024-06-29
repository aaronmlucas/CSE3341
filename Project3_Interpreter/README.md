# Project 3 Interpreter
Principles of Programming Languages

## Name: Aaron Lucas

This zip contains the third project for CSE 3341. In this project, I was tasked with creating an interpreter for the same fictional language (Core) used in project 1 and 2. This project is built on top of the work produced in Project 1 and 2. As such, a couple files are borrowed from it and are included in this zip. Similar to the previous project, many files were provided by the instructor which were *mostly* unedited.

## The files provided or borrowed from previous projects

Correct (Directory)
    - #.code - The code read by the built Scanner, Parsed, and then executed.
    - #.data - The data read in by a second Scanner. It is used during the execution of the code.
    - #.expected - The expected output.
    - #.student - The actual output.

Error (Directory)
    - #.code - Code containing errors meant to be caught by the interpreter.

**'3341 Project 3'.pdf** - The instructions for this Project.

**Core.java** - Includes the Core enum containing all possible tokens in the Core language. Borrowed from Project 1.

**Scanner.java** - The file containing the Scanner class and all functions pertaining to it. Borrowed from Project 1.

## The files created/edited for this project were:

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


*For more info on the Core language, please see the final page of the "3341 Project 3.pdf" file.*


## Author Comments:
This project was interesting. I wish I had started it earlier so I could enjoy it a bit more and experiment with a few things. Overall, I'm fairly happy with my solution. It's definitely a bit rough around the edges *(looking at you CoreInteger and CoreObject)*, but it's alright. I'm particularly proud of the ProgramStack and ProgramBlock classes. ProgramStack is essentially the stack that holds all other stacks/blocks/scopes. Blocks lower down on the stack are considered to be in a higher scope. The higher up on the stack, the more specified the scope is. ProgramBlock is a class that holds the variables. These are popped off the stack once the scope is left. Pretty nifty.

### Known Bugs:
- Not sure what is going on with test case 15. I think the test case has an issue itself (and not my solution). As always any feedback is appreciated.
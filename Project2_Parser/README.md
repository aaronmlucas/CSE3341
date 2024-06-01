# Project 2 Parser
Principles of Programming Languages

## Name: Aaron Lucas

This zip contains the second project for CSE 3341. In this project, I was tasked with creating a parser with semantic checking for the same fictional language (Core) used in project 1. This project is built on top of the work produced in Project 1. As such, a couple files are borrowed from it and are included in this zip. Similar to the previous project, many files were provided by the instructor which were *mostly* unedited.

## The files provided or borrowed from previous projects

Correct (Directory)
    - #.code - The code read by the built Scanner.
    - #.expected - The expected output of the Scanner.
    - #.student - The actual output of the Scanner.

Error (Directory)
    - #.code - Code containing syntax errors intended to be caught by the Scanner.

**'3341 Project 2'.pdf** - The instructions for this Project.

**Core.java** - Includes the Core enum containing all possible tokens in the Core language. Borrowed from Project 1.

**Scanner.java** - The file containing the Scanner class and all functions pertaining to it. Borrowed from Project 1.

## The files created/edited for this project were:

**README.md** - Congratulations, you found it.

**Main.java** - The file containing the main method for the Project. It is what initializes the Scanner, Parser, and calls the necessary methods.

**tester.sh** - A file provided by the instructor to make testing a bit easier. I made slight modifications to the java commands to suit my computer better. *If you wish to run this tester script, you may want to change lines 6 and 7 by removing the .exe*

**Parser.java** - The file containing the Parser class and all functions pertaining to it. This is the protagonist of this story.

**ProgramStack.java** - The file containing the ProgramStack data structure designed for this project. This is what allows for (mostly functional) variable scoping. This is the fan-favorite character of this story. *I'm quite proud of this not gonna lie.*

**CoreVariable.java** - The file containing the CoreVariable data structure designed for this project. It holds the Core variables' names, types, and values. This is the fan-favorite character's sweetheart. *Also quite proud of this*

**Procedure.java** - The file containing the Procedure class representing the Procedures/programs in the Core language. This is the protagonist's bestfriend.

**DeclSeq.java** - The file containing the DeclSeq class representing the declaration sequences in the Core language.

**StmtSeq.java** - The file containing the StmtSeq class representing the statement sequences in the Core language.

**Decl.java** - The class representing the variable declarations in the Core language.

**Stmt.java** - The class representing the various different statements in the Core language. Including assignments, if-statements, loops, output-statements, and declarations. This is the underrated character.

**Cond.java** - The class representing conditionals in the Core language.

**Expr.java** - The class representing expressions in the Core language.

**Term.java** - The class representing terms in the Core language.

**Factor.java** - The class representing factors in the Core language.

*For more info on the Core language, please see the final page of the "3341 Project 2.pdf" file.*


## Author Comments:
This project was a lot of work and it kind of kicked my butt. My solution still contains a lot of issues. Some easily fixable, some not so much. Overall, I'm fairly happy with how it turned out. I was able to get each test case to pass and error messages to print out for almost all of the proper tests (although some error messages could use a little work).
I also tried to design this project in a way that could be expanded upon. For example, almost all classes have a print method even though most of them are not used. I could realistically see these being useful for debugging or other applications should this be expanded upon.

I would greatly appreciate feedback on how you think I did and how I could refactor/restructure my solution. Particularly with the known bugs mentioned below.

### Known Bugs:
 - Output formatting still isn't quite perfect. Particularly with nested blocks.
 - Local scope variables are still having a few issues. Particularly variables that are no longer in scope after leaving a block. (See the 2nd error test provided)
 - As mentioned above, error messages could be a little bit more descriptive. (Although it may be sufficient)
 - Not necessarily a bug, but I feel like I may have some extraneous code I forgot to remove after trying a few things out. So if you see something that looks useless, it very well might be.
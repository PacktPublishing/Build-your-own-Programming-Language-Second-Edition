# Build-your-own-Programming-Language-Second-Edition
This is the code repository for [Build your own Programming Language, Second Edition](https://www.packtpub.com/product/build-your-own-programming-language-second-edition/9781804618028), published by Packt.

**A developer's comprehensive guide to crafting, compiling, and implementing programming languages**

The author of this book is - [Clinton Jeffery](https://www.linkedin.com/in/clinton-jeffery-b109464/)

## About the book
There are many reasons to build a programming language: out of necessity, as a learning exercise, or just for fun. Whatever your reasons, this book gives you the tools to succeed.

You’ll build the frontend of a compiler for your language with a lexical analyzer and parser, including the handling of parse errors. Then you’ll explore a series of syntax tree traversals before looking at code generation for a bytecode virtual machine or native code. In this edition, a new chapter has been added to assist you in comprehending the nuances and distinctions between preprocessors and transpilers. Code examples have been modernized, expanded, and rigorously tested, and all content has undergone thorough refreshing. You’ll learn to implement code generation techniques using practical examples, including the Unicon Preprocessor and transpiling Jzero code to Unicon. You'll move to domain-specific language features and learn to create them as built-in operators and functions. You’ll also cover garbage collection.

Dr. Jeffery’s experiences building the Unicon language are used to add context to the concepts, and relevant examples are provided in both Unicon and Java so that you can follow along in your language of choice.

By the end of this book, you'll be able to build and deploy your own domain-specific language.

## Key Takeaways
- Analyze requirements for your language and design syntax and semantics.
- Write grammar rules for common expressions and control structures.
- Build a scanner to read source code and generate a parser to check syntax.
- Implement syntax-coloring for your code in IDEs like VS Code.
- Write tree traversals and insert information into the syntax tree.
- Implement a bytecode interpreter and run bytecode from your compiler.
- Write native code and run it after assembling and linking using system tools.
- Preprocess and transpile code into another high-level language

## Outline and Chapter Summary
This second edition was begun primarily at the suggestion of a first edition reader who called me one day and explained that they were using this book for a programming language project that was not generating code for a bytecode interpreter or a native instruction set as covered in the first edition. Instead, they were creating a transpiler from a classic legacy programming language to a modern mainstream language. There are many such projects, because there is a lot of old code out there that is still heavily used. The Unicon translator itself started as a preprocessor and then was extended until it is, in some sense, a transpiler. So, when Packt asked for a second edition, it was natural to propose a new chapter on that topic; this edition has a new Chapter 11 and all chapters (starting from what was chapter 11 in the previous edition) have seen their number incremented by one.  A second major facet of this second edition was requested by Packt and not my idea at all. They requested that the IDE syntax coloring chapter (which was their idea in the first place) be extended to deal with the topic of adding syntax coloring to mainstream IDEs that I did not write and do not use, instead of its previous content on syntax coloring in the Unicon IDEs. Although this topic is outside my comfort zone, it is a valuable topic that is somewhat under-documented at present, that easily deserves inclusion, so here it is. The reader can decide whether I have managed to do it any justice as an introduction to that topic.
After 60+ years of high-level language development, programming is still too difficult. The demand for software of ever-increasing size and complexity has exploded due to hardware advances, while programming languages have improved far more slowly. Creating new languages for specific purposes is one antidote for this software crisis.
This book is about building new programming languages. The topic of programming language design is introduced, although the primary emphasis is on programming language implementation. Within this heavily studied subject, the novel aspect of this book is its fusing of traditional compiler-compiler tools (Flex and Byacc) with two higher-level implementation languages. A very high-level language (Unicon) plows through a compiler's data structures and algorithms like butter, while a mainstream modern language (Java) shows how to implement the same code in a more typical production environment.
One thing I didn't really understand after my college compiler class is that the compiler is only one part of a programming language implementation. Higher-level languages, including most newer languages, may have a runtime system that dwarfs their compiler. For this reason, the second half of this book spends quality time on a variety of aspects of language runtime systems, ranging from bytecode interpreters to garbage collection.

### Chapter 1, Why Build Another Programming Language?
This chapter points out a few good reasons to build your own programming language, as well as some circumstances in which you don’t need to build your contemplated language. After all, designing a class library for your application domain is often simpler and just as effective. However, libraries have their limitations, and sometimes, only a new language will do.

**Key Insights**:

1.	It is much easier to generate C code than to generate machine code, but the resulting code may be larger or slower than native code, causing a performance cost. A transpiler depends on an underlying compiler that may be a bit of a moving target, but if the underlying compiler is highly portable, the transpiler will be far more portable than a compiler that generates native code.
2.	Lexical, syntax, and semantic analysis, followed by intermediate and final code generation.
3.	Classic pain points include input/output being overly difficult, especially on new kinds of hardware; concurrency; and making a program run across many different operating systems and CPUs. One feature that languages have used to simplify input/output has been to reduce the problem of communicating with new hardware via a set of strings in human-readable formats, for example, to play music or read touch input. Concurrency has been simplified in languages with built-in threads and monitors. Portability has been simplified in languages that provide their own high-level virtual machine implementation.
4.	This depends on your application domain of interest, but here is one. The language will input programs written in a Java-like syntax stored in files with a .j0 extension, generating target code in the form of HTML5 + JavaScript that runs on websites. The language will support JDBC and socket communications via websockets, and 2D and 3D graphics by means of OpenGL. The language will support an intuitive square-bracket syntax for accessing string elements and HashMap keys. The language will support JSON syntax natively within the source code as a HashMap literal.

### Chapter 2, Programming Language Design 
This chapter points out the crucial steps in designing a programming language, emphasizing the importance of thorough definition before implementation. It discusses the necessity of outlining both surface-level features, such as word formation and punctuation, and higher-level syntax governing program structure. The process involves writing example code to illustrate language constructs and variations, followed by the formulation of lexical and syntax rules. Once examples are comprehensive and rules are defined, a language design document is created as a reference for implementation. Key topics covered include word and punctuation selection, control flow specification, data types, program structure, and the completion of language definition through a case study.

**Key Insights**:
1.	Reserved words contribute both to human readability and ease of parsing for the language implementation, but they also sometimes preclude the most natural names for the variables in a program, and too many reserved words can make it more difficult to learn a programming language.
2.	Integers in C or Java, for example, can be expressed as signed or unsigned in a decimal, octal, hexadecimal, or maybe even binary format, for small, medium, large, or super-sized words.
3.	Several languages implement a semicolon insertion mechanism that makes semicolons optional. Sometimes ,this involves using the newline character to replace the role of the semicolon as a statement terminator or separator. It is not usually a straightforward mapping of newline==semi-colon; there are often contextual rules involved. For example, Go adds semi-colons at newlines when the last token on a line is a member of a prescribed set of tokens that require semi-colons, which is a simple contextual rule. Icon and Unicon have additional context: in addition to looking at the last token on a line, Icon and Unicon also look at the first token of the next line, and they only insert a semi-colon if the first token on the next line can legally come after a semi-colon.
4.	Although most Java programs do not make use of this capability, putting main() in several (or all) classes might be very useful in unit testing and integration testing.
5.	While it is feasible to provide pre-opened input/output facilities, these can involve substantial resources and initialization costs that programs should not have to pay for unless a given input/output facility will be used in them. If you design a language that specifically targets a domain where one of these forms of input/output is guaranteed, it makes good sense to consider how to make access as simple as possible.

### Chapter 3, Scanning Source Code
This chapter delves into the fundamental process of parsing source code by identifying characters and grouping them into meaningful units. It draws parallels between natural language comprehension and programming language understanding, highlighting the importance of recognizing words and punctuation through pattern matching. Key topics covered include lexemes, lexical categories, and tokens, along with techniques such as regular expressions. The discussion extends to practical tools like UFlex and JFlex for scanning source code and constructing parsers. Additionally, it addresses the limitations of regular expressions in certain contexts. The chapter emphasizes the necessity of categorizing entities in program source code to facilitate interpretation, akin to distinguishing parts of speech in natural language.

**Key Insights**:
1.	A first approximation of the regular expression is [0-3][0-9]”/”[01][0-9]”/”[0-9]{4}. While it is possible to write a regular expression that matches only legal dates, such an expression is impractically long, especially when you consider leap years. In such cases, it makes sense to use the regular expression that provides the simplest close approximation of correctness, and then check the correctness in the semantic action or a subsequent semantic analysis phase.
2.	yylex() returns an integer category for use in syntax analysis, while yytext is a string that contains the symbols matched, and yylval holds an object called a token that contains all the lexical attributes of that lexeme.
3.	When a regular expression does not return a value, the characters that it matches are discarded and the yylex() function continues with a new match, starting with the next character in the input.
4.	Flex matches the longest string that it can; it breaks ties among multiple regular expressions by selecting whichever one matches the longest string. When two regular expressions match the same length in a given point, Flex selects whichever regular expression occurs first in the lex specification file.

### Chapter 4, Parsing
This chapter explores the process of parsing, where individual lexemes are organized into larger programming constructs like expressions, statements, functions, classes, and packages. Parsing involves defining syntax rules using grammars and constructing a parser using parser generator tools. Additionally, the chapter addresses the importance of generating clear and helpful syntax error messages to aid developers in debugging. Key topics include syntax analysis, context-free grammars, and the utilization of tools like iyacc and BYACC/J to automate parser generation. Practical application is demonstrated through the creation of a parser for Jzero and strategies for enhancing syntax error reporting are discussed.

**Key Insights**:
1.	A terminal symbol is not defined by a production rule in terms of other symbols. This is the opposite of a non-terminal symbol, which can be replaced by or constructed from the sequence of symbols on the right-hand side of a production rule that defines that non-terminal symbol.
2.	A shift removes the current symbol from the input and pushes it onto the parse stack. A reduce pops zero or more symbols from the top of the parse stack that match the right-hand side of a production rule, pushing the corresponding non-terminal from the left side of the production rule in their place.
3.	YACC gives you a chance to execute some semantic action code only when a reduce operation takes place.
4.	The integer categories returned from yylex() in the previous chapter are exactly the sequence of terminal symbols that the parser sees and shifts during parsing. A successful parse shifts all the available input symbols and gradually reduces them back to the starting non-terminal of the grammar.

### Chapter 5, Syntax Trees
This chapter focuses on the subsequent phases of compiler construction: semantic analysis and code generation, following lexical and syntax analysis. While the parser developed in the previous chapter handles syntax errors, it also constructs a data structure known as a syntax tree when no errors are present. Syntax trees represent the logical organization of a program based on how tokens and program components are grouped together according to grammar rules. These trees serve as the foundation for language implementation, guiding semantic analysis and code generation phases. The chapter introduces the concept of syntax trees, detailing their construction and role in language implementation. Key topics include understanding tree data structures, creating leaves from terminal symbols, building internal nodes from production rules, forming syntax trees for the Jzero language, and techniques for debugging and testing syntax trees. Additionally, the chapter previews new tools aimed at facilitating language development throughout the book.

**Key Insights**:
1.	The yylex() lexical analyzer allocates a leaf and stores it in yylval for each terminal symbol that it returns to yyparse().
2.	When a production rule in the grammar is reduced, the semantic action code in the parser allocates an internal node, and it initializes its children to refer to the leaves and internal nodes corresponding to symbols on the right-hand side of that production rule.
3.	yyparse() maintains a value stack that grows and shrinks in lock-step with the parse stack during parsing. Leaves and internal nodes are stored on the value stack until they are inserted as children into a containing internal node.
4.	A value stack is fully generic and can contain whatever type(s) of values the compiler may require. In C, this is done using a union type, which is type-unsafe. In Java, it is done using a parserVal class that contains the tree nodes generically. In Unicon and other dynamic languages, no wrapping or unwrapping is needed.

### Chapter 6, Symbol Tables
This chapter delves into the critical task of semantic analysis in compiler construction, focusing on understanding and managing symbols in program source code. Symbol tables, auxiliary data structures to syntax trees, play a pivotal role in this process, enabling the compiler to resolve references to names like variables, functions, classes, or packages. Semantic analysis involves studying the meaning of the input source code, identifying semantic errors, and preparing for subsequent phases like type checking and code generation. Key concepts include distinguishing terminal symbols and non-terminal symbols in context-free grammars from symbols representing names in source code. The chapter introduces the construction, population, and utilization of symbol tables, highlighting their importance in error detection, type checking, and code generation. Practical examples focus on building symbol tables for a subset of Java, emphasizing recursive tree traversal techniques. Topics covered include establishing groundwork for symbol tables, managing scopes, handling undeclared and redeclared variables, and addressing package and class scopes. This chapter lays the foundation for advanced compiler concepts and techniques.

**Key Insights**:
1.	Symbol tables allow your semantic analysis and code generation phases to quickly look up symbols declared far away in the syntax tree, following the scoping rules of the language.
2.	Synthesized attributes are computed using the information located immediately at a node, or using information obtained from its children. Inherited attributes are computed using information from elsewhere in the tree, such as parent or sibling nodes. Synthesized attributes are typically computed using a bottom-up, post-order traversal of the syntax tree, while inherited attributes are typically computed using a pre-order traversal. Both kinds of attributes are stored in syntax tree nodes in variables added to the node’s data type.
3.	The Jzero language calls for a global scope, a class scope, and one local scope for each member function. The symbol tables are typically organized in a tree structure corresponding to the scoping rules of the language, with child symbol tables attached or associated with the corresponding symbol table entries in the enclosing scope.
4.	If Jzero allowed multiple classes in separate files, the symbol tables would need a mechanism to be aware of the classes. In Java, this may entail reading other source files at compile time while compiling a given file. This implies that classes must be easily found without reference to their filename, hence Java’s requirement that classes be placed in files whose base name is the same as the class name.

### Chapter 7, Checking Base Types
This chapter focuses on implementing simple type checks for the base types found in the Jzero subset of Java, enhancing the compiler's ability to generate correct instructions based on operand types. By associating type information with syntax tree nodes, the compiler gains insight into the types of operands, allowing for more accurate code generation. Key topics include representing types within the compiler, assigning type information to declared variables, determining types at each syntax tree node, and exploring runtime type checks and type inference through a Unicon example. The chapter addresses the significance of type checking in ensuring both efficiency and safety in program execution. While languages like Python, Lisp, BASIC, and Unicon may forgo explicit type checking for user-friendliness, opting for runtime checks, compile-time type checking offers performance benefits and helps prevent fatal errors. Thus, the chapter delves into type checking methodologies, starting with base types, and emphasizes the importance of representing type information extracted from source code.

**Key Insights**:
1.	Type checking finds many errors that would prevent the program from running correctly, but it also helps determine how much memory will be needed to hold variables, and exactly what instructions will be needed to perform the various operations in the program.
2.	A structure type is needed to represent arbitrarily deep composite structures, including recursive structures such as linked lists. Any given program only has a finite number of such types, so it would be possible to enumerate them and represent them using integer subscripts by placing them in a type table. However, references to structures provide a more direct representation.
3.	If real compilers reported an OK line for every successful type check, non-toy programs would emit thousands of such checks on every compile, making it difficult to notice the occasional errors.
4.	Picky type checkers may be a pain for programmers, but they help avoid unintended type conversions that hide logic errors, and they also reduce the tendency of a language to run slow due to silently and automatically converting types repeatedly at runtime.

### Chapter 8, Checking Types on Arrays, Method Calls, and Structure Accesses
This chapter expands on the type checking principles introduced earlier, focusing on more intricate operations. It covers type checks for arrays, method calls, and structured types like classes in the Jzero subset of Java. Key topics include array type checks, method call verification, and structured type access validation. Mastering these concepts enables developers to conduct sophisticated tree traversals for comprehensive type checking, crucial for building practical programming languages. Beginning with array type checking, this chapter lays the groundwork for handling complex composite types, empowering readers to enhance compiler capabilities effectively.

**Key Insights**:
1.	For any specific array access, the result of a subscript operator will be the array’s element type. With a struct or class access, the name of the (member) field within the struct must be used to determine the resulting type, via a symbol table lookup or something equivalent.
2.	A function’s return type can be stored in the function’s symbol table and looked up from anywhere within the function’s body. One easy way to do this is to store the return type under a symbol that is not a legal variable name, such as return. An alternative would be to propagate the function’s return type down into the function body as an inherited attribute. This might be relatively straightforward, but it seems like a waste of space in the parse tree nodes.
3.	Generally, operators such as plus and minus have a fixed number of operands and a fixed number of types for which they are defined; this lends itself to storing the type checking rules in a table or a switch statement of some kind. Function calls have to perform type checking over an arbitrary number of arguments, which can be of an arbitrary type. The function’s parameters and return type are stored in its symbol table entry. They are looked up and used to type check each site where that function is called.
4.	Besides member access, type checking occurs when composite types are created, assigned, passed as parameters, and, in some languages, destroyed.

### Chapter 9, Intermediate Code Generation
This chapter introduces the concept of intermediate code and demonstrates its generation, focusing on examples from the Jzero language. After mastering tree traversals to analyze and augment syntax trees in previous chapters, this chapter initiates the compiler's output construction process. Intermediate code, a sequence of machine-independent instructions, serves as an intermediary step before optimization and final code generation for a target machine. Key topics include understanding intermediate code, defining an instruction set, and generating code for expressions and control flow. This chapter lays the groundwork for subsequent optimization phases and final code generation. 
**Key Insights**:
1.	It would be reasonable, and could be appropriate, to introduce three address instructions to do input and output. However, most languages’ input and output operations are encapsulated by a function or method calling interface, as I/O tends to be encapsulated either by language runtime system calls or system calls.
2.	Semantic rules are logical declarative statements of how to compute semantic attributes. Synthesized attributes can generally be computed by bottom-up, post-order tree traversals. Depending on their interdependences, inherited attributes can be computed by one or more top-down, pre-order tree traversals.
3.	Computing labels during the same tree traversal that generates code may improve the compiler’s performance. However, it is difficult to generate code with gotos whose labels point forward to code that has not been generated yet. A single-pass compiler may have to build auxiliary structures and backpatch code.
4.	A naïve code generator that calls genlocal() for every new local variable may use far more space on the stack than is necessary. Excessive stack sizes may reduce performance due to poorer page caching, and in heavily recursive code, it may increase the possibility of a stack overflow or running out of memory.

### Chapter 10, Syntax Coloring in an IDE
This chapter explores the essential ecosystem of tools required for developing a programming language, emphasizing the significance of an integrated development environment (IDE). An IDE streamlines the development process by incorporating source code editing, compilation, linking, and execution within a unified interface. Modern IDEs offer additional features like GUI builders and debuggers. Addressing the importance of IDE support for language adoption, this chapter tackles challenges related to integrating language features into popular IDEs, focusing on Visual Studio Code for Jzero support and demonstrating syntax-coloring IDE code in Unicon. Key topics include considerations for building your own IDE versus supporting existing ones, setting up software dependencies, adding language support to Visual Studio Code, integrating compilers into programmer's editors, optimizing file processing to avoid unnecessary parsing, and leveraging lexical information for syntax coloring and error highlighting. The chapter emphasizes communication and coordination within software systems, highlighting efficient data sharing mechanisms between IDEs and compilers. 

**Key Insights**:
1.	Colorblind individuals may be able to utilize a limited number of grayscales or textures in IDEs where color-seeing individuals use colors. For many users who have only partial color blindness, allowing users to customize the assignments of colors to various source code elements may be the best solution. If using textures to substitute for colors, one might use the background texture where text fonts are drawn. Other font styles such as bold, italics, underlining, or shadowing might also be used.
2.	Reparsing does not have to depend on cursor motion. It could be based on the number of tokens inserted, deleted, or modified, the number of keystrokes, the amount of elapsed real time, or some combination of several of these factors.
3.	There are many possible ways to indicate syntactic nesting. For example, nesting might be represented by indentation, a progressive darkening of the background color, or boundaries of scopes might be explicitly drawn with dashed lines.

### Chapter 11, Preprocessors and Transpilers
This chapter marks a return to generating executable output from source code, offering various approaches. We discuss translation to another high-level language here, while subsequent chapters explore bytecode and native code translation. Starting with translation to a high-level language is often the quickest path to implementation.

In the past, languages were primarily implemented through interpreters or compilers. Now, many new languages use preprocessors or transpilers to generate code for existing languages. This chapter introduces preprocessors and transpilers, covering their implementation and differences. Topics include understanding preprocessors, code generation in the Unicon preprocessor, and transpiling Jzero code to Unicon.

**Key Insights**:
1.	Preprocessors in C/C++ have been known to be a subtle source of bugs, particularly when programmers misuse them. Many of these bugs occur due to macros with parameters, whose bodies can call other macros with parameters. The output after all macros are expanded can be tricky, or surprising. Setting aside the issue of bugs, if one is not careful, macros can reduce readability instead of improving it, or they can give a false sense of security when a macro looks simple but what it expands to is complex.
2.	Transpilers have a bootstrapping problem: they depend on some other high-level language being ported first. Transpilers leave you dependent on the underlying language working correctly, but the underlying language may change in ways that break the transpiler. Transpilers may also introduce problems with performance or the debugging of code.

### Chapter 12, Bytecode Interpreters
This chapter explores the significance of bytecode in enabling the execution of programming languages with novel features beyond mainstream CPU capabilities. By generating bytecode for an abstract machine tailored to the language's domain, developers can liberate their language from hardware constraints and produce code optimized for specific problem domains. Designing a custom bytecode instruction set empowers developers to execute programs using a virtual machine tailored to interpret those instructions.

Key topics covered include understanding bytecode, distinguishing it from intermediate code, designing a bytecode instruction set for Jzero, and implementing a bytecode interpreter. Close ties between this chapter and Chapter 13, "Generating Bytecode," are emphasized, urging readers to explore both before diving into the code.

**Key Insights**:
1.	Complex instruction sets take more time and logic to decode and might make the implementation of the byte-code interpreter more difficult or less portable. On the other hand, the closer the final code comes to resembling intermediate code, the simpler the final code generation stage becomes.
2.	Implementing bytecode addresses using hardware addresses provides the best performance that you might hope for, but it may leave an implementation more vulnerable to memory safety and security issues. A bytecode interpreter that implements addresses using offsets within an array of bytes may find it has fewer memory problems; performance may or may not be a problem.
3.	Some bytecode interpreters may benefit from the ability to modify code at runtime. For example, bytecode that was linked using byte-offset information may be converted into code that uses pointers. Immutable code makes this type of self-modifying behavior more difficult or impossible.

### Chapter 13, Generating Bytecode
This chapter continues the journey of code generation, transitioning from intermediate code to bytecode. This process transforms intermediate representations into executable final code, typically performed at compile time but adaptable to other stages like link time or runtime. By generating bytecode, developers unlock another option for executing programs beyond transpilation to high-level languages.

Translation to bytecode involves iterating through intermediate instructions and converting them into bytecode instructions. This straightforward process, although depicted simply in this chapter, is crucial for bringing new programming languages to life.

Key topics covered include converting intermediate code to Jzero bytecode, comparing bytecode assembler with binary formats, and integrating the runtime system through linking and loading. Additionally, an example from Unicon illustrates bytecode generation in icont.

**Key Insights**:
1.	Operands from multi-operand instructions are pushed onto the stack by PUSH instructions. The actual operation computes a result. The result is stored in memory by a POP instruction.
2.	A table that maps each of the labels to byte offset 120 is constructed. Uses of labels encountered after their table entry exists are simply replaced by the value 120. Uses of labels encountered before their table entry exists are forward references; the table must contain a linked list of forward references that are backpatched when the label is encountered.
3.	On the Jzero bytecode stack machine, operands might already be on the stack and PARM instructions might be redundant, allowing for substantial optimization. Also, on the Jzero machine, the function call sequence calls for a reference/address to the method being called to be pushed before the operands; this is a very different calling convention from that used in the three-address intermediate code.
4.	Static methods do not get invoked on an object instance. In the case of a static method with no parameters, you may need to push the procedure address within the CALL instruction, since it is preceded by no PARM instructions.
5.	It is possible to guarantee no nesting of PARM…CALL sequences by re-arranging code and introducing additional temporary variables, but that can be cumbersome. If you determine that your three-address code for nested calls does in fact result in nested PARM…CALL sequences, you will need a stack of PARM instructions to manage it and will need to carefully search for the correct CALL instruction, skipping over any nested CALL instructions whose number of PARM instructions were placed on the stack after the PARM instruction that you are searching for. Have fun!
6.	Whether portability trumps performance is a design decision, and there is no one right answer. It is possible to write a portable bytecode in which portable instructions rewrite themselves into native formats on the fly. In an extreme case, this might entail just-in-time compilation to pure native code.

### Chapter 14, Native Code Generation
This chapter explores the process of generating native code from intermediate code, focusing on the x64 architecture, prevalent in laptops and desktops. Native code refers to instructions directly supported by the hardware of a specific machine. Key topics include deciding when to generate native code, introducing the x64 instruction set, utilizing registers, converting intermediate code to x64 code, and generating x64 output.

**Key Insights**:
1.	There are many new concepts in native code. These include many kinds and sizes of registers and main memory access modes. Choosing from many possible underlying instruction sequences is also important.
2.	Even with the runtime addition required, addresses that are stored as offsets relative to the instruction pointer may be more compact and take advantage of instruction prefetching in the pipelined architecture, providing faster access to global variables than specifying them using absolute addresses. Disadvantages might include increased difficulty of debugging, reduced human readability of the assembler code, and/or higher complexity needed for alias analysis than what might be needed for optimization and reverse engineering tasks.
3.	Function call speed is important because modern software is often organized into many frequently called tiny functions. The x64 architecture performs fast function calls if functions take advantage of passing the first six parameters in registers. Several aspects of x64 architecture seem to have the potential to reduce execution speed, such as a need to save and restore large numbers of registers to memory before and after a call.

### Chapter 15, Implementing Operators and Built-In Functions
This chapter delves into enhancing programming languages by incorporating very high-level and domain-specific features through built-in operators and functions. While libraries are common for extending mainstream languages, they may not always suffice for certain requirements. Integrating operators and built-in functions directly into the language can streamline coding, improve performance, and enable complex language semantics.

Using Jzero as an example, this chapter illustrates the implementation of operators and functions, focusing on String and array types. Additionally, it compares these implementations with those in Unicon.

Key topics covered include implementing operators, writing built-in functions, integrating built-ins with control structures, and developing operators and functions for Unicon. 

**Key Insights**:
1.	Although libraries are great, they have downsides. Libraries tend to have more version compatibility problems than the features that are built into the language. Libraries are unable to provide a notation that is concise and readable as built-ins. Lastly, libraries do not lend themselves to interactions with novel control structures to support new application domains.
2.	If your new computation only needs one or two parameters, appears many times in typical applications in your domain, and computes a new value without side effects, it is a good candidate to be made into an operator. An operator is limited to two operands, or at the most, three; otherwise, it will not provide any readability advantage over a function. Unless new operators are familiar or can be used analogously to familiar operators, they may be less readable over time than using named functions.
3.	Ultimately, we have to read the books written by the Java language inventors to hear their reasons, but one answer might be that Java designers wanted to use strings as a class and decided classes would not be free to implement operators, for the sake of referential transparency.

### Chapter 16, Domain Control Structures
This chapter explores the importance of introducing novel control structures in domain-specific languages (DSLs) to accommodate unique or customized semantics. While adding new functions or operators is relatively straightforward, creating new control structures poses greater challenges. However, these domain-specific control structures can significantly enhance the expressiveness and efficiency of a language, making it more suitable for specific application domains.

Key topics covered include recognizing the need for new control structures, processing text using string scanning, and rendering graphics regions. The chapter aims to provide insights into determining when and how to implement new control structures in language design, emphasizing the balance between familiarity and innovation.

**Key Insights**:
1.	Control structures in very high-level and domain-specific languages had better be a lot more expressive and powerful than just if statements and loops; otherwise, programmers would be better off just coding in a mainstream language. Often, a control structure can add power by changing the semantic interpretation of the code inside it, or by changing the data to which the code is applied.
2.	We provided some examples in which control structures provided defaults for parameters or ensured an open resource was closed afterward. Domain-specific control structures can certainly provide additional high-level semantics, such as performing domain-specific input/output or accessing specialty hardware in a way that is difficult to accomplish within the context of a mainstream control flow.
3.	The application domain is string analysis. Maybe some additional operators or built-in functions would improve Unicon’s expressive power for string analysis. Can you think of any candidates you could add to the six-string analysis functions or the two position-moving functions? You could easily run some statistics on common Icon and/or Unicon applications and discover which combinations of tab() or move() and the six-string analysis functions occur most frequently in the code and are candidates for becoming operators, besides tab(match()). I doubt that tab(match()) is the most frequent. But beware: if you add too many primitives, it makes the control structure more difficult to learn and master. Also, the ideas from this control structure could be applied to the analysis of other sequential data, such as arrays/lists of numeric or object instance values.
4.	It is tempting to bundle as much additional semantics into a domain control structure as possible so that you make the code more concise. However, if a good number of wsection constructs are not based on a hierarchical 3D model and would not make use of the built-in functionality of PushMatrix() and PopMatrix(), bundling that into wsection might slow down the construct’s execution speed unnecessarily.

### Chapter 17, Garbage Collection
This chapter emphasizes the critical role of memory management in programming, advocating for automatic heap memory management through garbage collection in any modern language. Garbage collection automates the process of freeing heap memory when it's no longer needed, enhancing memory utilization and reducing the risk of memory leaks.

Two primary methods of garbage collection are discussed: reference counting and mark-and-sweep collection. Reference counting offers incremental memory freeing but suffers from fatal flaws outlined in the chapter. Mark-and-sweep collection, while more robust, introduces periodic execution pauses during garbage collection.

Key topics include understanding the importance of garbage collection, implementing reference counting, and executing mark-and-sweep collection. Through these discussions, readers will learn essential skills such as tracking object references, identifying live data, and freeing memory for reuse.

The chapter aims to elucidate the significance of garbage collection and provide insights into its implementation. Understanding these concepts is crucial for effective memory management in programming languages. 

**Key Insights**:
1.	You could modify the PostDescrip() macro to check for a null value before checking whether a value is a qualifier or a pointer. Whether such a check pays for itself depends on how costly the bitwise AND operator is, and the actual frequency of different types of data encountered during these checks, which can be measured but may well vary, depending on the application.
2.	If each class type had its own heap region, it may become possible in the implementation that class instances might no longer need to track their size, potentially saving memory costs for classes that have many small instances. The freed garbage instances could be managed on a linked list and compared with a mark-and-sweep collector, and instances might never need to be moved or pointers updated, simplifying garbage collection. On the other hand, some program runs might only use a very few of the various classes, and allocating a dedicated heap region for such classes might be a waste.
3.	While some time might be saved by not moving data during garbage collection, over time, a substantial amount of memory might be lost to fragmentation. Small chunks of free memory might go unused because later memory allocation requests were for larger amounts. The task of finding a free chunk of sufficient size might become more complex, and that cost might exceed the time saved by not moving data.



> If you feel this book is for you, get your [copy](https://www.amazon.com/Build-Your-Own-Programming-Language/dp/1804618020) today! <img alt="Coding" height="15" width="35"  src="https://media.tenor.com/ex_HDD_k5P8AAAAi/habbo-habbohotel.gif">

With the following software and hardware list you can run all code files present in the book.

## Software and hardware list

| Software required    | Link to the software    | Hardware specifications    | OS required    |
|:---:  |:---:  |:---:  |:---:  |
| Java 21 | [http://openjdk.org](http://openjdk.org) | Should work on any recent computer | Windows, MacOS, Linux  |
| Unicon version 13.3 | [http://unicon.org](http://unicon.org) | Should work on any recent computer | Windows, MacOS, Linux  |
|JFlex | [http://jflex.de/download.html](http://jflex.de/download.html) | Should work on any recent computer | Windows, MacOS, Linux  |





## Know more on the Discord server <img alt="Coding" height="25" width="32"  src="https://cliply.co/wp-content/uploads/2021/08/372108630_DISCORD_LOGO_400.gif">

You can get more engaged on the discord server for more latest updates and discussions in the community at [Discord](https://discord.com/invite/zGVbWaxqbw)

## Download a free PDF <img alt="Coding" height="25" width="40" src="https://emergency.com.au/wp-content/uploads/2021/03/free.gif">

_If you have already purchased a print or Kindle version of this book, you can get a DRM-free PDF version at no cost. Simply click on the link to claim your free PDF._
[Free-Ebook](https://packt.link/free-ebook/9781804618028) <img alt="Coding" height="15" width="35"  src="https://media.tenor.com/ex_HDD_k5P8AAAAi/habbo-habbohotel.gif">

We also provide a PDF file that has color images of the screenshots/diagrams used in this book at [GraphicBundle](https://packt.link/gbp/9781804618028) <img alt="Coding" height="15" width="35"  src="https://media.tenor.com/ex_HDD_k5P8AAAAi/habbo-habbohotel.gif">


## Get to know the Author
Clinton L. Jeffery is Professor and Chair of the Department of Computer Science and Engineering at the New Mexico Institute of Mining and Technology. He received his B.S. from the University of Washington, and M.S. and Ph.D. degrees from the University of Arizona, all in computer science. He has conducted research and written many books and papers on programming languages, program monitoring, debugging, graphics, virtual environments, and visualization. With colleagues, he invented the Unicon programming language, hosted at unicon.org.

## Other Related Books
- [50 Algorithms Every Programmer Should Know – Second Edition](https://www.packtpub.com/product/50-algorithms-every-programmer-should-know-second-edition/9781803247762)
- [Learn LLVM 17 – Second Edition](https://www.packtpub.com/product/learn-llvm-17-second-edition/9781837631346)




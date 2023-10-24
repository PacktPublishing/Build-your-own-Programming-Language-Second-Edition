#
# Build Your Own Programming Language, 2nd edition, Chapter 4 Linux makefile
#
# For testing purposes!
# You do not need "make" to run the examples from Chapter 4.
# I use "make" in order to regression-test and ensure that the code runs.
#
# On Linux one may need to set the CLASSPATH by whatever syntax one now
# uses for that. It varies by shell:L
#
#   export CLASSPATH=.:..
# or just
#   export CLASSPATH=..
# or maybe
#   export CLASSPATH=/c/users/username/example
# where that path is the directory above the ch4/ directory.
#

all: u j

u: trivial-u j0-u

trivial-u:
	uflex nnws.l
	iyacc -dd ns.y
	unicon trivial nnws ns ns_tab
	./trivial dorrie3.in >dorrie.out
	diff dorrie.out dorrie3.std

j0-u:
	uflex javalex.l
	iyacc -dd j0gram.y
	unicon j0 javalex j0gram j0gram_tab yyerror
	./j0 hello.java >hello.out
	diff hello.out hello.std

j: trivial-j j0-j

trivial-j:
	export CLASSPATH=.:..
	jflex nnws.l
	yacc -Jpackage=ch4 -Jyylex=ch4.lexer.yylex \
		-Jyyerror=ch4.yyerror.yyerror ns.y
	javac trivial.java Yylex.java Parser.java lexer.java \
		yyerror.java ParserVal.java
	java ch4.trivial dorrie3.in >dorrie.out
	diff dorrie.out dorrie3.std

j0-j:
	export CLASSPATH=.:..
	jflex javalex.l
	yacc -Jclass=parser -Jpackage=ch4 -Jyylex=ch4.j0.yylex\
		-Jyyerror=ch4.yyerror.yyerror j0gram.y
	javac parser.java Yylex.java j0.java parserVal.java \
		token.java yyerror.java
	java ch4.j0 hello.java > hello.out
	diff hello.out hello.std

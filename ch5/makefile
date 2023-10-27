#
# Build Your Own Programming Language, 2nd edition, Chapter 5 Linux makefile,
# tested on MSYS2 Mingw bash shell.
#
# On Linux one may need to set the CLASSPATH by whatever syntax one now
# uses for that. It varies by shell:
#
#   export CLASSPATH=.:..
# or just
#   export CLASSPATH=..
# or maybe
#   export CLASSPATH=/c/users/username/example
# where that path is the directory above the ch5/ directory.
#

all: u j

LYU=javalex.u j0gram.u j0gram_tab.u
U=j0.u token.u tree.u serial.u yyerror.u $(LYU)
unicon: j0
%.u : %.icn
	unicon -c $<

u: unicon
	./j0 hello.java >hello.out
	diff hello.out hello.std
	dot -Tpng hello.java.dot >hello.png
	diff hellostd.png hello.png
j0: $(U)
	unicon $(U)
javalex.icn: javalex.l
	uflex javalex.l
j0gram.icn j0gram_tab.icn: j0gram.y
	iyacc -dd j0gram.y


# A typical run might use
# set CLASSPATH=".;c:\users\username\byopl"
# in order to run from c:\users\username\byopl\ch5
BYSRC=parser.java parserVal.java Yylex.java
JSRC=j0.java tree.java token.java yyerror.java serial.java $(BYSRC)
BYJOPTS= -Jclass=parser -Jpackage=ch5
BYJIMPS= -Jyylex=ch5.j0.yylex -Jyyerror=ch5.yyerror.yyerror
java: j0.class

j: java
	java ch5.j0 hello.java >hello.out
	diff hello.out hello.std
	dot -Tpng hello.java.dot >hello.png
	diff hellostd.png hello.png

j0.class: $(JSRC)
	javac $(JSRC)
parser.java parserVal.java: j0gram.y
	yacc $(BYJOPTS) $(BYJIMPS) j0gram.y
Yylex.java: javalex.l
	jflex javalex.l


#
# Build Your Own Programming Language, 2nd edition, Chapter 6 Linux makefile,
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
# where that path is the directory above the ch6/ directory.
#

all: java unicon

LYU=javalex.u j0gram.u j0gram_tab.u
STU=symtab.u symtab_entry.u
U=j0.u token.u tree.u serial.u yyerror.u $(LYU) $(STU)
unicon: j0
%.u : %.icn
	unicon -c $<
j0: $(U)
	unicon $(U)
javalex.icn: javalex.l
	uflex javalex.l
j0gram.icn j0gram_tab.icn: j0gram.y
	iyacc -dd j0gram.y

# A typical run might use
# set CLASSPATH=".;c:\users\username\byopl"
# in order to run from c:\users\username\byopl\ch5
LYSRC=Yylex.java parser.java parserVal.java
STJ=symtab.java symtab_entry.java
JSRC=j0.java token.java yyerror.java tree.java serial.java $(LYSRC) $(STJ)
BYJOPTS= -Jclass=parser -Jpackage=ch6
BYJIMPS= -Jyylex=ch6.j0.yylex -Jyyerror=ch6.yyerror.yyerror
java: j0.class

j: java
	java ch6.j0 hello.java
	dot -Tpng hello.java.dot >hello.png

j0.class: $(JSRC)
	javac $(JSRC)
parser.java parserVal.java: j0gram.y
	yacc $(BYJOPTS) $(BYJIMPS) j0gram.y
Yylex.java: javalex.l
	jflex javalex.l


all: java unicon

LYU=javalex.u j0gram.u j0gram_tab.u
STU=symtab.u symtab_entry.u typeinfo.u
CGU=tac.u address.u byc.u Op.u
U=j0.u token.u tree.u serial.u yyerror.u $(LYU) $(STU) $(CGU)
YACC=byaccj
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
# in order to run from c:\users\username\byopl\ch11_2e
LYSRC=Yylex.java parser.java parserVal.java
TPJ=typeinfo.java arraytype.java classtype.java methodtype.java parameter.java
STJ=symtab.java symtab_entry.java $(TPJ)
CGJ=tac.java address.java byc.java Op.java
JSRC=j0.java token.java yyerror.java tree.java serial.java $(LYSRC) $(STJ) $(CGJ)
BYJOPTS= -Jclass=parser -Jpackage=ch11_2e
BYJIMPS= -Jyylex=ch11_2e.j0.yylex -Jyyerror=ch11_2e.yyerror.yyerror
java: j0.class

j: java
	java ch11_2e.j0 hello.java
	dot -Tpng hello.java.dot >hello.png

j0.class: $(JSRC)
	javac $(JSRC)
parser.java parserVal.java: j0gram.y
	$(YACC) $(BYJOPTS) $(BYJIMPS) j0gram.y
Yylex.java: javalex.l
	jflex javalex.l

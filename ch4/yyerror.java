package ch4;
public class yyerror {
    public static void yyerror(String s) {
      System.err.println("line " + j0.yylineno +
			 " column " + j0.yycolno +
			 ", lexeme \"" + j0.yytext() + "\": " + s);
      System.exit(1);
    }
}

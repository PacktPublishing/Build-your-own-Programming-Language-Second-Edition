import java.io.FileReader;
public class simple2 {
   static Yylex lex;
   public static int yylineno, yycolno;
   public static token yylval;
   public static void main(String argv[]) throws Exception {
      lex = new Yylex(new FileReader(argv[0]));
      yylineno = yycolno = 1;
      int i;
      while ((i=lex.yylex()) != Yylex.YYEOF) {
	  System.out.println("token " + i +
                   " (line " + yylval.lineno + "): " + yytext());
      }
   }
   public static String yytext() {
      return lex.yytext();
   }
   public static void lexErr(String s) {
      System.err.println(s + ": line " + yylineno +
                             ": " + yytext());
      System.exit(1);
   }
   public static int scan(int cat) {
      yylval = new token(cat, yytext(), yylineno, yycolno);
      return cat;
   }
   public static void increment_lineno() {
      yylineno++;
   }
}

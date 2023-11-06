package ch4;
import java.io.FileReader;
public class j0 {
   public static Yylex yylexer;
   public static parser par;
    public static int yylineno, yycolno, count;
   public static void main(String argv[]) throws Exception {
      init(argv[0]);
      par = new parser();
      //      par.yydebug=true;
      yylineno = yycolno = 1;
      count = 0;
      int i = par.yyparse();
      if (i == 0)
         System.out.println("no errors, " + j0.count +
			    " tokens parsed");
   }
    //   public static parserVal yylval;
   public static void init(String s) throws Exception {
      yylexer = new Yylex(new FileReader(s));
   }
   public static int YYEOF() { return Yylex.YYEOF; }
   public static int yylex() {
      int rv = 0;
      try {
        rv = yylexer.yylex();
      } catch(java.io.IOException ioException) {
        rv = -1;
      }
      return rv;
   }
   public static String yytext() {
      return yylexer.yytext();
   }
   public static void lexErr(String s) {
      System.err.println(s);
      System.exit(1);
   }
   public static int scan(int cat) {
      j0.par.yylval = new parserVal(
				new token(cat, yytext(), yylineno, yycolno));
      count++;
      return cat;
   }
   public static void newline() {
      yylineno++;
   }
   public static void whitespace() {
   }
   public static void comment() {
   }
   public static short ord(String s) { return (short)(s.charAt(0)); }
}

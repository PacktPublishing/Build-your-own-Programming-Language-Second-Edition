package ch4;
public class token {
   public int cat;
   public String text;
   public int lineno, colno;
   public token(int c, String s, int l, int col) {
      cat = c; text = s; lineno = l; colno = col;
   }
}

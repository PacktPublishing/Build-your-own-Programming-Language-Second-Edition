package ch11_2e;
public class typeinfo {
   String basetype;
   public typeinfo() { basetype = "unknown"; }
   public typeinfo(String s) { basetype = s; }
   public String str() { return basetype; }
}

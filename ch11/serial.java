package ch11_2e;
class serial {
    static int serial;
    public static int getid(){ serial++; return serial; }
}

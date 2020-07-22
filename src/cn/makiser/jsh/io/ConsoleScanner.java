package cn.makiser.jsh.io;

public class ConsoleScanner implements Scanner {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    @Override
    public String getLine() {
        return scanner.nextLine();
    }
}

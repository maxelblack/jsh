package cn.makiser.jsh.io;

public class ConsolePrinter implements Printer {
    @Override
    public void print(Object o) {
        System.out.print(o);
    }
    @Override
    public void println(Object o) {
        System.out.println(o);
    }
}

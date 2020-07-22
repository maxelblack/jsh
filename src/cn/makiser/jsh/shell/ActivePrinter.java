package cn.makiser.jsh.shell;

import cn.makiser.jsh.io.Printer;

public class ActivePrinter implements Printer {
    private final Command command;
    private final Printer printer;
    protected ActivePrinter(Printer printer, Command command) {
        this.printer = printer;
        this.command = command;
    }

    @Override
    public void print(Object o) {
        printer.print(command.getName() + ": ");
        printer.print(o);
    }
    @Override
    public void println(Object o) {
        printer.print(command.getName() + ": ");
        printer.println(o);
    }
}

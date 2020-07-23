package cn.makiser.jsh.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parameter {
    private String commandLine;
    private final List<String> parameters = new ArrayList<>();
    private final Map<String, String> paraMap = new HashMap<>();

    public Parameter(String commandLine) {
        this.commandLine = commandLine;
        String[] r = commandLine.split(" ", 2);
        parameters.add(r[0]);
    }

    public void addParameter() {
        //TODO
    }
    public void init() {
        //TODO
    }

    //Object Methods
    @Override
    public String toString() {
        return commandLine;
    }

    public List<String> getParameters() {
        return parameters;
    }
    public String getParameter() {
        String s = null;
        int size = parameters.size();
        if(size > 1) {
            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < size; i++) {
                builder.append(parameters.get(i));
            }
            s = builder.toString();
        }
        return s;
    }
}

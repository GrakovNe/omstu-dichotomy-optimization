package org.grakovne.dichotomy.executor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptFunction implements MathFunction {
    private String fuctionString;
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

    private void setFunctionString(String fuctionString) throws ExecutorException {
        if ((fuctionString == null)||(fuctionString.length() == 0)){
            throw new ExecutorException("invalid function");
        }
        this.fuctionString = fuctionString;
    }

    public ScriptFunction(String fuctionString) throws ExecutorException {
        setFunctionString(fuctionString);
    }

    @Override
    public double execute(double x) {
        double result;
        try {
            String functionToCalc = fuctionString.toLowerCase();
            functionToCalc = functionToCalc.replace("x", String.valueOf(x)).replace("pow", "Math.pow");
            result = Double.parseDouble(String.valueOf(engine.eval(functionToCalc)));
        } catch (ScriptException e) {
            throw new ExecutorException("math error");
        }

        return result;
    }
}

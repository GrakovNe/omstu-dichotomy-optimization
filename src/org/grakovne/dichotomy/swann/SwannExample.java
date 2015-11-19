package org.grakovne.dichotomy.swann;

import org.grakovne.dichotomy.executor.MathFunction;
import org.grakovne.dichotomy.executor.ScriptFunction;

public class SwannExample {

    public static void main(String[] args) throws SwannException {
        MathFunction function = new ScriptFunction("2 * pow((x), 2) - 12 * x");
        SwannOptimizer optimizer = new SwannOptimizer(function, 1, 1);
        double[] limits = optimizer.optimize();

        System.out.println(limits[0] + " " + limits[1]);

    }


}

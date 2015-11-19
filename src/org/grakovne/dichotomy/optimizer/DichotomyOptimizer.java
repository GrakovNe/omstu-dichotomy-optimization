package org.grakovne.dichotomy.optimizer;

import org.grakovne.dichotomy.executor.MathFunction;
import org.grakovne.dichotomy.executor.ScriptFunction;
import org.grakovne.dichotomy.swann.SwannOptimizer;

public class DichotomyOptimizer {
    private SwannOptimizer optimizer;
    private MathFunction mathFunction;
    private double accuracy;

    public DichotomyOptimizer(String mathFunction, double x0, double step, double accuracy) {
        this.accuracy = accuracy;
        this.mathFunction = new ScriptFunction(mathFunction);

        optimizer = new SwannOptimizer(this.mathFunction, x0, step);
    }
}

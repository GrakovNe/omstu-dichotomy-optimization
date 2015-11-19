package org.grakovne.dichotomy.optimizer;

import org.grakovne.dichotomy.executor.MathFunction;
import org.grakovne.dichotomy.executor.ScriptFunction;

public class DichotomyExample {
    public static void main (String[] args){

        MathFunction function = new ScriptFunction("pow((x-11), 2)");
        double[] limits = {-1, 35};
        double accuracy = 0.001;

        DichotomyMinimizer minimizer = new DichotomyMinimizer(function, limits, accuracy);

        System.out.println(minimizer.minimize().get("left-limit"));
        System.out.println(minimizer.minimize().get("right-limit"));
        System.out.println(minimizer.minimize().get("averange-x"));
        System.out.println(minimizer.minimize().get("averange-y"));

    }
}

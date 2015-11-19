package org.grakovne.dichotomy.optimizer;

import org.grakovne.dichotomy.executor.ExecutorException;
import org.grakovne.dichotomy.executor.MathFunction;

import java.util.HashMap;
import java.util.Map;

public class DichotomyMinimizer {
    private MathFunction mathFunction;
    private double accuracy;
    private double[] limits = new double[2];

    public DichotomyMinimizer(MathFunction mathFunction, double[] limits, double accuracy) {
        setMathFunction(mathFunction);
        setLimits(limits);
        setAccuracy(accuracy);
    }


    public Map<String, Double> minimize(){

        double leftX, rightX, leftY, rightY;

        Map<String, Double> limits = new HashMap<>();
        limits.put("left-limit", this.limits[0]);
        limits.put("right-limit", this.limits[1]);


        double residual;
        do {
            leftX = (limits.get("left-limit") + limits.get("right-limit") - accuracy) / 2.0;
            rightX = (limits.get("left-limit") + limits.get("right-limit") + accuracy) / 2.0;
            leftY = mathFunction.execute(leftX);
            rightY = mathFunction.execute(rightX);

            if (leftY <= rightY) {
                limits.put("right-limit", rightX);
            }

            if (leftY >= rightY) {
                limits.put("left-limit", leftX);
            }

            residual = Math.abs(limits.get("right-limit") - limits.get("left-limit"));
        } while (residual > accuracy + accuracy / 2 );

        double averangeX = Math.abs(limits.get("right-limit") + limits.get("left-limit")) / 2.0;
        limits.put("averange-x", averangeX);
        limits.put("averange-y", mathFunction.execute(averangeX));
        return limits;
    }

    private void setMathFunction(MathFunction function){
        this.mathFunction = function;
    }

    private void setLimits(double[] limits){
        if ((limits.length != 2)||(limits[0] >= limits[1])){
            throw new DichotomyException("incorrect limits");
        }

        this.limits = limits;
    }

    private void setAccuracy(double accuracy){
        if (accuracy <= 0){
            throw new DichotomyException("incorrect accuracy");
        }
        this.accuracy = accuracy;
    }
}

package org.grakovne.dichotomy.swann;

import org.grakovne.dichotomy.executor.MathFunction;

public class SwannOptimizer {
    private double x0;
    private double step;
    private double delta;
    private long k = 0;
    MathFunction function = null;

    private void setX0(double x0) {
        this.x0 = x0;
    }

    private void setStep(double step) throws SwannException {
        if (step <= 0) {
            throw new SwannException("incorrect step value");
        }
        this.step = step;
    }

    private void setFunction(MathFunction function) throws SwannException {
        if (function == null){
            throw new SwannException("incorrect function");
        }

        this.function = function;
    }

    public SwannOptimizer(MathFunction function, double x0, double step) throws SwannException {
        setX0(x0);
        setStep(step);
        setFunction(function);
    }

    public double[] optimize() throws SwannException {

        MathFunction calculator = function;
        double[] limits = new double[2];

        /*check on the case x0 been found*/
        if ((calculator.execute(x0 - step) >= calculator.execute(x0)) && (calculator.execute(x0) <= calculator.execute(x0 + step))) {
            limits[0] = x0 - step;
            limits[1] = x0 + step;
            return limits;
        }

        /*check undecidability*/
        if ((calculator.execute(x0 - step) <= calculator.execute(x0)) && (calculator.execute(x0) >= calculator.execute(x0 + step))) {
            throw new SwannException("function is not unimodal");
        }

        if ((calculator.execute(x0 - step) >= calculator.execute(x0)) && (calculator.execute(x0) >= calculator.execute(x0 + step))){
            delta = step;
            limits[0] = x0;
            x0 = x0 + step;
            k = 1;
        }

        if ((calculator.execute(x0 - step) <= calculator.execute(x0)) && (calculator.execute(x0) <= calculator.execute(x0 + step))){
            delta = -step;
            limits[1] = x0;
            x0 = x0 - step;
            k = 1;
        }

        while (calculator.execute(x0 + (Math.pow(2, k) * delta)) <= calculator.execute(x0)){
            if (delta == step){
                limits[0] = x0;
            }

            if (delta == -step){
                limits[1] = x0;
            }

            k++;

            x0 = x0 + (Math.pow(2, k) * delta);
        }


        if (delta == step){
            limits[1] = x0 + (Math.pow(2, k) * delta);
        }

        if (delta == -step){
            limits[0] = x0 + (Math.pow(2, k) * delta);
        }

        return limits;

    }
}

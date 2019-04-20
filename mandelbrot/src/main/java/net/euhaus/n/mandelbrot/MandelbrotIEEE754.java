package net.euhaus.n.mandelbrot;

import org.apache.commons.math3.complex.Complex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MandelbrotIEEE754 extends Mandelbrot<Double, Complex> {

    public MandelbrotIEEE754(Double minR, Double maxR, Double minIm, Double maxIm) {
        super(minR, maxR, minIm, maxIm);
    }

    @Override
    public int applySeries(Complex z, Complex c, int step) {
        if (step < MAX_STEPS) {
            z = z.multiply(z).add(c);
            if (z.abs() > 2)
                return step;
            return applySeries(z, c, step + 1);
        }

        return step;
    }

    @Override
    protected Complex zero() {
        return Complex.ZERO;
    }

    @Override
    public Double rescaleToNewScale(Double unscaledValue, Double oldLowerBound, Double oldUpperbound, Double newLowerbound, Double newUpperbound) {
        return (1.0 * (unscaledValue-oldLowerBound) / (oldUpperbound-oldLowerBound)) * (newUpperbound - newLowerbound) + newLowerbound;
    }


}

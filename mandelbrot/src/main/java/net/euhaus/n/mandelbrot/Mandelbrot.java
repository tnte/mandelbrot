package net.euhaus.n.mandelbrot;

import org.apache.commons.math3.complex.Complex;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class Mandelbrot<S,C> {

    private S maxR;

    private S maxIm;

    private S minR;

    private S minIm;

    private static int resolution = 600;

    public static final int MAX_STEPS = 350;

    public Mandelbrot(S minR, S maxR, S minIm, S maxIm) {
        this.maxR = maxR;
        this.maxIm = maxIm;
        this.minR = minR;
        this.minIm = minIm;
    }

    public final int getConvergence(C c) {
        return applySeries(zero(), c, 0);
    }

    protected abstract int applySeries(C z, C c, int step);

    protected abstract C zero();

    public S rescaleToReal(S unscaledValue, S oldLowerBound, S oldUpperbound) {
        return rescaleToNewScale(unscaledValue, oldLowerBound, oldUpperbound, getMinR(), getMaxR());
    }

    public S rescaleToImaginary(S unscaledValue, S oldLowerBound, S oldUpperbound) {
        return rescaleToNewScale(unscaledValue, oldLowerBound, oldUpperbound, getMinIm(), getMaxIm());
    }

    public abstract S rescaleToNewScale(S unscaledValue, S oldLowerBound, S oldUpperbound, S newLowerbound, S newUpperbound);

    public S getMaxR() {
        return maxR;
    }

    public S getMaxIm() {
        return maxIm;
    }

    public S getMinR() {
        return minR;
    }

    public S getMinIm() {
        return minIm;
    }

}

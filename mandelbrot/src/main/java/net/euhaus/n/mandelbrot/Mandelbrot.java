package net.euhaus.n.mandelbrot;

import org.apache.commons.math3.complex.Complex;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Mandelbrot {

    private double maxR;

    private double maxIm;

    private double minR;

    private double minIm;

    private static int resolution = 1200;

    public static final int MAX_STEPS = 44;

    public Mandelbrot(double minR, double maxR, double minIm, double maxIm) {
        this.maxR = maxR;
        this.maxIm = maxIm;
        this.minR = minR;
        this.minIm = minIm;
    }

    public Map<Integer, Set<Complex>> sampleSet() {
        Map<Integer, Set<Complex>> values = new HashMap<>();
        for (double x = minR; x < maxR; x += (1.0 / resolution)) {
            for (double y = minIm; y < maxIm; y += (1.0 / resolution)) {
                Complex c = new Complex(x, y);
                int step = applySeries(Complex.ZERO, c, 0);
                values.putIfAbsent(step, new HashSet<>());
                values.get(step).add(c);
            }
        }

        return values;
    }

    public int applySeries(Complex z, Complex c, int step) {
        if (step < MAX_STEPS) {
            z = (z.multiply(z)).add(c);
            if (z.abs() > 2)
                return step;
            return applySeries(z, c, step + 1);
        }

        return step;
    }

    public double getMaxR() {
        return maxR;
    }

    public double getMaxIm() {
        return maxIm;
    }

    public double getMinR() {
        return minR;
    }

    public double getMinIm() {
        return minIm;
    }

    public static int getResolution() {
        return resolution;
    }

    public static int getMaxSteps() {
        return MAX_STEPS;
    }

}

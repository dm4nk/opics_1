package com.dm4nk.opics.model;

import com.vm.jcomplex.Complex;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final static int alpha = 1;
    private final static int n = 1000;
    private final static int m = 1000;

    private final static int a = 1;
    private final static int b = 5;
    private final static int p = 0;
    private final static int q = 3;

    private static final double h = (b-a)/(double)n;
    private static final double l = (q-p)/(double)m;

    public final List<Double> x_k = new ArrayList<>();
    private final List<Complex> f_k = new ArrayList<>();
    public final List<Double> ksi_k = new ArrayList<>();

    public Complex f(double x){
        return new Complex(Math.cos(x/10.), Math.sin(x/10.));
    }

    public Complex K(double ksi, double x){
        return new Complex(0, Math.pow(x, alpha*ksi-1));
    }

    public Complex F(double l) {

        Complex F = new Complex(0, 0);

        for(int k = 0; k < n; ++k){
            F = F.add(K(l, x_k.get(k)).multiply(f_k.get(k)).multiply(h));
        }

        return F;
    }

    public List<Complex> result(){
        for(double c = a; c < b; c+=h){
            x_k.add(c);
        }

        for(double c : x_k){
            f_k.add(f(c));
        }

        for(double c = p; c < q; c+=l){
            ksi_k.add(c);
        }

        if(x_k.size() != f_k.size() && f_k.size() != ksi_k.size()) throw new RuntimeException("SIZE!");

        List<Complex> res = new ArrayList<>();

        for(double l : ksi_k){
            res.add(F(l));
        }

        return res;
    }

    public List<Complex> resultForKsi(double ksi){
        for(double c = a; c < b; c+=h){
            x_k.add(c);
        }

        for(double c : x_k){
            f_k.add(f(c));
        }

        for(double c = p; c < q; c+=l){
            ksi_k.add(c);
        }

        List<Complex> res = new ArrayList<>();

        Complex F;
        for(int k = 0; k < n; ++k){
            F = K(ksi, x_k.get(k));
            res.add(F);
        }

        return res;
    }
}

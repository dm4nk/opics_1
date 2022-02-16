package com.dm4nk.opics;

import com.dm4nk.opics.model.Model;
import com.vm.jcomplex.Complex;

public class Main {
    public static void main(String[] args){
        Model model = new Model();
        int i = 0;
        for(Complex c : model.result()){
            System.out.println(i++ + ": " + c.getArgument() + ", " + c.getImaginary());
        }
    }
}

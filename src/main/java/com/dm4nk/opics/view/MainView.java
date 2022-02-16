package com.dm4nk.opics.view;

import com.dm4nk.opics.model.Model;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vm.jcomplex.Complex;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {
    private final Chart chart1 = new Chart();
    private final Configuration conf1 = chart1.getConfiguration();

    //private final Chart chart2 = new Chart();
    private final Configuration conf2 = chart1.getConfiguration();

    private final Chart chart3 = new Chart();
    private final Configuration conf3 = chart3.getConfiguration();

    public MainView() {
        Model model = new Model();
        List<Complex> resul = model.result();
        List<Complex> resultForKsi = model.resultForKsi(1);

        DataSeries ksi_real = new DataSeries();

        int i = 0;
        for(Complex c : resul){
            ksi_real.add(new DataSeriesItem(model.ksi_k.get(i++) ,c.getArgument()));
        }

        conf1.addSeries(ksi_real);

        /////

        DataSeries ksi_im = new DataSeries();

        int j = 0;
        for(Complex c : resul){
            ksi_im.add(new DataSeriesItem(model.ksi_k.get(j++) ,c.getImaginary()));
        }

        conf2.addSeries(ksi_im);

        //////

        DataSeries ksi = new DataSeries();
        
        for(Complex c : resultForKsi){
            ksi.add(new DataSeriesItem(c.getArgument() ,c.getImaginary()));
        }

        conf3.addSeries(ksi_im);

        add(chart1, chart3);
    }
}

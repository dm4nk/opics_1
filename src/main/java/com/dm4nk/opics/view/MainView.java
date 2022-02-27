package com.dm4nk.opics.view;

import com.dm4nk.opics.model.Model;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H2;
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
        //List<Complex> resultForKsi = model.resultForKsi(1);

        addGraph(model.getX_k(), model.getF_k(), "Function");

        addGraph(model.getKsi_k(), resul, "Function after transformation");
    }

    private void addGraph(List<Double> doubleList, List<Complex> complexList, String title) {
        if(doubleList.size() != complexList.size()) throw new RuntimeException("SIZE!!!!!!!");

        Chart chart = new Chart();
        Configuration real = chart.getConfiguration();
        Configuration imaginary = chart.getConfiguration();

        DataSeries phase = new DataSeries();
        DataSeries amplitude = new DataSeries();

        for(int i = 0; i < doubleList.size(); ++i){
            phase.add(new DataSeriesItem(
                    doubleList.get(i),
                    complexList.get(i).getArgument()
            ));

            amplitude.add(new DataSeriesItem(
                    doubleList.get(i),
                    complexList.get(i).abs()
            ));
        }

        phase.setName("Phase");
        amplitude.setName("Amplitude");
        real.addSeries(phase);
        imaginary.addSeries(amplitude);
        add(new H2(title), chart);
    }
}

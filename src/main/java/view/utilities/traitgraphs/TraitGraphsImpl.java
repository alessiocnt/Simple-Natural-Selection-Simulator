package view.utilities.traitgraphs;

import java.util.EnumMap;
import java.util.Map;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import model.mutation.MutationRarity;
import model.mutation.TraitType;

/**
 * Class for loading graphs of traits in the lateralPane.
 */
public class TraitGraphsImpl implements TraitGraphs {
    private final Map<TraitType, XYChart.Series<Number, Number>> graphMap = new EnumMap<>(TraitType.class);
    private int time;

    @Override
    public final void load(final Pane root) {
        root.getChildren().clear();
        //Create graphs dinamically based on the current trait.
        //In this way, if we add new trait, this class not need modifactions.
        for (final TraitType trait : TraitType.values()) {
            if (!trait.getRarity().equals(MutationRarity.NOMUTATION)) {
                final LineChart<Number, Number> lineChart = this.createGraph(trait);
                root.getChildren().add(lineChart);
            }
        }
    }

    private LineChart<Number, Number> createGraph(final TraitType type) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        //Axis animations are removed
        xAxis.setAnimated(false); 
        yAxis.setLabel("Value");
        //Axis animations are removed
        yAxis.setAnimated(false); 
        //Creating the line chart with the two axis created.
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(type.toString());
        //Define a series to display data
        final XYChart.Series<Number, Number> series;
        //Control if the series is already present (for example if you open another scene, series aren't deleted, but cached).
        if (this.graphMap.containsKey(type)) {
            series = this.graphMap.get(type);
        } else {
            series = new XYChart.Series<>();
            this.graphMap.put(type, series);
        }
        // add series to chart
        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);
        return lineChart;
    }


    @Override
    public final void reset() {
        this.time = 0;
        this.graphMap.clear();
    }

    @Override
    public final void update(final Map<TraitType, Double> values) {
        values.entrySet().stream()
              .filter((x) -> this.graphMap.containsKey(x.getKey()))
              .forEach((x) -> this.graphMap.get(x.getKey())
                                           .getData()
                                           .add(new XYChart.Data<>(this.time, x.getValue())));
        this.time++;
    }
}

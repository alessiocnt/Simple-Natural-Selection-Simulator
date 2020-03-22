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
    private static final Map<TraitType, XYChart.Series<Number, Number>> GRAPHMAP = new EnumMap<>(TraitType.class);
    private static int time;

    @Override
    public final void load(final Pane root) {
        root.getChildren().clear();
        for (final TraitType trait : TraitType.values()) {
            if (!trait.getRarity().equals(MutationRarity.NOMUTATION)) {
                final LineChart<Number, Number> lineChart = this.createGraph(trait);
                root.getChildren().add(lineChart);
            }
        }
    }

    private LineChart<Number, Number> createGraph(final TraitType type) {
        final NumberAxis xAxis = new NumberAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(false); // axis animations are removed
        //creating the line chart with two axis created above
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(type.toString());
        //defining a series to display data
        final XYChart.Series<Number, Number> series;
        //If the series is already present (for example if U click settings, series aren't deleted).
        if (GRAPHMAP.containsKey(type)) {
            series = GRAPHMAP.get(type);
        } else {
            series = new XYChart.Series<>();
            GRAPHMAP.put(type, series);
        }
        // add series to chart
        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);
        return lineChart;
    }


    @Override
    public final void reset() {
        time = 0;
        GRAPHMAP.clear();
    }

    @Override
    public final void update(final Map<TraitType, Double> values) {
        values.entrySet().stream()
              .filter((x) -> GRAPHMAP.containsKey(x.getKey()))
              .forEach((x) -> GRAPHMAP.get(x.getKey())
                                           .getData()
                                           .add(new XYChart.Data<>(time, x.getValue())));
        time++;
    }
}

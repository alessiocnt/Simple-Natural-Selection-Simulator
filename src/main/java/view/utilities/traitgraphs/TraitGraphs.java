package view.utilities.traitgraphs;

import java.util.Map;

import javafx.scene.layout.Pane;
import model.mutation.TraitType;

/**
 * Interface of traitGraphs.
 */
public interface TraitGraphs {
    /**
     * @param root
     * root where draw graphs
     */
    void load(Pane root);

    /**
     * @param values
     * values to put in the graphs.
     */
    void update(Map<TraitType, Double> values);

    /**
     * Method for clear graphs and reset time. 
     */
    void reset();
}

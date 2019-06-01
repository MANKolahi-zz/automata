package uni.madani.model.automata.turing;

import uni.madani.model.automata.AutomataEdge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

public class TuringEdge extends AutomataEdge {

    private final String wright;
    private final boolean move;

    public TuringEdge(long sourceId, long targetId, long weight,
                      EdgeGraphics edgeGraphics, EdgeLabelGraphics edgeLabelGraphics,
                      String sticker, String wright, String move) {
        super(sourceId, targetId, weight, edgeGraphics, edgeLabelGraphics, sticker);
        getValues().addValue(new GraphElementValue("wright",wright),
                new GraphElementValue("move",move));
        this.wright = wright;
        this.move = move.equals("r");
    }

    public String getWright() {
        return wright;
    }

    public boolean getMove() {
        return move;
    }


}

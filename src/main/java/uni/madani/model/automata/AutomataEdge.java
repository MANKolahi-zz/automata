package uni.madani.model.automata;

import uni.madani.model.graph.Edge.AbstractEdge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

public abstract class AutomataEdge extends AbstractEdge {

    private final String sticker;

    public AutomataEdge(long sourceId, long targetId, long weight,
                        EdgeGraphics edgeGraphics, EdgeLabelGraphics edgeLabelGraphics, String sticker) {
        super(sourceId, targetId, weight, edgeGraphics, edgeLabelGraphics);
        values.addValue(new GraphElementValue("sticker", sticker));
        this.sticker = sticker;
    }

    public String getSticker() {
        return sticker;
    }

}

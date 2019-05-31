package uni.madani.model.automata.pda;

import uni.madani.model.automata.AutomataEdge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

public class PDAEdge extends AutomataEdge {

    private transient final String push;
    private transient final String pop;

    public PDAEdge(long sourceId, long targetId, long weight, EdgeGraphics edgeGraphics,
                   EdgeLabelGraphics edgeLabelGraphics, String input, String pop, String push) {
        super(sourceId, targetId, weight, edgeGraphics, edgeLabelGraphics, input);
        getValues().addValue(new GraphElementValue("pop", pop), new GraphElementValue("push", push));
        this.pop = pop;
        this.push = push;
    }

    public String getPush() {
        return push;
    }

    public String getPop() {
        return pop;
    }

}

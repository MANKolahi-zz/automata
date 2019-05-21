package uni.madani.model;

import uni.madani.model.graph.Edge.Edge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;
import uni.madani.model.graph.Graph;
import uni.madani.model.graph.Vertex.Vertex;
import uni.madani.model.graph.graphValue.GraphElementValue;

import java.util.Arrays;

public class NFA extends FA {

    public NFA(String... alphabet) {
        super(alphabet);
        super.alphabet.add("lambda");
    }

    public NFA(Graph graph,String... alphabet){
        super(graph, alphabet);
        super.alphabet.add("lambda");
    }

    public void connect(long sourceId, long targetId, String sticker) throws IllegalArgumentException {
        if (alphabet.stream().anyMatch(s -> s.equals(sticker))) {
            Edge edge = new Edge(sourceId, targetId, 1);
            edge.getValues().addValue(new GraphElementValue("sticker", sticker));
            super.connect(edge);
            return;
        }
        throw new IllegalArgumentException();
    }


}

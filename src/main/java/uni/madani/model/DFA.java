package uni.madani.model;

import uni.madani.model.graph.Edge.Edge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;
import uni.madani.model.graph.Graph;
import uni.madani.model.graph.Vertex.Vertex;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DFA extends FA {


    public DFA(String... alphabet) {
        super(alphabet);
    }

    public DFA(Graph graph, String... alphabet) {
        super(graph, alphabet);
    }

    public List<String> getAlphabet() {
        return alphabet;
    }


    @Override
    public void connect(Edge edge) throws IllegalArgumentException {
        String value = edge.getValues().getValue("sticker");
        if (getVertices().get(edge.getSourceId()).getOut().stream().
                noneMatch(edge1 -> edge1.getValues().getValue("sticker").equals(value))) {
            super.connect(edge);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void connect(long sourceId, long targetId, String sticker) throws IllegalArgumentException {
        if (alphabet.stream().anyMatch(s -> s.equals(sticker))) {
            if (getVertices().get(sourceId).getOut().stream().
                    noneMatch(edge -> edge.getValues().getValue("sticker").equals(sticker))) {
                Edge edge = new Edge(sourceId, targetId, 1);
                edge.getValues().addValue(new GraphElementValue("sticker", sticker));
                super.connect(edge);
                return;
            }
            throw new IllegalArgumentException();
        }
    }

}

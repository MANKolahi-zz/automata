package uni.madani.model.NFA;

import uni.madani.model.FA;
import uni.madani.model.FAEdge;
import uni.madani.model.graph.graph.Graph;

public class NFA extends FA<NFAVertex> {

    public NFA(String... alphabet) {
        super.alphabet.add("lambda");
    }

    public NFA(Graph graph, String... alphabet) {
        super.alphabet.add("lambda");
        graph.getVertices().values().forEach(vertex -> {
            NFAVertex nfaVertex = new NFAVertex(vertex.getId(),
                    vertex.getVertexGraphics(), vertex.getVertexLabelGraphics(),
                    this, Boolean.parseBoolean(vertex.getValues().getValue("isStart")),
                    Boolean.parseBoolean(vertex.getValues().getValue("isFinal")));
            addVertex(nfaVertex);
        });
        graph.getEdges().forEach(edge -> {
            FAEdge faEdge = new FAEdge(edge.getSourceId(), edge.getTargetId(),
                    1, edge.getEdgeGraphics(), edge.getEdgeLabelGraphics(),
                    edge.getValues().getValue("sticker"));
            connect(faEdge);
        });
    }


}

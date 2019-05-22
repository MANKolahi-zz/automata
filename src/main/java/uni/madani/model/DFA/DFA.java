package uni.madani.model.DFA;

import uni.madani.model.FA;
import uni.madani.model.FAEdge;
import uni.madani.model.graph.graph.Graph;

public class DFA extends FA<DFAVertex> {


    public DFA(String... alphabet) {
        super(alphabet);
    }

    public DFA(Graph graph,String... alphabet){
        super(alphabet);
        graph.getVertices().values().forEach(vertex -> {
            DFAVertex dfaVertex = new DFAVertex(vertex.getId(),
                    vertex.getVertexGraphics(), vertex.getVertexLabelGraphics(),
                    this, Boolean.parseBoolean(vertex.getValues().getValue("isStart")),
                    Boolean.parseBoolean(vertex.getValues().getValue("isFinal")));
            addVertex(dfaVertex);
        });
        graph.getEdges().forEach(edge -> {
            FAEdge faEdge = new FAEdge(edge.getSourceId(), edge.getTargetId(),
                    1, edge.getEdgeGraphics(), edge.getEdgeLabelGraphics(),
                    edge.getValues().getValue("sticker"));
            connect(faEdge);
        });
    }



}

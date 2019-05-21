package uni.madani.model;

import uni.madani.model.graph.Edge.Edge;
import uni.madani.model.graph.Vertex.AbstractVertex;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

import java.util.HashMap;


public abstract class FAVertex extends AbstractVertex<FAEdge> {

    private transient FA fa;
    private final HashMap<String,FAEdge> out = new HashMap<>();

    public FAVertex(long id, VertexGraphics vertexGraphics,
                    VertexLabelGraphics vertexLabelGraphics,FA fa,boolean isStart) {
        super(id, vertexGraphics, vertexLabelGraphics);
        this.fa = fa;
        if(isStart)getValues().addValue(new GraphElementValue("isStart",Boolean.toString(true)));
    }

    public void addOut(FAEdge faEdge) throws OutEdgeCapacityException {
        if(faEdge.getSourceId() == id){
            out.put(faEdge.getSticker(),faEdge);
            super.out.add(faEdge);
        }
    }

    public FA getFa() {
        return fa;
    }
    // TODO: 21.05.19 add DFAVertex and etc.
}

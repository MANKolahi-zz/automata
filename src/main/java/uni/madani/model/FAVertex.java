package uni.madani.model;

import uni.madani.model.graph.Vertex.AbstractVertex;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

import java.util.List;


public abstract class FAVertex extends AbstractVertex<FAEdge> {

    private transient FA fa;

    public FAVertex(long id, VertexGraphics vertexGraphics,
                    VertexLabelGraphics vertexLabelGraphics,FA fa,boolean isStart,boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics);
        this.fa = fa;
        getValues().addValue(new GraphElementValue("isStart",Boolean.toString(isStart)),
                new GraphElementValue("isFinal",Boolean.toString(isFinal)));
    }

    public void addOut(FAEdge faEdge){
        super.out.add(faEdge);
    }

    public void addIn(FAEdge faEdge){
        super.in.add(faEdge);
    }

    public boolean isStart(){
        return Boolean.parseBoolean(values.getValue("isStart"));
    }

    public boolean isFinal(){
        return Boolean.parseBoolean(values.getValue("isFinal"));
    }

    public FA getFa() {
        return fa;
    }

    public abstract List<FAEdge> getOut(String sticker);

}

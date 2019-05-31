package uni.madani.model.automata;

import uni.madani.model.graph.Vertex.AbstractVertex;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;
import uni.madani.model.graph.graphValue.GraphElementValue;

public abstract class State<edgeType extends AutomataEdge,
                automataType extends Automata<? extends State,edgeType>>
        extends AbstractVertex<edgeType> {

    protected transient automataType automata;
    protected transient final boolean isFinal;
    protected transient final boolean isStart;

    public State(long id, VertexGraphics vertexGraphics, VertexLabelGraphics vertexLabelGraphics,
                 automataType automata, boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics);
        this.automata = automata;
        getValues().addValue(new GraphElementValue("isStart",Boolean.toString(isStart)),
                new GraphElementValue("isFinal",Boolean.toString(isFinal)));
        this.isFinal = isFinal;
        this.isStart = isStart;

    }

    public automataType getAutomata() {
        return automata;
    }

    public void addOut(edgeType edge){
        if(automata.getAlphabet().contains(edge.getSticker())) {
            super.out.add(edge);
        }else throw new IllegalArgumentException("edge sticker is not in automata alphabet list");
    }

    public void addIn(edgeType edge){
        super.in.add(edge);
    }

    public boolean isStart(){
        return isStart;
    }

    public boolean isFinal(){
        return isFinal;
    }

}

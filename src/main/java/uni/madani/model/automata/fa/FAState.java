package uni.madani.model.automata.fa;

import uni.madani.model.automata.State;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;

import java.util.List;


public abstract class FAState<stateType extends FAState<stateType>> extends State<FAEdge,FA<stateType>> {

    public FAState(long id, VertexGraphics vertexGraphics,
                   VertexLabelGraphics vertexLabelGraphics, FA<stateType> fa, boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics,fa,isStart,isFinal);
    }

    public abstract List<FAEdge> getOut(String sticker);

}

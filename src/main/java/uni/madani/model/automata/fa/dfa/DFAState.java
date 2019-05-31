package uni.madani.model.automata.fa.dfa;

import uni.madani.model.automata.fa.FA;
import uni.madani.model.automata.fa.FAEdge;
import uni.madani.model.automata.fa.FAState;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;
import uni.madani.model.automata.fa.utils.OutEdgeCapacityException;

import java.util.HashMap;
import java.util.List;

public class DFAState extends FAState<DFAState> {

    private final HashMap<String, FAEdge> out = new HashMap<>();

    public DFAState(long id, VertexGraphics vertexGraphics,
                    VertexLabelGraphics vertexLabelGraphics, FA<DFAState> fa, boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics, fa, isStart, isFinal);
    }

    public DFAState(long id, boolean isStart, boolean isFinal, FA<DFAState> fa) {
        super(id, null, null, fa, isStart, isFinal);
    }

    @Override
    public void addOut(FAEdge faEdge)  throws OutEdgeCapacityException {
        if (!out.containsKey(faEdge.getSticker())) {
            super.getOut().add(faEdge);
            out.put(faEdge.getSticker(), faEdge);
        }else throw new OutEdgeCapacityException();
    }

    public FAEdge getEdge(String sticker) {
        return out.get(sticker);
    }

    @Override
    public List<FAEdge> getOut(String sticker){
        return List.of(out.get(sticker));
    }

}

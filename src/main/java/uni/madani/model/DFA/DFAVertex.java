package uni.madani.model.DFA;

import uni.madani.model.FA;
import uni.madani.model.FAEdge;
import uni.madani.model.FAVertex;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;

import java.util.HashMap;
import java.util.List;

public class DFAVertex extends FAVertex {

    private final HashMap<String, FAEdge> out = new HashMap<>();

    public DFAVertex(long id, VertexGraphics vertexGraphics,
                     VertexLabelGraphics vertexLabelGraphics, FA<DFAVertex> fa, boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics, fa, isStart, isFinal);
    }

    public DFAVertex(long id, boolean isStart, boolean isFinal, FA<DFAVertex> fa) {
        super(id, null, null, fa, isStart, isFinal);
    }

    @Override
    public void addOut(FAEdge faEdge) {
        if (!out.containsKey(faEdge.getSticker())) {
            super.getOut().add(faEdge);
            out.put(faEdge.getSticker(), faEdge);
        }
    }

    public FAEdge getEdge(String sticker) {
        return out.get(sticker);
    }

    @Override
    public List<FAEdge> getOut(String sticker){
        return List.of(out.get(sticker));
    }

}

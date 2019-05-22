package uni.madani.model.NFA;

import uni.madani.model.FA;
import uni.madani.model.FAEdge;
import uni.madani.model.FAVertex;
import uni.madani.model.OutEdgeCapacityException;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NFAVertex extends FAVertex {

    private final HashMap<String, List<FAEdge>> out = new HashMap<>();

    public NFAVertex(long id, VertexGraphics vertexGraphics,
                     VertexLabelGraphics vertexLabelGraphics, FA<NFAVertex> fa, boolean isStart,boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics, fa, isStart,isFinal);
        fa.getAlphabet().forEach(s -> out.put(s, new ArrayList<>()));
    }


    public NFAVertex(long id, boolean isStart,boolean isFinal, FA<NFAVertex> fa) {
        this(id, null, null, fa, isStart,isFinal);
    }

    @Override
    public void addOut(FAEdge faEdge) throws OutEdgeCapacityException {
        out.get(faEdge.getSticker()).add(faEdge);
        super.getOut().add(faEdge);
    }

    public List<FAEdge> getOut(String sticker) {
        return out.get(sticker);
    }

}

package uni.madani.model.automata.fa.nfa;

import uni.madani.model.automata.fa.FA;
import uni.madani.model.automata.fa.FAEdge;
import uni.madani.model.automata.fa.FAState;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NFAState extends FAState<NFAState> {

    private final HashMap<String, List<FAEdge>> out = new HashMap<>();

    public NFAState(long id, VertexGraphics vertexGraphics,
                    VertexLabelGraphics vertexLabelGraphics, FA<NFAState> fa, boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics, fa, isStart, isFinal);
        fa.getAlphabet().forEach(s -> out.put(s, new ArrayList<>()));
    }


    public NFAState(long id, boolean isStart, boolean isFinal, FA<NFAState> fa) {
        this(id, null, null, fa, isStart, isFinal);
    }

    @Override
    public void addOut(FAEdge faEdge){
        super.getOut().add(faEdge);
        this.out.get(faEdge.getSticker()).add(faEdge);
    }

    public List<FAEdge> getOut(String sticker) {
        return out.get(sticker);
    }

    public static List<FAEdge> getLambdaEdgeTargets(NFAState vertex) {
        List<FAEdge> edges = new ArrayList<>();
        List<FAEdge> finalEdges = getLambdaFinalEdges(vertex);
        for (FAEdge faEdge : finalEdges) {
            FAEdge edge = new FAEdge(vertex.id, faEdge.getTargetId(), faEdge.getSticker());
            edges.add(edge);
        }
        return edges;
    }

    private static List<FAEdge> getLambdaFinalEdges(NFAState vertex) {
        List<FAEdge> finalEdges = new ArrayList<>();
        for (FAEdge lambdaEdge : vertex.getOut("lambda")) {
            boolean haveLambda = false;
            NFAState targetVertex = (NFAState) vertex.getAutomata().getVertices().get(lambdaEdge.getTargetId());
            for (FAEdge faEdge : targetVertex.getOut()) {
                if (!faEdge.getSticker().equals("lambda")) {
                    finalEdges.add(faEdge);
                } else haveLambda = true;
            }
            if (haveLambda) finalEdges.addAll(getLambdaEdgeTargets(targetVertex));
        }
        return finalEdges;
    }

    public HashSet<NFAState> equivalent() {
        var vertices = new HashSet<NFAState>();
        getOut("lambda").forEach(faEdge -> {
            var target = getAutomata().getVertex(faEdge.getTargetId());
            if (target != this) {
                vertices.add(target);
                vertices.addAll(target.equivalent());
            }
        });
        return vertices;
    }

}

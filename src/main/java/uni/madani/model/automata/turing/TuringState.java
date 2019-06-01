package uni.madani.model.automata.turing;

import uni.madani.model.automata.State;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;

import java.util.HashMap;

public class TuringState extends State<TuringEdge, Turing> {

    private HashMap<String, TuringEdge> out = new HashMap<>();

    public TuringState(long id, VertexGraphics vertexGraphics,
                       VertexLabelGraphics vertexLabelGraphics, Turing automata,
                       boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics, automata, isStart, isFinal);
    }

    @Override
    public void addOut(TuringEdge edge) {
        super.addOut(edge);
        out.put(edge.getSticker(),edge);
    }

    boolean matches() {
        if (out.size() == 0)
            return isFinal;
        TuringEdge edge = out.get(getAutomata().getTape().getCurrentChar());
        if(edge == null)
            return isFinal;
        getAutomata().getTape().setCurrentChar(edge.getWright());
        if(edge.getMove() == Turing.right) getAutomata().getTape().next();
        else getAutomata().getTape().previous();
        return getAutomata().getVertex(edge.getTargetId()).matches();
    }

}

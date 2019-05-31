package uni.madani.model.automata.pda;

import uni.madani.model.automata.Automata;
import uni.madani.model.graph.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PDA extends Automata<PDAState, PDAEdge> {

    private final List<String> stackAlphabet = new ArrayList<>();

    public PDA(String[] stackAlphabet, String[] alphabet) {
        super(alphabet);
        Collections.addAll(this.stackAlphabet, stackAlphabet);
        this.stackAlphabet.add("$");
    }

    public PDA(Graph graph, String[] stackAlphabet, String[] alphabet) {
        this(stackAlphabet, alphabet);
        graph.getVertices().values().forEach(vertex -> {
            var newState = new PDAState(vertex.getId(), vertex.getVertexGraphics(),
                    vertex.getVertexLabelGraphics(), this,
                    Boolean.parseBoolean(vertex.getValues().getValue("isStart")),
                    Boolean.parseBoolean(vertex.getValues().getValue("isFinal")));
            addVertex(newState);
        });
        graph.getEdges().forEach(edge -> {
            var newEdge = new PDAEdge(edge.getSourceId(), edge.getTargetId(), edge.getWeight(), edge.getEdgeGraphics(),
                    edge.getEdgeLabelGraphics(), edge.getValues().getValue("sticker"),
                    edge.getValues().getValue("pop"), edge.getValues().getValue("push"));
            connect(newEdge);
        });
    }

    @Override
    public void connect(PDAEdge edge) throws IllegalArgumentException {
        if (stackAlphabet.contains(edge.getPop()) && stackAlphabet.contains(edge.getPush()))
            super.connect(edge);
        else throw new IllegalArgumentException("edge stack alphabet not matches with PDA stack alphabet");
    }

    public boolean matches(String string){
        Stack<String> stack = new Stack<>();
        stack.push("$");
        return vertices.get(getStartVertexId()).matches(string.toCharArray(),0, stack);
    }

}

package uni.madani.model.automata;

import uni.madani.model.graph.graph.AbstractGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Automata
        <vertexType extends State<EdgeType, ? extends Automata>, EdgeType extends AutomataEdge>
        extends AbstractGraph<vertexType, EdgeType> {

    protected List<String> alphabet;
    private long startVertexId = -1;

    public Automata(String... alphabet) {
        this.alphabet = new ArrayList<>(Arrays.asList(alphabet));
    }

    @Override
    public void connect(EdgeType edge) throws IllegalArgumentException {
        if (vertices.containsKey(edge.getSourceId()) && vertices.containsKey(edge.getTargetId()) &&
                alphabet.contains(edge.getSticker())) {
            vertices.get(edge.getSourceId()).addOut(edge);
            vertices.get(edge.getTargetId()).addIn(edge);
        } else throw new IllegalArgumentException("sourceId : " + edge.getSourceId() +
                " , targetId : " + edge.getTargetId() + " , sticker :" + edge.getSticker());
    }

    @Override
    public void addVertex(vertexType vertex) {
        super.addVertex(vertex);
        if (vertex.isStart()) {
            startVertexId = vertex.getId();
        }
    }

    public List<String> getAlphabet() {
        return List.copyOf(alphabet);
    }

    public long getStartVertexId() {
        return startVertexId;
    }

}

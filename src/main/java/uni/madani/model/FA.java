package uni.madani.model;

import uni.madani.model.graph.Edge.Edge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;
import uni.madani.model.graph.Vertex.Vertex;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;
import uni.madani.model.graph.graph.Graph;
import uni.madani.model.graph.graphValue.GraphElementValue;

import java.util.List;

public abstract class FA extends Graph {

    protected List<String> alphabet;
    protected long startVertexId = -1;


    public FA(String... alphabet) {
        super(true);
        this.alphabet = List.of(alphabet);
    }

    public FA(Graph graph, String... alphabet) {
        this(alphabet);
        graph.getVertices().values().forEach(this::addVertex);
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    @Override
    public void connect(long sourceId, long targetId, long weight) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Override
    public void connect(Vertex source, Vertex target, long weight) {
        throw new IllegalArgumentException();
    }

    @Override
    public void connect(long sourceId, long targetId, long weight, EdgeGraphics edgeGraphics,
                        EdgeLabelGraphics edgeLabelGraphics, GraphElementValue... values)
            throws IllegalArgumentException {
        Edge edge = new Edge(sourceId, targetId, 1, edgeGraphics, edgeLabelGraphics);
        edge.getValues().addValue(values);
        connect(edge);
        throw new IllegalArgumentException();
    }

    @Override
    public void connect(Edge edge) throws IllegalArgumentException {
        if (edge.getValues().hasValue("sticker")) {
            String value = edge.getValues().getValue("sticker");
            if (alphabet.stream().anyMatch(s -> s.equals(value))) {
                    super.connect(edge);
                    return;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public final void connect(Vertex source, Vertex target, long weight,
                        EdgeGraphics edgeGraphics, EdgeLabelGraphics edgeLabelGraphics, GraphElementValue... values) {
        connect(source.getId(), target.getId(), weight, edgeGraphics, edgeLabelGraphics, values);
    }

    public abstract void connect(long sourceId, long targetId, String sticker) throws IllegalArgumentException;


    @Override
    public final void addVertex(Vertex vertex) {
        String isFinal = vertex.getValues().getValue("isFinal");
        if (isFinal != null && (isFinal.equals("true") || isFinal.equals("false"))) {
            if(vertex.getValues().hasValue("isStart")){
                startVertexId = vertex.getId();
            }
            super.addVertex(vertex);
        } else
            throw new IllegalArgumentException("vertex have no isFinal or have bad value");
    }

    @Override
    public final void addVertex(long id) {
        throw new IllegalArgumentException();
    }

    @Override
    public final void addVertex(double x, double y, long id) {
        throw new IllegalArgumentException();
    }

    @Override
    public final void addVertex(long id, VertexGraphics vertexGraphics, VertexLabelGraphics vertexLabelGraphics) {
        throw new IllegalArgumentException();
    }

    public long getStartVertexId() {
        return startVertexId;
    }

    public void setStartVertexId(long startVertexId) {
        if (startVertexId == -1 && getVertices().containsKey(startVertexId))
            this.startVertexId = startVertexId;
    }

}

package uni.madani.model;

import uni.madani.model.graph.graph.AbstractGraph;

import java.util.List;

public abstract class FA<VertexType extends FAVertex> extends AbstractGraph<VertexType, FAEdge> {

    protected List<String> alphabet;
    protected long startVertexId = -1;
    Class<VertexType> typeClass;

    public FA(String... alphabet) {
        this.alphabet = List.of(alphabet);
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void connect(long sourceId, long targetId, String sticker) throws IllegalArgumentException{
        connect(new FAEdge(sourceId,targetId,sticker));
    }

    @Override
    public void connect(FAEdge edge) throws IllegalArgumentException {
        if (vertices.containsKey(edge.getSourceId()) && vertices.containsKey(edge.getTargetId())) {
            vertices.get(edge.getSourceId()).addOut(edge);
            vertices.get(edge.getTargetId()).addIn(edge);
        }else throw new IllegalArgumentException();
    }

    @Override
    public void addVertex(VertexType vertex) {
        super.addVertex(vertex);
        if(vertex.isStart()){
            startVertexId = vertex.getId();
        }
    }

    public long getStartVertexId() {
        return startVertexId;
    }

}

package uni.madani.model;


import java.util.HashSet;

public class PackVertex<VertexType extends FAVertex> implements Comparable<PackVertex> {

    private final HashSet<VertexType> vertices;

    public PackVertex() {
        vertices = new HashSet<>();
    }

    public HashSet<VertexType> getVertices() {
        return vertices;
    }


    public HashSet<VertexType> getTargetVerticesOf(String sticker) {
        var targets = new HashSet<VertexType>();
        vertices.forEach(vertexType -> {
            vertexType.getOut(sticker).forEach(faEdge -> {
                targets.add((VertexType) vertexType.getFa().getVertices().get(faEdge.getTargetId()));
            });
        });
        return targets;
    }

    public boolean isStart() {
        for (VertexType vertexType : vertices) if (vertexType.isStart()) return true;
        return false;
    }

    public boolean isFinal() {
        for (VertexType vertexType : vertices) if (vertexType.isFinal()) return true;
        return false;
    }

    @Override
    public int compareTo(PackVertex packVertex) {
        if (packVertex.vertices.size() == vertices.size()) {
            for (VertexType vertex : vertices) {
                if (packVertex.vertices.stream().noneMatch(vertex::equals))
                    return -1;
            }
            return 0;
        } else return vertices.size() - packVertex.vertices.size();
    }

    public int compareTo(HashSet<VertexType> vertices){
        if (vertices.size() == this.vertices.size()) {
            for (VertexType vertex : this.vertices) {
                if (vertices.stream().noneMatch(vertex::equals))
                    return -1;
            }
            return 0;
        } else return this.vertices.size() - vertices.size();
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        vertices.forEach(vertexType -> {
            stringBuilder.append("(").append(vertexType.getVertexLabelGraphics().getText()).append(")");
        });
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

}

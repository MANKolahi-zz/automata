package uni.madani.model.automata.fa.utils;


import uni.madani.model.automata.fa.FA;
import uni.madani.model.automata.fa.FAEdge;
import uni.madani.model.automata.fa.FAState;
import uni.madani.model.automata.fa.nfa.NFAState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PackVertex<VertexType extends FAState<VertexType>> implements Comparable<PackVertex<VertexType>> {

    public HashMap<String, HashSet<FAEdge>> getEdges() {
        return edges;
    }

    private HashMap<String, HashSet<FAEdge>> edges = new HashMap<>();
    private final HashSet<VertexType> vertices;
    private long id = -1;

    public FA<VertexType> getFa() {
        return fa;
    }

    private FA<VertexType> fa;

    public PackVertex(long id,FA<VertexType> fa) {
        this(fa);
        this.id = id;
    }

    public PackVertex(FA<VertexType> fa) {
        vertices = new HashSet<>();
        this.fa = fa;
    }

    public HashSet<VertexType> getVertices() {
        return vertices;
    }

    public long getId() {
        return id;
    }

    public boolean isStart() {
        for (VertexType vertexType : vertices) if (vertexType.isStart()) return true;
        return false;
    }

    public boolean isFinal() {
        for (VertexType vertexType : vertices) if (vertexType.isFinal()) return true;
        return false;
    }

    public HashSet<VertexType> getTargetVerticesOf(String sticker) {
        var targets = new HashSet<VertexType>();
        for (VertexType vertexType : vertices) {
            List<FAEdge> out = vertexType.getOut(sticker);
            for (FAEdge faEdge : out) {
                targets.add(vertexType.getAutomata().getVertices().get(faEdge.getTargetId()));
            }
        }
        return targets;
    }

    public HashSet<FAEdge> getOutEdgeOf(String sticker) {
        if (!edges.containsKey(sticker)) {
            var faEdges = new HashSet<FAEdge>();
            getVertices().forEach(vertexType -> {
                faEdges.addAll(vertexType.getOut(sticker));
            });
            return faEdges;
        }
        return edges.get(sticker);
    }

    public static HashSet<NFAState> equivalent(PackVertex<NFAState> packVertex){
        var vertices = new HashSet<NFAState>();
        packVertex.getVertices().forEach(vertex -> {
            vertices.addAll(vertex.equivalent());
        });
        return vertices;
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

    @Override
    public int compareTo(PackVertex<VertexType> packVertex) {
        if (packVertex.vertices.size() == vertices.size()) {
            for (VertexType vertex : vertices) {
                if (packVertex.vertices.stream().noneMatch(vertex::equals))
                    return -1;
            }
            return 0;
        } else return vertices.size() - packVertex.vertices.size();
    }

    public int compareTo(HashSet<VertexType> vertices) {
        if (vertices.size() == this.vertices.size()) {
            for (FAState<VertexType> vertex : this.vertices) {
                if (vertices.stream().noneMatch(vertex::equals))
                    return -1;
            }
            return 0;
        } else return this.vertices.size() - vertices.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        PackVertex<VertexType> packVertex = (PackVertex<VertexType>) obj;
        return this.compareTo(packVertex.getVertices()) == 0;
    }


}

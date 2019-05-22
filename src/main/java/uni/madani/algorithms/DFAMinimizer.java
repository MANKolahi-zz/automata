package uni.madani.algorithms;

import uni.madani.model.DFA.DFA;
import uni.madani.model.DFA.DFAVertex;
import uni.madani.model.FAEdge;
import uni.madani.model.PackVertex;

import java.util.*;

public class DFAMinimizer {

    private final DFA dfa;
    private List<PackVertex<DFAVertex>> groups = new ArrayList<>();

    public DFAMinimizer(DFA dfa) {
        this.dfa = dfa;
    }

    public DFA getMinimizedDFA() {

        removeUnreachableVertex();

        groups.add(new PackVertex<>());
        groups.add(new PackVertex<>());
        dfa.getVertices().values().forEach(vertex -> {
            if (vertex.isFinal()) {
                groups.get(0).getVertices().add(vertex);
            } else
                groups.get(1).getVertices().add(vertex);
        });

        PackVertex[] packVertices;

        boolean parted = true;

        while (parted) {
            parted = false;
            packVertices = groups.toArray(PackVertex[]::new);
            for (PackVertex<DFAVertex> vertices : packVertices) {
                if (vertices.getVertices().size() > 0) {

                    DFAVertex[] group = vertices.getVertices().toArray(DFAVertex[]::new);
                    var mainVertex = group[0];
                    var newGroup = new PackVertex<DFAVertex>();

                    for (int i = 1; i < group.length; i++) {
                        DFAVertex vertex = group[i];
                        if (!equals(mainVertex, vertex)) {
                            newGroup.getVertices().add(vertex);
                            vertices.getVertices().remove(vertex);
                        }
                    }

                    if (newGroup.getVertices().size() > 0) {
                        parted = true;
                        groups.add(newGroup);
                        break;
                    }

                }
            }
        }

        packVertices = groups.toArray(PackVertex[]::new);

        var dfa = new DFA(this.dfa.getAlphabet().toArray(String[]::new));
        var dfaVertices = new ArrayList<DFAVertex>();
        var edges = new ArrayList<FAEdge>();

        for (int i = 0; i < packVertices.length; i++) {
            long targetId = -1L;
            PackVertex packVertex1 = packVertices[i];
            var vertex = new DFAVertex(i, packVertex1.isStart(), packVertex1.isFinal(), dfa);
            vertex.getVertexLabelGraphics().setText(packVertex1.toString());
            dfaVertices.add(vertex);
            for (String alphabet : dfa.getAlphabet()) {
                HashSet<DFAVertex> targetVertices = packVertex1.getTargetVerticesOf(alphabet);
                for (int j = 0; j < packVertices.length; j++) {
                    PackVertex<DFAVertex> packVertex2 = packVertices[j];
                    if (contains(packVertex2, targetVertices)) {
                        targetId = j;
                    }
                }
                if (targetId != -1) {
                    var edge = new FAEdge((long) i, targetId, alphabet);
                    edges.add(edge);
                }
            }
        }
        dfaVertices.forEach(dfa::addVertex);
        edges.forEach(dfa::connect);

        return dfa;
    }

    private boolean contains(PackVertex<DFAVertex> packVertex, HashSet<DFAVertex> vertices) {
        for (DFAVertex dfaVertex : vertices) {
            if (!packVertex.getVertices().contains(dfaVertex)) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(DFAVertex v1, DFAVertex v2) {
        boolean equals = true;
        for (String alphabet : dfa.getAlphabet()) {
            var edge1 = v1.getEdge(alphabet);
            var edge2 = v2.getEdge(alphabet);

            var dfaVertex1 = dfa.getVertices().get(edge1.getTargetId());
            var dfaVertexGroup1 = groups.stream().
                    filter(dfaVertices -> dfaVertices.getVertices().contains(dfaVertex1)).
                    findAny().orElse(null);

            var dfaVertex2 = dfa.getVertices().get(edge2.getTargetId());
            var dfaVertexGroup2 = groups.stream().
                    filter(dfaVertices -> dfaVertices.getVertices().contains(dfaVertex2)).
                    findAny().orElse(null);

            if (dfaVertexGroup1 != dfaVertexGroup2) {
                equals = false;
            }

        }
        return equals;
    }

    private void removeUnreachableVertex() {
        Collection<DFAVertex> vertices = dfa.getVertices().values();
        var continues = true;
        while (continues) {
            continues = false;
            for (DFAVertex vertex : vertices)
                if (vertex.getIn().size() <= 0 && !vertex.isStart()) {
                    dfa.removeVertex(vertex);
                    continues = true;
                    break;
                }
        }
    }

}

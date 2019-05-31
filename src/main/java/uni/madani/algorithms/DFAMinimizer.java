package uni.madani.algorithms;

import uni.madani.model.automata.fa.dfa.DFA;
import uni.madani.model.automata.fa.dfa.DFAState;
import uni.madani.model.automata.fa.FAEdge;
import uni.madani.model.automata.fa.utils.PackVertex;

import java.util.*;

public class DFAMinimizer {

    private final DFA dfa;
    private List<PackVertex<DFAState>> groups = new ArrayList<>();

    public DFAMinimizer(DFA dfa) {
        this.dfa = dfa;
    }

    public DFA getMinimizedDFA() {

        removeUnreachableVertex();

        groups.add(new PackVertex<>(dfa));
        groups.add(new PackVertex<>(dfa));
        dfa.getVertices().values().forEach(vertex -> {
            if (vertex.isFinal()) {
                groups.get(0).getVertices().add(vertex);
            } else
                groups.get(1).getVertices().add(vertex);
        });


        boolean parted = true;

        while (parted) {
            parted = false;

            for (PackVertex<DFAState> vertices : groups) {
                if (vertices.getVertices().size() > 0) {

                    DFAState[] group = vertices.getVertices().toArray(DFAState[]::new);
                    var mainVertex = group[0];
                    var newGroup = new PackVertex<>(dfa);

                    for (int i = 1; i < group.length; i++) {
                        DFAState vertex = group[i];
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

        var dfa = new DFA(this.dfa.getAlphabet().toArray(String[]::new));
        var dfaVertices = new ArrayList<DFAState>();
        var edges = new ArrayList<FAEdge>();

        for (int i = 0; i < groups.size(); i++) {
            long targetId = -1L;
            PackVertex<DFAState> packVertex1 = groups.get(i);
            var vertex = new DFAState(i, packVertex1.isStart(), packVertex1.isFinal(), dfa);
            vertex.getVertexLabelGraphics().setText(packVertex1.toString());
            dfaVertices.add(vertex);
            for (String alphabet : dfa.getAlphabet()) {
                HashSet<DFAState> targetVertices = packVertex1.getTargetVerticesOf(alphabet);
                for (int j = 0; j < groups.size(); j++) {
                    PackVertex<DFAState> packVertex2 = groups.get(i);
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

    private boolean contains(PackVertex<DFAState> packVertex, HashSet<DFAState> vertices) {
        for (DFAState dfaVertex : vertices) {
            if (!packVertex.getVertices().contains(dfaVertex)) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(DFAState v1, DFAState v2) {
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
        Collection<DFAState> vertices = dfa.getVertices().values();
        var continues = true;
        while (continues) {
            continues = false;
            for (DFAState vertex : vertices)
                if (vertex.getIn().size() <= 0 && !vertex.isStart()) {
                    dfa.removeVertex(vertex);
                    continues = true;
                    break;
                }
        }
    }

}

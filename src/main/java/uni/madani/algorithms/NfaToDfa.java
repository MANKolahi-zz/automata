package uni.madani.algorithms;

import uni.madani.model.automata.fa.dfa.DFA;
import uni.madani.model.automata.fa.dfa.DFAState;
import uni.madani.model.automata.fa.FAEdge;
import uni.madani.model.automata.fa.nfa.NFA;
import uni.madani.model.automata.fa.nfa.NFAState;
import uni.madani.model.automata.fa.utils.PackVertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NfaToDfa {

    private static class TableRow {
        TableRow(PackVertex<NFAState> packVertex) {
            this.packVertex = packVertex;
        }

        PackVertex<NFAState> packVertex;
        HashMap<String, PackVertex<NFAState>> edges = new HashMap<>();

        @Override
        public String toString() {
            return packVertex + edges.toString();
        }
    }

    public static DFA nfaToDfa(NFA nfa) {

        removeUnreachableVertex(nfa);

        ArrayList<TableRow> table = new ArrayList<>();
        table.add(new TableRow(getStartPackVertex(nfa)));

        var nfaAlphabet = nfa.getAlphabet().subList(0, nfa.getAlphabet().size() - 1);

        for (int i = 0, id = 1; i < table.size(); i++) {
            TableRow row = table.get(i);
            for (String alphabet : nfaAlphabet) {
                var targetVertices = row.packVertex.getTargetVerticesOf(alphabet);
                if (targetVertices.size() > 0) {
                    var newVertex = new PackVertex<>(id++, nfa);
                    newVertex.getVertices().addAll(targetVertices);
                    var equivalent = PackVertex.equivalent(newVertex);
                    newVertex.getVertices().addAll(equivalent);
                    row.edges.put(alphabet, table.stream()
                            .filter(row1 -> row1.packVertex.compareTo(newVertex) == 0).
                                    map(row1 -> row1.packVertex).findAny().orElseGet(() -> {
                                table.add(new TableRow(newVertex));
                                return newVertex;
                            }));
                }
            }
        }

        table.forEach(System.out::println);

        DFA dfa = new DFA(nfaAlphabet.toArray(String[]::new));

        for (int i = 0, tableSize = table.size(); i < tableSize; i++) {
            TableRow row = table.get(i);
            var packVertex = row.packVertex;
            dfa.addVertex(new DFAState(packVertex.getId(), i == 0, packVertex.isFinal(), dfa));
        }

        table.forEach(row -> {
            row.edges.forEach((s, packVertex) -> {
                dfa.connect(new FAEdge(row.packVertex.getId(), packVertex.getId(), s));
            });
        });

        return dfa;
    }

    private static PackVertex<NFAState> getStartPackVertex(NFA nfa) {
        var startVertex = nfa.getVertices().get(nfa.getStartVertexId());
        var startPackVertex = new PackVertex<>(0, nfa);
        startPackVertex.getVertices().add(startVertex);
        startPackVertex.getVertices().addAll(startVertex.equivalent());
        return startPackVertex;
    }

    private static void removeUnreachableVertex(NFA fa) {
        Collection<NFAState> vertices = fa.getVertices().values();
        boolean hasUnreachable = true;
        while (hasUnreachable) {
            hasUnreachable = false;
            for (NFAState vertex : vertices)
                if (vertex.getIn().size() <= 0 && !vertex.isStart()) {
                    fa.removeVertex(vertex);
                    hasUnreachable = true;
                    break;
                }
        }
    }

}

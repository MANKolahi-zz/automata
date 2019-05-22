package uni.madani.algorithms;

import uni.madani.model.PackVertex;
import uni.madani.model.DFA.DFA;
import uni.madani.model.FA;
import uni.madani.model.NFA.NFA;
import uni.madani.model.graph.Vertex.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NfaToDfa {

    public DFA NfaTODfa(NFA nfa) {
        removeUnreachableVertex(nfa);
        ArrayList<PackVertex> newVertices = new ArrayList<>();



        nfa.getVerticesCollection().forEach(vertex -> {

            PackVertex packVertex = new PackVertex();
//            packVertex.getVertices().add(vertex);
            newVertices.add(packVertex);

            List<String> nfaAlphabet = nfa.getAlphabet();

            for (String alphabet : nfaAlphabet) {

//                ArrayList<Vertex> vertexArrayList = vertex.getOut().stream().
//                        filter(edge -> edge.getValues().getValue("sticker").equals(alphabet)).
//                        map(edge -> nfa.getVertices().get(edge.getTargetId())).
//                        collect(Collectors.toCollection(ArrayList::new));
//
//                PackVertex vertexOut = new PackVertex();
//                vertexOut.getVertices().addAll(vertexArrayList);

                putIfAbsent(newVertices, packVertex);

            }

        });

        DFA dfa = new DFA(nfa.getAlphabet().toArray(String[]::new));


        return null;
    }

    private void putIfAbsent(ArrayList<PackVertex> packVertices, PackVertex packVertex){
        if(packVertices.stream().noneMatch(packVertex1 -> packVertex1.compareTo(packVertex) == 0)){
            packVertices.add(packVertex);
        }
    }


    private void removeUnreachableVertex(FA fa) {
        Collection<Vertex> vertices = fa.getVertices().values();
        boolean hasUnreachable = true;
        while (hasUnreachable) {

            hasUnreachable = false;

            for (Vertex vertex : vertices)
                if (vertex.getIn().size() <= 0) {
                    fa.removeVertex(vertex);
                    hasUnreachable = true;
                    break;
                }
        }
    }

}

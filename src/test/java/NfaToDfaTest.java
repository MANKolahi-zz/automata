import uni.madani.algorithms.NfaToDfa;
import uni.madani.model.automata.fa.nfa.NFA;
import uni.madani.model.graph.graph.Graph;
import uni.madani.persist.filePersist.GMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class NfaToDfaTest {

    public static void main(String[] args) throws IOException {
        Graph graph = GMLParser.getInstance().parsingFromFile(Path.of("NFA1.graph"));
        System.out.println(graph);
        NFA nfa = new NFA(graph, "a", "b");
        System.out.println(NfaToDfa.nfaToDfa(nfa));
    }
}

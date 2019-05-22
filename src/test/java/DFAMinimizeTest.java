import uni.madani.algorithms.DFAMinimizer;
import uni.madani.model.DFA.DFA;
import uni.madani.model.graph.graph.Graph;
import uni.madani.persist.filePersist.GMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class DFAMinimizeTest {

    public static void main(String[] args) throws IOException {
        Graph graph = GMLParser.getInstance().parsingFromFile(Path.of("DFA.graph"));

        DFA dfa = new DFA(graph, "0", "1");

        System.out.println(new DFAMinimizer(dfa).getMinimizedDFA());
    }
}

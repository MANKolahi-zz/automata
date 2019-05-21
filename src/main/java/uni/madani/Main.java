package uni.madani;

import uni.madani.model.DFA;
import uni.madani.model.graph.Graph;
import uni.madani.persist.filePersist.GMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph = GMLParser.getInstance().parsingFromFile(Path.of("graph.graph"));
        System.out.println(graph);
        DFA dfa = new DFA(graph, "0", "1");
        System.out.println(dfa);
    }
}

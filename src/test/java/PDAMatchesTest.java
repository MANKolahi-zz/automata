import uni.madani.model.automata.pda.PDA;
import uni.madani.model.graph.graph.Graph;
import uni.madani.persist.filePersist.GMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class PDAMatchesTest {
    public static void main(String[] args) throws IOException {
        Graph graph2 = GMLParser.getInstance().parsingFromFile(Path.of("PDA.graph"));
        PDA pda = new PDA(graph2,new String[]{"lambda","a","b"}, new String[]{"a","b","lambda"});
        System.out.println(graph2);
        System.out.println(pda);
        System.out.println(pda.matches("abba"));
    }
}

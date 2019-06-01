import uni.madani.model.automata.turing.Turing;
import uni.madani.model.graph.graph.Graph;
import uni.madani.persist.filePersist.GMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class TuringMatchesTest {
    public static void main(String[] args) throws IOException {
        Graph graph3 = GMLParser.getInstance().parsingFromFile(Path.of("automata/Turing.graph"));
        Turing turing = new Turing(graph3,new String[]{"a","b","x","y"});
        System.out.println(graph3);
        System.out.println(turing);
        System.out.println(turing.matches("ab"));
    }
}

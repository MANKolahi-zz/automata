import uni.madani.model.automata.fa.dfa.DFA;
import uni.madani.model.automata.fa.nfa.NFA;
import uni.madani.model.automata.pda.PDA;
import uni.madani.model.automata.turing.Turing;
import uni.madani.model.graph.graph.Graph;
import uni.madani.persist.filePersist.GMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class ModelTest {
    public static void main(String[] args) throws IOException {
        Graph graph = GMLParser.getInstance().parsingFromFile(Path.of("automata/DFA.graph"));
        DFA dfa = new DFA(graph, "0", "1");
        System.out.println(graph);
        System.out.println(dfa);
        Graph graph1 = GMLParser.getInstance().parsingFromFile(Path.of("automata/NFA1.graph"));
        NFA nfa = new NFA(graph1,"a","b");
        System.out.println(graph1);
        System.out.println(nfa);
        Graph graph2 = GMLParser.getInstance().parsingFromFile(Path.of("automata/PDA.graph"));
        PDA pda = new PDA(graph2,new String[]{"lambda","a","b"}, new String[]{"a","b","lambda"});
        System.out.println(graph2);
        System.out.println(pda);
        Graph graph3 = GMLParser.getInstance().parsingFromFile(Path.of("automata/Turing.graph"));
        Turing turing = new Turing(graph3,new String[]{"a","b","x","y"});
        System.out.println(graph3);
        System.out.println(turing);
    }
}

package uni.madani.model.automata.turing;

import uni.madani.model.automata.Automata;
import uni.madani.model.graph.graph.Graph;

public class Turing extends Automata<TuringState, TuringEdge> {

    public static final boolean right = true;
    public static final boolean left = false;
    private Tape tape;

    public Turing(String[] alphabet) {
        super(alphabet);
        super.alphabet.add("blank");
    }

    public Turing(Graph graph, String[] alphabet) {
        this(alphabet);
        graph.getVertices().values().forEach(vertex -> {
            var newState = new TuringState(vertex.getId(), vertex.getVertexGraphics(),
                    vertex.getVertexLabelGraphics(), this,
                    Boolean.parseBoolean(vertex.getValues().getValue("isStart")),
                    Boolean.parseBoolean(vertex.getValues().getValue("isFinal")));
            addVertex(newState);
        });
        graph.getEdges().forEach(edge -> {
            var newEdge = new TuringEdge(edge.getSourceId(), edge.getTargetId(), edge.getWeight(), edge.getEdgeGraphics(),
                    edge.getEdgeLabelGraphics(), edge.getValues().getValue("sticker"),
                    edge.getValues().getValue("wright"), edge.getValues().getValue("move"));
            connect(newEdge);
        });
    }


    @Override
    public void connect(TuringEdge edge) throws IllegalArgumentException {
        if (alphabet.contains(edge.getWright()))
            super.connect(edge);
        else throw new IllegalArgumentException
                ("edge tape alphabet not matches with turing tape alphabet");
    }

    public boolean matches(String string){
        String[] strings = new String[string.toCharArray().length];
        char[] charArray = string.toCharArray();
        for (int i = 0, charArrayLength = charArray.length;
             i < charArrayLength; i++) {
            strings[i] = String.valueOf(charArray[i]);
        }
        tape = new Tape(strings);
        try {
            return getVertex(getStartVertexId()).matches();
        }catch (StackOverflowError error){
            return false;
        }
    }

    public Tape getTape() {
        return tape;
    }
}

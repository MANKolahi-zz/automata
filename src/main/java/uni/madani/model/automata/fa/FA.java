package uni.madani.model.automata.fa;

import uni.madani.model.automata.Automata;

public abstract class FA<VertexType extends FAState<VertexType>> extends Automata<VertexType, FAEdge> {

    public FA(String... alphabet) {
        super(alphabet);
    }

    public void connect(long sourceId, long targetId, String sticker) throws IllegalArgumentException {
        connect(new FAEdge(sourceId, targetId, sticker));
    }


}

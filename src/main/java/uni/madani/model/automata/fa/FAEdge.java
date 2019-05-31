package uni.madani.model.automata.fa;

import uni.madani.model.automata.AutomataEdge;
import uni.madani.model.graph.Edge.EdgeGraphics;
import uni.madani.model.graph.Edge.EdgeLabelGraphics;

public class FAEdge extends AutomataEdge implements Comparable<FAEdge> {

    public FAEdge(long sourceId, long targetId, String sticker) {
        this(sourceId, targetId, 1, null, null, sticker);
    }

    public FAEdge(long sourceId, long targetId, long weight,
                  EdgeGraphics edgeGraphics, EdgeLabelGraphics edgeLabelGraphics, String sticker) {
        super(sourceId, targetId, weight, edgeGraphics, edgeLabelGraphics, sticker);
    }

    @Override
    public String toString() {
        return String.format("edge[ source\t%d target\t%d values[%s] ]",
                sourceId, targetId, values);
    }

    @Override
    public int compareTo(FAEdge faEdge) {
        return getSticker().compareTo(faEdge.getSticker()) &
                Long.compare(sourceId, faEdge.getSourceId()) &
                Long.compare(targetId, faEdge.getTargetId());
    }

}

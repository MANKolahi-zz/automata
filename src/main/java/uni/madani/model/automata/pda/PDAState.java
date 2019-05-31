package uni.madani.model.automata.pda;

import uni.madani.model.automata.State;
import uni.madani.model.graph.Vertex.VertexGraphics;
import uni.madani.model.graph.Vertex.VertexLabelGraphics;

import java.util.*;

public class PDAState extends State<PDAEdge, PDA> {

    private HashMap<String, List<PDAEdge>> out = new HashMap<>();

    public PDAState(long id, VertexGraphics vertexGraphics, VertexLabelGraphics vertexLabelGraphics,
                    PDA pda, boolean isStart, boolean isFinal) {
        super(id, vertexGraphics, vertexLabelGraphics, pda, isStart, isFinal);
        pda.getAlphabet().forEach(s -> out.putIfAbsent(s, new ArrayList<>()));
    }

    @Override
    public void addOut(PDAEdge edge) {
        super.addOut(edge);
        out.get(edge.getSticker()).add(edge);
    }

    boolean matches(char[] chars, int i, Stack<String> stack) {
        if (out.size() == 0)
            return isFinal;
        Stack<String> copyStack = new Stack<>();
        copyStack.addAll(stack);
        for (PDAEdge edge : out.get("lambda")) {
            if (stack.lastElement().equals(edge.getPop())) {
                stack.pop();
                if (!edge.getPush().equals("lambda")) stack.push(edge.getPush());
                if (getAutomata().getVertex(edge.getTargetId()).matches(chars, i, stack)) {
                    return true;
                }
                stack.clear();
                stack.addAll(copyStack);
            } else if (edge.getPop().equals("lambda")) {
                if (!edge.getPush().equals("lambda")) stack.push(edge.getPush());
                if (getAutomata().getVertex(edge.getTargetId()).matches(chars, i, stack)) {
                    return true;
                }
                stack.clear();
                stack.addAll(copyStack);
            }
        }
        if (i != chars.length) {
            String key = String.valueOf(chars[i]);
            for (PDAEdge edge : out.get(key)) {
                if (stack.lastElement().equals(edge.getPop())) {
                    stack.pop();
                    if (!edge.getPush().equals("lambda")) stack.push(edge.getPush());
                    if (getAutomata().getVertex(edge.getTargetId()).matches(chars, i + 1, stack)) {
                        return true;
                    }
                    stack.clear();
                    stack.addAll(copyStack);
                } else if (edge.getPop().equals("lambda")) {
                    if (!edge.getPush().equals("lambda")) stack.push(edge.getPush());
                    if (getAutomata().getVertex(edge.getTargetId()).matches(chars, i + 1, stack)) {
                        return true;
                    }
                    stack.clear();
                    stack.addAll(copyStack);
                }
            }
        }
        return isFinal;
    }

}

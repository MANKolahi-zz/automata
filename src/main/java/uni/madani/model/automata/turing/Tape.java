package uni.madani.model.automata.turing;

import java.util.ArrayList;
import java.util.Arrays;

public class Tape {
    private ArrayList<String> tape = new ArrayList<>();
    private int head;
    public Tape(String[] data){
        for (int i = 0; i < 50; i++) {
            tape.add("blank");
        }
        tape.addAll(Arrays.asList(data));
        for (int i = 0; i < 50; i++) {
            tape.add("blank");
        }
        head = 50;
    }

    public String getCurrentChar(){
        return tape.get(head);
    }

    public void setCurrentChar(String c){
        tape.set(head,c);
    }

    public void next(){
        if(head == tape.size() - 1){
            for (int i = 0; i < 50; i++) {
                tape.add(null);
            }
        }
        head++;
    }

    public void previous(){
        if (head == 0){
            var tapeCopy = tape;
            tape = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                tape.add(null);
            }
            tape.addAll(tapeCopy);
            head = 50;
        }else
            head--;
    }

}

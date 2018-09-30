package data;

import java.util.ArrayList;
import java.util.List;

public class Answer {

    List<Value> values = new ArrayList<>();

    public boolean evaluateAnswerByInput(String input) {
        for(Value value : values) {
            List<String> patterns = value.getInputPattern();
            if(patterns.contains(input)){
                return value.getSelectionType();
            }
        }
        throw new RuntimeException("invalid answer");
    }

    public void addValue(Value value) {
        values.add(value);
    }
}
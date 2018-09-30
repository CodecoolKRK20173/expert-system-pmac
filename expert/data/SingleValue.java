package data;

import java.util.ArrayList;
import java.util.List;

public class SingleValue extends Value {

    private String params;
    private boolean selectionType;

    public SingleValue(String params, boolean selectionType) {
        this.params = params;
        this.selectionType = selectionType;
    }

    public List<String> getInputPattern() {
        List<String> input = new ArrayList<>();
        input.add(params);
        return input;
    }
    
    public boolean getSelectionType() {
        return selectionType;
    }
}
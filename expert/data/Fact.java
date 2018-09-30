package data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fact {

    private String id;
    private String desctription;
    private Map<String, Boolean> factMap = new HashMap<>();

    public Fact(String id, String description) {
        this.id = id;
        this.desctription = description;
    }
    
    public Set<String> getIdSet() {
        return factMap.keySet();
    }

    public void setFactValueById(String id, boolean value) {
        factMap.put(id, value);
    }

    public boolean getValueById(String id) {
        return factMap.get(id);
    }

    public String getDescription() {
        return desctription;
    } 
}
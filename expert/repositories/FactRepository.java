package repositories;

import data.Fact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactRepository {

    private List<Fact> facts = new ArrayList<>();

    public void addFact(Fact fact){
        facts.add(fact);
    }

    public Iterator <Fact> getIterator(){
        return facts.iterator();
    }
}
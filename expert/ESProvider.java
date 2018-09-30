import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import data.Fact;
import data.Question;
import parsers.FactParser;
import parsers.RuleParser;


public class ESProvider {

    private FactParser factParser;
    private RuleParser ruleParser;
    
    private Map<String, Boolean> usersAnswers = new HashMap<>(); 
    private Scanner scanner = new Scanner(System.in);

    public ESProvider(FactParser factParser, RuleParser ruleParser){
        this.factParser = factParser;
        this.ruleParser = ruleParser;
    }

    public void collectAnswers(){
        Iterator <Question> ruleIterator = ruleParser.getRuleRepository().getIterator();
        while(ruleIterator.hasNext()){
            Question question = ruleIterator.next();
            while(true){
                System.out.println(question.getQuestion());
                
                String input = scanner.nextLine().toLowerCase();
                try {
                    boolean evalAnswer = question.getEvaluatedAnswer(input);
                    usersAnswers.put(question.getId(), evalAnswer);
                    break;
                }
                catch (Exception e) {
                    System.out.println(e.getMessage() + ", try again.");
                }
            }
        }
    }

    public boolean getAnswersByQuestion(String questionId){
        return usersAnswers.get(questionId);
    }

    public String evaluate() {
        Iterator <Fact> factIterator = factParser.getFactRepository().getIterator();
        while(factIterator.hasNext()){
            Fact fact = factIterator.next();
            boolean matches = true;
            for (String key : usersAnswers.keySet()) {
                if(!usersAnswers.get(key).equals(fact.getValueById(key))){
                    matches = false;
                    break;
                }
            }
            if(matches == true) { 
                return fact.getDescription();
            }
        }
        return null;
    }

    public String evaluateMore() {
        Iterator <Fact> factIterator = factParser.getFactRepository().getIterator();
        Fact bestMatch = null;
        int bestMatchCount = 0;
        while(factIterator.hasNext()){
            Fact fact = factIterator.next();
        
            int matches = 0;
            for (String key : usersAnswers.keySet()) {
                if(usersAnswers.get(key).equals(fact.getValueById(key))){
                    matches += 1;
                }
            }
            
            if(matches > bestMatchCount) {
                bestMatchCount = matches; 
                bestMatch = fact;
            }
        }
        return bestMatch.getDescription() + " by " + bestMatchCount + " of " + usersAnswers.size() + " questions";
    
    }
}
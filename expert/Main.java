import parsers.FactParser;
import parsers.RuleParser;

public class Main{

    public static void main(String[] args) {
        FactParser factParser = new FactParser("/home/rageoverkill/jabba/expert/expert-system-pmac/expert/xml/facts.xml");
        RuleParser ruleParser = new RuleParser("/home/rageoverkill/jabba/expert/expert-system-pmac/expert/xml/rules.xml");
        ESProvider esProvider = new ESProvider(factParser, ruleParser);
        esProvider.collectAnswers();
        String result = esProvider.evaluate();

        if(result != null)  {
            System.out.println("You should start playing Guild Wars 2 with " + result);
        } else {
            System.out.println("Couldnt match 100% character class");
            String resultMore = esProvider.evaluateMore();
            System.out.println("Best class for you to start playing Guild Wars 2 could be " + resultMore);
        }
    }
}
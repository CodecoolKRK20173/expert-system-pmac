package parsers;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Arrays;

import repositories.RuleRepository;
import data.*;

public class RuleParser extends XMLParser {
    
    RuleRepository ruleRepository;

    public RuleParser(String xmlPath){
        loadXmlDocument(xmlPath);
        ruleRepository = getRuleRepository();
    }
    public RuleRepository getRuleRepository(){
        RuleRepository ruleRepository = new RuleRepository();
        NodeList rules = document.getElementsByTagName("Rule");
        for(int i = 0; i < rules.getLength(); i++) {
            Node rule = rules.item(i);

            Node answer = rule.getChildNodes().item(3);
            Node answerTrue = answer.getChildNodes().item(1);
            Node answerFalse = answer.getChildNodes().item(3);
            Value answerT = getValueFromNode(answerTrue);
            Value answerF = getValueFromNode(answerFalse);

            String questionId = getQuestionIdFromNode(rule);
            String questionString = getQuestionStringFromNode(rule);
            Answer questionAnswer = createAnswer(answerT, answerF);

            Question question = new Question(questionId, questionString, questionAnswer);
            ruleRepository.addQuestion(question);  
        }
        return ruleRepository;
    }

    private Value getValueFromNode(Node answer) {
        String answerName = answer.getChildNodes().item(1).getNodeName();  
        Element a = (Element)answer;
        String selectionValue = a.getAttribute("value"); 
        String value = ((Element)answer.getChildNodes().item(1)).getAttribute("value");
        boolean selectionValueBoolean = false;
        if(selectionValue.equals("true")){
            selectionValueBoolean = true;
        }
        if(answerName.equals("SingleValue")){
            return new SingleValue(value, selectionValueBoolean);
        }
        return new MultipleValue(Arrays.asList(value.split(",")), selectionValueBoolean);
    }

    private String getQuestionIdFromNode(Node rule) {
        Element e = (Element)rule;
        String id = e.getAttribute("id");
        return id;
    }

    private String getQuestionStringFromNode(Node rule) {
        Node question = rule.getChildNodes().item(1); 
        String questionString = question.getTextContent();
        return questionString;
    }

    private Answer createAnswer(Value val1, Value val2) {
        Answer theAnswer = new Answer();
        theAnswer.addValue(val1);
        theAnswer.addValue(val2);
        return theAnswer;
    }
}

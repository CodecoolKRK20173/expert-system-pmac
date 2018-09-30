package parsers;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repositories.FactRepository;
import data.Fact;

public class FactParser extends XMLParser {

    FactRepository factRepository;

    public FactParser(String xmlPath){
        loadXmlDocument(xmlPath);
        factRepository = getFactRepository();
    }

    public FactRepository getFactRepository(){
        FactRepository factRepository = new FactRepository();

        NodeList facts = document.getElementsByTagName("Fact");
        for(int i = 0; i < facts.getLength(); i++) {
            Node fact = facts.item(i);
            
            String id = getFactIdFromNode(fact);
            String descriptionString = getFactDescriptionFromNode(fact);            
            
            Fact theFact = new Fact(id, descriptionString);

            NodeList evals = fact.getChildNodes().item(3).getChildNodes();
            
            for(int j = 1; j < evals.getLength(); j = j+2) {
                Node eval = evals.item(j);
                Element e = (Element)eval;
                String evalId = e.getAttribute("id");
                String evalText = eval.getTextContent();
                boolean evalTextBoolean = false;
                if(evalText.equals("true")){
                    evalTextBoolean = true;
                }
                theFact.setFactValueById(evalId, evalTextBoolean);
            }
            factRepository.addFact(theFact);
        }
        return factRepository;
    }

    private String getFactIdFromNode(Node fact) {
        Element f = (Element)fact;
        String id = f.getAttribute("id");
        return id;
    }

    private String getFactDescriptionFromNode(Node fact) {
        Node description = fact.getChildNodes().item(1); 
        Element d = (Element)description;
        String descriptionString = d.getAttribute("value"); 
        return descriptionString;
    }
}
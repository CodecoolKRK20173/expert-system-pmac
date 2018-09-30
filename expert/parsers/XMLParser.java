package parsers;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public abstract class XMLParser {

    protected Document document;

    public void loadXmlDocument(String xmlPath){
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
            document = builder.parse(new FileInputStream(xmlPath));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();  
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
}


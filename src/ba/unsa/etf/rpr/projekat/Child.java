package ba.unsa.etf.rpr.projekat;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Child extends Person{

    private Parentt parentt = new Parentt();
    Institution institution = new Institution();


    public Child(){
        try{
            final String xmlFilePath = this.getName() + String.valueOf(this.getId()) + ".xml";
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement(this.getName() + "_" + this.getSurename());
            document.appendChild(root);

            // employee element
            Element employee = document.createElement("date");

            root.appendChild(employee);

            // set an attribute to staff element
            Attr attr = document.createAttribute("id");
            attr.setValue("10");
            employee.setAttributeNode(attr);

            //you can also use staff.setAttribute("id", "1") for this

            // firstname element
            Element firstName = document.createElement("timeOfArrival");
            firstName.appendChild(document.createTextNode("James"));
            employee.appendChild(firstName);

            // lastname element
            Element lastname = document.createElement("departuretime");
            lastname.appendChild(document.createTextNode("Harley"));
            employee.appendChild(lastname);

            // email element
            Element email = document.createElement("notes");
            email.appendChild(document.createTextNode("james@example.org"));
            employee.appendChild(email);


            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public Parentt getParentt() {
        return parentt;
    }

    public void setParentt(Parentt parentt) {
        this.parentt = parentt;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}

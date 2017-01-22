package com.dom.java;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DemoDom {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse("sitemap.xml");

			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			/**
			 * Get nodelist with tagname and then list all their items
			 */

			NodeList nList = doc.getElementsByTagName("url");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					System.out.println("Current" + eElement.toString() + " element has "
							+ eElement.getElementsByTagName("loc").item(0).getTextContent()
							+ "location and has priority"
							+ eElement.getElementsByTagName("loc").item(0).getTextContent());
				}
			}

			/**
			 * Add new element in xml
			 */

			Document docWrite = dBuilder.newDocument();
			
			//root element
			Element newElement = docWrite.createElement("root");
			docWrite.appendChild(newElement);

			//comment
			Comment comment = docWrite.createComment("Just my first element a.k.a Root");
			newElement.appendChild(comment);

			//child
			Element childElement = docWrite.createElement("child");
			childElement.setAttribute("name", "mladen");
			newElement.appendChild(childElement);

			//text between tags
			Text text = docWrite.createTextNode("This is random text");
			childElement.appendChild(text);
			
			
			//write to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(docWrite);
			StreamResult result = new StreamResult(new File("newXml.xml"));
			transformer.transform(source, result);
			
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

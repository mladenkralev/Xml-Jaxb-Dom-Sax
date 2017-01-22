package com.sax.java;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class DemoSax {
	public static void main(String[] args) {
		/**
		 * Reading from sitemap.xml
		 */
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("sitemap.xml"));

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {
				/**
				 * If event is XMLStreamConstants.START_ELEMENT, then the reader
				 * just read the opening tag of an XML element. In your example,
				 * this could be <url> or any other tag with inner elements in
				 * it this is the element you found
				 */
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String qName = startElement.getName().getLocalPart();
					if (qName.equalsIgnoreCase("url")) {
						System.out.print("Start Element : url");
					}
					break;
				/**
				 * CHARACTERS refers to text in the document that isn't a
				 * comment or CDATA. In your example, this would be the content
				 * inside any of the loc, lastmod tags. This is the inner chars.
				 * You can get Inner elements via this.
				 */
				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					System.out.println(characters.getData().toString());
					break;
				/**
				 * Conversely, if event is XMLStreamConstants.END_ELEMENT, then
				 * the reader just read the closing tag of an XML element. In
				 * your example, this could be </url> or any other tag.
				 */
				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					System.out.print("End eleements" + endElement.asCharacters());
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

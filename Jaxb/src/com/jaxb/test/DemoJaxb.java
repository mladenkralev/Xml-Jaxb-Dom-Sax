package com.jaxb.test;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Getting context file, list all urls in URLSET and putting element in URLSET
 * and save it in a new sitemap2.xml file
 * 
 * @author Mladen
 *
 */
public class DemoJaxb {
	public static void main(String[] args) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaler = context.createUnmarshaller();
		
		// get object from sitemap.xml and try to cast it 
		Object unmarshal = unmarshaler.unmarshal(new File("sitemap.xml"));
		if (unmarshal instanceof Urlset) {
			System.out.println("It's the right instance ");
			Urlset unmarshalUrsSet = (Urlset) unmarshal;
			
			// list all the Tursl in Urlset
			List<TUrl> list = unmarshalUrsSet.getUrl();
			for (TUrl url : list) {
				System.out.println("Url " + url.getLoc());
			}
			
			// add new Turl in list 
			TUrl url = new TUrl();
			url.setLoc("http://test.com");
			list.add(url);
			
			//adding in list
			Marshaller marshaller = context.createMarshaller();
			//save it in list
			marshaller.marshal(url, new File("sitemap2.xml"));
		}

	}
}

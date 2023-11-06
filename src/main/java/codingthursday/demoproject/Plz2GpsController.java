package codingthursday.demoproject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class Plz2GpsController {
	
	@Value("classpath:AT.txt")
	private Resource resource;
	
	
	private Map<String, List<String>> data = new HashMap<>(); 
	
	/**
	 * Reads the Postalcode GPS data from the file AT.txt
	 * Only containe austrian postal codes and gps cordinates.
	 * @throws IOException
	 */
	@PostConstruct
	public void setup() throws IOException {
		
		Scanner sc =new Scanner(resource.getInputStream());
		sc.useDelimiter("\r");
		while( sc.hasNext() ) {
			 String line = sc.next();
	         String[] result = line.split("\t");
	         for (int i = 0 ; i < result.length ; i = i + 11) {
	        	 if (9+i > result.length) break;
	        	 List<String> coordinates = new ArrayList<>();
		         coordinates.add(result[9+i]);
		         coordinates.add(result[10+i]);
		         data.put(result[1+i], coordinates);	 
	         }   
		}
		sc.close();
	        
	}
	
	@RequestMapping(value="/plz2gps/{plz}", method = RequestMethod.GET)
	public List<String> postleitzahl2gps (@PathVariable("plz") String plz ) {
		
		List<String> result = data.get(plz);
		
		System.out.println("PLZ is "+plz+" GPS coordinate is "+result);
		
		return result;
	}

}

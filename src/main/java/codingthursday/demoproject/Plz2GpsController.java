package codingthursday.demoproject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Plz2GpsController {
	
	private Map<String, List<String>> data = new HashMap<>(); 
	
	@PostConstruct
	public void setup() throws IOException {
		Resource resource = new ClassPathResource("./AT.txt");
		File file  = resource.getFile();
		
		Scanner sc =new Scanner(new FileReader(file));
		sc.useDelimiter("\r");
	        String line = sc.next();
	         String[] result = line.split("\t");
	         for (int i = 0 ; i < result.length ; i = i + 11) {
	        	 List<String> coordinates = new ArrayList<>();
	        	 if (9+i > result.length) return;
		         coordinates.add(result[9+i]);
		         coordinates.add(result[10+i]);
		         data.put(result[1+i], coordinates);	 
	         }         
	}
	
	@RequestMapping(value="/plz2gps/{plz}", method = RequestMethod.GET)
	public List<String> postleitzahl2gps (@PathVariable("plz") String plz ) {
		return data.get(plz);
	}

}

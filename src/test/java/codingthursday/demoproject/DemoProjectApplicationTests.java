package codingthursday.demoproject;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class) // required for JUnit 4 tests
public class DemoProjectApplicationTests {

	
	private RestTemplate template = new RestTemplate();

	@Test
	public void testEndpoint() throws Exception {
		
		String[] gps = template.getForObject("http://localhost:8080/plz2gps/5020", String[].class);
		
		assertNotNull(gps);

	}

}

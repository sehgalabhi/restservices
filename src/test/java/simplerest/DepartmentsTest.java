package simplerest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rest.Department;




public class DepartmentsTest {

	private Client client;
	private WebTarget target;

	private static final String BASE_URI = "http://localhost:8080/rest";

	@Before
	public void setup() {
		client = ClientBuilder.newClient();
		target = client.target(BASE_URI);
	}

	@After
	public void destroy() {

	}

	@Test
	public void testGetDepartments() {
		WebTarget resource = target.path("departments");
		Builder builder = resource.request(MediaType.APPLICATION_JSON);
		Invocation invocation = builder.buildGet();
		
//GenericType<List<Department>> responseType = new GenericType<>(List.class);
		List<Department> response = invocation.invoke(new GenericType<List<Department>>(){});
		
		

	}
	@Test
	public void testDeleteDepartment(){
		WebTarget resource = target.path("departments/10");
		Builder builder = resource.request(MediaType.APPLICATION_JSON);
		
		Invocation invocation = builder.buildDelete();
		String  response = (String) invocation.invoke(new GenericType(String.class));
		System.out.println(response);
		
		
		
		
	}

}

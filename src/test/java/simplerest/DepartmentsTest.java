package simplerest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.Department;

public class DepartmentsTest {

	private static Client client;
	private static WebTarget target;

	private static final String BASE_URI = "http://localhost:8080/rest";

	@BeforeClass
	public static void setup() {
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

		// GenericType<List<Department>> responseType = new
		// GenericType<>(List.class);
		Response response = invocation.invoke();
		System.out.println(response.readEntity(String.class));

	}

	@Test
	public void testGetResponseDepartments() {
		WebTarget resource = target.path("departments/response");
		Builder builder = resource.request(MediaType.APPLICATION_JSON);
		Invocation invocation = builder.buildGet();

		// GenericType<List<Department>> responseType = new
		// GenericType<>(List.class);
		Response response = invocation.invoke();
		System.out.println(response);
		System.out.println(response.readEntity(String.class));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void testGetDepartment() {
		WebTarget resource = target.path("departments/10");
		Builder builder = resource.request(MediaType.APPLICATION_JSON);

		Invocation invocation = builder.buildGet();
		Response response = invocation.invoke();
		System.out.println(response);
		// System.out.println(response.readEntity(String.class));
		Department department = response.readEntity(Department.class);
		System.out.println(department);

	}

	@Test
	public void testCount() {
		WebTarget resource = target.path("departments/count");
		Builder builder = resource.request(MediaType.APPLICATION_JSON);

		Invocation invocation = builder.buildGet();
		Response response = invocation.invoke();
		System.out.println(response);
		System.out.println(response.readEntity(String.class));

	}

	@Test
	public void testAdd() {
		WebTarget resource = target.path("departments");

		Builder builder = resource.request(MediaType.APPLICATION_JSON);
		Department entity = new Department();
		short id = 10;
		entity.setDepartmentId(Short.valueOf(id));
		Invocation invocation = builder.buildPost(Entity.entity(entity, MediaType.APPLICATION_JSON));
		Response response = invocation.invoke();
		System.out.println(response.readEntity(String.class));

	}

	@Test
	public void testDelete() {

		WebTarget resource = target.path("departments");
		short id = 10;
		target.property("id", id);
		Builder builder = resource.request(MediaType.APPLICATION_JSON);

		Invocation invocation = builder.buildDelete();

		Response response = invocation.invoke();
		System.out.println(response.readEntity(String.class));

	}

	@Test
	public void testQueryParamObject() {
		WebTarget resource = target.path("departments").path("query");

		Invocation.Builder invocation = resource.queryParam("depatmentId", 10).request(MediaType.APPLICATION_JSON);

		Department department = invocation.get(Department.class);
		System.out.println(department);

		try {
			JsonGenerator generator = new JsonFactory()
					.createGenerator(new FileOutputStream(new File("json_response_dept.json")));
			generator.writeStartObject();
			generator.writeStringField("deptID", department.getDepartmentId().toString());
			generator.writeStringField("deptName", department.getDepartmentName());
			generator.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testQueryParamString() {
		WebTarget resource = target.path("departments").path("query");

		Invocation.Builder invocation = resource.queryParam("depatmentId", 10).request(MediaType.APPLICATION_JSON);

		String jsonString = invocation.get(String.class);
		System.out.println(jsonString);

		try {
			JsonParser jsonParser = new JsonFactory().createParser(jsonString);
			ObjectMapper mapper = new ObjectMapper();

			while (jsonParser != null && jsonParser.isClosed()) {
				JsonToken jsonToken = jsonParser.nextToken();

				if (jsonToken.equals(JsonToken.START_OBJECT)) {
					Department department = mapper.readValue(jsonParser, Department.class);
					System.out.println(department);

				}
			}

			/*
			 * JsonGenerator generator = new JsonFactory() .createGenerator(new
			 * FileOutputStream(new File("json_response_dept.json")));
			 * generator.writeStartObject();
			 * generator.writeStringField("deptID",
			 * department.getDepartmentId().toString());
			 * generator.writeStringField("deptName",
			 * department.getDepartmentName()); generator.close();
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testFormParam() {
		WebTarget resource = target.path("departments").path("query");

		Form form = new Form();
		form.param("deptId", "10");

		Department department = resource.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED)).readEntity(Department.class);

		System.out.println(department);

	}

}

package com.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("departments")
public class DepartmentRest {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "HR", "HR");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * EntityManagerFactory entityManagerFactory =
		 * Persistence.createEntityManagerFactory("jpa-example"); entityManager
		 * = entityManagerFactory.createEntityManager();
		 */
	}

	private static Connection connection;

	@Path("count")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getTotalDepartments() throws SQLException {
		Statement statement;
		String value = null;
		try {
			statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("Select count(*) from DEPARTMENTS");

			while (resultset.next()) {
				value = resultset.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> allDepartments() {
		List<Department> departments = new ArrayList<>();
		Statement statement;

		try {
			statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("Select * from DEPARTMENTS");

			while (resultset.next()) {
				Department department = new Department();
				department.setDepartmentId(resultset.getShort("DEPARTMENT_ID"));
				department.setDepartmentName(resultset.getString("DEPARTMENT_NAME"));
				department.setLocationId(resultset.getShort("LOCATION_ID"));
				department.setManagerId(resultset.getInt("MANAGER_ID"));
				departments.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departments;

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Department> find(@PathParam("id") short id) {
		List<Department> departments = new ArrayList<>();
		Statement statement;

		try {
			statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("Select * from DEPARTMENTS where DEPARTMENT_ID = " + id);

			while (resultset.next()) {
				Department department = new Department();
				department.setDepartmentId(resultset.getShort("DEPARTMENT_ID"));
				department.setDepartmentName(resultset.getString("DEPARTMENT_NAME"));
				department.setLocationId(resultset.getShort("LOCATION_ID"));
				department.setManagerId(resultset.getInt("MANAGER_ID"));
				departments.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departments;

	}

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createDepartment(Department department) {

	}

	@Path("edit/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void editDepartment(@PathParam("id") short id, Department department) {

	}

	@Path("{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteDepartment(@PathParam("id") short id) {
		return "Got"+ Integer.valueOf(id);
	}
}

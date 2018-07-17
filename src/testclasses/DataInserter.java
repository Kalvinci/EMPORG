package testclasses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * Servlet implementation class servlet1
 */
@WebServlet("/DataInserter")
public class DataInserter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataInserter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	static int numberOfPages = 0;
	List<Employee> employees = new ArrayList<Employee>();
	DriverProg driverProg;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	public DriverProg getDriverProg() throws ClassNotFoundException, SQLException {
		driverProg = new DriverProg();
		return driverProg;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		System.out.println("inside datainserter");
		if(request.getParameter("request").equalsIgnoreCase("department")) {
			System.out.println("datainserter department");
			String dept_name = request.getParameter("name");
			try {
				new DriverProg(new Department(dept_name));
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			response.sendRedirect("department.jsp");
		}
		else if(request.getParameter("request").equalsIgnoreCase("company")) {
			System.out.println("datainserter company");
			String comapany_name = request.getParameter("name");
			String website = request.getParameter("website");
			int employee_count = Integer.parseInt(request.getParameter("count"));
			String street  = request.getParameter("street");
			String state = request.getParameter("state");
			int country = Integer.parseInt(request.getParameter("country"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			Address address = new Address(street, state, country, pin);
			try {
				new DriverProg(new Company(comapany_name,website,employee_count),address);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("company.jsp");
		}
		else if(request.getParameter("request").equalsIgnoreCase("employee")) {
			System.out.println("datainserter employee");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String street  = request.getParameter("street");
			String state = request.getParameter("state");
			int country = Integer.parseInt(request.getParameter("country"));
			int dept_id = Integer.parseInt(request.getParameter("department"));
			int company_id = Integer.parseInt(request.getParameter("company"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			Address address = new Address(street, state, country, pin);
			try {
				new DriverProg(new Employee(firstName, lastName, gender, email, dept_id, company_id),address);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("datainserter employee error");
				e.printStackTrace();
			}
			response.sendRedirect("employee.jsp");
		}
		else if(request.getParameter("request").equalsIgnoreCase("changes")) {
			System.out.println("hey submit changes works");
			int emp_id = Integer.parseInt(request.getParameter("emp_id"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String street  = request.getParameter("street");
			String state = request.getParameter("state");
			System.out.println(firstName+ " " +state + " " +emp_id+" "+request.getParameter("country"));
			int country = Integer.parseInt(request.getParameter("country"));
			int dept_id = Integer.parseInt(request.getParameter("department"));
			int company_id = Integer.parseInt(request.getParameter("company"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			Address address = new Address(street, state, country, pin);
			try {
				new DriverProg(new Employee(emp_id, firstName, lastName, gender, email, dept_id, company_id),address);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("datainserter employee changes error");
				e.printStackTrace();
			}

			response.setContentType("text/plain");
			response.getWriter().write("success");
			
		}
		else if(request.getParameter("request").equalsIgnoreCase("deleteEmployee")) {
			int empId = Integer.parseInt(request.getParameter("empid"));
			try {
				System.out.println("datainserter employee deletion " + empId);
				DriverProg driverProg = new DriverProg();
				driverProg.deleteEmployee(empId);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("datainserter employee deletion error");
				e.printStackTrace();
			}
			response.sendRedirect("employee.jsp");
		}
		else if(request.getParameter("request").equalsIgnoreCase("paginateright")) {
			response.setContentType("application/json");
			int pageno = Integer.parseInt(request.getParameter("currentpage"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			try {
				DriverProg driverProg = new DriverProg();
				employees = driverProg.getEmployees(limit,pageno*limit);
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(employees);
				JsonObject obj = new JsonObject();
				obj.add("objects", element);
				if(pageno >= numberOfPages) {
					pageno = numberOfPages;
					System.out.println("right max " + pageno);
				}
				else {
					pageno += 1;
					System.out.println("right not max " + pageno);
				}
				obj.addProperty("pageno", pageno);
				response.getWriter().write(obj.toString());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("request").equalsIgnoreCase("paginateleft")) {
			response.setContentType("application/json");
			int pageno = Integer.parseInt(request.getParameter("currentpage"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			int offset = (pageno-2)*limit;
			System.out.println("left " + pageno);
			if(pageno <= 1) {
				pageno = 1;
				offset = 0;
				System.out.println("left min " + pageno);
			}
			else {
				pageno -= 1;
				System.out.println("left not min " + pageno);
			}
			try {
				DriverProg driverProg = new DriverProg();
				employees = driverProg.getEmployees(limit,offset);
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(employees);
				JsonObject obj = new JsonObject();
				obj.add("objects", element);
				obj.addProperty("pageno", pageno);
				response.getWriter().write(obj.toString());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("request").equalsIgnoreCase("load")) {
			System.out.println("inside load");
			response.setContentType("application/json");
			int limit = Integer.parseInt(request.getParameter("limit"));
			int offSet = Integer.parseInt(request.getParameter("offset"));
			try {
				DriverProg driverProg = new DriverProg();
				employees = driverProg.getEmployees(limit, offSet);
				int size = driverProg.getEmployeeSize();
				System.out.println("size "  + size);
				numberOfPages = size/2 + (size%2 == 0 ? 0:1);
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(employees);
				System.out.println("number of pages"+ numberOfPages);
				response.getWriter().write(element.toString());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("no match found");
		}
	}
}

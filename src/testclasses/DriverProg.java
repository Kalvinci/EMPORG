package testclasses;
//import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DriverProg{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private static final String user = "kalyan";
	private static final String password = "kat@123";
	private static final String URL = "jdbc:mysql://localhost:3306/employee_mgmt";
	private static Connection con;
	private List<Employee> employees = new ArrayList<Employee>();
	private List<Company> companies = new ArrayList<Company>();
	private List<Department> departments = new ArrayList<Department>();
	private List<Country> countries = new ArrayList<Country>();
	private int addcount = 0;
	
	
	public DriverProg() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(URL, user, password);
	}

	public DriverProg(Department department) throws SQLException, ClassNotFoundException {
		this();
		System.out.println("Reached here too");
		this.insertDepartment(department);
		System.out.println("done here too");
	}

	public DriverProg(Company company, Address address) throws SQLException, ClassNotFoundException {
		this();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select max(id) as id from addresses");
		resultSet.next();
		addcount = resultSet.getInt(1);
		addcount++;
		System.out.println("Reached here too");
		company.setAddress_id(addcount);
		address.setId(addcount);
		this.insertAddress(address);
		this.insertCompany(company);
		System.out.println("done here too");
	}

	public DriverProg(Employee employee, Address address) throws SQLException, ClassNotFoundException {
		this();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select max(id) as id from addresses");
		resultSet.next();
		addcount = resultSet.getInt(1);
		addcount++;
		System.out.println("Reached here too emp");
		employee.setAddress_id(addcount);
		address.setId(addcount);
		this.insertAddress(address);
		if (employee.getId() == 0) {
			this.insertEmployee(employee);
		} else {
			this.updateEmployee(employee);
		}
		System.out.println("done here too emp");
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		DriverProg.con = con;
	}
	
	public int getAddcount() {
		return addcount;
	}

	public void setAddcount(int addcount) {
		this.addcount = addcount;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return URL;
	}
	
	public List<Employee> getEmployees(int limit, int offSet) {
		
		try {
			Statement statement_emp = con.createStatement();
			Statement statement_comp = con.createStatement();
			Statement statement_dept = con.createStatement();
			Statement statement_addr = con.createStatement();
			Statement statement_country = con.createStatement();
			ResultSet resultset  ,result_comp, resultset_country, result_dept, result_addr;
			resultset = statement_emp.executeQuery("select id,firstname,lastname,email_id"
					+ ",gender,department_id,company_id,address_id from employees order by id limit " + offSet + ", " + limit + ";");
			while (resultset.next()) {
				result_comp = statement_comp
						.executeQuery("select id, name, employee_count, website, address_id from companies where id = "
								+ resultset.getInt(7) + ";");
				result_comp.next();
				Company company = (new Company(result_comp.getInt(1), result_comp.getString(2),
						result_comp.getString(4), result_comp.getInt(3), result_comp.getInt(5)));

				result_dept = statement_dept
						.executeQuery("select id, name from departments where id = " + resultset.getInt(6) + ";");
				result_dept.next();
				Department department = (new Department(result_dept.getInt(1), result_dept.getString(2)));

				result_addr = statement_addr.executeQuery(
						"select id, street,state,country,pin from addresses where id = " + resultset.getInt(8) + ";");
				result_addr.next();
				Address address = (new Address(result_addr.getInt(1), result_addr.getString(2),
						result_addr.getString(3), result_addr.getInt(4), result_addr.getInt(5)));

				resultset_country = statement_country
						.executeQuery("select id,name from countries where id = " + result_addr.getInt(4) + ";");
				resultset_country.next();
				Country country = new Country(resultset_country.getInt(1), resultset_country.getString(2));

				employees.add(new Employee(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(5), resultset.getString(4), resultset.getInt(6), resultset.getInt(7),
						resultset.getInt(8), department, company, address, country));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	

	public List<Company> getCompanies() throws SQLException {
		ResultSet result_comp;
		Statement populate_comp = con.createStatement();
		result_comp = populate_comp.executeQuery("select id, name, employee_count, website, address_id from companies order by id");
		while (result_comp.next()) {
			companies.add(new Company(result_comp.getInt(1), result_comp.getString(2), result_comp.getString(4),
					result_comp.getInt(3), result_comp.getInt(5)));
		}
		return companies;
	}
	
	public int getEmployeeSize() throws SQLException {
		Statement statement =  con.createStatement();
		ResultSet resultSet = statement.executeQuery("select count(*) from employees");
		int size = 0;
		while(resultSet.next()) {
			size = resultSet.getInt(1);
		}
		return size;
	}
	
	public List<Department> getDepartments() throws SQLException {
		ResultSet result_dept;
		Statement populate_dept = con.createStatement();
		result_dept = populate_dept.executeQuery("select id, name from departments order by id");
		while (result_dept.next()) {
			departments.add(new Department(result_dept.getInt(1), result_dept.getString(2)));
		}
		return departments;
	} 
	
	public List<Country> getCountries() throws SQLException {
		Statement populate_country = con.createStatement();
		ResultSet resultset_country = populate_country.executeQuery("select id, name from countries order by id");
		while (resultset_country.next()) {
			countries.add(new Country(resultset_country.getInt(1), resultset_country.getString(2)));
		}
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public void setCompanies(List<Company> companies) throws SQLException {
		this.companies = companies;
	}
	
	public void insertAddress(Address address) throws SQLException {
		String command = "insert into addresses (id,street,state,country,pin) values (?,?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setInt(1, address.getId());
		statement.setString(2, address.getStreet().toUpperCase());
		statement.setString(3, address.getState().toUpperCase());
		statement.setInt(4, address.getCountryId());
		statement.setInt(5, address.getPin());
		statement.executeUpdate();
	}

	public void insertDepartment(Department department) throws SQLException {

		String command = "insert into departments (name) values (?)";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, department.getName().toUpperCase());
		statement.executeUpdate();
	}

	public void insertCompany(Company company) throws SQLException {

		String command = "insert into companies (name, website, address_id, employee_count) values (?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, company.getCompany_name().toUpperCase());
		statement.setString(2, company.getWebsite_address());
		statement.setInt(3, company.getAddress_id());
		statement.setInt(4, company.getNumber_of_employees());
		statement.executeUpdate();
	}

	public void insertEmployee(Employee employee) throws SQLException {
		String command = "insert into employees (firstname,lastname,gender,email_id,address_id,department_id,company_id) values (?,?,?,?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, employee.getFirstName().toUpperCase());
		statement.setString(2, employee.getLastName().toUpperCase());
		statement.setString(3, employee.getGender().toUpperCase());
		statement.setString(4, employee.getEmail_Id());
		statement.setInt(5, employee.getAddress_id());
		statement.setInt(6, employee.getDepartment_Id());
		statement.setInt(7, employee.getCompany_id());
		statement.executeUpdate();
	}

	public void updateEmployee(Employee employee) throws SQLException {
		String command = "update employees set firstname = ?,lastname = ?, gender = ?,"
				+ "email_id = ?, address_id = ?, department_id = ?, company_id = ? where id = ?";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, employee.getFirstName().toUpperCase());
		statement.setString(2, employee.getLastName().toUpperCase());
		statement.setString(3, employee.getGender().toUpperCase());
		statement.setString(4, employee.getEmail_Id());
		statement.setInt(5, employee.getAddress_id());
		statement.setInt(6, employee.getDepartment_Id());
		statement.setInt(7, employee.getCompany_id());
		statement.setInt(8, employee.getId());
		statement.executeUpdate();
	}
	
	public void deleteEmployee(int id) throws SQLException {
		Statement s = con.createStatement();
		s.executeUpdate("delete from employees where id = " + id +";");
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

	}

}

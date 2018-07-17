package testclasses;

import java.io.Serializable;

public class Employee implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id, department_Id, address_id, company_id, country_id;
    private String firstName, lastName, email_Id, gender;
    private Address address;
    private Department department;
    private Company company;
    private Country country;
    

	public Employee(String firstName, String lastName, String gender, String email_Id, int dept_id, int comp_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email_Id = email_Id;
        this.department_Id = dept_id;
        this.company_id = comp_id;
    }
    
    
    public Employee(int id, String firstName, String lastName, String gender, String email_Id, int dept_id, int comp_id, int address_id, Department
    		department, Company company, Address address, Country country) {
    	this(firstName, lastName, gender, email_Id, dept_id, comp_id);
    	this.id = id;
        this.department = department;
        this.address = address;
        this.company = company;
        this.country = country;
    }
    
    public Employee(int id, String firstName, String lastName, String gender, String email_Id, int dept_id, int comp_id) {
    	this(firstName, lastName, gender, email_Id, dept_id, comp_id);
        this.id = id;
    }
    
    
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDepartment_Id() {
		return department_Id;
	}


	public void setDepartment_Id(int department_Id) {
		this.department_Id = department_Id;
	}


	public String getEmail_Id() {
		return email_Id;
	}


	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

    public int getAddress_id() {
		return address_id;
	}


	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}


	public int getCompany_id() {
		return company_id;
	}


	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}


	public int getCountry_id() {
		return country_id;
	}


	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public Address getAddress() {
		return address;
	}
    
}

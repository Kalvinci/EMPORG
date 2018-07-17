package testclasses;
public class Company {

    private int id, number_of_employees, address_id;
    private String company_name, website_address; 

    public Company(String name, String website, int count) {
    	this.company_name = name;
    	this.website_address = website;
    	this.number_of_employees = count;
    }

    public Company(int id, String name, String website, int count, int address_id) {
    	this.id = id;
    	this.company_name = name;
    	this.website_address = website;
    	this.number_of_employees = count;
    	this.address_id = address_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_employees() {
        return number_of_employees;
    }

    public void setNumber_of_employees(int number_of_employees) {
        this.number_of_employees = number_of_employees;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getWebsite_address() {
        return website_address;
    }

    public void setWebsite_address(String website_address) {
        this.website_address = website_address;
    }

    public int getAddress_id() {
        return this.address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}

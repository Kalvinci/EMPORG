package testclasses;

public class Address {

    private String street, state;
    private int pin, id, countryId;

    public Address(String street, String state, int country, int pin) {
        this.street = street;
        this.state = state;
        this.countryId = country;
        this.pin = pin;
    }

    public Address(int id, String street, String state, int country, int pin) {
        this.id = id;
    	this.street = street;
        this.state = state;
        this.countryId = country;
        this.pin = pin;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public int getPin() {
        return pin;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
    
}

package testclasses;

public class Department {

    private int id;
    private String name;

    public Department(int id, String name) {
    	this.id = id;
        this.name = name;
    }
    
    public Department(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;

    }

    public String getName() {
        return name;
    }
}

package models;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String address;

    public Person(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public void setFirstName(String fn) { this.firstName = fn; }
    public void setLastName(String ln) { this.lastName = ln; }
    public void setAddress(String ad) { this.address = ad; }

    public String getFullName() { return firstName + " " + lastName; }
    
   public String displayInfo() {
    return "Person: " + getFullName() + " | Address: " + getAddress();
}

}

public class Person {
    private int id;
    private String name;
    private String phone;
    private String adress;
    public Person(int id,String name,String phone,String adress)
    {
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.adress=adress;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void displayInformation()
    {
        System.out.println("Customer Id: "+id);
        System.out.println("Name: "+name);
        System.out.println("Phone: "+phone);
        System.out.println("Address: "+adress);
        System.out.println();
    }
}
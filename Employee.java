class Employee{
    private int id;
    private String name;
    private String department;
    private long salary;
    private String city;
    private long year;
    private boolean isActive;

    public Employee(int id, String name,String department, long salary, String city, long year,boolean isActive){
        this.id=id;
        this.name=name;
        this.department=department;
        this.salary=salary;
        this.city=city;
        this.year=year;
        this.isActive=isActive;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDepartment(){
        return department;
    }

    public long getSalary(){
        return salary;
    }

    public String getCity(){
        return city;
    }

    public long getYear(){
        return year;
    }

    public boolean getIsActive(){
        return isActive;
    }
}
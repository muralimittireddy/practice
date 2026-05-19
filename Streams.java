
// import .Employee;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;

public class Streams{

    public static void main(String[] args){
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Murali", "IT", 75000, "Indore", 2022, true),
            new Employee(2, "Rahul", "HR", 45000, "Pune", 2021, true),
            new Employee(3, "Sneha", "IT", 90000, "Bangalore", 2020, true),
            new Employee(4, "Amit", "FINANCE", 65000, "Indore", 2019, false),
            new Employee(5, "Priya", "HR", 55000, "Hyderabad", 2023, true),
            new Employee(6, "Kiran", "IT", 40000, "Pune", 2024, true),
            new Employee(7, "Neha", "FINANCE", 85000, "Bangalore", 2021, true),
            new Employee(8, "Vikram", "OPS", 35000, "Indore", 2022, false),
            new Employee(9, "Anjali", "OPS", 50000, "Chennai", 2020, true),
            new Employee(10, "Suresh", "IT", 120000, "Hyderabad", 2018, true),
            new Employee(11, "Deepak", "FINANCE", 70000, "Pune", 2023, true),
            new Employee(12, "Meena", "HR", 60000, "Bangalore", 2022, false)
        );

        // List<Employee>  abc = employees.stream().filter(e -> e.getDepartment().equals("FINANCE"))
        // .limit(1).collect(Collectors.toList());

        // boolean abc = employees.stream().anyMatch(e -> e.getDepartment().equals("OPS"));
        // System.out.println(abc);

//         List<Employee> abc = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
// .skip(2).collect(Collectors.toList());
// System.out.println(abc.get(0).getName());

// boolean abc = employees.stream().filter(e -> !e.getIsActive() ).anyMatch(e -> e.getDepartment().equals("HR") );
Map<String,List<Employee>> abc = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingDouble(Employee::getSalary)));
// System.out.println(abc);
        // abc.forEach(e -> System.out.println(e.getName() + " " +e.getDepartment()));

        // List<Employee> ord = employees.stream().sorted(Comparator.comparing(Employee::getDepartment)
        // .thenComparing(Comparator.comparingLong(Employee::getSalary).reversed()))
        // .collect(Collectors.toList());

        // Optional<Employee> maxi = employees.stream().max(Comparator.comparing(Employee::getSalary));
        // .collect(Collectors.toList());

        // maxi.ifPresent(e-> System.out.println(e.getName()+" "+e.getSalary()));

        // List<String> orderByAsc = employees.stream().sorted(Comparator.comparing(Employee::getName).reversed())
        // .map(Employee::getName).collect(Collectors.toList());

        // ord.forEach(e -> System.out.println(e.getDepartment() + ' '+ e.getSalary()));
    // List<Employee> emp = employees.stream()
    //                     // .filter(e -> !e.getIsActive())
    //                     // .filter(e -> e.getCity().equals("Indore"))
    //                     // .filter(e -> e.getDepartment().equals("IT"))
    //                     .collect(Collectors.toList());

    // List<String> names = employees.stream()
    // // .filter( e -> e.getSalary()>60000)
    //                     .filter(e -> e.getDepartment().equals("HR"))
    //                     .map(Employee::getName)
    //                     .collect(Collectors.toList());

    // long count = employees.stream()
    // // .filter( e -> e.getSalary()>60000)
    //                     .filter(e -> e.getDepartment().equals("HR"))
    //                     // .map(Employee::getName)
    //                     .collect(Collectors.counting());

    //                     System.out.println(count);


    // List<String> names =
    //  employees.stream().filter(e -> e.getName().startsWith("S"))
    // .map(Employee::getName).map(String::toUpperCase).forEach(e -> {
    //     System.out.println(e);
    // });
    // .collect(Collectors.toList());

    // names.forEach( e -> {
    //     System.out.println(e);
    // });
    
    // names.forEach( e -> {
    //     System.out.println(e);
    // });

    // emp.forEach(e -> {
    //     System.out.println(e.getName());
    //     // System.out.println(e.getSalary());
    // });
}
}

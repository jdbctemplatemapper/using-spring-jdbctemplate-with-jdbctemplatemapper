# Using Spring JdbcTemplate with JdbcTemplateMapper #
Spring's JdbcTemplate provides data access using JDBC for relational databases. It is an option for applications where using an ORM with their impedance mismatch and nuances may not be a good fit.

JdbcTemplate  abstracts away a lot of the JDBC low level code but using it still remains verbose. The JdbcTemplateMapper library mitigates this verboseness. Its a wrapper around JdbcTemplate. Sprinkle the models with a few annotations and you get single line CRUD and fluent queries for relationships like hasOne, hasMany etc.


## Example code:

```

@Table(name = "employee")
public class Employee {

  @Id(type = IdType.AUTO_INCREMENT)
  private Integer id; // maps to id column in table. The id gets assigned on insert.

  @Column
  private String lastName; // maps to last_name column in table

  @Column
  private String firstName; // maps to first_name column in table

  @Column
  private LocalDateTime startDate; // maps to start_date in table

  @Column
  private Integer departmentId; // maps to department_id in table. Foreign key

  private Department department; // there are no mappings for relationships
  
  ...
}

@Table(name = "department")
public class Department {
  @Id(type = IdType.AUTO_INCREMENT)
  private Integer id; // maps to id column in table. The id gets assigned on insert

  @Column(name = "department_name")
  private String name; // maps to department_name in table

  ...
}

```

## Usage:

```
  @Autowired
  private JdbcTemplateMapper jtm;
 
  ...
 
  Department dept = new Department();
  dept.setName("HR department");
  jtm.insert(dept); // auto assigns id on insert since id configured as auto increment
  
  Employee emp = new Employee();
  emp.setFirstName("John");
  emp.setLastName("Doe");
  emp.setStartDate(LocalDateTime.now());
  emp.setDepartmentId(dept.getId());
  jtm.insert(emp); // auto assigns id on insert since id configured as auto increment
  
  emp = jtm.findById(Employee.class, emp.getId());
  emp.setLastName("Smith");
  jtm.update(emp);
    
  // Querying a hasOne relationship. The query allows for where and orderBy clauses but 
  // for this tutorial not using it. The IDE will help you chain the fluent methods.  
  
  List<Employee> employees = Query.type(Employee.class) // owning class
                                  .hasOne(Department.class) // related class
                                  .joinColumnOwningSide("department_id") // join column (the foreign key) is on owning (employee) table
                                  .populateProperty("department")
                                  .execute(jtm);
                                  
```

## pom.xml entry for jdbctemplatemapper:

```
<dependency>
   <groupId>io.github.jdbctemplatemapper</groupId>
   <artifactId>jdbctemplatemapper</artifactId>
   <version>2.1.2</version>
</dependency>
```

## Spring bean configuration:

```
  @Bean
  public JdbcTemplateMapper jdbcTemplateMapper(JdbcTemplate jdbcTemplate) {
    return  new JdbcTemplateMapper(jdbcTemplate);
  }
```
  
The library provides multiple ways to query relationships and has other features which make developing a little bit easier.

The JdbctemplateMapper github project is at https://github.com/jdbctemplatemapper/jdbctemplatemapper 







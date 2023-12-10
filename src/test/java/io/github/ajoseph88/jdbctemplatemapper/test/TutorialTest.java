package io.github.ajoseph88.jdbctemplatemapper.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import io.github.ajoseph88.jdbctemplatemapper.model.Department;
import io.github.ajoseph88.jdbctemplatemapper.model.Employee;
import io.github.jdbctemplatemapper.core.JdbcTemplateMapper;
import io.github.jdbctemplatemapper.core.Query;
import io.github.jdbctemplatemapper.core.QueryCount;
import io.github.jdbctemplatemapper.core.QueryMerge;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TutorialTest {

  @Autowired
  private JdbcTemplateMapper jtm;

  @Test
  public void tutorial_test() {
    // @formatter:off
    
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
    
    // updateProperties() updates only the specified properties passed as arguments.
    emp.setFirstName("Joe");
    jtm.updateProperties(emp, "firstName"); // will issue an SQL update only for firstName
    

    // query the employee hasOne department relationship
    List<Employee> employees =
        Query.type(Employee.class) // type class
             .hasOne(Department.class)
             .joinColumnTypeSide("department_id") // join column is on type side table employee 
             .populateProperty("department")
             .execute(jtm);

    assertTrue(employees.size() > 0);
    assertTrue("HR department".equals(employees.get(0).getDepartment().getName()));

    // query the department hasMany employee relationship
    List<Department> departments =
        Query.type(Department.class) // type class
             .hasMany(Employee.class) 
             .joinColumnManySide("department_id") // join column is on many side table employee
             .populateProperty("employees")
             .where("department.department_name like ?", "HR%")
             .orderBy("employee.last_name")
             .execute(jtm);

    assertTrue(departments.size() > 0);
    assertTrue(departments.get(0).getEmployees().size() > 0);
    assertTrue("Joe".equals(departments.get(0).getEmployees().get(0).getFirstName()));

    // Paginated query for departments
    departments = 
        Query.type(Department.class)
             .where("department_name like ?", "HR%")
             .orderBy("department_name")
             .limitOffsetClause("LIMIT 10 OFFSET 0") // MySQL syntax. Different for other databases
             .execute(jtm);

    // QueryMerge will issue an SQL 'IN' clause with department ids and populate the employees
    // for the corresponding departments
    QueryMerge.type(Department.class)
              .hasMany(Employee.class)
              .joinColumnManySide("department_id") 
              .populateProperty("employees")
              .execute(jtm, departments); // merges employees to their corresponding department

    assertTrue(departments.get(0).getEmployees().size() > 0);

    // To get total count of records
    Integer count =
        QueryCount.type(Department.class)
                  .where("department_name like ?", "HR%")
                  .execute(jtm);

    assertTrue(count > 0);

    // @formatter:on
  }
}

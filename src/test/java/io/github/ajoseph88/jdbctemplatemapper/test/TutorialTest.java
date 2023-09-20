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

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TutorialTest {

  @Autowired
  private JdbcTemplateMapper jtm;

  @Test
  public void employee_test() {
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
    
    // query the employee hasOne department relationship
    // Query.class has methods for where and orderBy clauses. Not using them for this tutorial.
    List<Employee> employees = 
        Query.type(Employee.class)
             .hasOne(Department.class)
             .joinColumnOwningSide("department_id") // join column (the foreign key) is on owning side table employee
             .populateProperty("department")
             .execute(jtm);
   
    assertTrue(employees.size() > 0);
    assertTrue("HR department".equals(employees.get(0).getDepartment().getName()));
    
    
    // query the department hasMany employee relationship
    List<Department> departments = 
        Query.type(Department.class)
             .hasMany(Employee.class)
             .joinColumnManySide("department_id") // join column (the foreign key) is on many side table employee
             .populateProperty("employees")
             .where("department.department_name like ?", "HR%")
             .orderBy("employee.last_name")
             .execute(jtm);
    
    assertTrue(departments.size() > 0);
    assertTrue(departments.get(0).getEmployees().size() > 0);
    assertTrue("John".equals(departments.get(0).getEmployees().get(0).getFirstName()));

  }
}

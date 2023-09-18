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
    
    List<Employee> employees = Query.type(Employee.class)
        .hasOne(Department.class)
        .joinColumnOwningSide("department_id") // join column (the foreign key) is on employee table
        .populateProperty("department")
        .execute(jtm);
   
    assertTrue(employees.size() > 0);
    assertTrue("HR department".equals(employees.get(0).getDepartment().getName()));
    
  }
}
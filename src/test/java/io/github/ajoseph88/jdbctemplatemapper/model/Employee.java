package io.github.ajoseph88.jdbctemplatemapper.model;

import java.time.LocalDateTime;
import io.github.jdbctemplatemapper.annotation.Column;
import io.github.jdbctemplatemapper.annotation.Id;
import io.github.jdbctemplatemapper.annotation.IdType;
import io.github.jdbctemplatemapper.annotation.Table;

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

  private Department department; // there are no mapping for relationships

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
}

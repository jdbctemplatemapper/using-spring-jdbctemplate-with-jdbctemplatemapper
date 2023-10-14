package io.github.ajoseph88.jdbctemplatemapper.model;

import java.util.ArrayList;
import java.util.List;
import io.github.jdbctemplatemapper.annotation.Column;
import io.github.jdbctemplatemapper.annotation.Id;
import io.github.jdbctemplatemapper.annotation.IdType;
import io.github.jdbctemplatemapper.annotation.Table;

@Table(name = "department")
public class Department {
  @Id(type = IdType.AUTO_INCREMENT)
  private Integer id; // maps to id column in table. The id gets assigned on insert

  @Column(name = "department_name")
  private String name; // maps to department_name in table

  private List<Employee> employees = new ArrayList<>(); // there are no mappings for relationships

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

}

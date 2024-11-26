package co.com.scotiabank.backendtest.domain.model;

import java.time.LocalDate;

/**
 * Model representing the position of an Employee.
 */
public class PositionEmployee {

    private Long id;

    private Long employeeId;

    private String positionTitle;

    private String email;

    private Double salary;

    private LocalDate positionDate;

    private String status;

    public PositionEmployee() {
    }

    public PositionEmployee(Long id, Long employeeId, String positionTitle, String email, Double salary, LocalDate positionDate, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.positionTitle = positionTitle;
        this.email = email;
        this.salary = salary;
        this.positionDate = positionDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(LocalDate positionDate) {
        this.positionDate = positionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

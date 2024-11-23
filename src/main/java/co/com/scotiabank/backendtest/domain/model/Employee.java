package co.com.scotiabank.backendtest.domain.model;

import java.time.LocalDate;

/**
 * Model representing an Employee
 */
public class Employee {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private LocalDate arrivalDate;

    private String locationCity;

    private String address;

    private LocalDate birthDate;

    private LocalDate hireDate;

    private Double telephone;

    private String status;

    public Employee() {
    }

    public Employee(Long id, String firstName, String middleName, String lastName, LocalDate arrivalDate, String locationCity, String address, LocalDate birthDate, LocalDate hireDate, Double telephone, String status) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.arrivalDate = arrivalDate;
        this.locationCity = locationCity;
        this.address = address;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.telephone = telephone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getTelephone() {
        return telephone;
    }

    public void setTelephone(Double telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

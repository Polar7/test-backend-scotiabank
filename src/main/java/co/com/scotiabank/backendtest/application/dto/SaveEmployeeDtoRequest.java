package co.com.scotiabank.backendtest.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Dto for endpoint to save a new employee
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmployeeDtoRequest {

    @NotEmpty(message = "FirstName cannot be empty")
    private String firstName;

    @NotEmpty(message = "MiddleName cannot be empty")
    private String middleName;

    @NotEmpty(message = "LastName cannot be empty")
    private String lastName;

    @NotEmpty(message = "The locationCity cannot be empty")
    private String locationCity;

    @NotEmpty(message = "The address cannot be empty")
    private String address;

    @NotNull(message = "BirthDate cannot be null")
    private LocalDate birthDate;

    @NotNull(message = "HireDate cannot be null")
    private LocalDate hireDate;

    @NotNull(message = "Telephone cannot be null")
    private Double telephone;
}

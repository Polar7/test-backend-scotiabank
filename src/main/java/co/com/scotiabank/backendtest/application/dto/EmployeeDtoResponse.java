package co.com.scotiabank.backendtest.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDtoResponse {

    private String firstName;

    private String lastName;

    private String positionTitle;

    private LocalDate arrivalDate;

    private String status;
}

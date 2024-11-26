package co.com.scotiabank.backendtest.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Dto for endpoint to save a new PositionEmployee
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePositionEmployeeDtoRequest {

    private Long employeeId;

    private String positionTitle;

    private String email;

    private Double salary;

    private LocalDate positionDate;
}

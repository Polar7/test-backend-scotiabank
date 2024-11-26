package co.com.scotiabank.backendtest.infrastructure.entrypoint.rest;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GetEmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.dto.UpdateEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.service.IEmployeeServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Employee
 */
@Tag(name = "EmployeeController", description = "Controller for the rest service 'Employee'")
@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeServicePort employeeService;

    /**
     * Get all the employees
     * @return ResponseEntity with employee list
     */
    @Operation(summary = "Get all the employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All employees returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class)))),
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * Get the details of an employee
     * @param employeeId Employee id to search for
     * @return ResponseEntity with Details found
     */
    @Operation(summary = "Get the details of an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEmployeeDetailsDtoResponse.class))),
    })
    @GetMapping(path = "{employeeId}")
    public ResponseEntity<GetEmployeeDetailsDtoResponse> getEmployeeDetails(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeDetails(employeeId));
    }

    /**
     * Save a new employee
     * @param newEmployee New employee to save
     * @return ResponseEntity with confirmation of operation
     */
    @Operation(summary = "Save a new employee")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericDtoResponse.class))),
    })
    public ResponseEntity<GenericDtoResponse> saveNewEmployee(@Valid @RequestBody SaveEmployeeDtoRequest newEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveNewEmployee(newEmployee));
    }

    /**
     * Update the details of an employee and his position
     * @param updateEmployee Dto of employee to update
     * @return ResponseEntity with dto with confirmation of operation
     */
    @Operation(summary = "Update the details of an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateEmployeeDtoRequest.class))),
    })
    @PutMapping
    public ResponseEntity<GenericDtoResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeDtoRequest updateEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(updateEmployee));
    }

}

package co.com.scotiabank.backendtest.infrastructure.entrypoint.rest;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.service.IEmployeeServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Employee
 */
@Tag(name = "EmployeeController", description = "Controlador para el servicio 'Empleado'")
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

}

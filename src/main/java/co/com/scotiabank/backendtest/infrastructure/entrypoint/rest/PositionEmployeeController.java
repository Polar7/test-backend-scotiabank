package co.com.scotiabank.backendtest.infrastructure.entrypoint.rest;

import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.service.IPositionEmployeeServicePort;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for PositionEmployee
 */
@Tag(name = "PositionEmployeeController", description = "Controller for the rest service 'Empleado'")
@RestController
@RequestMapping("position-employee")
@RequiredArgsConstructor
public class PositionEmployeeController {

    private final IPositionEmployeeServicePort positionEmployeeService;

    /**
     * Get all the positions of the employees
     * @return ResponseEntity with Positions list found
     */
    @Operation(summary = "Get all the positions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All positions returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PositionEmployee.class)))),
    })
    @GetMapping
    public ResponseEntity<List<PositionEmployee>> getAllPositionEmployee() {
        return ResponseEntity.ok(positionEmployeeService.getAllPositionEmployee());
    }

    /**
     * Save a new position for an employee
     * @param newPositionEmployee New position to add
     * @return ResponseEntity with dto with confirmation of operation
     */
    @Operation(summary = "Save a new position of an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Position created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericDtoResponse.class))),
    })
    @PostMapping
    public ResponseEntity<GenericDtoResponse> saveNewPositionEmployee(@RequestBody SavePositionEmployeeDtoRequest newPositionEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionEmployeeService.savePositionEmployee(newPositionEmployee));
    }
}

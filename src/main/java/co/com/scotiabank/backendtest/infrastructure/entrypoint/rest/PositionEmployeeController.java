package co.com.scotiabank.backendtest.infrastructure.entrypoint.rest;

import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.service.IPositionEmployeeServicePort;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
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

    @GetMapping
    public ResponseEntity<List<PositionEmployee>> getAllPositionEmployee() {
        return ResponseEntity.ok(positionEmployeeService.getAllPositionEmployee());
    }

    @PostMapping
    public ResponseEntity<GenericDtoResponse> saveNewPositionEmployee(@RequestBody SavePositionEmployeeDtoRequest newPositionEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionEmployeeService.savePositionEmployee(newPositionEmployee));
    }
}

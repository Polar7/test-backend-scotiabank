package co.com.scotiabank.backendtest.infrastructure.configuration;

import co.com.scotiabank.backendtest.domain.persistance.IEmployeePersistencePort;
import co.com.scotiabank.backendtest.domain.usecase.EmployeeUsecaseAdapter;
import co.com.scotiabank.backendtest.domain.usecase.IEmployeeUsecasePort;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.adapter.EmployeeJpaAdapter;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.mapper.IEmployeeEntityMapper;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository.IEmployeeCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IEmployeeCrudRepository employeeCrudRepository;

    private final IEmployeeEntityMapper employeeEntityMapper;

    @Bean
    public IEmployeePersistencePort employeePersistencePort() {
        return new EmployeeJpaAdapter(employeeCrudRepository, employeeEntityMapper);
    }

    @Bean
    public IEmployeeUsecasePort employeeUsecase() {
        return new EmployeeUsecaseAdapter(employeePersistencePort());
    }
}

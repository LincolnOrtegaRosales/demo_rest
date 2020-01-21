package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.CompanyService;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (!employee.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return employee.get();
    }

    @PostMapping
    public ResponseEntity<Employee> employees(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<Employee>(employeeService.save(employee), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> employees(@RequestBody Employee employee, @PathVariable Long id) {
        Optional<Employee> updatedEmployee = employeeService.getEmployee(id);
        if (!updatedEmployee.isPresent()) return ResponseEntity.notFound().build();
        updatedEmployee.get().setNombre(employee.getNombre());
        updatedEmployee.get().setSueldoNeto(employee.getSueldoNeto());
        updatedEmployee.get().setEdad(employee.getEdad());
        updatedEmployee.get().setCorreo(employee.getCorreo());
        updatedEmployee.get().setEstadoCivil(employee.getEstadoCivil());
        updatedEmployee.get().setCompany(employee.getCompany());

        return new ResponseEntity<Employee>(employeeService.save(updatedEmployee.get()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> employees(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (!employee.isPresent()) return ResponseEntity.notFound().build();
        employeeService.delete(employee.get());
        return ResponseEntity.noContent().build();
    }
}

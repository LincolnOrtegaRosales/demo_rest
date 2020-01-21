package com.example.demo.controllers;

import com.example.demo.models.Company;
import com.example.demo.models.Employee;
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
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}/products")
    public List<Employee> findAllByCompany(@PathVariable Long id) {
        return companyService.findAllByCompany(id);
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompany(id);

        if (!company.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return company.get();
    }

    @PostMapping
    public ResponseEntity<Company> insert(@RequestBody Company company) {
        return new ResponseEntity<Company>(companyService.save(company), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@RequestBody Company company, @PathVariable Long id) {
        Optional<Company> updatedCategory = companyService.getCompany(id);

        if (!updatedCategory.isPresent()) return ResponseEntity.notFound().build();

        updatedCategory.get().setRazSocial(company.getRazSocial());
        updatedCategory.get().setRuc(company.getRuc());

        return new ResponseEntity<Company>(companyService.save(updatedCategory.get()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> delete(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompany(id);
        if (!company.isPresent()) return ResponseEntity.notFound().build();
        companyService.delete(company.get());
        return ResponseEntity.noContent().build();
    }
}

package com.example.demo.services;

import com.example.demo.models.Company;
import com.example.demo.models.Employee;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public List<Employee> findAllByCompany(Long id){
        return companyRepository.getOne(id).getEmployees();
    }

    public Optional<Company> getCompany(Long id){
        return companyRepository.findById(id);
    }

    public Company save(Company company){
        return companyRepository.save(company);
    }

    public boolean delete(Company company){
        boolean status = getCompany(company.getId()).isPresent();
        if (status == true) {
            companyRepository.delete(company);
        }
        return status;
    }
}

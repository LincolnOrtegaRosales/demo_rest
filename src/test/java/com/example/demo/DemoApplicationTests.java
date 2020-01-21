package com.example.demo;

import com.example.demo.models.Company;
import com.example.demo.models.Employee;
import com.example.demo.services.CompanyService;
import com.example.demo.services.EmployeeService;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyService companyService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void enviarCorreo(){
		employeeService.enviarCorreo(Long.valueOf(1));
	}

	@Test
	public void registrarEmpleado(){
		Employee employee= new Employee();

		Faker faker = new Faker();
        employee.setDni(String.valueOf(faker.number().numberBetween(1,10)));
        employee.setCorreo("lincoln.ortega@gmail.com");
        employee.setEdad(faker.number().numberBetween(1,30));
        employee.setSueldoNeto(faker.number().numberBetween(1000,5000));
        employee.setNombre(faker.name().firstName());
        employee.setEstadoCivil(faker.name().username());
        //employee.setCompany(employee.getCompany());

		Assert.assertNotNull(employeeService.save(employee));
	}

	@Test
	public void registerCompany(){

		Faker faker = new Faker();
		Company company = new Company();

        company.setRazSocial(faker.name().firstName());
        company.setRuc(String.valueOf(Math.random()*10));

        Assert.assertNotNull(companyService.save(company));
	}

}

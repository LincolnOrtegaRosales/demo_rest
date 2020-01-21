package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.utils.ServicioCorreo;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ServicioCorreo servicioCorreo;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Long id){
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public boolean delete(Employee employee){
        boolean status = getEmployee(employee.getId()).isPresent();
        if (status == true) {
            employeeRepository.delete(employee);
        }
        return status;
    }

    public void enviarCorreo(Long id){
        Optional<Employee> employee = getEmployee(id);

        if(employee.isPresent()){
            String mensaje="";

            mensaje +="Hi, " + employee.get().getNombre().toUpperCase();
            mensaje +="\n";
            mensaje +="This is a test message.";
            mensaje +="\n";

            servicioCorreo.enviarCorreo("no-reply@gmail.com",employee.get().getCorreo(),"Prueba de Correo",mensaje);
        }

    }


}

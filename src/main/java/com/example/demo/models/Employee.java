package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "tb_empleado")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String dni;

    @NotNull
    private String nombre;

    @NotNull
    private int edad;
    private String estadoCivil;

    @Email
    private String correo;
    private double sueldoNeto;

    @ManyToOne(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "empresa_id")
    private Company company;
}

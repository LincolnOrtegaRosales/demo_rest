package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "tb_empresa")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String ruc;

    @NotNull
    private String razSocial;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade ={CascadeType.MERGE,CascadeType.REFRESH})
    private List<Employee> employees;

}

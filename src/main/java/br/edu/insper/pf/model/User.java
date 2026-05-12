package br.edu.insper.pf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Papel papel;

    @ManyToMany(mappedBy = "usuarios")
    private List<Curso> cursos = new ArrayList<>();
}
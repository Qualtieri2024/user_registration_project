package com.exemplo.userregistration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String dataNascimento;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private String status;

    private LocalDateTime dataCriacao;
    private String usuarioCriacao;
    private LocalDateTime dataAtualizacao;
    private String usuarioAtualizacao;
    private LocalDateTime dataRemocao;
    private String usuarioRemocao;

}

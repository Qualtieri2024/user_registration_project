package com.exemplo.userregistration.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Endereco {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}

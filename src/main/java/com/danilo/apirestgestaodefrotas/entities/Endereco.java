package com.danilo.apirestgestaodefrotas.entities;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Data
@Embeddable
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    private String logadouro;
    private String bairro;
    private String cep;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private EnumUF uf;

}

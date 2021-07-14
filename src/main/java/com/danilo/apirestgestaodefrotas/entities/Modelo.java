package com.danilo.apirestgestaodefrotas.entities;

import lombok.Data;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Data
@Embeddable
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeModelo;
    private String descricao;

}


package com.danilo.apirestgestaodefrotas.entities;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Data
@Embeddable
public class Cnh implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cnhNumero;
    private String cnhVencimento;
    @Enumerated(EnumType.STRING)
    private EnumCnhCategoria cnhCategoria;

}

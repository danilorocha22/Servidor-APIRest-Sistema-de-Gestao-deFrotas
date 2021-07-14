package com.danilo.apirestgestaodefrotas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Data
@Entity
@Table(name = "tb_condutor")
public class Condutor implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID do Condutor gerado automáticamente pelo Banco de Dados")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ApiModelProperty(notes = "Nome do Condutor")
    @NotNull
    private String nome;

    @ApiModelProperty(notes = "Sobrenome do Condutor")
    @NotNull
    private String sobrenome;

    @ApiModelProperty(notes = "CPF do Condutor")
    @NotNull
    private String cpf;

    @ApiModelProperty(notes = "RG do Condutor")
    @NotNull
    private String rg;

    @ApiModelProperty(notes = "Data de nascimento do Condutor")
    @NotNull
    private String data_nasc;

    @ApiModelProperty(notes = "Numero da CNH do Condutor")
    @Embedded
    private Cnh cnh;

    @ApiModelProperty(notes = "Endereço do Condutor")
    @Embedded
    private Endereco endereco;

    @ApiModelProperty(notes = "Lista de Ordens de Trafego do Condutor")
    @JsonIgnore
    @OneToMany(mappedBy = "condutor")
    private List<OrdemTrafego> ordemTrafego;

    @ApiModelProperty(notes = "Sexo do Condutor")
    @Column(name="genero")
    @Enumerated(EnumType.STRING)
    private EnumGenero genero;

}

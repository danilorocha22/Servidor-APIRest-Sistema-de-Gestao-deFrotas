package com.danilo.apirestgestaodefrotas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Data
@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID do Veículo gerado automáticamente pelo Banco de Dados.")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ApiModelProperty(notes = "Número da placa do Veículo.")
    @NotNull
    private String num_placa;

    @ApiModelProperty(notes = "Ano de fabricação do Veículo.")
    @NotNull
    private String ano_fab;

    @ApiModelProperty(notes = "Quilometragem do Veículo.")
    @Null
    private float quilometragem;

    @ApiModelProperty(notes = "Marca do Veículo.")
    @Embedded
    private Marca marca;

    @ApiModelProperty(notes = "Modelo do Veículo.")
    @Embedded
    private Modelo modelo;

    @ApiModelProperty(notes = "Estade conservaçao do Veículo.")
    @NotNull
    @Column(name="est_conservacao")
    @Enumerated(EnumType.STRING)
    private EnumEstadoConservacao estadoConservacao;

    @ApiModelProperty(notes = "Categoria do Veículo.")
    @NotNull
    @Column(name="categoria")
    @Enumerated(EnumType.STRING)
    private EnumCarroCategoria categoria;

    //Interromper a associação cíclica
    @JsonIgnore
    @ApiModelProperty(notes = "Lista de Ordens de Trafego do Veículo.")
    @OneToMany(mappedBy = "veiculo")
    private List<OrdemTrafego> ordensTrafego;

}

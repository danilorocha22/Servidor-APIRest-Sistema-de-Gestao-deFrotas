package com.danilo.apirestgestaodefrotas.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Data
@Entity
@Table(name = "tb_ordemTrafego")
public class OrdemTrafego implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID da Ordem de Trafego gerado automáticamente pelo Banco de Dados.")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ApiModelProperty(notes = "Origem da viagem.")
    @NotNull
    private String origem;

    @ApiModelProperty(notes = "Destino da viagem.")
    @NotNull
    private String destino;

    @ApiModelProperty(notes = "Data de partida da viagem.")
    @NotNull
    private String dataViagem;

    @ApiModelProperty(notes = "Data de retorno da viagem.")
    @NotNull
    private String dataRetorno;

    @ApiModelProperty(notes = "Quilometragem da viagem.")
    @NotNull
    private float distanciaPercorrida;

    @ApiModelProperty(notes = "Status da viagem (aguardando, iniciado, finalizado, cancelado).")
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EnumStatusOrdemTrafego status;

    @ApiModelProperty(notes = "ID do Veiculo.")
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @ApiModelProperty(notes = "ID do Condutor.")
    @ManyToOne
    @JoinColumn(name = "id_condutor")
    private Condutor condutor;

}

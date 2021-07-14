package com.danilo.apirestgestaodefrotas.repositories;

import com.danilo.apirestgestaodefrotas.entities.EnumEstadoConservacao;
import com.danilo.apirestgestaodefrotas.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    //busca veiculo pela marca
    @Query("select v from Veiculo v where v.marca.nomeMarca = :marca")
    List<Veiculo> buscaVeiculoPelaMarca(String marca);

    //busca veiculo por modelo
    @Query("select v from Veiculo v where v.modelo.nomeModelo like ?1")
    List<Veiculo> buscaVeiculoPeloModelo(String modelo);

    //busca veiculo pelo estado de conservação
    @Query("select v from Veiculo v where v.estadoConservacao= :conservacao")
    List<Veiculo> buscaVeiculoPelaConservacao(EnumEstadoConservacao conservacao);

    //buscar veículo pela quilometragem
    @Query("select v from Veiculo v where v.quilometragem >= :menor and v.quilometragem <= :maior")
    List<Veiculo> buscaVeiculoPelaQuilometragem(@Param("menor") float menor, @Param("maior") float maior);


}

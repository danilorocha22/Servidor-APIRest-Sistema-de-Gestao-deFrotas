package com.danilo.apirestgestaodefrotas.repositories;

import com.danilo.apirestgestaodefrotas.entities.OrdemTrafego;
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
public interface OrdemTrafegoRepository extends JpaRepository<OrdemTrafego, Long> {

    //buscar ordem de trafego pelo trajeto
    @Query("select ot from OrdemTrafego ot where ot.origem like :origem% and ot.destino like :destino%")
    List<OrdemTrafego> buscaOrdemTrafegoPeloTrajeto(@Param("origem") String origem, @Param("destino") String destino);

    //buscar ordem de trafego pela data
    @Query("select ot from OrdemTrafego ot where ot.dataViagem like :data_inicio and ot.dataRetorno like :data_fim")
    List<OrdemTrafego> buscaOrdemTrafegoPelaData(@Param("data_inicio") String data_inicio, @Param("data_fim") String data_fim);

    //buscar ordem de trafego pela quilometragem
    @Query("select ot from OrdemTrafego ot where ot.distanciaPercorrida >= :menor and ot.distanciaPercorrida <= :maior")
    List<OrdemTrafego> buscaOrdemTrafegoPelaQuilometragem(@Param("menor") float menor, @Param("maior") float maior);

    //buscar ordem de trafego pelo id do veículo
    @Query("select ot from OrdemTrafego ot where ot.veiculo.id = :id ")
    List<OrdemTrafego> buscaOrdemTrafegoPeloIdVeiculo(@Param("id") Long id);

    //buscar ordem de trafego pelo id do condutor
    @Query("select ot from OrdemTrafego ot where ot.condutor.id = :id ")
    List<OrdemTrafego> buscaOrdemTrafegoPeloIdCondutor(@Param("id") Long id);

}

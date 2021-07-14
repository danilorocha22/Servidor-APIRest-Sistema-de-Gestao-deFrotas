package com.danilo.apirestgestaodefrotas.repositories;

import com.danilo.apirestgestaodefrotas.entities.Condutor;
import com.danilo.apirestgestaodefrotas.entities.EnumCnhCategoria;
import com.danilo.apirestgestaodefrotas.entities.EnumGenero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {

    //busca condutor pelo nome
    @Query("select c from Condutor c where c.nome like ?1")
    List<Condutor> buscaCondutorPeloNome(String nome);

    //busca condutor pelo número da cnh
    @Query("select c from Condutor c where c.cnh.cnhNumero = :cnh")
    Condutor buscaCondutorPelaCnh(String cnh);

    //busca condutores pela categoria da CNH
    @Query("select c from Condutor c where c.cnh.cnhCategoria = :cat")
    List<Condutor> buscaCondutoresPelaCatCNH(EnumCnhCategoria cat);

    //busca condutor pelo cpf
    @Query("select c from Condutor c where c.cpf = :cpf")
    Condutor buscaCondutorPeloCpf(String cpf);

    //busca condutor pelo genero
    @Query("select c from Condutor c where c.genero = :genero")
    List<Condutor> buscaCondutoresPeloGenero(EnumGenero genero);



    
}

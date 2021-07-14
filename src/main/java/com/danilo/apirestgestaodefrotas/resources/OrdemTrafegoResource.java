package com.danilo.apirestgestaodefrotas.resources;

import com.danilo.apirestgestaodefrotas.entities.OrdemTrafego;
import com.danilo.apirestgestaodefrotas.repositories.OrdemTrafegoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author danilo
 */
@SuppressWarnings("all")
@ApiResponses({
        @ApiResponse(code = 400, message = "Requisição ruim!"),
        @ApiResponse(code = 401, message = "Não autorizado!"),
        @ApiResponse(code = 403, message = "Proibido!"),
        @ApiResponse(code = 404, message = "Não encontrado!"),
        @ApiResponse(code = 500, message = "Erro no Servidor Interno!")
})
@RestController
@RequestMapping("ordenstrafego")
public class OrdemTrafegoResource {

    @Autowired
    OrdemTrafegoRepository ordemTrafegoRepository;

    /********************
     * CRUD
     ********************/
    @ApiOperation(value = "Lista todas as Ordens de Tráfego.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping
    public ResponseEntity<List<OrdemTrafego>> buscaTodasOrdensTrafego() {
        return new ResponseEntity<>(ordemTrafegoRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma única Ordem de Tráfego.")
    @ApiResponses({@ApiResponse(code = 200, message = "Ordem de Tráfego encontrada!")})
    @GetMapping("/{id}")
    public ResponseEntity<OrdemTrafego> buscaOrdemTrafego(@PathVariable Long id) {
        return new ResponseEntity<>(ordemTrafegoRepository.findById(id).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria uma Ordem de Tráfego.")
    @ApiResponses({@ApiResponse(code = 201, message = "Ordem de Tráfego criada com sucesso!")})
    @PostMapping
    public ResponseEntity<OrdemTrafego> saveOrdemTrafego(@RequestBody @Valid OrdemTrafego ordemTrafego) {
        return new ResponseEntity<>(ordemTrafegoRepository.save(ordemTrafego), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove uma única Ordem de Tráfego.")
    @ApiResponses({@ApiResponse(code = 204, message = "Ordem de Tráfego removida com sucesso!")})
    @DeleteMapping("/unico")
    public ResponseEntity<OrdemTrafego> deletaOrdemTrafego(@RequestBody @Valid OrdemTrafego ordemTrafego) {
        ordemTrafegoRepository.delete(ordemTrafego);
        return new ResponseEntity(ordemTrafegoRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove uma lista de Ordens de Tráfego.")
    @ApiResponses({@ApiResponse(code = 204, message = "Ordens de Lista de Tráfego removidas com sucesso!")})
    @DeleteMapping
    public ResponseEntity<OrdemTrafego> deletaListCondutores(@RequestBody @Valid List<OrdemTrafego> ordensTrafego) {
        ordemTrafegoRepository.deleteInBatch(ordensTrafego);
        return new ResponseEntity(ordemTrafegoRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza os dados de uma Ordem de Tráfego.")
    @ApiResponses({@ApiResponse(code = 201, message = "Ordem de Tráfego atualizada com sucesso!")})
    @PutMapping
    public ResponseEntity<OrdemTrafego> atualizaOrdemTrafego(@RequestBody @Valid OrdemTrafego ordemTrafego) {
        return new ResponseEntity<>(ordemTrafegoRepository.saveAndFlush(ordemTrafego), HttpStatus.CREATED);
    }

    /****************************
     * CONSULTAS CUSTOMIZADAS
     ****************************/
    @ApiOperation(value = "Lista todas as Ordens de Trafego pelo trajego da viagem.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/trajeto/{origem}/{destino}")
    public ResponseEntity<List<OrdemTrafego>> pesquisaOrdTrafTrajeto(
            @PathVariable String origem,
            @PathVariable String destino) {
        return new ResponseEntity(ordemTrafegoRepository.buscaOrdemTrafegoPeloTrajeto(
                origem, destino), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista todas as Ordens de Trafego pela data da viagem.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/data/{inicio}/{fim}")
    public ResponseEntity<List<OrdemTrafego>> pesquisaOrdTrafData(
            @PathVariable String inicio,
            @PathVariable String fim) {
        return new ResponseEntity(ordemTrafegoRepository.buscaOrdemTrafegoPelaData(inicio, fim), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista todas as Ordens de Trafego pela quilometragem da viagem.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/km/{menor}/{maior}")
    public ResponseEntity<List<OrdemTrafego>> pesquisaOrdTrafKm(
            @PathVariable float menor,
            @PathVariable float maior) {
        return new ResponseEntity(ordemTrafegoRepository.buscaOrdemTrafegoPelaQuilometragem(
                menor, maior), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista todas as Ordens de Trafego de um determinado Veículo.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/veiduloid/{id}")
    public ResponseEntity<List<OrdemTrafego>> pesquisaOrdTrafPeloIdVeiculo(@PathVariable Long id) {
        return new ResponseEntity(ordemTrafegoRepository.buscaOrdemTrafegoPeloIdVeiculo(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista todas as Ordens de Trafego de um determinado Condutor.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/condutorid/{id}")
    public ResponseEntity<List<OrdemTrafego>> pesquisaOrdTrafPeloIdCondutor(@PathVariable Long id) {
        return new ResponseEntity(ordemTrafegoRepository.buscaOrdemTrafegoPeloIdCondutor(id), HttpStatus.OK);
    }

}

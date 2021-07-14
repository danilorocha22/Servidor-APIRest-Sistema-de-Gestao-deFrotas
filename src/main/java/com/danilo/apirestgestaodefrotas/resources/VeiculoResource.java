package com.danilo.apirestgestaodefrotas.resources;

import com.danilo.apirestgestaodefrotas.entities.EnumEstadoConservacao;
import com.danilo.apirestgestaodefrotas.entities.Veiculo;
import com.danilo.apirestgestaodefrotas.repositories.VeiculoRepository;
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
@RequestMapping("veiculos")
public class VeiculoResource {

    @Autowired
    VeiculoRepository veiculoRepository;

    /********************
     * CRUD
     ********************/
    @ApiOperation(value = "Retorna uma lista de Veículos.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping
    public ResponseEntity<List<Veiculo>> buscaTodosVeiculos() {
        return new ResponseEntity<>(veiculoRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um único Veículo.")
    @ApiResponses({@ApiResponse(code = 200, message = "Veículo encontrado!")})
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscaVeiculo(@PathVariable Long id)  {
        return new ResponseEntity<>(veiculoRepository.findById(id).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria um Veiculo.")
    @ApiResponses({@ApiResponse(code = 201, message = "Veículo criado com sucesso!")})
    @PostMapping
    public ResponseEntity<Veiculo> salvaVeiculo(@RequestBody @Valid Veiculo veiculo) {
        return new ResponseEntity<>(veiculoRepository.save(veiculo), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove um único Veículo.")
    @ApiResponses({@ApiResponse(code = 204, message = "Veículo removido com sucesso!")})
    @DeleteMapping("/unico")
    public ResponseEntity<Veiculo> deletaVeiculo(@RequestBody @Valid Veiculo veiculo) {
        veiculoRepository.delete(veiculo);
        return new ResponseEntity(veiculoRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove uma lista de Veículos.")
    @ApiResponses({@ApiResponse(code = 204, message = "Veículos removidos com sucesso!")})
    @DeleteMapping
    public ResponseEntity<Veiculo> deletaListVeiculos(@RequestBody @Valid List<Veiculo> veiculos) {
        veiculoRepository.deleteInBatch(veiculos);
        return new ResponseEntity(veiculoRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza os dados de um Veiculo.")
    @ApiResponses({@ApiResponse(code = 201, message = "Veículo atualizado com sucesso!")})
    @PutMapping
    public ResponseEntity<Veiculo> atualizaVeiculo(@RequestBody @Valid Veiculo veiculo) {
        return new ResponseEntity<>(veiculoRepository.saveAndFlush(veiculo), HttpStatus.CREATED);
    }

    /****************************
     * CONSULTAS CUSTOMIZADAS
     ****************************/
    @ApiOperation(value = "Retorna uma lista de Veiculos pela marca.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Veiculo>> pesquisaMarca(@PathVariable String marca) {
        return new ResponseEntity(veiculoRepository.buscaVeiculoPelaMarca(marca), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de Veiculos pelo modelo.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<Veiculo>> pesquisaModelo(@PathVariable String modelo) {
        return new ResponseEntity(veiculoRepository.buscaVeiculoPeloModelo(modelo), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de Veiculos pelo estado de conservaçao (novo, usado ou velho).")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/estado/{conservacao}")
    public ResponseEntity<List<Veiculo>> pesquisaEstConservacao(@PathVariable String conservacao) {
        return new ResponseEntity(veiculoRepository.buscaVeiculoPelaConservacao(
                EnumEstadoConservacao.valueOf(conservacao)), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de Veiculos pelo intervalo da quilometragem.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/km/{menor}/{maior}")
    public ResponseEntity<List<Veiculo>> pesquisaGenero(@PathVariable float menor, @PathVariable float maior) {
        return new ResponseEntity(veiculoRepository.buscaVeiculoPelaQuilometragem(
               menor, maior), HttpStatus.OK);
    }

}

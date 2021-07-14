package com.danilo.apirestgestaodefrotas.resources;

import com.danilo.apirestgestaodefrotas.entities.Condutor;
import com.danilo.apirestgestaodefrotas.entities.EnumCnhCategoria;
import com.danilo.apirestgestaodefrotas.entities.EnumGenero;
import com.danilo.apirestgestaodefrotas.repositories.CondutorRepository;
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
@RequestMapping("condutores")
public class CondutorResource {

    @Autowired
    CondutorRepository condutorRepository;

    /********************
     * CRUD
     ********************/
    @ApiOperation(value = "Retorna uma lista de Condutores.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping
    public ResponseEntity<List<Condutor>> buscaTodosCondutores() {
        return new ResponseEntity<>(condutorRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um único Condutor.")
    @ApiResponses({@ApiResponse(code = 200, message = "Condutor encontrado!")})
    @GetMapping("/{id}")
    public ResponseEntity<Condutor> buscaCondutor(@PathVariable Long id) {
        return new ResponseEntity<>(condutorRepository.findById(id).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria um Condutor.")
    @ApiResponses({@ApiResponse(code = 201, message = "Condutor criado com sucesso!")})
    @PostMapping
    public ResponseEntity<Condutor> salvaCondutor(@RequestBody @Valid Condutor condutor) {
        return new ResponseEntity<>(condutorRepository.save(condutor), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove um único Condutor.")
    @ApiResponses({@ApiResponse(code = 204, message = "Condutor removido com sucesso!")})
    @DeleteMapping("/unico")
    public ResponseEntity<Condutor>  deletaCondutor(@RequestBody @Valid Condutor condutor) {
        condutorRepository.delete(condutor);
        return new ResponseEntity(condutorRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove uma lista de Condutores.")
    @ApiResponses({@ApiResponse(code = 204, message = "Condutores removidos com sucesso!")})
    @DeleteMapping
    public ResponseEntity<Condutor> deletaListCondutores(@RequestBody @Valid List<Condutor> condutores) {
        condutorRepository.deleteInBatch(condutores);
        return new ResponseEntity(condutorRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza os dados de um Condutor.")
    @ApiResponses({@ApiResponse(code = 201, message = "Condutor atualizado com sucesso!")})
    @PutMapping
    public ResponseEntity<Condutor> atualizaCondutor(@RequestBody @Valid Condutor condutor) {
        return new ResponseEntity<>(condutorRepository.saveAndFlush(condutor), HttpStatus.CREATED);
    }

    /****************************
     * CONSULTAS CUSTOMIZADAS
     ****************************/
    @ApiOperation(value = "Retorna um Condutor pelo número da CNH.")
    @ApiResponses({@ApiResponse(code = 200, message = "Condutor encontrado!")})
    @GetMapping("/cnh/{cnh}")
    public ResponseEntity<Condutor> pesquisaCnh(@PathVariable String cnh) {
        return new ResponseEntity(condutorRepository.buscaCondutorPelaCnh(cnh), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de Condutores pela categoia da CNH.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/cnhcat/{cat}")
    public ResponseEntity<List<Condutor>> pesquisaCnhCat(@PathVariable String cat) {
        return new ResponseEntity(condutorRepository.buscaCondutoresPelaCatCNH(EnumCnhCategoria.valueOf(cat)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um Condutor pelo número do CPF.")
    @ApiResponses({@ApiResponse(code = 200, message = "Condutor encontrado!")})
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Condutor> pesquisaCpf(@PathVariable String cpf) {
        return new ResponseEntity(condutorRepository.buscaCondutorPeloCpf(cpf), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de Condutores pelo nome.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Condutor>> pesquisaNome(@PathVariable String nome) {
        return new ResponseEntity(condutorRepository.buscaCondutorPeloNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de Condutores pelo sexo.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista gerada com sucesso!")})
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Condutor>> pesquisaGenero(@PathVariable String genero) {
        return new ResponseEntity(condutorRepository.buscaCondutoresPeloGenero(EnumGenero.valueOf(genero)),
                   HttpStatus.OK);
    }

}
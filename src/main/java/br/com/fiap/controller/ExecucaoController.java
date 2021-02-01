package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.business.ExecucaoBusiness;
import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;
import br.com.fiap.repository.ExecucaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/execucao")
public class ExecucaoController {

	@Autowired
	public ExecucaoRepository repository;
	
	@Autowired
	public ExecucaoBusiness execucaoBusiness;
	
	private AcaoModel acao;

	// FIND ALL
	@GetMapping()
	@ApiOperation(value = "Retorna uma lista de execucoes")
	public ResponseEntity<List<ExecucaoModel>> findAll() {

		List<ExecucaoModel> execucoes = repository.findAll();
		return ResponseEntity.ok(execucoes);
	}

	// FIND BY ID
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna uma execucao a partir do identificador")
	public ResponseEntity<ExecucaoModel> findById(@PathVariable("id") long id) {

		ExecucaoModel execucao = repository.findById(id).get();
		return ResponseEntity.ok(execucao);
	}

	// SAVE
	@PostMapping()
	@ApiOperation(value = "Salva uma nova execucao")
	public ResponseEntity save(@RequestBody @Valid ExecucaoModel execucaoModel) throws ResponseBusinessException{
			
		ExecucaoModel execucao = execucaoBusiness.applyBusiness(execucaoModel);
		
			repository.save(execucaoModel);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(execucao.getIdExecucao()).toUri();
			
			return ResponseEntity.created(location).build();
		

		
	}

	// UPDATE
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza uma execucao a partir do identificador")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody @Valid ExecucaoModel execucaoModel) throws ResponseBusinessException{
		
		ExecucaoModel execucao = execucaoBusiness.applyBusiness(execucaoModel);
		
		execucaoModel.setIdExecucao(id);
		repository.save(execucaoModel);

		return ResponseEntity.ok().build();
	}

	// DELETE
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui uma execucao")
	public ResponseEntity deleteById(@PathVariable("id") long id) {

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}

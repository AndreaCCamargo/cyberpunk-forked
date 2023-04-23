package br.com.fiap.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ExecucaoControllerIntegrationTeste {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas execucoes e retornar status 200")
	public void shouldListAllexecutions() throws Exception {
		
		mvc.perform(get("/execucao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve retornar uma execucao pelo ID e com status 200")
	public void shouldFindExecutionById() throws Exception {
		
		mvc.perform(get("/execucao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"idExecucao\":1,\"data_execucao\":\"03/12/2077 17:07:00\",\"acao\":{\"idAcao\":1,\"nome\":\"Falar\",\"descricao\":\"Aperte L1 e L2 para falar\",\"ativo\":\"true\"}}"));
	}
	
	@Test
	@DisplayName("Deve salvar uma execucao com ativo 'true', retornar status 201 e Location no Header")
	public void shouldSaveExecution() throws Exception{
		mvc.perform(post("/execucao")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"acao\":{\"idAcao\":1},\"data_execucao\":\"17/03/8989 10:08:09\"}"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}
	
	@Test
	@DisplayName("Nao deve salvar uma execucao com ativo 'false', retornar status 404 e Location no Header")
	public void shouldNotSaveExecution() throws Exception{
		mvc.perform(post("/execucao")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"acao\":{\"idAcao\":3},\"data_execucao\":\"17/03/8989 10:08:09\"}"))
				.andDo(print())
				.andExpect(status().isBadRequest());
				
	}
	
	@Test
	@DisplayName("Deve atualizar uma execucao pelo id e retornar status 200")
	public void shouldUpdateExecution() throws Exception {
		
		mvc.perform(put("/execucao/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"acao\":{\"idAcao\":1},\"data_execucao\":\"10/10/1010 10:08:09\"}"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma execucao pelo id e retornar status 204")
	public void shouldDeleteExecutionById() throws Exception {
		
		mvc.perform(delete("/execucao/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	
}

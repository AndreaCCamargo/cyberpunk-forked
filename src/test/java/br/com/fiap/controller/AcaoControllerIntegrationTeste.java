package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AcaoControllerIntegrationTeste {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas acoes e retornar status 200")
	public void shouldListAllactions() throws Exception {
		
		mvc.perform(get("/acao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve retornar uma acao pelo ID e com status 200")
	public void shouldFindActionById() throws Exception {
		
		mvc.perform(get("/acao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"idAcao\":1,\"nome\":\"Falar\",\"descricao\":\"Aperte L1 e L2 para falar\",\"ativo\":\"true\"}"));
	}
	
	@Test
	@DisplayName("Deve salvar uma acao, retornar status 201 e Location no Header")
	public void shouldSaveAction() throws Exception{
		mvc.perform(post("/acao")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"COMER\",\"descricao\":\"APERTE L2 + SETA PRA ESQUERDA PARA COMER\",\"ativo\":\"true\"}"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}
	
	@Test
	@DisplayName("Deve atualizar uma acao pelo id e retornar status 200")
	public void shouldUpdateAction() throws Exception {
		
		mvc.perform(put("/acao/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Roboooo Editado\",\"descricao\":\"Robo descricao\",\"ativo\":\"false\"}"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma acao pelo id e retornar status 204")
	public void shouldDeleteActionById() throws Exception {
		
		mvc.perform(delete("/acao/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
}

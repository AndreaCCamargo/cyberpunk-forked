package br.com.fiap.business;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;

@SpringBootTest
public class ExecucaoBusinessTest {
	
	@InjectMocks
	public ExecucaoBusiness execucaoBusiness;
	
	@Mock
	public AcaoRepository acaoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testVerifyAtivoAcaoWithTrue() throws ResponseBusinessException{
		
		
		//GIVEN 
		AcaoModel acao = new AcaoModel(1, null, null, true);
		
		//WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));
		execucaoBusiness.verifyAtivoAcao(acao);
			
	}
	
	@Test(expected = ResponseBusinessException.class)
	public void testVerifyAtivoAcaoWithFalse() throws ResponseBusinessException{
		
		
		//GIVEN 
		AcaoModel acao = new AcaoModel(1, null, null, false);
		
		//WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));
		execucaoBusiness.verifyAtivoAcao(acao);
			
	}
	
	@Test
	public void testApplyBusiness() throws ResponseBusinessException {
		
		//GIVEN
		AcaoModel acao = new AcaoModel(1, "Execucao Teste", "Descricao Teste", true);
		ExecucaoModel execucao = new ExecucaoModel(1, "TO_DATE(nvl('','01/01/2001 11:11:11'),'DD/MM/YYYY HH24:MI:SS')", acao);
		ExecucaoModel expected = new ExecucaoModel(1, "TO_DATE(nvl('','01/01/2001 11:11:11'),'DD/MM/YYYY HH24:MI:SS')", acao);
		
		//WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));
		ExecucaoModel actual = execucaoBusiness.applyBusiness(execucao);
		
		//THEN
		assertEquals(expected.toString(), actual.toString());
		
		
	}
	
	

}

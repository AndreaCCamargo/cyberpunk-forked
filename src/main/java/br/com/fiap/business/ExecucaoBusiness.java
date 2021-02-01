package br.com.fiap.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;
import br.com.fiap.repository.ExecucaoRepository;

@Component
public class ExecucaoBusiness {
	
	@Value("${rest.exception.business.ativo-invalido}")
	private String ativoInvalido;
	
	@Autowired
	public AcaoRepository acaoRepository;
	
	public ExecucaoModel applyBusiness (ExecucaoModel execucao) throws ResponseBusinessException {
		
		verifyAtivoAcao(execucao.getAcao());
		
		return execucao;
	}
	
	void verifyAtivoAcao(AcaoModel acao) throws ResponseBusinessException{
		
		AcaoModel acaoModel = acaoRepository.findById(acao.getIdAcao()).get();
		
		if(acaoModel.isAtivo() == false) {
			throw new ResponseBusinessException(ativoInvalido);
		}
		
	}
	
}

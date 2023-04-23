package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_EXECUCAO")
public class ExecucaoModel {
	
	private long idExecucao;
	private String data_execucao;
	private AcaoModel acao;
	
	public ExecucaoModel() {}

	public ExecucaoModel(long idExecucao, String data_execucao, AcaoModel acao) {
		super();
		this.idExecucao = idExecucao;
		this.data_execucao = data_execucao;
		this.acao = acao;
	}

	@Id
	@Column(name = "ID_EXECUCAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUCAO_SEQ")
	@SequenceGenerator(name = "EXECUCAO_SEQ", sequenceName = "EXECUCAO_SEQ", allocationSize = 1)
	public long getIdExecucao() {
		return idExecucao;
	}

	public void setIdExecucao(long idExecucao) {
		this.idExecucao = idExecucao;
	}
	
	@Column(name = "DATA_EXECUCAO")
	@NotNull(message = "Data e hora obrigatoria")
	public String getData_execucao() {
		return data_execucao;
	}

	public void setData_execucao(String data_execucao) {
		this.data_execucao = data_execucao;
	}
	
	@ManyToOne()
	@JoinColumn(name="ID_ACAO", nullable = false)
	public AcaoModel getAcao() {
		return acao;
	}

	public void setAcao(AcaoModel acao) {
		this.acao = acao;
	}

	

	
	
	
	
	
}

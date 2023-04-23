package br.com.fiap.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

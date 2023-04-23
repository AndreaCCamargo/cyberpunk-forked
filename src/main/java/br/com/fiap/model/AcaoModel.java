package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "TB_ACAO")
public class AcaoModel {
	
	private long idAcao;
	private String nome;
	private String descricao;
	private boolean ativo;
	private List<ExecucaoModel> execucoes;
	
	public AcaoModel() {}

	public AcaoModel(long idAcao, String nome, String descricao, boolean ativo) {
		super();
		this.idAcao = idAcao;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	
	@Id
	@Column(name = "ID_ACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_SEQ")
	@SequenceGenerator(name = "ACAO_SEQ", sequenceName = "ACAO_SEQ", allocationSize = 1)
	public long getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(long idAcao) {
		this.idAcao = idAcao;
	}
	
	@Column(name = "NOME")
	@NotNull(message = "Nome obrigatorio")
	@Size(min = 2, max = 40, message = "NOME deve ser entre 2 e 40 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Column(name = "DESCRICAO")
	@NotNull(message = "Descricao obrigat�ria")
	@Size(min = 2, max = 100, message = "DESCRICAO deve ser entre 2 e 100 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "ATIVO")
	@NotNull(message = "Ativo obrigat�rio")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "acao")
	public List<ExecucaoModel> getExecucoes() {
		return execucoes;
	}

	public void setExecucoes(List<ExecucaoModel> execucoes) {
		this.execucoes = execucoes;
	}
	
	
	

}

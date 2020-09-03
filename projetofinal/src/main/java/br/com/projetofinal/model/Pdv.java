package br.com.projetofinal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="tb_pdv")
@Entity
public class Pdv {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="numero_ponto")
	private int numPonto;
	
	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="telefone", length=20)
	private String telefone;
	
	@Column(name="endereco", length=100)
	private String endereco;
	
	@OneToMany(mappedBy="pdvId", cascade=CascadeType.ALL)
	@JsonIgnoreProperties("pdvId")
	private List<Solicitacao> solicitacoes;

	public Pdv() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pdv(int id, int numPonto, String nome, String telefone, String endereco, List<Solicitacao> solicitacoes) {
		super();
		this.id = id;
		this.numPonto = numPonto;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.solicitacoes = solicitacoes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumPonto() {
		return numPonto;
	}

	public void setNumPonto(int numPonto) {
		this.numPonto = numPonto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
}

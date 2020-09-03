package br.com.projetofinal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="tb_solicitacoes")
@Entity
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="num_seq")
	private int numSeq;
	
	@Column(name="nome_tecnico", length=100)
	private String nomeTec;
	
	@Column(name="operadora", length=50)
	private String operadora;
	
	@Column(name="telefone", length=20)
	private String telefone;
	
	@Column(name="doc", length=20)
	private String doc;
	
	@Column(name="data")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
	private Date data;
	
	@Column(name="hora", length=5)
	private String hora;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne
	@JsonIgnoreProperties("solicitacoes")
	private Pdv pdvId;

	public Solicitacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Solicitacao(int numSeq, String nomeTec, String operadora, String telefone, String doc, Date data, String hora,
			int status, Pdv pdvId) {
		super();
		this.numSeq = numSeq;
		this.nomeTec = nomeTec;
		this.operadora = operadora;
		this.telefone = telefone;
		this.doc = doc;
		this.data = data;
		this.hora = hora;
		this.status = status;
		this.pdvId = pdvId;
	}

	public int getNumSeq() {
		return numSeq;
	}

	public void setNumSeq(int numSeq) {
		this.numSeq = numSeq;
	}

	public String getNomeTec() {
		return nomeTec;
	}

	public void setNomeTec(String nomeTec) {
		this.nomeTec = nomeTec;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Pdv getPdvId() {
		return pdvId;
	}

	public void setPdvId(Pdv pdvId) {
		this.pdvId = pdvId;
	}
	
}

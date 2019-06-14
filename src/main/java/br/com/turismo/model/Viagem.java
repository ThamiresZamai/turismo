package br.com.turismo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "viagem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_de", nullable = false)
	public Local localDe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_para", nullable = false)
	public Local localPara;
	
	public String dtSaida;
	public String dtVolta;	
	
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Local getLocalDe() {
		return localDe;
	}
	
	public void setLocalDe(Local localDe) {
		this.localDe = localDe;
	}
	public Local getLocalPara() {
		return localPara;
	}
	public void setLocalPara(Local localPara) {
		this.localPara = localPara;
	}
	public String getDtSaida() {
		return dtSaida;
	}
	public void setDtSaida(String dtSaida) {
		this.dtSaida = dtSaida;
	}
	public String getDtVolta() {
		return dtVolta;
	}
	public void setDtVolta(String dtVolta) {
		this.dtVolta = dtVolta;
	}
	
	
}

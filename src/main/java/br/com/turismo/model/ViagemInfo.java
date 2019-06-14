package br.com.turismo.model;

public class ViagemInfo {
	public Long id;
	public Integer localDe;
	public Integer localPara;
	public String dtSaida;
	public String dtVolta;
	
	
	public Integer getLocalDe() {
		return localDe;
	}
	public void setLocalDe(Integer localDe) {
		this.localDe = localDe;
	}
	public Integer getLocalPara() {
		return localPara;
	}
	public void setLocalPara(Integer localPara) {
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

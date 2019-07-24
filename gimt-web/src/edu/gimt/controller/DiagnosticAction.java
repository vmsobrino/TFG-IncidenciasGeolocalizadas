package edu.gimt.controller;

import com.opensymphony.xwork2.ActionSupport;

import edu.gimt.model.Cluster;
import edu.gimt.view.DiagnosticView;

/**
 * Search Action
 */
public class DiagnosticAction extends ActionSupport {
	
	private static final long serialVersionUID = -6010987587837472649L;
	
	String vuelcos;
	String accidentesInvierno;
	String accidentesOtono;
	String nivelVerdeBlanco;
	String nivelAmarillo;
	String accidentes1218;
	String accidentes1800;

	DiagnosticView diagnostico;

	public String execute() {
		String retValue = null;
		String prediction = null;
		try {
			diagnostico = new DiagnosticView();
			Cluster c = new Cluster();
			prediction = c.predictCluster(Double.valueOf(getVuelcos()),
											 Double.valueOf(getAccidentesInvierno()),
											 Double.valueOf(getAccidentesOtono()),
											 Double.valueOf(getNivelVerdeBlanco()),
											 Double.valueOf(getNivelAmarillo()),
											 Double.valueOf(getAccidentes1218()),
											 Double.valueOf(getAccidentes1800()));
			if (prediction == null) {
				diagnostico.setCluster("");
				setDiagnostico(null);
			}
			else {
				diagnostico.setCluster(prediction);
				setDiagnostico(diagnostico);
			}
			retValue = SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			retValue = ERROR;
		}
		return retValue;
	}

	public String getVuelcos() {
		return vuelcos;
	}

	public void setVuelcos(String vuelcos) {
		this.vuelcos = vuelcos;
	}

	public String getAccidentesInvierno() {
		return accidentesInvierno;
	}

	public void setAccidentesInvierno(String accidentesInvierno) {
		this.accidentesInvierno = accidentesInvierno;
	}

	public String getAccidentesOtono() {
		return accidentesOtono;
	}

	public void setAccidentesOtono(String accidentesOtono) {
		this.accidentesOtono = accidentesOtono;
	}

	public String getNivelVerdeBlanco() {
		return nivelVerdeBlanco;
	}

	public void setNivelVerdeBlanco(String nivelVerdeBlanco) {
		this.nivelVerdeBlanco = nivelVerdeBlanco;
	}

	public String getNivelAmarillo() {
		return nivelAmarillo;
	}

	public void setNivelAmarillo(String nivelAmarillo) {
		this.nivelAmarillo = nivelAmarillo;
	}

	public String getAccidentes1218() {
		return accidentes1218;
	}

	public void setAccidentes1218(String accidentes1218) {
		this.accidentes1218 = accidentes1218;
	}

	public String getAccidentes1800() {
		return accidentes1800;
	}

	public void setAccidentes1800(String accidentes1800) {
		this.accidentes1800 = accidentes1800;
	}

	public DiagnosticView getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(DiagnosticView diagnostico) {
		this.diagnostico = diagnostico;
	}


}


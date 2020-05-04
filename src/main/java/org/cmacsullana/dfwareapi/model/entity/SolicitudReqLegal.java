package org.cmacsullana.dfwareapi.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUER_LEGAL")
public class SolicitudReqLegal implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_REQ_LEGAL", unique = true)
	private Long idSolicitudReqLegal;

	@Column(name = "NOM_CLIENTE", length = 80)
	private String nombreCliente;
	
	@Column(name = "ASUNTO", length = 120)
	private String asunto;
	
	@Column(name = "DESCRIPCION", length = 250)
	private String descripcion;
	
	@Column(name = "OFIC_REGISTRAL", length = 80)
	private String oficRegistral;
	
	@Column(name = "DIRECC_CLIENTE", length = 100, nullable = false)
	private String direcCliente;

	@Column(name = "FEC_REG_TICKET")
	private Date fechaRegTicket;
	
	
	public SolicitudReqLegal() {
		
	}

	public Long getIdSolicitudReqLegal() {
		return idSolicitudReqLegal;
	}

	public void setIdSolicitudReqLegal(Long idSolicitudReqLegal) {
		this.idSolicitudReqLegal = idSolicitudReqLegal;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOficRegistral() {
		return oficRegistral;
	}

	public void setOficRegistral(String oficRegistral) {
		this.oficRegistral = oficRegistral;
	}

	public String getDirecCliente() {
		return direcCliente;
	}

	public void setDirecCliente(String direcCliente) {
		this.direcCliente = direcCliente;
	}

	public Date getFechaRegTicket() {
		return fechaRegTicket;
	}

	public void setFechaRegTicket(Date fechaRegTicket) {
		this.fechaRegTicket = fechaRegTicket;
	}
}

package org.cmacsullana.dfwareapi.model.service.interfaz;

import java.util.List;

import org.cmacsullana.dfwareapi.model.entity.SolicitudReqLegal;

public interface ISolicitudReqLegalService {

	public List<SolicitudReqLegal> listSolicitudReqLegal();
	
	public SolicitudReqLegal grabarSolicReqLegal(SolicitudReqLegal solicReqLegal);
	
	public SolicitudReqLegal mostrarSolicReqLegalById(Long idSolicReqLegal);
	
	public void eliminarSolicReqLegalById(Long idSolicReqLegal);
	
}

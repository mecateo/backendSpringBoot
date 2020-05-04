package org.cmacsullana.dfwareapi.model.service.impl;

import java.util.List;

import org.cmacsullana.dfwareapi.model.dao.interfaz.ISolicitudReqLegal;
import org.cmacsullana.dfwareapi.model.entity.SolicitudReqLegal;
import org.cmacsullana.dfwareapi.model.service.interfaz.ISolicitudReqLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudReqLegalServiceImpl implements ISolicitudReqLegalService{

	@Autowired
	ISolicitudReqLegal solicitudReqLegalDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<SolicitudReqLegal> listSolicitudReqLegal() {
		return (List<SolicitudReqLegal>) solicitudReqLegalDao.findAll();
	}

	@Override
	@Transactional
	public SolicitudReqLegal grabarSolicReqLegal(SolicitudReqLegal solicReqLegal) {
		
		return solicitudReqLegalDao.save(solicReqLegal);
	}

	@Override
	@Transactional(readOnly = true)
	public SolicitudReqLegal mostrarSolicReqLegalById(Long idSolicReqLegal) {
		return solicitudReqLegalDao.findById(idSolicReqLegal).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarSolicReqLegalById(Long idSolicReqLegal) {
		solicitudReqLegalDao.deleteById(idSolicReqLegal);
		
	}

}

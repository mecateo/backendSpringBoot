package org.cmacsullana.dfwareapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cmacsullana.dfwareapi.model.entity.SolicitudReqLegal;
import org.cmacsullana.dfwareapi.model.service.interfaz.ISolicitudReqLegalService;
import org.cmacsullana.dfwareapi.util.funciones.UtilFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})

@RequestMapping("/solicitudReq-legal")
public class SolicitudReqLegalRestController {
	
	@Autowired
	public ISolicitudReqLegalService solicitudReqLegalService;
	
	
	@GetMapping("/solicitudesReqLegales")
	public List<SolicitudReqLegal> index(){
		return solicitudReqLegalService.listSolicitudReqLegal();
	}
	
	@PostMapping("/agregarSolicReqLegal")
	public ResponseEntity<?> agregarSolicitudReqLegal(@RequestBody SolicitudReqLegal solicReqLegal) {
		
		SolicitudReqLegal solicReqLegalNew = null;
		Map<String, Object> respuesta = new HashMap<>();
		
		try {
			solicReqLegal.setFechaRegTicket(UtilFunctions.fechaActual());
			solicReqLegalNew =solicitudReqLegalService.grabarSolicReqLegal(solicReqLegal);
		}catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al insertar en la base de datos!!!");
			respuesta.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "La solicitud de requisición legal ha sido creado con éxito!!");
		respuesta.put("solicitudReqLegal", solicReqLegalNew);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/solicitudesReqLegales/{idSolicitud}")
	public ResponseEntity<?> mostrarSolicitudReqLegalById(@PathVariable Long idSolicitud) {
		
		SolicitudReqLegal solicitudReqLegal = null;
		Map<String, Object> respuesta = new HashMap<>();
		
		//Manejamos los errores de conexion con el servidor de base de datos
		try {
			solicitudReqLegal = solicitudReqLegalService.mostrarSolicReqLegalById(idSolicitud);
		}catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al consultar en la base de datos!!!");
			respuesta.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(solicitudReqLegal == null) {
			respuesta.put("mensaje", "La solicitud de requisición legal con ID: " 
					.concat(idSolicitud.toString().concat(" no existe en la base de datos!!")));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SolicitudReqLegal>(solicitudReqLegal, HttpStatus.OK);
	}
	
	@PutMapping("/editarSolicReqLegal/{idSolicitud}")
	public ResponseEntity<?> editarSolicitudReqLegal(@RequestBody SolicitudReqLegal solicReqLegal, @PathVariable Long idSolicitud) {
		
		SolicitudReqLegal solicitudReqLegalActual = solicitudReqLegalService.mostrarSolicReqLegalById(idSolicitud);
		SolicitudReqLegal solicitudReqLegalUpdate = null;
		Map<String, Object> respuesta = new HashMap<>();
		
		if(solicitudReqLegalActual == null) {
			respuesta.put("mensaje", "Error: no se puede editar,la solicitud de requisición legal con ID: " 
					.concat(idSolicitud.toString().concat(" no existe en la base de datos!!")));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		try {
			solicitudReqLegalActual.setNombreCliente(solicReqLegal.getNombreCliente());
			solicitudReqLegalActual.setAsunto(solicReqLegal.getAsunto());
			solicitudReqLegalActual.setDescripcion(solicReqLegal.getDescripcion());
			solicitudReqLegalActual.setOficRegistral(solicReqLegal.getOficRegistral());
			solicitudReqLegalActual.setDirecCliente(solicReqLegal.getDirecCliente());
			
			solicitudReqLegalUpdate =solicitudReqLegalService.grabarSolicReqLegal(solicitudReqLegalActual);
			
		}catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al actualizar la solicitud en la base de datos!!!");
			respuesta.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "La solicitud de requisición legal ha sido actualizada con éxito!!");
		respuesta.put("solicitudReqLegal", solicitudReqLegalUpdate);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/solicitudesReqLegales/{idSolicitud}")
	public ResponseEntity<?> eliminarSolicitudReqLegal(@PathVariable(name = "idSolicitud") Long idSolicReqLegal) {
		
		Map<String, Object> respuesta = new HashMap<>();
		
		try {
			
			solicitudReqLegalService.eliminarSolicReqLegalById(idSolicReqLegal);
			
		}catch (DataAccessException e) {
			
			respuesta.put("mensaje", "Error al eliminar la solicitud de la base de datos!!!");
			respuesta.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		respuesta.put("mensaje", "La solicitud de requisición legal ha sido eliminada con éxito!!");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		
	}
	
}

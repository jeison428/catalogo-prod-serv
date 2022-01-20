package com.prueba.polux.app.catalogo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.polux.app.catalogo.models.ennum.Unidades;
import com.prueba.polux.app.catalogo.models.entity.Servicio;
import com.prueba.polux.app.catalogo.models.repository.ServicioRepository;
import com.prueba.polux.app.catalogo.service.validaciones.Validaciones;

@Service
public class ServicioServiceImpl implements ServicioService{
	
	/**
	 * Atributos inyectados mediante inyeccion de dependencias.
	 */
	@Autowired
	private ServicioRepository repository;

	/**
	 * Implementacion de los metodos de la interface.
	 */
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findAll(Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Page<Servicio> servicioDB = this.repository.findAll(pageable);
		
		if (!servicioDB.isEmpty()) {
			respuesta = new ResponseEntity<Page<Servicio>>(servicioDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay Servicios registrados en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}		
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findById(Long id) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Servicio servicioDB = this.repository.findById(id).orElse(null);
		
		if (servicioDB != null) {
			respuesta = new ResponseEntity<Servicio>(servicioDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay un Servicio registrado con el ID "+ id +" en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}

	@Override
	@Transactional
	public ResponseEntity<?> save(Servicio servicio) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Servicio servicioDB = null;
		try {
			servicioDB = this.repository.save(servicio);			
			if(servicioDB != null) {
				respuesta = new ResponseEntity<Servicio>(servicioDB, HttpStatus.CREATED);
			}else {
				respuestas.put("mensaje", "No se pudo registrar el Servicio en la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			respuestas.put("mensaje", "No se pudo registrar el Servicio, error interno en el servidor.");
			respuestas.put("error", "Descripcion del error: "+e.getMessage());
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> update(Long id, Servicio servicio){
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Servicio servicioDB = null;
		try {
			servicioDB = this.repository.findById(id).orElse(null);			
			if(servicioDB != null) {
				
				if (servicio.getPrecio() != null)
					servicioDB.setPrecio(servicio.getPrecio());
				
				if (servicio.getEtiqueta() != null)
					servicioDB.setEtiqueta(servicio.getEtiqueta());
				
				if (servicio.getDescripcion() != null)
					servicioDB.setDescripcion(servicio.getDescripcion());
				
				if (servicio.getPrecioFijo() != null)
					servicioDB.setPrecioFijo(servicio.getPrecioFijo());
				
				Servicio saveServicio = this.repository.save(servicioDB);
				respuesta = new ResponseEntity<Servicio>(saveServicio, HttpStatus.CREATED);
			}else {
				respuestas.put("mensaje", "No se pudo actualizar el Servicio en la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			respuestas.put("mensaje", "No se pudo actualizar el Servicio, error interno en el servidor.");
			respuestas.put("error", "Descripcion del error: "+e.getMessage());
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteById(Long id) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		if (this.repository.findById(id).orElse(null) != null) {
			this.repository.deleteById(id);
			Servicio servicioDB = this.repository.findById(id).orElse(null);
			
			if (servicioDB != null) {
				respuestas.put("mensaje", "No se pudo eliminar el Servicio.");
				respuestas.put("servicio", servicioDB);
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}else {
				respuestas.put("mensaje", "Se elimino correctamente el Servicio con ID "+ id +" de la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.OK);
			}
		}else {
			respuestas.put("mensaje", "No se pudo eliminar el servicio, el servicio no existe.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findByEtiqueta(String etiqueta, Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Page<Servicio> serviciosDB = this.repository.findByEtiqueta(etiqueta, pageable);
		
		if (serviciosDB != null) {
			respuesta = new ResponseEntity<Page<Servicio>>(serviciosDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay registros de esa Etiqueta en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findByUnidad(String unidad, Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		if (Validaciones.validacionUnidades(unidad)) {
			Page<Servicio> serviciosDB = this.repository.findByUnidad(Unidades.valueOf(unidad), pageable);
			
			if (!serviciosDB.isEmpty()) {
				respuesta = new ResponseEntity<Page<Servicio>>(serviciosDB, HttpStatus.OK);
			}else {
				respuestas.put("mensaje", "No hay registros de esa Unidad en la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}
		}else {
			respuestas.put("mensaje", "No se pudo realizar la consulta, La unidad ingresada no es valida.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}

}

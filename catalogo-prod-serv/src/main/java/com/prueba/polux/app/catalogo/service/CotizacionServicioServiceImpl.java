package com.prueba.polux.app.catalogo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.polux.app.catalogo.models.entity.CotizacionServicio;
import com.prueba.polux.app.catalogo.models.entity.Servicio;
import com.prueba.polux.app.catalogo.models.repository.CotizacionServicioRepository;
import com.prueba.polux.app.catalogo.models.repository.ServicioRepository;
import com.prueba.polux.app.catalogo.service.validaciones.Validaciones;

@Service
public class CotizacionServicioServiceImpl implements CotizacionServicioService{
	
	/**
	 * Repository de cotizacion de servicios inyectado mediante inyeccion de dependencias.
	 */
	@Autowired
	private CotizacionServicioRepository repository;
	
	/**
	 * Repository de servicios inyectado mediante inyeccion de dependencias.
	 */
	@Autowired
	private ServicioRepository repositoryServ;
	
	// Implementacion de los metodos de la interface.
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findAllById(Long id, Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Page<CotizacionServicio> cotizacionesDB = this.repository.findByServicioId(id, pageable);
		
		if (!cotizacionesDB.isEmpty()) {
			respuesta = new ResponseEntity<Page<CotizacionServicio>>(cotizacionesDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay Cotizaciones registradas en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}		
		return respuesta;
	}

	@Override
	@Transactional
	public ResponseEntity<?> findByServicioId(Long id, Integer cantidad) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Servicio tempServ = this.repositoryServ.findById(id).orElse(null);
		if (tempServ != null) {
			if (Validaciones.validarPrecioCantidad(tempServ, cantidad)) {
				CotizacionServicio cot = new CotizacionServicio();
				cot.setUnidad(tempServ.getUnidad());
				cot.setCantidad(cantidad);
				cot.setValor(cantidad * tempServ.getPrecio());
				cot.setServicio(tempServ);
				respuesta = new ResponseEntity<CotizacionServicio>(this.repository.save(cot), HttpStatus.CREATED);
			}else {
				respuestas.put("mensaje", "No se pudo realizar la cotizacionya que el precio es fijo y la cantidad es mayor a 1.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}
		}else {
			respuestas.put("mensaje", "No hay un Servicio registrado con el ID "+ id +" en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}
	
	

}

package com.prueba.polux.app.catalogo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.polux.app.catalogo.models.entity.CotizacionProducto;
import com.prueba.polux.app.catalogo.models.entity.Producto;
import com.prueba.polux.app.catalogo.models.repository.CotizacionProductoRepository;
import com.prueba.polux.app.catalogo.models.repository.ProductoRepository;

@Service
public class CotizacionProductoServiceImpl implements CotizacionProductoService{
	
	/**
	 * Repository de cotizacion de productos inyectado mediante inyeccion de dependencias.
	 */
	@Autowired
	private CotizacionProductoRepository repository;
	
	/**
	 * Repository de productos inyectado mediante inyeccion de dependencias.
	 */
	@Autowired
	private ProductoRepository repositoryProd;
	
	// Implementacion de los metodos de la interface
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findAllById(Long id, Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Page<CotizacionProducto> cotizacionesDB = this.repository.findByProductoId(id, pageable);
		
		if (!cotizacionesDB.isEmpty()) {
			respuesta = new ResponseEntity<Page<CotizacionProducto>>(cotizacionesDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay Cotizaciones registradas en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}		
		return respuesta;
	}

	@Override
	@Transactional
	public ResponseEntity<?> findByProductoId(Long id, Integer cantidad) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();

		Producto tempProd = this.repositoryProd.findById(id).orElse(null);
		if (tempProd != null) {
			CotizacionProducto cot = new CotizacionProducto();
			cot.setUnidad(tempProd.getUnidad());
			cot.setCantidad(cantidad);
			cot.setValor(cantidad * tempProd.getPrecio());
			cot.setProducto(tempProd);
			respuesta = new ResponseEntity<CotizacionProducto>(this.repository.save(cot), HttpStatus.CREATED);
		}else {
			respuestas.put("mensaje", "No hay un Producto registrado con el ID "+ id +" en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}

}

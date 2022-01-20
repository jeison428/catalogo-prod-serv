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
import com.prueba.polux.app.catalogo.models.entity.Producto;
import com.prueba.polux.app.catalogo.models.repository.ProductoRepository;
import com.prueba.polux.app.catalogo.service.validaciones.Validaciones;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	/**
	 * Atributos inyectados mediante inyeccion de dependencias.
	 */
	@Autowired
	private ProductoRepository repository;

	/**
	 * Implementacion de los metodos de la interface.
	 */
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findAll(Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Page<Producto> productosDB = this.repository.findAll(pageable);
		
		if (!productosDB.isEmpty()) {
			respuesta = new ResponseEntity<Page<Producto>>(productosDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay Productos registrados en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}		
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findById(Long id) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Producto productoDB = this.repository.findById(id).orElse(null);
		
		if (productoDB != null) {
			respuesta = new ResponseEntity<Producto>(productoDB, HttpStatus.OK);
		}else {
			respuestas.put("mensaje", "No hay un Producto registrado con el ID "+ id +" en la base de datos.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}

	@Override
	@Transactional
	public ResponseEntity<?> save(Producto producto) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Producto productoDB = null;
		try {
			productoDB = this.repository.save(producto);			
			if(productoDB != null) {
				respuesta = new ResponseEntity<Producto>(productoDB, HttpStatus.CREATED);
			}else {
				respuestas.put("mensaje", "No se pudo registrar el producto en la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			respuestas.put("mensaje", "No se pudo registrar el producto, error interno en el servidor.");
			respuestas.put("error", "Descripcion del error: "+e.getMessage());
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> update(Long id, Producto producto){
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Producto productoDB = null;
		try {
			productoDB = this.repository.findById(id).orElse(null);			
			if(productoDB != null) {
				
				if (producto.getPrecio() != null)
					productoDB.setPrecio(producto.getPrecio());
				
				if (producto.getEtiqueta() != null)
					productoDB.setEtiqueta(producto.getEtiqueta());
				
				if (producto.getDescripcion() != null)
					productoDB.setDescripcion(producto.getDescripcion());
				
				Producto saveProducto = this.repository.save(productoDB);
				respuesta = new ResponseEntity<Producto>(saveProducto, HttpStatus.CREATED);
			}else {
				respuestas.put("mensaje", "No se pudo actualizar el producto en la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			respuestas.put("mensaje", "No se pudo actualizar el producto, error interno en el servidor.");
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
			Producto productoDB = this.repository.findById(id).orElse(null);
			
			if (productoDB != null) {
				respuestas.put("mensaje", "No se pudo eliminar el producto.");
				respuestas.put("producto", productoDB);
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
			}else {
				respuestas.put("mensaje", "Se elimino correctamente el producto con ID "+ id +" de la base de datos.");
				respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.OK);
			}
		}else {
			respuestas.put("mensaje", "No se pudo eliminar el producto, el producto no existe.");
			respuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findByEtiqueta(String etiqueta, Pageable pageable) {
		ResponseEntity<?> respuesta = null;
		HashMap<String, Object> respuestas = new HashMap<String, Object>();
		Page<Producto> productosDB = this.repository.findByEtiqueta(etiqueta, pageable);
		
		if (productosDB != null) {
			respuesta = new ResponseEntity<Page<Producto>>(productosDB, HttpStatus.OK);
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
			Page<Producto> productosDB = this.repository.findByUnidad(Unidades.valueOf(unidad), pageable);
			if (!productosDB.isEmpty()) {
				respuesta = new ResponseEntity<Page<Producto>>(productosDB, HttpStatus.OK);
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

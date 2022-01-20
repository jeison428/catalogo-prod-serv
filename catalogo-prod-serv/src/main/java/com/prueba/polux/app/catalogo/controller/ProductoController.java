package com.prueba.polux.app.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.polux.app.catalogo.models.entity.Producto;
import com.prueba.polux.app.catalogo.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	/**
	 * Service de servicios inyectado mediante inyeccion de dependencias.
	 */
	
	@Autowired
	private ProductoService service;

	/**
	 * Metodo que mapea el endpoint para generar una lista paginada de productos en 
	 * una URL para ser usado.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Respuesta con una lista paginada generada exitosamente o un mensaje de error.
	 */
	@GetMapping
	public ResponseEntity<?> listar(Pageable pageable){
		return this.service.findAll(pageable);
	}

	/**
	 * Metodo que mapea el endpoint para generar una busqueda de un producto en 
	 * una URL para ser usado.
	 * @param id del producto que se quiere buscar.
	 * @return Respuesta con el producto encontrado o un mensaje de error.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Long id){
		return this.service.findById(id);
	}

	/**
	 * Metodo que mapea el endpoint para generar una lista paginada de productos filtrados
	 * por su etiqueta en una URL para ser usado.
	 * @param etiqueta por la cual se quiere realizar la busqueda.
	 * @return Respuesta con la lista paginada o un mensaje de error.
	 */
	@GetMapping("/etiqueta/{etiqueta}")
	public ResponseEntity<?> buscarEtiqueta(@PathVariable String etiqueta, Pageable pageable){
		return this.service.findByEtiqueta(etiqueta, pageable);
	}

	/**
	 * Metodo que mapea el endpoint para generar una lista paginada de productos filtrados 
	 * por su unidad en una URL para ser usado.
	 * @param unidad por la cual se quiere realizar la busqueda.
	 * @return Respuesta con la lista paginada o un mensaje de error.
	 */
	@GetMapping("/unidad/{unidad}")
	public ResponseEntity<?> buscarUnidad(@PathVariable String unidad, Pageable pageable){
		return this.service.findByUnidad(unidad, pageable);
	}
	
	/**
	 * Metodo que mapea el endpoint para crear un nuevo producto en 
	 * una URL para ser usado.
	 * @param producto que se quiere crear.
	 * @return Respuesta con el producto creado o un mensaje de error.
	 */
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Producto producto){
		return this.service.save(producto);
	}
	
	/**
	 * Metodo que mapea el endpoint para actualizar un producto en 
	 * una URL para ser usado.
	 * @param id del producto que se quiere actualizar.
	 * @param producto con los atributos que se van a actualizar.
	 * @return Respuesta con el producto actulizado o un mensaje de error.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto producto){
		return this.service.update(id, producto);
	}
	
	/**
	 * Metodo que mapea el endpoint para eliminar un producto en 
	 * una URL para ser usado.
	 * @param id del producto que se quiere eliminar.
	 * @return Respuesta con un mensaje de confirmacion de eliminacion o un mensaje de 
	 * 		   error.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		return this.service.deleteById(id);
	}

}

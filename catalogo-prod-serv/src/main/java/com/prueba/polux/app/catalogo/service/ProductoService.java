package com.prueba.polux.app.catalogo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.prueba.polux.app.catalogo.models.entity.Producto;

public interface ProductoService {
	
	/**
	 * Declaracion de los metodos que posteriormente seran implementados
	 */
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada con los 
	 * productos almacenados en la base de datos.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Lista paginada de productos.
	 */
	public ResponseEntity<?> findAll(Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para buscar un producto por medio de su
	 * identificador.
	 * @param id Identificador del producto.
	 * @return Respuesta con el producto encontrado o un mensaje de error.
	 */
	public ResponseEntity<?> findById(Long id);
	
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada con los 
	 * productos que tengan una etiqueta especifica.
	 * @param etiqueta por la cual se quiere hacer la busqueda.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return
	 */
	public ResponseEntity<?> findByEtiqueta(String etiqueta, Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada con los 
	 * productos que tengan una unidad especifica.
	 * @param unidad por la cual se quiere hacer la busqueda.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return
	 */
	public ResponseEntity<?> findByUnidad(String unidad, Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para guardar un producto en la base de datos.
	 * @param producto que se quiere almacenar en la base de datos.
	 * @return Respuesta con el producto almacenado correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> save(Producto producto);
	
	/**
	 * Metodo donde se implementa la logica para actualizar un producto almacenado en la
	 * base de datos.
	 * @param id del producto que se quiere actualizar.
	 * @param producto con los atributos que se quieren actualizar.
	 * @return Respuesta con el producto actualizado correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> update(Long id, Producto producto);
	
	/**
	 * Metodo donde se implementa la logica para eliminar un producto de la base de datos.
	 * @param id del producto que se quiere eliminar.
	 * @return Respuesta con un mensaje donde se verifica que se elimino el producto 
	 * 		   correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> deleteById(Long id);

}

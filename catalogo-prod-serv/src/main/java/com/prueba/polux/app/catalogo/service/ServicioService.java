package com.prueba.polux.app.catalogo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.prueba.polux.app.catalogo.models.entity.Servicio;

public interface ServicioService {
	
	/**
	 * Declaracion de los metodos que posteriormente seran implementados
	 */
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada con los 
	 * servicios almacenados en la base de datos.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Lista paginada de servicios.
	 */
	public ResponseEntity<?> findAll(Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para buscar un servicio por medio de su
	 * identificador.
	 * @param id Identificador del servicio.
	 * @return Respuesta con el servicio encontrado o un mensaje de error.
	 */
	public ResponseEntity<?> findById(Long id);
	
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada con los 
	 * servicios que tengan una etiqueta especifica.
	 * @param etiqueta por la cual se quiere hacer la busqueda.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return
	 */
	public ResponseEntity<?> findByEtiqueta(String etiqueta, Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada con los 
	 * servicios que tengan una unidad especifica.
	 * @param unidad por la cual se quiere hacer la busqueda.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Respuesta con el servicio almacenado correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> findByUnidad(String unidad, Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para guardar un servicio en la base de datos.
	 * @param servicio que se quiere almacenar en la base de datos.
	 * @return Respuesta con el servicio almacenado correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> save(Servicio servicio);
	
	/**
	 * Metodo donde se implementa la logica para actualizar un servicio almacenado en la
	 * base de datos.
	 * @param id del servicio que se quiere actualizar.
	 * @param servicio con los atributos que se quieren actualizar.
	 * @return Respuesta con el servicio actualizado correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> update(Long id, Servicio servicio);
	
	/**
	 * Metodo donde se implementa la logica para eliminar un servicio de la base de datos.
	 * @param id del servicio que se quiere eliminar.
	 * @return Respuesta con un mensaje donde se verifica que se elimino el servicio 
	 * 		   correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> deleteById(Long id);


}

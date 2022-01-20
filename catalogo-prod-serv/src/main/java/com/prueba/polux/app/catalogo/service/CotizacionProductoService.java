package com.prueba.polux.app.catalogo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CotizacionProductoService {
	
	/**
	 * Declaracion de los metodos que posteriormente seran implementados
	 */
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada de cotizaciones 
	 * asociadas a un producto.
	 * @param id Identificador del producto asociado a las cotizaciones.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Respuesta con la lista paginada de cotizaciones asociada a un producto o
	 * 		   un mensaje de error.
	 */
	public ResponseEntity<?> findAllById(Long id, Pageable pageable);
	
	/**
	 * Metodo donde se implementa la logica para crear una cotizacion solicitada
	 * mediante el id del producto y la cantidad.
	 * @param id Identificador del producto que se desea cotizar.
	 * @param cantidad Cantidad del producto que se desea cotizar.
	 * @return Respuesta con la cotizacion generada correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> findByProductoId(Long id, Integer cantidad);

}

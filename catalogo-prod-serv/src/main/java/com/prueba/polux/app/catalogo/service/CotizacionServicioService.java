package com.prueba.polux.app.catalogo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CotizacionServicioService {
	
	/**
	 * Declaracion de los metodos que posteriormente seran implementados
	 */
	/**
	 * Metodo donde se implementa la logica para generar una lista paginada de cotizaciones 
	 * asociadas a un servicio.
	 * @param id Identificador del Servicio asociado a las cotizaciones.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Respuesta con la lista paginada de cotizaciones asociada a un servicio o
	 * 		   un mensaje de error.
	 */
	public ResponseEntity<?> findAllById(Long id, Pageable pageable);
	/**
	 * Metodo donde se implementa la logica para crear una cotizacion solicitada
	 * mediante el id del servicio y la cantidad.
	 * @param id Identificador del servicio que se desea cotizar.
	 * @param cantidad Cantidad del servicio que se desea cotizar.
	 * @return Respuesta con la cotizacion generada correctamente o un mensaje de error.
	 */
	public ResponseEntity<?> findByServicioId(Long id, Integer cantidad);

}

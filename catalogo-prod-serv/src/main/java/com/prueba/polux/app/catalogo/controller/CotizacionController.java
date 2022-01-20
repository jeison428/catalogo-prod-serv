package com.prueba.polux.app.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.polux.app.catalogo.service.CotizacionProductoService;
import com.prueba.polux.app.catalogo.service.CotizacionServicioService;

@RestController
@RequestMapping("")
public class CotizacionController {
	
	/**
	 * Service de cotizacion de productos inyectado mediante inyeccion de dependencias.
	 */
	@Autowired
	private CotizacionProductoService serviceProd;
	
	/**
	 * Service de cotizacion de servicios inyectado mediante inyeccion de dependencias.
	 */
	@Autowired
	private CotizacionServicioService serviceServ;
	
	/**
	 * Inicio endpoints para cotizacion de productos
	 */
	/**
	 * Metodo que mapea el endpoint para generar una Cotizacion de un producto en 
	 * una URL para ser usado.
	 * @param id del producto que se quiere cotizar.
	 * @param cantidad del producto que se quiere cotizar.
	 * @return Respuesta con una cotizacion generada exitosamente o un mensaje de error.
	 */
	@GetMapping("/producto/cotizacion/buscar/{id}")
	public ResponseEntity<?> buscarCotizacionProducto(@PathVariable Long id, @RequestParam Integer cantidad){
		return this.serviceProd.findByProductoId(id, cantidad);
	}
	
	/**
	 * Metodo que mapea el endpoint para generar una lista paginada con las Cotizaciones  
	 * asociadas a un producto en una URL para ser usado.
	 * @param id del producto asociado a las cotizaciones.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Respuesta con una lista de cotizaciones generada exitosamente o un mensaje 
	 * 		   de error.
	 */
	@GetMapping("/producto/cotizacion/{id}")
	public ResponseEntity<?> buscarCotizacionProductoId(@PathVariable Long id, Pageable pageable){
		return this.serviceProd.findAllById(id, pageable);
	}
	
	/**
	 * Inicio endpoints para cotizacion de servicios
	 */
	/**
	 * Metodo que mapea el endpoint para generar una Cotizacion de un servicio en 
	 * una URL para ser usado.
	 * @param id del servicio que se quiere cotizar.
	 * @param cantidad del servicio que se quiere cotizar.
	 * @return Respuesta con una cotizacion generada exitosamente o un mensaje de error.
	 */
	@GetMapping("/servicio/cotizacion/buscar/{id}")
	public ResponseEntity<?> buscarCotizacionServicio(@PathVariable Long id, @RequestParam Integer cantidad){
		return this.serviceServ.findByServicioId(id, cantidad);
	}
	
	/**
	 * Metodo que mapea el endpoint para generar una lista paginada con las Cotizaciones  
	 * asociadas a un servicio en una URL para ser usado.
	 * @param id del servicio asociado a las cotizaciones.
	 * @param pageable Objeto con el cual se genera la paginacion.
	 * @return Respuesta con una lista de cotizaciones generada exitosamente o un mensaje 
	 * 		   de error.
	 */
	@GetMapping("/servicio/cotizacion/{id}")
	public ResponseEntity<?> buscarCotizacionServicioId(@PathVariable Long id, Pageable pageable){
		return this.serviceServ.findAllById(id, pageable);
	}
	
}

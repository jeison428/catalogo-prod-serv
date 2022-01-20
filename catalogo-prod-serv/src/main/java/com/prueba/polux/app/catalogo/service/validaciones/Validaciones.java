package com.prueba.polux.app.catalogo.service.validaciones;

import com.prueba.polux.app.catalogo.models.ennum.Unidades;
import com.prueba.polux.app.catalogo.models.entity.Servicio;

public class Validaciones {
	
	/**
	 * Metodo estatico para validar si un string contiene una cadena igual a una unidad
	 * del enum Unidades.
	 * @param unidad Cadena que se quiere validar.
	 * @return TRUE si la cadena ingresada es igual a una Unidad del enum. FALSE si la  
	 * 		   cadena ingresada no es igual a una Unidad del enum
	 */
	public static boolean validacionUnidades(String unidad) {
		boolean resultado = false;
		if (Unidades.Centimetro.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Dia.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Galon.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Gramo.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Hora.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Kilogramo.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Litro.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Mes.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Metro.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Metro2.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Unidad.toString().compareTo(unidad)==0) {
			resultado = true;
		}else
		if (Unidades.Vez.toString().compareTo(unidad)==0) {
			resultado = true;
		}
		return resultado;
	}
	
	/**
	 * Metodo para validar que el servicio sea de precio fijo y su cantidad.
	 * @param servicio Servicio que se quiere validar.
	 * @param cantidad Cantidad de servicios para validar.
	 * @return Valor booleano resultado de la validacion. True si el precio es fijo y la
	 * 		   cantidad es 1 o si el precio no es fijo. False en todos los otros casos.
	 */
	public static boolean validarPrecioCantidad(Servicio servicio, Integer cantidad) {
		boolean resultado = false;
		if (servicio.getPrecioFijo() && cantidad == 1) {
			resultado = true;
		}else if (!servicio.getPrecioFijo()) {
			resultado = true;
		}
		return resultado;
	}

}

package com.prueba.polux.app.catalogo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.prueba.polux.app.catalogo.models.ennum.Unidades;

import lombok.Data;

@Entity
@Table(name = "servicios")
@Data
public class Servicio {
	
	/**
	 * Atributos de la entidad Servicio:
	 */
	/**
	 * Llave primaria para la tabla servicios
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Las unidades se manejaron en formato Enum para evitar la creacion de un tabla
	 */
	@NotNull
	private Unidades unidad;
	
	@Positive
	private Double precio;

	@NotNull
	@Column(name = "precio_fijo")
	private Boolean precioFijo;
	
	@NotNull
	@Size(min = 0, max = 30)
	private String etiqueta;
	
	@Size(min = 0, max = 100)
	private String descripcion;

}

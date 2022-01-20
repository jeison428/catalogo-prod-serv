package com.prueba.polux.app.catalogo.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.prueba.polux.app.catalogo.models.ennum.Unidades;

import lombok.Data;

@Entity
@Table(name = "cotizaciones_servicios")
@Data
public class CotizacionServicio {
	
	/**
	 * Atributos de la entidad CotizacionServicio:
	 */
	/**
	 * Llave primaria para la tabla cotizaciones_servicios
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Referencia al servicio al que se le va a hacer la cotizacion
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ref_servicio")
	private Servicio servicio;
	
	@NotNull
	private Unidades unidad;
	
	@NotNull
	@Positive
	private Integer cantidad;
	
	@NotNull
	@Positive
	private Double valor;
	
	/**
	 * Atributo donde se almacena la fecha y hora en la que se realizo la cotizacion
	 */
	@Column(name = "create_at", unique = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	/**
	 * Metodo que asigna la fecha antes de guardar el registro en la base de datos
	 */
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

}

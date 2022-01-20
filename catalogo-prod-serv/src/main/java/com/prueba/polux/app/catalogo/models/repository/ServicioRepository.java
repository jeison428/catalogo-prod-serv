package com.prueba.polux.app.catalogo.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.polux.app.catalogo.models.ennum.Unidades;
import com.prueba.polux.app.catalogo.models.entity.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
	
	public Page<Servicio> findByEtiqueta(String etiqueta, Pageable pageable);
	public Page<Servicio> findByUnidad(Unidades unidad, Pageable pageable);

}

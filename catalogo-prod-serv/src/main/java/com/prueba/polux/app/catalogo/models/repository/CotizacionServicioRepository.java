package com.prueba.polux.app.catalogo.models.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.polux.app.catalogo.models.entity.CotizacionServicio;

public interface CotizacionServicioRepository extends JpaRepository<CotizacionServicio, Long>{
	
	public Page<CotizacionServicio> findByServicioId(Long id, Pageable pageable);
	public Optional<CotizacionServicio> findByServicioIdAndCantidad(Long id, Integer cantidad);

}

package com.prueba.polux.app.catalogo.models.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.polux.app.catalogo.models.entity.CotizacionProducto;

public interface CotizacionProductoRepository extends JpaRepository<CotizacionProducto, Long>{
	
	public Page<CotizacionProducto> findByProductoId(Long id, Pageable pageable);
	public Optional<CotizacionProducto> findByProductoIdAndCantidad(Long id, Integer cantidad);

}

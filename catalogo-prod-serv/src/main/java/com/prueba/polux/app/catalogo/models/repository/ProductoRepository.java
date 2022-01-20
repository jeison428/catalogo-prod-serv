package com.prueba.polux.app.catalogo.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.polux.app.catalogo.models.ennum.Unidades;
import com.prueba.polux.app.catalogo.models.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	public Page<Producto> findByEtiqueta(String etiqueta, Pageable pageable);
	public Page<Producto> findByUnidad(Unidades unidad, Pageable pageable);
	
}

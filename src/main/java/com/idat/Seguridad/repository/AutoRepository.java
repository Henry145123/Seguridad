package com.idat.Seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.Seguridad.model.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {

}

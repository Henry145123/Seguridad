package com.idat.Seguridad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.Seguridad.model.Auto;
import com.idat.Seguridad.repository.AutoRepository;

@Service
public class AutoServiceImpl implements AutoService {

	@Autowired
	AutoRepository repo;
	
	@Override
	public void guardarAuto(Auto a) {
		repo.save(a);
		
	}

	@Override
	public void actualizarAuto(Auto a) {
		repo.saveAndFlush(a);
		
	}

	@Override
	public void eliminarAuto(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<Auto> listaAutos() {
	
		return repo.findAll();
	}

	@Override
	public Auto obtenerAutoId(Integer id) {

		return repo.findById(id).orElse(null);
	}

}

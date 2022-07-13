package com.idat.Seguridad.service;

import java.util.List;

import com.idat.Seguridad.model.Auto;

public interface AutoService {
	
	void guardarAuto(Auto a);
	void actualizarAuto(Auto a);
	void eliminarAuto(Integer id);
	List<Auto> listaAutos();
	Auto obtenerAutoId(Integer id);

}

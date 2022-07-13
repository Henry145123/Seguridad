package com.idat.Seguridad.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.idat.Seguridad.model.Auto;
import com.idat.Seguridad.service.AutoService;

@RestController
@RequestMapping("/auto/v1")
public class AutoController {
	
	@Autowired
	private AutoService autServ;
	
	
	@RequestMapping("/listar")
	public @ResponseBody ResponseEntity<Collection<Auto>>  listar(){
		
		 return new ResponseEntity<Collection<Auto>>( autServ.listaAutos(),HttpStatus.OK);
	}
	
	@RequestMapping(path="/guardar",method=RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Auto auto) {
		autServ.guardarAuto(auto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(path="/borrar/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		Auto a = autServ.obtenerAutoId(id);
		if(a!=null) {
			autServ.eliminarAuto(id);
			return new ResponseEntity<Void>(HttpStatus.OK); 
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path="/buscar/{id}",method=RequestMethod.GET)
	public ResponseEntity<Auto> buscarPorId(@PathVariable Integer id){
		Auto a =autServ.obtenerAutoId(id);
		if(a!=null) {
			return new ResponseEntity<Auto>(a,HttpStatus.OK);
		}
		return new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path="/editar",method=RequestMethod.PUT)
	public ResponseEntity<Void> actualizar(@RequestBody Auto a){
		
		Auto aut=autServ.obtenerAutoId(a.getIdAuto());		
		if(aut!=null) {
			autServ.actualizarAuto(a);
			return new ResponseEntity<Void>(HttpStatus.OK); 
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}

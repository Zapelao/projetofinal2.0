package br.com.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.dao.PdvDAO;
import br.com.projetofinal.model.Pdv;

@RestController
@CrossOrigin("*")
public class PdvController {


	@Autowired
	private PdvDAO pdvdao;

	@PostMapping("/pdv/newpdv")
	public ResponseEntity<Pdv> insertPdv(@RequestBody Pdv pdv){
		try {
			Pdv retorno = pdvdao.save(pdv);
			return ResponseEntity.ok(retorno);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/pdv/consulta/id/{id}")
	public ResponseEntity<Pdv> getPdvId(@PathVariable int id){
		Pdv retorno = pdvdao.findById(id).orElse(null);
		if (retorno == null) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(retorno);
		}
	}
	
	@GetMapping("/pdv/consulta/pdvs")
	public ResponseEntity<ArrayList<Pdv>> getPdvs(){
		ArrayList<Pdv> retorno = (ArrayList<Pdv>) pdvdao.findAll();
		if (retorno.size() == 0) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(retorno);
		}
	}

}

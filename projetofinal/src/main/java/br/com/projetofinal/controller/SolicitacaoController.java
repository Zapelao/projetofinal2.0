package br.com.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.dao.SolicitacaoDAO;
import br.com.projetofinal.model.Solicitacao;

@RestController
@CrossOrigin("*")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoDAO solicdao;

	@GetMapping("/solicitacao/consulta")
	public ResponseEntity<List<Solicitacao>> getAllSolic(){
		ArrayList<Solicitacao> lista = (ArrayList<Solicitacao>) solicdao.findAll();
		if (lista.size()==0) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}

	@GetMapping("/solicitacao/consulta/status/{status}")
	public ResponseEntity<List<Solicitacao>> getAllSolicStatus(@PathVariable int status){
		ArrayList<Solicitacao> lista = (ArrayList<Solicitacao>) solicdao.findByStatus(status);
		if (lista.size()==0) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}

	@PostMapping("/solicitacao/novasolicitacao")
	public ResponseEntity<Solicitacao> insertSolic(@RequestBody Solicitacao solic){
		try{
			Solicitacao retorno = solicdao.save(solic);
			return ResponseEntity.ok(retorno);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	@PostMapping("/solicitacao/atualizarstatus")
	public ResponseEntity<Solicitacao> autorizarSolic(@RequestBody Solicitacao solic){
		try{
			Solicitacao retorno = solicdao.findById(solic.getNumSeq()).orElse(null);
			if(retorno != null) {
				retorno.setStatus(solic.getStatus());
				solicdao.save(retorno);		
			}
			return ResponseEntity.ok(retorno);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	

}

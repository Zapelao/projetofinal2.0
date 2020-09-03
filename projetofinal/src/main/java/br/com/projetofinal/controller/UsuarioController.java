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

import br.com.projetofinal.dao.UsuarioDAO;
import br.com.projetofinal.model.Usuario;

@RestController
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioDAO userdao;

	@GetMapping("/usuario/consulta")
	public ResponseEntity<List<Usuario>> getAllUsers(){
		ArrayList<Usuario> lista = (ArrayList<Usuario>) userdao.findAll();
		if (lista.size()==0) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}

	@GetMapping("/usuario/consulta/{id}")
	public ResponseEntity<Usuario> getAllUser(@PathVariable int id){
		Usuario usuario = userdao.findById(id).orElse(null);
		if (usuario == null) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(usuario);	
		}	
	}

	@PostMapping("/usuario/login")
	public ResponseEntity<Usuario> Login(@RequestBody Usuario user){
		Usuario usuario = null;
		if (user.getEmail().indexOf("@")>1) {
			usuario = userdao.findByEmailAndSenha(user.getEmail(), user.getSenha());
		}else {
			user.setRacf(user.getEmail());
			usuario = userdao.findByRacfAndSenha(user.getRacf(), user.getSenha());	
		}
		if (usuario == null) {
			return ResponseEntity.status(401).build();
		}else {
			return ResponseEntity.ok(usuario);	
		}
	}

	@PostMapping("/usuario/newuser")
	public ResponseEntity<Usuario> insertUser(@RequestBody Usuario user){
		try {
			Usuario retorno = userdao.save(user);
			return ResponseEntity.ok(retorno);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

}

package br.com.projetofinal.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.projetofinal.model.Solicitacao;

public interface SolicitacaoDAO extends CrudRepository<Solicitacao, Integer>{
	public ArrayList<Solicitacao> findByStatus(int status);
}

package br.com.wm.leilao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.wm.leilao.model.Lance;
import br.com.wm.leilao.model.Leilao;

@Repository
public class LanceDao {

	private EntityManager em;
	
	@Autowired
	public LanceDao(EntityManager em) {
		this.em = em;
	}

	public Lance salvar(Lance lance) {
		return em.merge(lance);
	}

	public Lance buscarMaiorLanceDoLeilao(Leilao leilao) {
		return em.createQuery("SELECT l FROM Lance l WHERE l.valor = (SELECT MAX(lance.valor) FROM Lance lance WHERE lance.leilao = :leilao)", Lance.class)
				.setParameter("leilao", leilao)
				.getSingleResult();
	}
	
}
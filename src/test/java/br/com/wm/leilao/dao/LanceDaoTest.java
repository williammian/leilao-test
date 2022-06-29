package br.com.wm.leilao.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.wm.leilao.model.Lance;
import br.com.wm.leilao.model.Leilao;
import br.com.wm.leilao.model.Usuario;
import br.com.wm.leilao.util.JPAUtil;
import br.com.wm.leilao.util.builder.LanceBuilder;
import br.com.wm.leilao.util.builder.LeilaoBuilder;
import br.com.wm.leilao.util.builder.UsuarioBuilder;

class LanceDaoTest {

	private LeilaoDao leilaoDao;
	private LanceDao lanceDao;
	private EntityManager em;

	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.leilaoDao = new LeilaoDao(em);
		this.lanceDao = new LanceDao(em);
		em.getTransaction().begin();
	}

	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}

	@Test
	void deveriaEncontrarMaiorLanceCadastrado() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@email.com")
				.comSenha("12345678")
				.criar();
		
		em.persist(usuario);
		
		Leilao leilao = new LeilaoBuilder()
				.comData(LocalDate.now())
				.comNome("Leilao")
				.comValorInicial("1.00")
				.comUsuario(usuario)
				.criar();
		
		leilao = leilaoDao.salvar(leilao);
		
		Lance lance1 = new LanceBuilder()
				.comData(LocalDate.now())
				.comValor("10.00")
				.comUsuario(usuario)
				.comLeilao(leilao)
				.criar();
		
		lanceDao.salvar(lance1);
		
		Lance lance2 = new LanceBuilder()
				.comData(LocalDate.now())
				.comValor("20.00")
				.comUsuario(usuario)
				.comLeilao(leilao)
				.criar();
		
		lanceDao.salvar(lance2);
		
		Lance lance3 = new LanceBuilder()
				.comData(LocalDate.now())
				.comValor("30.00")
				.comUsuario(usuario)
				.comLeilao(leilao)
				.criar();
		
		lanceDao.salvar(lance3);
		
		Lance maiorLance = this.lanceDao.buscarMaiorLanceDoLeilao(leilao);
		Assert.assertEquals(new BigDecimal("30.00"), maiorLance.getValor());
	}

}

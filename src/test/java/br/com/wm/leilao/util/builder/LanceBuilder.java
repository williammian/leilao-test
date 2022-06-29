package br.com.wm.leilao.util.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wm.leilao.model.Lance;
import br.com.wm.leilao.model.Leilao;
import br.com.wm.leilao.model.Usuario;

public class LanceBuilder {
	
	private BigDecimal valor;
	private LocalDate data;
	private Usuario usuario;
	private Leilao leilao;
	
	public LanceBuilder comValor(String valor) {
		this.valor = new BigDecimal(valor);
		return this;
	}
	
	public LanceBuilder comData(LocalDate data) {
		this.data = data;
		return this;
	}
	
	public LanceBuilder comUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}
	
	public LanceBuilder comLeilao(Leilao leilao) {
		this.leilao = leilao;
		return this;
	}
	
	public Lance criar() {
		return new Lance(valor, data, usuario, leilao);
	}

}

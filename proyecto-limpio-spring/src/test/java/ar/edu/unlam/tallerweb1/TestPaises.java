package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;

public class TestPaises extends SpringTest {

	@Test
	@Transactional
	@Rollback(true)
	public void testPaisesHablaInglesa() {
		Pais españa = new Pais();
		españa.setIdioma("Español");

		Pais inglaterra = new Pais();
		inglaterra.setIdioma("Ingles");

		Pais italia = new Pais();
		italia.setIdioma("Italiano");

		Pais estadosUnidos = new Pais();
		estadosUnidos.setIdioma("Ingles");

		getSession().save(españa);
		getSession().save(inglaterra);
		getSession().save(estadosUnidos);

		List<Pais> paisesHablaInglesa = getSession().createCriteria(Pais.class)
				.add(Restrictions.eq("idioma", "Ingles"))
				.list();
		for (Pais pais : paisesHablaInglesa) {
			assertThat(pais.getIdioma()).isEqualTo("Ingles");
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testPaisesDeEuropa() {
		Continente europa = new Continente();
		europa.setNombre("Europa");
		Continente america = new Continente();
		america.setNombre("America");
		
		Pais argentina = new Pais();
		argentina.setContinente(america);
		
		Pais uruguay = new Pais();
		uruguay.setContinente(america);
	
		Pais inglaterra = new Pais();
		inglaterra.setContinente(europa);

		Pais italia = new Pais();
		italia.setContinente(europa);
		
		getSession().save(argentina);
		getSession().save(uruguay);
		getSession().save(inglaterra);
		getSession().save(italia);
		
		List<Pais> paisesDeEuropa = getSession().createCriteria(Pais.class)
				.add(Restrictions.eq("continente",europa))
				.list();
		
		for (Pais pais : paisesDeEuropa) {
			assertThat(pais.getContinente().getNombre()).isEqualTo("Europa");
		}
	}
}

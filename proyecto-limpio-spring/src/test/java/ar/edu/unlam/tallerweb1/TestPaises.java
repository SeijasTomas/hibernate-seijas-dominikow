package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

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
	@Test
	@Transactional
	@Rollback(true)
	public void testQueBuscaPaisesCuyaCapitalEstaAlNorteDelTropicoDeCancer(){
		Pais argentina = new Pais();
		Pais uruguay = new Pais();
		Pais estadosUnidos = new Pais();
		Pais polonia = new Pais();
		
		Ciudad buenosAires = new Ciudad();
		Ciudad montevideo = new Ciudad();
		Ciudad washington = new Ciudad();
		Ciudad varsovia = new Ciudad();
		
		Ubicacion ubicacionBuenosAires = new Ubicacion();
		Ubicacion ubicacionMontevideo = new Ubicacion();
		Ubicacion ubicacionWashington = new Ubicacion();
		Ubicacion ubicacionVarsovia = new Ubicacion();
		
		ubicacionBuenosAires.setLatitud(-34);
		ubicacionMontevideo.setLatitud(-34);
		ubicacionWashington.setLatitud(38);
		ubicacionVarsovia.setLatitud(52);
		
		buenosAires.setUbicacionGeografica(ubicacionBuenosAires);
		montevideo.setUbicacionGeografica(ubicacionMontevideo);
		washington.setUbicacionGeografica(ubicacionWashington);
		varsovia.setUbicacionGeografica(ubicacionVarsovia);
		
		argentina.setCapital(buenosAires);
		uruguay.setCapital(montevideo);
		estadosUnidos.setCapital(washington);
		polonia.setCapital(varsovia);
		
		getSession().save(argentina);
		getSession().save(uruguay);
		getSession().save(estadosUnidos);
		getSession().save(polonia);
		
		List<Pais> paisesConCapitalAlNorte = getSession().createCriteria(Pais.class)
				.createAlias("capital", "c")
				.createAlias("c.ubicacionGeografica", "ubicacionGeografica")
				.add(Restrictions.gt("ubicacionGeografica.latitud", 23))
				.list();
		
		for(Pais pais : paisesConCapitalAlNorte) {
			assertThat(pais.getCapital().getUbicacionGeografica().getLatitud()>23);
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueBuscaTodasLasCiudadesDelHemisferioSur(){
		Ciudad buenosAires = new Ciudad();
		Ciudad montevideo = new Ciudad();
		Ciudad washington = new Ciudad();
		Ciudad varsovia = new Ciudad();
		
		Ubicacion ubicacionBuenosAires = new Ubicacion();
		Ubicacion ubicacionMontevideo = new Ubicacion();
		Ubicacion ubicacionWashington = new Ubicacion();
		Ubicacion ubicacionVarsovia = new Ubicacion();
		
		ubicacionBuenosAires.setLatitud(-34);
		ubicacionMontevideo.setLatitud(-34);
		ubicacionWashington.setLatitud(38);
		ubicacionVarsovia.setLatitud(52);
		
		buenosAires.setUbicacionGeografica(ubicacionBuenosAires);
		montevideo.setUbicacionGeografica(ubicacionMontevideo);
		washington.setUbicacionGeografica(ubicacionWashington);
		varsovia.setUbicacionGeografica(ubicacionVarsovia);
		
		getSession().save(buenosAires);
		getSession().save(montevideo);
		getSession().save(washington);
		getSession().save(varsovia);
		
		List<Ciudad> ciudadesAlHemisferioSur = getSession().createCriteria(Ciudad.class)
				.createAlias("ubicacionGeografica", "u")
				.add(Restrictions.lt("u.latitud", 0))
				.list();
		
		for(Ciudad ciudad : ciudadesAlHemisferioSur) {
			assertThat(ciudad.getUbicacionGeografica().getLatitud()>0);
		}
	}
	
}

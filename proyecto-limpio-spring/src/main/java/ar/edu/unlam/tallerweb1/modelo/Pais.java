package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private Integer habitantes;
	private String idioma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(Integer habitantes) {
		this.habitantes = habitantes;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private Ciudad capital = new Ciudad();

	public Ciudad getCapital() {
		return capital;
	}

	public void setCapital(Ciudad capital) {
		this.capital = capital;
	}

	@OneToMany(cascade = CascadeType.ALL)
	private List<Ciudad> ciudades = new ArrayList();

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private Continente continente = new Continente();

	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}

}

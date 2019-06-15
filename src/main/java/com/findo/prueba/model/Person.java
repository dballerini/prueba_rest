package com.findo.prueba.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String lastName;
	private String firstName;
	private String aliases;
	
	@OneToMany
	private List<Movie> asAct;
	
	@OneToMany
	private List<Movie> asDirector;
	
	@OneToMany
	private List<Movie> asProducer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public List<Movie> getAsAct() {
		return asAct;
	}

	public void setAsAct(List<Movie> asAct) {
		this.asAct = asAct;
	}

	public List<Movie> getAsDirector() {
		return asDirector;
	}

	public void setAsDirector(List<Movie> asDirector) {
		this.asDirector = asDirector;
	}

	public List<Movie> getAsProducer() {
		return asProducer;
	}

	public void setAsProducer(List<Movie> asProducer) {
		this.asProducer = asProducer;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

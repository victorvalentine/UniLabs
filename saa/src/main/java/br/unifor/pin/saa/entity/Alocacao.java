package br.unifor.pin.saa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unifor.pin.saa.enums.Estado;

@Entity
@Table(name="Alocacao")
public class Alocacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String disciplina;
	
	@ManyToOne
	private Usuarios professor;

	private String observacao;
	
	@ManyToOne
	private Calendario calendario;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Estado situacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Usuarios getProfessor() {
		return professor;
	}

	public void setProfessor(Usuarios professor) {
		this.professor = professor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Estado getSituacao() {
		return situacao;
	}

	public void setSituacao(Estado situacao) {
		this.situacao = situacao;
	}

}

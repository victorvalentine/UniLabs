package br.unifor.pin.saa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Laboratorio")
public class Laboratorio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Integer quantidade_cpus;
	
	@Column(nullable = false)
	private String tecnico;
	
	@ManyToOne
	private PerfilCPU perfil_cpu;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="Lab_Software",
			joinColumns=@JoinColumn(name="lab_id"),
			inverseJoinColumns=@JoinColumn(name="software_id"))
	private List<Software> softwares;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=false, mappedBy="laboratorio",fetch=FetchType.LAZY)
	private List<Calendario> calendarios;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade_cpus() {
		return quantidade_cpus;
	}

	public void setQuantidade_cpus(Integer quantidade_cpus) {
		this.quantidade_cpus = quantidade_cpus;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public PerfilCPU getPerfil_cpu() {
		return perfil_cpu;
	}

	public void setPerfil_cpu(PerfilCPU perfil_cpu) {
		this.perfil_cpu = perfil_cpu;
	}
	
	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}
	
	public List<Calendario> getCalendarios() {
		return calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void adicionaSoftware(Software software){
		if(softwares == null)
			softwares = new ArrayList<Software>();
		softwares.add(software);
	}
	
	public void adicionaCalendario(Calendario calendario){
		if(calendarios == null)
			calendarios = new ArrayList<Calendario>();
		calendarios.add(calendario);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendarios == null) ? 0 : calendarios.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil_cpu == null) ? 0 : perfil_cpu.hashCode());
		result = prime * result + ((quantidade_cpus == null) ? 0 : quantidade_cpus.hashCode());
		result = prime * result + ((softwares == null) ? 0 : softwares.hashCode());
		result = prime * result + ((tecnico == null) ? 0 : tecnico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laboratorio other = (Laboratorio) obj;
		if (calendarios == null) {
			if (other.calendarios != null)
				return false;
		} else if (!calendarios.equals(other.calendarios))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil_cpu == null) {
			if (other.perfil_cpu != null)
				return false;
		} else if (!perfil_cpu.equals(other.perfil_cpu))
			return false;
		if (quantidade_cpus == null) {
			if (other.quantidade_cpus != null)
				return false;
		} else if (!quantidade_cpus.equals(other.quantidade_cpus))
			return false;
		if (softwares == null) {
			if (other.softwares != null)
				return false;
		} else if (!softwares.equals(other.softwares))
			return false;
		if (tecnico == null) {
			if (other.tecnico != null)
				return false;
		} else if (!tecnico.equals(other.tecnico))
			return false;
		return true;
	}

	

}

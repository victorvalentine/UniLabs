package br.unifor.pin.saa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Calendario")
public class Calendario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private Integer mes;
	
	@Column(nullable=false)
	private Date data;
	
	@ManyToOne
	private Laboratorio laboratorio;
	
	@ManyToOne
	private Periodo periodo;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=false, mappedBy="calendario", fetch=FetchType.LAZY)
	private List<Alocacao> alocacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public List<Alocacao> getAlocacoes() {
		return alocacoes;
	}

	public void setAlocacoes(List<Alocacao> alocacoes) {
		this.alocacoes = alocacoes;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public void adicionaAlocacao(Alocacao alocacao){
		if(alocacoes == null)
			alocacoes = new ArrayList<Alocacao>();
		
		alocacoes.add(alocacao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alocacoes == null) ? 0 : alocacoes.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((laboratorio == null) ? 0 : laboratorio.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
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
		Calendario other = (Calendario) obj;
		if (alocacoes == null) {
			if (other.alocacoes != null)
				return false;
		} else if (!alocacoes.equals(other.alocacoes))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (laboratorio == null) {
			if (other.laboratorio != null)
				return false;
		} else if (!laboratorio.equals(other.laboratorio))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		return true;
	}
	
}

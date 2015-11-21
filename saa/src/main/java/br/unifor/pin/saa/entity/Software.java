package br.unifor.pin.saa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Software")
public class Software {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private String nome_software;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getnome_software() {
		return nome_software;
	}
	public void setnome_software(String nome_software) {
		this.nome_software = nome_software;
	}
	
	
	
}

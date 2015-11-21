package br.unifor.pin.saa.manager.software;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.SoftwareBO;
import br.unifor.pin.saa.entity.Software;
import br.unifor.pin.saa.utils.Navigation;
/**
 * @author victor.nobrega
 * 
 */
@RequestScoped
@ManagedBean(name="listSoftware")
@Component(value="listSoftware")
public class ListSoftwareManager {

	@Autowired
	private SoftwareBO softwareBO;
	private String nome_software;
	private List<Software> softwares;
	
	public void listaTodos(){
		
		softwares = softwareBO.listaTodos();
		
	}
	
	public void excluir(Software software){
		softwareBO.removerSoftware(software.getId());
		softwares = softwareBO.listaTodos();
	}
	
	public String preparaAtualizar(Software software){
		System.out.println(software.getnome_software());
		return null;
	}
	
	public String preparaListar(){
		this.limparDados();
		return Navigation.SUCESSO;
	}
	
	public void limparDados(){
		this.nome_software = "";
		this.softwares = null;
	}
	
	
	public String salvar(){
		return null;
	}

	public String getNome() {
		return nome_software;
	}
	public void setNome(String nome_software) {
		this.nome_software = nome_software;
	}

	public List<Software> getSoftware() {
		return softwares;
	}
	public void setUsuarios(List<Software> software) {
		this.softwares = software;
	}
	
}

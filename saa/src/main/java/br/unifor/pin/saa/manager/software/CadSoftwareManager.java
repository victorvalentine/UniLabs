package br.unifor.pin.saa.manager.software;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.SoftwareBO;
import br.unifor.pin.saa.entity.Software;
import br.unifor.pin.saa.utils.MessagesUtils;
import br.unifor.pin.saa.utils.Navigation;

@RequestScoped
@ManagedBean(name = "cadSoftware")
@Component(value = "cadSoftware")
public class CadSoftwareManager {

	@Autowired
	private SoftwareBO sofwareBO;

	private String nome_software;

	public String salvar() {
		Software novoSoftware = new Software();
		novoSoftware.setnome_software(nome_software);

		sofwareBO.salvarSoftware(novoSoftware);
		
		MessagesUtils.info("Software com sucesso!");

		return Navigation.SUCESSO;
	}
	
	public String preparaSalvar(){
		this.limpaDados();
		
		return Navigation.SUCESSO;
	}
	
	public String preparaListar(){
		this.limpaDados();
		return Navigation.SUCESSO;
	}
			
	public void limpaDados(){
		this.nome_software = "";
	}

	public String getNome_software() {
		return nome_software;
	}

	public void setNome_software(String nome_software) {
		this.nome_software = nome_software;
	}
	
	

}

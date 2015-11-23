package br.unifor.pin.saa.manager.perfilcpu;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.PerfilCPUBO;
import br.unifor.pin.saa.entity.PerfilCPU;
import br.unifor.pin.saa.utils.MessagesUtils;
import br.unifor.pin.saa.utils.Navigation;

@RequestScoped
@ManagedBean(name = "cadPerfilCPU")
@Component(value = "cadPerfilCPU")
public class CadPerfilCPUManager {

	@Autowired
	private PerfilCPUBO perfilCPUBO;

	private String nome;
	private String processador;
	private String memoria;
	private String hd;
	
	public String salvar(){ 
		PerfilCPU novoPerfilCPU = new PerfilCPU();
		novoPerfilCPU.setNome(nome);
		novoPerfilCPU.setProcessador(processador);
		novoPerfilCPU.setMemoria(memoria);
		novoPerfilCPU.setHd(hd);
		
		perfilCPUBO.salvarPerfilCPU(novoPerfilCPU);
		
		MessagesUtils.info("Perfil de CPU salvo com sucesso!");
		
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
		this.nome = "";
		this.processador = "";
		this.memoria = "";
		this.hd = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

}

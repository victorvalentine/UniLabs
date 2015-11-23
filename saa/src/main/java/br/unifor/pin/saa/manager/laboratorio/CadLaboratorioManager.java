package br.unifor.pin.saa.manager.laboratorio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.LaboratorioBO;
import br.unifor.pin.saa.entity.Laboratorio;
import br.unifor.pin.saa.entity.PerfilCPU;
import br.unifor.pin.saa.utils.MessagesUtils;
import br.unifor.pin.saa.utils.Navigation;

@RequestScoped
@ManagedBean(name = "cadLaboratorio")
@Component(value = "cadLaboratorio")
public class CadLaboratorioManager {

	@Autowired
	private LaboratorioBO laboratorioBO;

	private String nome;
	private Integer quantidade_cpus;
	private String tecnico;
	private PerfilCPU perfil_cpu;
	
	
	public String salvar(){ 
		Laboratorio novoLaboratorio = new Laboratorio();
		novoLaboratorio.setNome(nome);
		novoLaboratorio.setQuantidade_cpus(quantidade_cpus);
		novoLaboratorio.setTecnico(tecnico);
		novoLaboratorio.setPerfil_cpu(perfil_cpu);
		
		laboratorioBO.salvarLaboratorio(novoLaboratorio);
		
		MessagesUtils.info("Laboratório salvo com sucesso!");
		
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
	//	this.quantidade_cpus = ;
		this.tecnico = "";
	//	this.perfil_cpu = ;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LaboratorioBO getLaboratorioBO() {
		return laboratorioBO;
	}

	public void setLaboratorioBO(LaboratorioBO laboratorioBO) {
		this.laboratorioBO = laboratorioBO;
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

	

}

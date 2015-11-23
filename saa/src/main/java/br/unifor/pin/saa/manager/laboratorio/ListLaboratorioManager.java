package br.unifor.pin.saa.manager.laboratorio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.LaboratorioBO;
import br.unifor.pin.saa.entity.Laboratorio;
import br.unifor.pin.saa.entity.PerfilCPU;
import br.unifor.pin.saa.utils.Navigation;
/**
 * @author victor.nobrega
 * 
 */
@RequestScoped
@ManagedBean(name="listLaboratorio")
@Component(value="listLaboratorio")
public class ListLaboratorioManager {

	@Autowired
	private LaboratorioBO laboratorioBO;
	private String nome;
	private Integer quantidade_cpus;
	private String tecnico;
	private PerfilCPU perfil_cpu;
	private List<Laboratorio> laboratorios;
	
	public void listaTodos(){
		
		laboratorios = laboratorioBO.listaTodos();
		
	}
	
	public void excluir(Laboratorio laboratorio){
		laboratorioBO.removerLaboratorio(laboratorio.getId());
		laboratorios = laboratorioBO.listaTodos();
	}
	
	public String preparaAtualizar(Laboratorio laboratorio){
		System.out.println(laboratorio.getNome());
		return null;
	}
	
	public String preparaListar(){
		this.limparDados();
		return Navigation.SUCESSO;
	}
	
	public void limparDados(){
		this.nome = "";
		this.laboratorios = null;
	}
	
	
	public String salvar(){
		return null;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Laboratorio> getLaboratorio() {
		return laboratorios;
	}
	public void setLaboratorio(List<Laboratorio> laboratorios) {
		this.laboratorios = laboratorios;
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

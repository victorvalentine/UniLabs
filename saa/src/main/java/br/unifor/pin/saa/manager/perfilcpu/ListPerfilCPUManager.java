package br.unifor.pin.saa.manager.perfilcpu;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.PerfilCPUBO;
import br.unifor.pin.saa.entity.PerfilCPU;
import br.unifor.pin.saa.utils.Navigation;
/**
 * @author victor.nobrega
 * 
 */
@RequestScoped
@ManagedBean(name="listPerfilCPU")
@Component(value="listPerfilCPU")
public class ListPerfilCPUManager {

	@Autowired
	private PerfilCPUBO perfilCPUBO;
	private String nome;
	private List<PerfilCPU> perfis;
	
	public void lista(){
		
		perfis = perfilCPUBO.buscaPorNome(nome);
		
	}
	
	public void excluir(PerfilCPU perfil){
		perfilCPUBO.removerPerfilCPU(perfil.getId());
		perfis = perfilCPUBO.buscaPorNome(nome);
	}
	
	public String preparaAtualizar(PerfilCPU perfil){
		System.out.println(perfil.getNome());
		return null;
	}
	
	public String preparaListar(){
		this.limparDados();
		return Navigation.SUCESSO;
	}
	
	public void limparDados(){
		this.nome = "";
		this.perfis = null;
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

	public List<PerfilCPU> getPerfis() {
		return perfis;
	}
	public void setUsuarios(List<PerfilCPU> perfis) {
		this.perfis = perfis;
	}
	
}

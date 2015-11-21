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
@ManagedBean(name = "atualizaPerfilCPU")
@Component(value = "atualizaPerfilCPU")
public class AtualizaPerfilCPUManager {
	
	@Autowired
	private PerfilCPUBO perfilCPUBO;
	private PerfilCPU perfilCPUSelecionado;

	public String atualizar() {
		perfilCPUBO.atualizarPerfilCPU(perfilCPUSelecionado);
		MessagesUtils.info("Perfil da CPU atualizado com sucesso!");

		return Navigation.SUCESSO;
	}

	public String preparaAtualizar(PerfilCPU perfilCPU) {
		perfilCPUSelecionado = perfilCPUBO.buscaPorId(perfilCPU.getId());

		return Navigation.ATUALIZA;
	}
	
	public void limparDados(){
		perfilCPUSelecionado.setNome("");
		perfilCPUSelecionado.setProcessador("");
		perfilCPUSelecionado.setMemoria("");
		perfilCPUSelecionado.setHd("");
		
	}

	public PerfilCPU getPerfilCPUSelecionado() {
		return perfilCPUSelecionado;
	}
	public void setPerfilCPUSelecionado(PerfilCPU perfilCPUSelecionado) {
		this.perfilCPUSelecionado = perfilCPUSelecionado;
	}

}

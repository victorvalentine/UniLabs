package br.unifor.pin.saa.manager.laboratorio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.saa.bussines.LaboratorioBO;
import br.unifor.pin.saa.entity.Laboratorio;
import br.unifor.pin.saa.utils.MessagesUtils;
import br.unifor.pin.saa.utils.Navigation;

@RequestScoped
@ManagedBean(name = "atualizaLaboratorio" )
@Component(value = "atualizaLaboratorio")
public class AtualizaLaboratorioManager {
	
	@Autowired
	private LaboratorioBO laboratorioBO;
	private Laboratorio laboratorioSelecionado;

	public String atualizar() {
		laboratorioBO.atualizarLaboratorio(laboratorioSelecionado);
		MessagesUtils.info("Laboratório atualizado com sucesso!");

		return Navigation.SUCESSO;
	}

	public String preparaAtualizar(Laboratorio laboratorio) {
		laboratorioSelecionado = laboratorioBO.buscarPorId(laboratorio.getId());

		return Navigation.ATUALIZA;
	}
	
	public void limparDados(){
		laboratorioSelecionado.setNome("");
				
	}

	public Laboratorio getLaboratorioSelecionado() {
		return laboratorioSelecionado;
	}
	public void setLaboratorioSelecionado(Laboratorio laboratorioSelecionado) {
		this.laboratorioSelecionado = laboratorioSelecionado;
	}

}

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
@ManagedBean(name = "atualizaSoftware" )
@Component(value = "atualizaSoftware")
public class AtualizaSoftwareManager {
	
	@Autowired
	private SoftwareBO softwareBO;
	private Software softwareSelecionado;

	public String atualizar() {
		softwareBO.atualizarSoftware(softwareSelecionado);
		MessagesUtils.info("Software atualizado com sucesso!");

		return Navigation.SUCESSO;
	}

	public String preparaAtualizar(Software software) {
		softwareSelecionado = softwareBO.buscaPorId(software.getId());

		return Navigation.ATUALIZA;
	}
	
	public void limparDados(){
		softwareSelecionado.setnome_software("");
				
	}

	public Software getSoftwareSelecionado() {
		return softwareSelecionado;
	}
	public void setPerfilCPUSelecionado(Software softwareSelecionado) {
		this.softwareSelecionado = softwareSelecionado;
	}

}

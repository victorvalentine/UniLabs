package br.unifor.pin.saa.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifor.pin.saa.aspectj.Loggable;
import br.unifor.pin.saa.dao.SoftwareDAO;
import br.unifor.pin.saa.entity.Software;

@Loggable
@Service
public class SoftwareBO {

	@Autowired
	private SoftwareDAO softwareDAO;
	
	public void salvarSoftware(Software sofware){
		softwareDAO.salvar(sofware);
	}
	
	public void atualizarSoftware(Software software){
		softwareDAO.atualizar(software);
	}
	
	public void removerSoftware(Integer id){
		softwareDAO.excluir(id);
	}
	
	public List<Software> buscaPorNome(String nome_software){
		return softwareDAO.buscarPorNome(nome_software);
	}
	
	public Software buscaPorId(Integer id){
		return softwareDAO.buscarPorId(id);
	}
	
	public List<Software> listaTodos(){
		return softwareDAO.listaTodos();
	}
	
}

package br.unifor.pin.saa.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifor.pin.saa.aspectj.Loggable;
import br.unifor.pin.saa.dao.LaboratorioDAO;
import br.unifor.pin.saa.entity.Laboratorio;

@Loggable
@Service
public class LaboratorioBO {

	@Autowired
	private LaboratorioDAO laboratorioDAO;
	
	public void salvarLaboratorio(Laboratorio laboratorio){
		laboratorioDAO.salvar(laboratorio);
	}
	
	public void atualizarLaboratorio(Laboratorio laboratorio){
		laboratorioDAO.atualizar(laboratorio);
	}
	
	public void removerLaboratorio(Integer id){
		laboratorioDAO.excluir(id);
	}
	
	public List<Laboratorio> buscaPorNome(String nome){
		return laboratorioDAO.buscarPorNome(nome);
	}
	
	public Laboratorio buscarPorId(Integer id){
		return laboratorioDAO.buscarPorId(id);
	}
	
	public List<Laboratorio> listaTodos(){
		return laboratorioDAO.listaTodos();
	}
	
}

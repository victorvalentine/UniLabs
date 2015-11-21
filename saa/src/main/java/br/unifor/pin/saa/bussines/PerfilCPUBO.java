package br.unifor.pin.saa.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifor.pin.saa.aspectj.Loggable;
import br.unifor.pin.saa.dao.PerfilCPUDAO;
import br.unifor.pin.saa.entity.PerfilCPU;

@Loggable
@Service
public class PerfilCPUBO {
	
	@Autowired
	private PerfilCPUDAO perfilCpuDAO;
	
	public void salvarPerfilCPU(PerfilCPU perfil){
		perfilCpuDAO.salvar(perfil);
	}
	
	public void atualizarPerfilCPU(PerfilCPU perfil){
		perfilCpuDAO.atualizar(perfil);
	}
	
	public void removerPerfilCPU(Integer id){
		perfilCpuDAO.excluir(id);
	}
	
	public List<PerfilCPU> buscaPorNome(String nome){
		return perfilCpuDAO.buscarPorNome(nome);	
	}
	
	public PerfilCPU buscaPorId(Integer id){
		return perfilCpuDAO.buscarPorId(id);
	}
	
}

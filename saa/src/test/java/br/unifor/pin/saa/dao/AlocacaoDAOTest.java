package br.unifor.pin.saa.dao;

import java.sql.Time;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.entity.Alocacao;
import br.unifor.pin.saa.entity.Calendario;
import br.unifor.pin.saa.entity.Laboratorio;
import br.unifor.pin.saa.entity.PerfilCPU;
import br.unifor.pin.saa.entity.Periodo;
import br.unifor.pin.saa.enums.Estado;
import br.unifor.pin.saa.exceptions.DAOException;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional(propagation = Propagation.REQUIRED)
public class AlocacaoDAOTest {

	@Autowired
	private AlocacaoDAO alocacaoDao;
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private PeriodoDAO periodoDao;
	@Autowired
	private PerfilCPUDAO perfilCpuDao;
	@Autowired
	private LaboratorioDAO labDao;
	@Autowired
	private CalendarioDAO calendarioDao;

	@Test
	public void testaSeEstaSalvando() throws DAOException {
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);

		periodoDao.salvar(periodo);

		Alocacao alocacao = new Alocacao();
		alocacao.setDisciplina("Calculo III");
		alocacao.setObservacao("Uso de datashow");
		alocacao.setProfessor(usuarioDao.buscaPorId(2));
		alocacao.setSituacao(Estado.LIVRE);
		alocacaoDao.salvar(alocacao);

		Assert.assertNotNull("Testa se id ainda está nulo.", alocacao.getId());
	}
	
	@Test
	public void testaSeEstaSalvandoComCalendario() throws DAOException{
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		periodoDao.salvar(periodo);
		
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		Laboratorio lab = new Laboratorio();
		lab.setNome("M35");
		lab.setTecnico("Victor");
		lab.setPerfil_cpu(perfil);
		lab.setQuantidade_cpus(20);
		labDao.salva(lab);
		
		
		Calendario calendario = new Calendario();
		calendario.setData(new Date());
		calendario.setLaboratorio(lab);
		calendario.setMes(11);
		calendario.setPeriodo(periodo);
		calendarioDao.salva(calendario);

		Alocacao alocacao = new Alocacao();
		alocacao.setDisciplina("Calculo III");
		alocacao.setObservacao("Uso de datashow");
		alocacao.setProfessor(usuarioDao.buscaPorId(2));
		alocacao.setCalendario(calendario);
		alocacao.setSituacao(Estado.PENDENTE);
		alocacaoDao.salvar(alocacao);
		
		Alocacao alo2 = alocacaoDao.buscarPorId(alocacao.getId());
		
		Assert.assertNotNull("Verifica se objeto não está vazio.", alo2);
		Assert.assertNotNull("Verifica se trouxe calendario.", alo2.getCalendario());
		
	}
	
	@Test
	public void verificaSeExcluiComCalendario() throws DAOException{
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		periodoDao.salvar(periodo);
		
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		Laboratorio lab = new Laboratorio();
		lab.setNome("M35");
		lab.setTecnico("Victor");
		lab.setPerfil_cpu(perfil);
		lab.setQuantidade_cpus(20);
		labDao.salva(lab);
		
		
		Calendario calendario = new Calendario();
		calendario.setData(new Date());
		calendario.setLaboratorio(lab);
		calendario.setMes(11);
		calendario.setPeriodo(periodo);
		calendarioDao.salva(calendario);

		Alocacao alocacao = new Alocacao();
		alocacao.setDisciplina("Calculo III");
		alocacao.setObservacao("Uso de datashow");
		alocacao.setProfessor(usuarioDao.buscaPorId(2));
		alocacao.setCalendario(calendario);
		alocacao.setSituacao(Estado.PENDENTE);
		alocacaoDao.salvar(alocacao);
		
		
		
		alocacaoDao.excluir(alocacao.getId());
		
		Alocacao alo2 = alocacaoDao.buscarPorId(alocacao.getId());
		
		Calendario cal2 = calendarioDao.findById(calendario.getId());
		
		Assert.assertNull("Objeto deve está nulo. " , alo2);
		Assert.assertNotNull("Objeto ainda deve existir." , cal2);
	
	}

	

}

package br.unifor.pin.saa.dao;

import java.sql.Time;
import java.util.Date;

import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional(propagation=Propagation.REQUIRED)
public class CalendarioDAOTest {

	@Autowired
	private CalendarioDAO calendarioDao;
	@Autowired
	private LaboratorioDAO labDao;
	@Autowired
	private PeriodoDAO periodoDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private AlocacaoDAO alocacaoDao;
	@Autowired
	private PerfilCPUDAO perfilCpuDao;
	
	@Test
	public void testaSeSalvaCalendario(){
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		
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
		
		Assert.assertNotNull("Verifica se o id do objeto foi preenchido pelo hibernate.", calendario.getId());
		
	}
	@Test
	public void testaSeAtualiza(){
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		
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
		
		Calendario cal2 = calendarioDao.findById(calendario.getId());
		cal2.setMes(10);
		
		calendarioDao.update(cal2);
		
		Calendario cal3 = calendarioDao.findById(cal2.getId());
		
		Assert.assertEquals("verifia se atualizou.", cal2, cal3);
		
	}
	@Test
	public void testaSeExclui(){
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
		
		calendarioDao.delete(calendario.getId());
		
		Calendario cal2 = calendarioDao.findById(calendario.getId());
		
		Assert.assertNull("objeto tem que está vazio.", cal2);
	}
	
	@Test
	public void verificaSeTrazTodasAlocacoes() throws DAOException{
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
		calendario.adicionaAlocacao(alocacao);
		
		Alocacao alocacao2 = new Alocacao();
		alocacao2.setDisciplina("Algebra Linear");
		alocacao2.setObservacao("Uso de datashow");
		alocacao2.setProfessor(usuarioDao.buscaPorId(2));
		alocacao2.setCalendario(calendario);
		alocacao2.setSituacao(Estado.PENDENTE);
		calendario.adicionaAlocacao(alocacao2);
		
		
		calendarioDao.update(calendario);
		
		Calendario cal2 = calendarioDao.findById(calendario.getId());
		
		Assert.assertNotNull("Objeto não deve está nulo.", cal2);
		Assert.assertNotNull("propriedade não deve está nula.", cal2.getAlocacoes());
		Assert.assertFalse("propriedade não deve está vazia", cal2.getAlocacoes().isEmpty());
		Assert.assertEquals("tamanho deve ser 2.", cal2.getAlocacoes().size(), 2);
		
	}
	
	@Test
	public void verificaOsLabs(){
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
		
		Calendario calendario2 = new Calendario();
		calendario2.setData(new Date());
		calendario2.setLaboratorio(lab);
		calendario2.setMes(11);
		calendario2.setPeriodo(periodo);
		calendarioDao.salva(calendario);
		
		Laboratorio lab2 = labDao.findById(lab.getId());
		
		Assert.assertNotNull("Objeto não deve está nulo.", lab2);
		Assert.assertNotNull("propriedade não deve está nula.", lab2.getCalendarios());
		Assert.assertFalse("propriedade não deve está vazia", lab2.getCalendarios().isEmpty());
		Assert.assertEquals("tamanho deve ser 2.", lab2.getCalendarios().size(), 2);
	}
	
}

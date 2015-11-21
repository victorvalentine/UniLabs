package br.unifor.pin.saa.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.entity.Laboratorio;
import br.unifor.pin.saa.entity.PerfilCPU;
import br.unifor.pin.saa.entity.Software;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional(propagation=Propagation.REQUIRED)
public class LaboratorioDAOTest {

	@Autowired
	private LaboratorioDAO labDao;
	
	@Autowired
	private SoftwareDAO softDao;
	
	@Autowired
	private PerfilCPUDAO perfilCpuDao;
	
	@Test
	public void testaSeSalvaLab(){
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
		
		Assert.assertNotNull("Verifica se id está nulo.", lab.getId());
		
	}
	
	@Test
	public void testeSeAtualizaLab(){
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
		
		lab.setNome("K09");
		labDao.update(lab);
		
		Laboratorio lab2 = labDao.findById(lab.getId());
		Assert.assertNotNull("Testa se achou.", lab2);
		Assert.assertEquals("Verifica se tem mesmo nome.", lab2.getNome(), "K09");
		
	}
	
	@Test
	public void testaSeRemove(){
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
		
		labDao.delete(lab.getId());
		
		Laboratorio lab2 = labDao.findById(lab.getId());
		
		Assert.assertNull("Ve se esta trazendo lab.", lab2);
	}
	
	@Test
	public void testaSeAchaPorId(){
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
		Laboratorio lab2 = labDao.findById(lab.getId());
		
		Assert.assertNotNull("Ve se esta trazendo lab.", lab2);
	}
	
	@Test
	public void testaSeAchaPorNome(){
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
		
		List<Laboratorio> labs = labDao.findByName(lab.getNome());
		Assert.assertNotNull("Verfica se esta nulo.", labs);
		Assert.assertFalse("Esta vazio.", labs.isEmpty());
		Assert.assertTrue("Verifica se é o mesmo.", labs.contains(lab));
	}
	@Test
	public void testaSeRetornaSoftwares(){
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		
		Software software = new Software();
		software.setnome_software("UNITY");
		softDao.salvar(software);
		
		Laboratorio lab = new Laboratorio();
		lab.setNome("M35");
		lab.setTecnico("Victor");
		lab.setPerfil_cpu(perfil);
		lab.setQuantidade_cpus(20);
		lab.adicionaSoftware(software);
		labDao.salva(lab);
		
		
		Laboratorio teste = labDao.findById(lab.getId());
		
		Assert.assertNotNull("encontrou objeto.", teste);
		Assert.assertFalse("software foi vinculado.", teste.getSoftwares().isEmpty());
		
	}
	
}
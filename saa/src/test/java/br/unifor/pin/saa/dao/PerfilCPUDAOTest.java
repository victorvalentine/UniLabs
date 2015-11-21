package br.unifor.pin.saa.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.dao.PerfilCPUDAO;
import br.unifor.pin.saa.entity.PerfilCPU;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional(propagation=Propagation.REQUIRED)
public class PerfilCPUDAOTest {
	
	@Autowired
	private PerfilCPUDAO perfilCpuDao;
	
	@Test
	public void testaSeEstaSalvando(){
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		Assert.assertNotNull("Testa se id ainda está nulo.", perfil.getId());
	}
	
	@Test
	public void testaSeDaUpdate(){
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		perfil.setNome("ASUS - Foda");
		perfilCpuDao.atualizar(perfil);
		
		PerfilCPU p2 = perfilCpuDao.buscarPorId(perfil.getId());
		Assert.assertNotNull("Testa se achou.", p2);
		Assert.assertEquals("Verifica se tem mesmo nome.", p2.getNome(), "ASUS - Foda");
		
	}
	
	@Test
	public void testaSeAchaById(){
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		PerfilCPU p2 = perfilCpuDao.buscarPorId(perfil.getId());
		Assert.assertEquals("Verifica se é o mesmo.", p2,perfil);
	}
	
	@Test
	public void testaSeDeleta(){
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		perfilCpuDao.excluir(perfil.getId());
		PerfilCPU p2 = perfilCpuDao.buscarPorId(perfil.getId());
		
		Assert.assertNull("Ve se esta trazendo perfil.", p2);
		
	}
	
	@Test
	public void testaSeAchaPorNome(){
		PerfilCPU perfil = new PerfilCPU();
		perfil.setNome("G1 Victor");
		perfil.setHd("Seagate 1 terabyte");
		perfil.setMemoria("6 gigabyte jigsaw");
		perfil.setProcessador("FX-8350 AMD");
		perfilCpuDao.salvar(perfil);
		
		List<PerfilCPU> perfis = perfilCpuDao.buscarPorNome(perfil.getNome());
		Assert.assertNotNull("Verfica se esta nulo.", perfis);
		Assert.assertFalse("Esta vazio.", perfis.isEmpty());
		Assert.assertTrue("Verifica se é o mesmo.", perfis.contains(perfil));
	}

}

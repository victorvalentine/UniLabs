package br.unifor.pin.saa.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.entity.Software;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional(propagation=Propagation.REQUIRED)
public class SoftwareDAOTest {
	
	@Autowired
	private SoftwareDAO softDao;

	@Test
	public void testaSeTrazTodos(){
		Software software = new Software();
		software.setnome_software("Unity");
		
		Software software1 = new Software();
		software1.setnome_software("Visual Studio");
		
		Software software2 = new Software();
		software2.setnome_software("Java");
		
		Software software3 = new Software();
		software3.setnome_software("Office 2002");
	
		
		softDao.salvar(software);
		softDao.salvar(software1);
		softDao.salvar(software2);
		softDao.salvar(software3);
		
		List<Software> softs = softDao.listaTodos();
		
		Assert.assertNotNull("verifica se array está nulo.", softs);
		Assert.assertFalse("Verifica se array está vazio.", softs.isEmpty());
		
	}
}

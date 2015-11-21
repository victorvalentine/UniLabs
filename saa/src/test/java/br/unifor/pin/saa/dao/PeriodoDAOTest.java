package br.unifor.pin.saa.dao;

import java.sql.Time;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.entity.Periodo;
import br.unifor.pin.saa.exceptions.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@Transactional(propagation = Propagation.REQUIRED)
public class PeriodoDAOTest {

	@Autowired
	private PeriodoDAO periodoDao;

	@Test
	public void testaSeEstaSalvando() throws DAOException {
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);

		periodoDao.salvar(periodo);

		Assert.assertNotNull("verifica se o id do periodo está nulo.", periodo.getId());

	}
	
	@Test
	public void testaSeDaUpdate() throws DAOException{
		Periodo periodo = new Periodo();
		periodo.setHorario("AB");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		
		periodoDao.salvar(periodo);
		
		periodo.setHorario("CD");
		periodoDao.atualizar(periodo);
		
		Periodo p2 = periodoDao.buscarPorId(periodo.getId());
		Assert.assertNotNull("Testa se achou.", p2);
		Assert.assertEquals("Verifica se tem mesmo nome.", p2.getHorario(), "CD");
		
	}
	
	@Test
	public void testaSeAchaById() throws DAOException{
		Periodo periodo = new Periodo();
		periodo.setHorario("Uso de datashow");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		
		periodoDao.salvar(periodo);
		
		Periodo p2 = periodoDao.buscarPorId(periodo.getId());
		Assert.assertEquals("Verifica se é o mesmo.", p2,periodo);
	}
	
	@Test
	public void testaSeDeleta() throws DAOException{
		Periodo periodo = new Periodo();
		periodo.setHorario("Uso de datashow");
		Time agora = new Time(System.currentTimeMillis());
		periodo.setHora(agora);
		
		periodoDao.salvar(periodo);
		
		periodoDao.excluir(periodo.getId());
		Periodo p2 = periodoDao.buscarPorId(periodo.getId());
		
		Assert.assertNull("Ve se esta trazendo perfil.", p2);
		
	}
	
	
}

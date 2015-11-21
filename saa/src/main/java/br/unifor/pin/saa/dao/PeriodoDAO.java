package br.unifor.pin.saa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.entity.Periodo;



@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class PeriodoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	public void salvar(Periodo periodo){
		entityManager.persist(periodo);
	}
	
	
	public void atualizar(Periodo periodo){
		entityManager.merge(periodo);
	}
	
	public void excluir(Long id){
		entityManager.remove(buscarPorId(id));
	}
	
	public List<Periodo> buscarPorNome(String nome){
		getSession();
		Criteria criteria = session.createCriteria(Periodo.class);
		criteria.add(Restrictions.like("nome", nome));
		
		return criteria.list(); 
	}
	
	public List<Periodo> listaTodos(){
		getSession();
		Criteria criteria = session.createCriteria(Periodo.class);
		return criteria.list();
	}
	
	public Periodo buscarPorId(Long id){
		return (Periodo)entityManager.find(Periodo.class, id);
	}

}

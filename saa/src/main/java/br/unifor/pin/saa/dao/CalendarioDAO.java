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

import br.unifor.pin.saa.entity.Calendario;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CalendarioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	public void salva(Calendario calendario){
		entityManager.persist(calendario);
	}
	
	public void update(Calendario calendario){
		entityManager.merge(calendario);
	}
	
	public void delete(Long id){
		entityManager.remove(findById(id));
	}
	
	public Calendario findById(Long id){
		return (Calendario)entityManager.find(Calendario.class, id);
	}
	
	public List<Calendario> findByMes(Integer mes){
		getSession();
		Criteria criteria = session.createCriteria(Calendario.class);
		criteria.add(Restrictions.eq("mes", mes));
		
		return criteria.list();
	}
	
}

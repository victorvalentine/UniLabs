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

import br.unifor.pin.saa.entity.Laboratorio;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LaboratorioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	public void salva(Laboratorio lab){
		entityManager.persist(lab);
	}
	
	public void update(Laboratorio lab){
		entityManager.merge(lab);
	}
	
	public void delete(Integer id){
		entityManager.remove(findById(id));
	}
	
	public Laboratorio findById(Integer id){
		return (Laboratorio)entityManager.find(Laboratorio.class, id);
	}
	
	public List<Laboratorio> findByName(String nome){
		getSession();
		Criteria criteria = session.createCriteria(Laboratorio.class);
		criteria.add(Restrictions.like("nome", nome));
		
		return criteria.list(); 
	}

}

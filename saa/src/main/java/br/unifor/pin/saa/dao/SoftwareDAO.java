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

import br.unifor.pin.saa.entity.Software;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class SoftwareDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	public void salvar(Software software){
		entityManager.persist(software);
	}
	
	public void atualizar(Software software){
		entityManager.merge(software);
	}
	
	public void excluir(Integer id){
		entityManager.remove(buscarPorId(id));
	}
	
	public Software buscarPorId(Integer id){
		return (Software)entityManager.find(Software.class, id);
	}
	
	public List<Software> listaTodos(){
		getSession();
		Criteria criteria = session.createCriteria(Software.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Software> buscarPorNome(String nome_software){
		getSession();
		Criteria criteria = session.createCriteria(Software.class);
		criteria.add(Restrictions.like("nome_software", nome_software));
		
		return criteria.list(); 
	}
}

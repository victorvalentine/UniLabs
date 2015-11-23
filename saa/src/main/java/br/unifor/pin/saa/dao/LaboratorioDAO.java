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
import br.unifor.pin.saa.entity.Software;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LaboratorioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	public void salvar(Laboratorio lab){
		entityManager.persist(lab);
	}
	
	public void atualizar(Laboratorio lab){
		entityManager.merge(lab);
	}
	
	public void excluir(Integer id){
		entityManager.remove(buscarPorId(id));
	}
	
	public Laboratorio buscarPorId(Integer id){
		return (Laboratorio)entityManager.find(Laboratorio.class, id);
	}
	
	public List<Laboratorio> buscarPorNome(String nome){
		getSession();
		Criteria criteria = session.createCriteria(Laboratorio.class);
		criteria.add(Restrictions.like("nome", nome));
		
		return criteria.list(); 
	}
	
	public List<Laboratorio> listaTodos(){
		getSession();
		Criteria criteria = session.createCriteria(Laboratorio.class);
		return criteria.list();
	}

}

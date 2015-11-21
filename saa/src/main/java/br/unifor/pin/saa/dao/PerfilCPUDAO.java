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

import br.unifor.pin.saa.entity.PerfilCPU;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class PerfilCPUDAO {

	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	
	public void salvar(PerfilCPU perfil){
		entityManager.persist(perfil);
	}
	
	
	public void atualizar(PerfilCPU perfil){
		entityManager.merge(perfil);
	}
	
	public void excluir(Integer id){
		entityManager.remove(buscarPorId(id));
	}
	
	public List<PerfilCPU> buscarPorNome(String nome){
		getSession();
		Criteria criteria = session.createCriteria(PerfilCPU.class);
		criteria.add(Restrictions.like("nome", nome));
		
		return criteria.list(); 
	}
	
	public List<PerfilCPU> listaTodos(){
		getSession();
		Criteria criteria = session.createCriteria(PerfilCPU.class);
		return criteria.list();
	}
	
	public PerfilCPU buscarPorId(Integer id){
		return (PerfilCPU)entityManager.find(PerfilCPU.class, id);
	}
 }

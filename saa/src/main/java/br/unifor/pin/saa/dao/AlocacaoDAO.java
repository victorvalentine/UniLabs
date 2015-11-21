package br.unifor.pin.saa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.saa.entity.Alocacao;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AlocacaoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private Session session;
	
	private void getSession(){
		session = entityManager.unwrap(Session.class);
	}
	
	public void salvar(Alocacao alocacao){
		entityManager.persist(alocacao);
	}
	
	public void atualizar(Alocacao alocacao){
		entityManager.merge(alocacao);
	}
	
	public void excluir(Long id){
		entityManager.remove(buscarPorId(id));
		
	}

	public Alocacao buscarPorId(Long id){
		return (Alocacao)entityManager.find(Alocacao.class, id);
	}
	
	public List<Alocacao> listaTodos(){
		getSession();
		Criteria criteria = session.createCriteria(Alocacao.class);
		return criteria.list();
	}

	
}

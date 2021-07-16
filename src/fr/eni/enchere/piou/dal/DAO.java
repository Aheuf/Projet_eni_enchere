package fr.eni.enchere.piou.dal;

import java.util.List;

import fr.eni.enchere.piou.BusinessException;

public interface DAO<T> {

	void insert(T object) throws BusinessException;
	
	void delete(int id) throws BusinessException;
	
	List<T> selectAll() throws BusinessException;
	 
	List<T> selectByMotCle(String montCle) throws BusinessException;
	
	List<T> selectById(int id) throws BusinessException;
	
	void update(T object) throws BusinessException;
}

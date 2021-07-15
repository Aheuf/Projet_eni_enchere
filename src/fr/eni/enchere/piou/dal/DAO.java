package fr.eni.enchere.piou.dal;

import java.util.List;

public interface DAO<T> {

	void insert(T object);
	
	void delete(int id);
	
	List<T> selectAll();
	
	List<T> selectByMotCle(String montCle);
	
	List<T> selectById(int id);
	
	void update(T object);
}

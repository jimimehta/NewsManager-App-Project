package net.newsmanager.springboot.dao;

import java.util.List;
import java.util.Optional;



import net.newsmanager.springboot.model.News;

public interface NewsDao {

	int save(News newss);

	int update(News newss);

	List<News> findAll();

	Optional<News> findById(Long id);

	int delete(Long id);

	List<News> findByAuthor(String name);

}

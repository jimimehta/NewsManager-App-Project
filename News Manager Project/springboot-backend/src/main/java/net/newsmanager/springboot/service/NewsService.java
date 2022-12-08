package net.newsmanager.springboot.service;

import java.util.List;
import java.util.Optional;

import net.newsmanager.springboot.model.News;

public interface NewsService {

	public List<News> getAllNews();
	
	public News getNewsById(Long id);
	
	public void deleteNews(Long id);
	
	public void createNews(News newss);

	public void updateNews(News newss);

	public List<News> findNewsByAuthor(String name);
}

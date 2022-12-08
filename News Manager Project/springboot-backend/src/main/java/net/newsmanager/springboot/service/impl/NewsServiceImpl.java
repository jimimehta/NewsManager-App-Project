package net.newsmanager.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.newsmanager.springboot.model.News;
import net.newsmanager.springboot.dao.impl.*;
import net.newsmanager.springboot.service.NewsService;



@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDaoImpl newsDao;

	
	@Override
	public List<News> getAllNews() {

		return newsDao.findAll();
	}

	@Override
	public News getNewsById(Long id) {

		News newss = new News();
		Optional<News> existingNews = newsDao.findById(id);
		if (existingNews.isPresent())
			newss = existingNews.get();
		return newss;
	}

	@Override
	public void deleteNews(Long id) {
		newsDao.delete(id);
	}
	
	
	@Override
	public void createNews(News newss) {
		newsDao.save(newss);
	}
	@Override
	public void updateNews(News newss) {
		newsDao.update(newss);
	}

	@Override
	public List<News> findNewsByAuthor(String name) {
		List<News> newss = newsDao.findByAuthor(name);
		return newss;
		
	}

	
	
	
	
	
	

	

	


}

package net.newsmanager.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import net.newsmanager.springboot.exception.ResourceNotFoundException;
import net.newsmanager.springboot.model.News;
import net.newsmanager.springboot.repository.NewsRepository;
import net.newsmanager.springboot.service.impl.NewsServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class NewsController {

	@Autowired
	private NewsServiceImpl newsRepository;

	@Autowired
	private NewsRepository nR;

	//Implementation using Resultset

	// get all news
	@GetMapping("/news")
	public List<News> getAllNews(){
		try {
			return newsRepository.getAllNews();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}		

	// get news by id rest api
	@GetMapping("/news/{id}")
	public ResponseEntity<?> getNewsById(@PathVariable("id") long id) {

		try {
			News newss = newsRepository.getNewsById(id);
			return new ResponseEntity<>(newss, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	// Delete news by id rest api
	@DeleteMapping("/news/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable("id") long id){
		try {
			newsRepository.deleteNews(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	// Create news by id rest api
	@PostMapping("/news")
	public ResponseEntity<?> createNews(@RequestBody News newss) {
		try {
			newsRepository.createNews(newss);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	//Update news by id rest api
	@PutMapping("/news/{id}")
	public ResponseEntity<?> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
		try {
			News newss = newsRepository.getNewsById(id);
			newss.setAuthor(newsDetails.getAuthor());
			newss.setDate(newsDetails.getDate());
			newss.setDescription(newsDetails.getDescription());
			newss.setHeadlines(newsDetails.getHeadlines());
			newss.setRead_more(newsDetails.getRead_more());
			newss.setArticle(newsDetails.getArticle());

			newsRepository.updateNews(newss);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	// get news by id rest api
	@GetMapping("/news/search")
	public ResponseEntity<?> findNewsByAuthor(@Param("author") String author) {
		List<News> newss =  newsRepository.findNewsByAuthor(author);
		return new ResponseEntity<>(newss, HttpStatus.OK);
	}

	//Implementation using JPA

	//		//get all news
	//		@GetMapping("/news")
	//		public List<News> getAllNews(){
	//			return nR.findAll();
	//		}		
	//		
	//		// create news rest api
	//		@PostMapping("/news")
	//		public News createNews(@RequestBody News newss) {
	//			return nR.save(newss);
	//		}
	//		
	//		// get news by id rest api
	//		@GetMapping("/news/{id}")
	//		public ResponseEntity<News> getNewsById(@PathVariable Long id) {
	//			News newss = nR.findById(id)
	//					.orElseThrow(() -> new ResourceNotFoundException("News does not exist with id :" + id));
	//			return ResponseEntity.ok(newss);
	//		}
	//		
	//		// update news rest api
	//		@PutMapping("/news/{id}")
	//		public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails){
	//			News newss = nR.findById(id)
	//					.orElseThrow(() -> new ResourceNotFoundException("News does not exist with id :" + id));
	//			
	//			newss.setAuthor(newsDetails.getAuthor());
	//			newss.setDate(newsDetails.getDate());
	//			newss.setDescription(newsDetails.getDescription());
	//			newss.setHeadlines(newsDetails.getHeadlines());
	//			newss.setRead_more(newsDetails.getRead_more());
	//			newss.setArticle(newsDetails.getArticle());
	//			
	//			News updatedNews = nR.save(newss);
	//			return ResponseEntity.ok(updatedNews);
	//		}
	//		
	//		// delete news rest api
	//		@DeleteMapping("/news/{id}")
	//		public ResponseEntity<Map<String, Boolean>> deleteNews(@PathVariable Long id){
	//			News newss = nR.findById(id)
	//					.orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
	//			
	//			nR.delete(newss);
	//			Map<String, Boolean> response = new HashMap<>();
	//			response.put("deleted", Boolean.TRUE);
	//			return ResponseEntity.ok(response);
	//		}
	//
	//		
	//		
	@GetMapping("/news/authors")
	public ResponseEntity<List<News>> getAllAuthor(@RequestParam(required = false) String author) {
		try {

			List<News> newss = new ArrayList<News>();
			if (author == null)
				newss.forEach(newss::add);
			else
				nR.findByAuthorContaining(author).forEach(newss::add);
			if (newss.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(newss, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

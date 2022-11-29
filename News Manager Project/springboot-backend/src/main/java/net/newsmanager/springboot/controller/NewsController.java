package net.newsmanager.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;
	
	// get all news
	@GetMapping("/news")
	public List<News> getAllNews(){
		return newsRepository.findAll();
	}		
	
	// create news rest api
	@PostMapping("/news")
	public News createNews(@RequestBody News newss) {
		return newsRepository.save(newss);
	}
	
	// get news by id rest api
	@GetMapping("/news/{id}")
	public ResponseEntity<News> getNewsById(@PathVariable Long id) {
		News newss = newsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
		return ResponseEntity.ok(newss);
	}
	
	// update news rest api
	@PutMapping("/news/{id}")
	public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails){
		News newss = newsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
		
		newss.setAuthor(newsDetails.getAuthor());
		newss.setDate(newsDetails.getDate());
		newss.setDescription(newsDetails.getDescription());
		newss.setHeadlines(newsDetails.getHeadlines());
		newss.setRead_more(newsDetails.getRead_more());
		newss.setArticle(newsDetails.getArticle());
		
		News updatedNews = newsRepository.save(newss);
		return ResponseEntity.ok(updatedNews);
	}
	
	// delete news rest api
	@DeleteMapping("/news/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteNews(@PathVariable Long id){
		News newss = newsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
		
		newsRepository.delete(newss);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	
	
	@GetMapping("/news/authors")
	  public ResponseEntity<List<News>> getAllAuthor(@RequestParam(required = false) String author) {
	    try {
	      List<News> newss = new ArrayList<News>();
	      if (author == null)
	        newss.forEach(newss::add);
	      else
	        newsRepository.findByAuthorContaining(author).forEach(newss::add);
	      if (newss.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(newss, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	
	
	
}

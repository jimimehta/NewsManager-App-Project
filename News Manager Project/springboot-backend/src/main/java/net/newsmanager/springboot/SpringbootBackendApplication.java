package net.newsmanager.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.BeanProperty;

import net.newsmanager.springboot.model.News;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		String sql="Select * from newsmanager";
//		List<News> news = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(News.class));
//		
//		news.forEach(System.out :: println);
	}

}

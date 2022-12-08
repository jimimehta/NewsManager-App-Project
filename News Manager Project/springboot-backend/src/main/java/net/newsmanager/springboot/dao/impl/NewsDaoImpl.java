package net.newsmanager.springboot.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.newsmanager.springboot.dao.NewsDao;
import net.newsmanager.springboot.dao.rowmapper.NewsRowMapper;
import net.newsmanager.springboot.model.News;



@Repository
public class NewsDaoImpl implements NewsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String INSERT_NEWS_QUERY = "INSERT INTO newsmanager(author,date,headlines,read_more,description,article) VALUES(?,?,?,?,?,?)";
	private final String UPDATE_NEWS_QUERY = "UPDATE newsmanager SET author=?,date=?,headlines=?,read_more=?,description=?,article=? WHERE id=?";
	private final String DELETE_NEWS_QUERY = "DELETE FROM newsmanager WHERE id=?";
	private final String GET_NEWS_BY_ID_QUERY = "SELECT * FROM newsmanager where id = ?";
	private final String GET_NEWS_BY_AUTHOR_QUERY = "SELECT * FROM newsmanager n where n.author = ?";
	private final String GET_NEWSS_QUERY = "SELECT * FROM newsmanager";

	
	
	@Override
	public int save(News newss) {

		return jdbcTemplate.update(INSERT_NEWS_QUERY, new Object[] { newss.getAuthor(),
			 newss.getDate(), newss.getHeadlines(), newss.getDescription(), newss.getRead_more(),newss.getArticle() });
	}

	@Override
	public int update(News newss) {

		return jdbcTemplate.update(UPDATE_NEWS_QUERY, new Object[] { 
				newss.getAuthor(), newss.getDate(), newss.getHeadlines(), newss.getDescription(), newss.getRead_more(),newss.getArticle(), newss.getId()});
	}

	@Override
	public int delete(Long id) {

		return jdbcTemplate.update(DELETE_NEWS_QUERY, new Object[] { id });
	}

	@Override
	public List<News> findAll() {

		return jdbcTemplate.query(GET_NEWSS_QUERY, new NewsRowMapper());
	}

	@Override
	public Optional<News> findById(Long id) {

		return Optional.of(jdbcTemplate.queryForObject(GET_NEWS_BY_ID_QUERY, new NewsRowMapper(), new Object[] {id}));
	}

	
	@Override
	public List<News> findByAuthor(String name) {
		
		return jdbcTemplate.query(GET_NEWS_BY_AUTHOR_QUERY, new NewsRowMapper(), new Object[] {name});
	}
	
	
	
	
}

package net.newsmanager.springboot.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.newsmanager.springboot.model.News;



public class NewsRowMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int rowNum) throws SQLException {

		News newss = new News();
		newss.setId(rs.getInt("id"));
		newss.setAuthor(rs.getString("author"));
		newss.setHeadlines(rs.getString("headlines"));
		newss.setDate(rs.getString("date"));
		newss.setRead_more(rs.getString("read_more"));
		newss.setDescription(rs.getString("description"));
		newss.setArticle(rs.getString("article"));
		return newss;
	}

}

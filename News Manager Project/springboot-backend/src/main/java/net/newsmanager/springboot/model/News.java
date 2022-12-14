package net.newsmanager.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "newsmanager")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "author")
	private String author;

	@Column(name = "date")
	private String date;

	@Column(name = "headlines")
	private String headlines;
	

	@Column(name = "read_more")
	private String read_more;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "article")
	private String article;
	
	public News() {
		
	}
	
	public News(String author, String date, String headlines, String read_more, String description, String article) {
		super();
		this.author = author;
		this.date = date;
		this.headlines = headlines;
		this.read_more = read_more;
		this.description = description;
		this.author = author;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeadlines() {
		return headlines;
	}

	public void setHeadlines(String headlines) {
		this.headlines = headlines;
	}

	public String getRead_more() {
		return read_more;
	}

	public void setRead_more(String read_more) {
		this.read_more = read_more;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}

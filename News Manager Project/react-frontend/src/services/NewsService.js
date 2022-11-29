import axios from 'axios';

const NEWS_API_BASE_URL = "http://localhost:8080/api/v1/news";

class NewsService {

    getNews(){
        return axios.get(NEWS_API_BASE_URL);
    }

    createNews(news){
        return axios.post(NEWS_API_BASE_URL, news);
    }

    getNewsById(newsId){
        return axios.get(NEWS_API_BASE_URL + '/' + newsId);
    }

    updateNews(news, newsId){
        return axios.put(NEWS_API_BASE_URL + '/' + newsId, news);
    }

    deleteNews(newsId){
        return axios.delete(NEWS_API_BASE_URL + '/' + newsId);
    }

    getAllAuthor(author){
        return axios.get(NEWS_API_BASE_URL+'/authors?author='+author)
    }
}

export default new NewsService()


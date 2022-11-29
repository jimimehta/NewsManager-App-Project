import React, { Component } from 'react'
import NewsService from '../services/NewsService'

class SearchNewsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                news: []
        }
        this.addNews = this.addNews.bind(this);
        this.editNews = this.editNews.bind(this);
        this.deleteNews = this.deleteNews.bind(this);
    }

    deleteNews(id){
        NewsService.deleteNews(id).then( res => {
            this.setState({news: this.state.news.filter(news => news.id !== id)});
        });
    }
    viewNews(id){
        this.props.history.push(`/view-news/${id}`);
    }
    editNews(id){
        this.props.history.push(`/add-news/${id}`);
    }

    componentDidMount(){
        NewsService.getNews().then((res) => {
            this.setState({ news: res.data});
        });
    }

    addNews(){
        this.props.history.push('/add-news/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">News List</h2>
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addNews}> Add News</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th> Author</th>
                                    <th> Date</th>
                                    <th>Headlines </th>
                                    {/* <th> read_more</th> */}
                                    <th> Description</th>
                                    {/* <th>article </th> */}
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.news.map(
                                        news => 
                                        <tr key = {news.id}>
                                             <td> { news.author} </td>   
                                             <td> {news.date}</td>
                                             <td> {news.headlines}</td>
                                             {/* <td> { news.read_more} </td>    */}
                                             <td> {news.description}</td>
                                             {/* <td> {news.article}</td> */}
                                             <td align='center'>
                                                 <button onClick={ () => this.editNews(news.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteNews(news.id)} className="btn btn-danger">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewNews(news.id)} className="btn btn-info">View Full Article</button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default SearchNewsComponent

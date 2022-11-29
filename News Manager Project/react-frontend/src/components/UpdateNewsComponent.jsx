import React, { Component } from 'react'
import NewsService from '../services/NewsService';

class UpdateNewsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            author:'',
            date:'',
            headlines:'',
            read_more:'',
            description:'',
            article:''
        }
        this.changeAuthorHandler = this.changeAuthorHandler.bind(this);
        this.changeDateHandler = this.changeDateHandler.bind(this);
        this.changeHeadlinesHandler = this.changeHeadlinesHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeRead_moreHandler = this.changeRead_moreHandler.bind(this);
        this.changeArticlesHandler = this.changeArticlesHandler.bind(this);
        this.updateNews = this.updateNews.bind(this);
    }

    componentDidMount(){
        NewsService.getNewsById(this.state.id).then( (res) =>{
            let news = res.data;
                this.setState({ author:news.author,
                date:news.date,
                headlines:news.headlines,
                read_more:news.read_more,
                description:news.description,
                article:news.article
            });
        });
    }

    updateNews = (e) => {
        e.preventDefault();
        let news = {author: this.state.author,
            date: this.state.date,
            headlines: this.state.headlines,
            read_more: this.state.read_more,
            description: this.state.description,
            article: this.state.article};
        console.log('news => ' + JSON.stringify(news));
        console.log('id => ' + JSON.stringify(this.state.id));
        NewsService.updateNews(news, this.state.id).then( res => {
            this.props.history.push('/news');
        });
    }
    
    changeAuthorHandler= (event) => {
        this.setState({author: event.target.value});
    }

    changeDateHandler= (event) => {
        this.setState({date: event.target.value});
    }

    changeHeadlinesHandler= (event) => {
        this.setState({headlines: event.target.value});
    }

    changeRead_moreHandler= (event) => {
        this.setState({read_more: event.target.value});
    }
    
    changeDescriptionHandler= (event) => {
        this.setState({description: event.target.value});
    }

    changeArticlesHandler= (event) => {
        this.setState({article: event.target.value});
    }

    
    cancel(){
        this.props.history.push('/news');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Employee</h3>
                                <div className = "card-body">
                                    <form>
                                    <div className = "row">
                                            <label> Author: </label>
                                            <input placeholder="Author" name="author" className="form-control" 
                                                value={this.state.author} onChange={this.changeAuthorHandler}/>
                                        </div>
                                        <div className = "row">
                                            <label> Date: </label>
                                            <input placeholder="Date" name="date" className="form-control" 
                                                value={this.state.date} onChange={this.changeDateHandler}/>
                                        </div>
                                        <div className = "row">
                                            <label> Headlines: </label>
                                            <input placeholder="headlines" name="headlines" className="form-control" 
                                                value={this.state.headlines} onChange={this.changeHeadlinesHandler}/>
                                        </div>

                                        <div className = "row">
                                        <label> Read_more: </label>
                                            <input placeholder="Read_more" name="read_more" className="form-control" 
                                                value={this.state.read_more} onChange={this.changeRead_moreHandler}/>
                                        </div>
                                        <div className = "row">
                                        <label> Description: </label>
                                            <input placeholder="Description" name="description" className="form-control" 
                                                value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                        </div>
                                        <div className = "row">
                                        <label> Article: </label>
                                            <input placeholder="Article" name="article" className="form-control" 
                                                value={this.state.article} onChange={this.changeArticlesHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.updateEmployee}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateNewsComponent

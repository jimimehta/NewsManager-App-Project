import React, { Component } from 'react'
import NewsService from '../services/NewsService';

class CreateNewsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
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
        this.saveOrUpdateNews = this.saveOrUpdateNews.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
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
    }
    saveOrUpdateNews = (e) => {
        e.preventDefault();
        let news = {author: this.state.author,
            date: this.state.date,
            headlines: this.state.headlines,
            read_more: this.state.read_more,
            description: this.state.description,
            article: this.state.article};
        console.log('news => ' + JSON.stringify(news));

        // step 5
        if(this.state.id === '_add'){
            NewsService.createNews(news).then(res =>{
                this.props.history.push('/news');
            });
        }else{
            NewsService.updateNews(news, this.state.id).then( res => {
                this.props.history.push('/news');
            });
        }
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

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add News</h3>
        }else{
            return <h3 className="text-center">Update News</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Author: </label>
                                            <input placeholder="Author" name="author" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeAuthorHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Date: </label>
                                            <input placeholder="Date" name="date" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeDateHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Headlines: </label>
                                            <input placeholder="headlines" name="headlines" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeHeadlinesHandler}/>
                                        </div>

                                        <div className = "form-group">
                                        <label> Read_more: </label>
                                            <input placeholder="Read_more" name="read_more" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeRead_moreHandler}/>
                                        </div>
                                        <div className = "form-group">
                                        <label> Description: </label>
                                            <input placeholder="Description" name="description" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeDescriptionHandler}/>
                                        </div>
                                        <div className = "form-group">
                                        <label> Article: </label>
                                            <input placeholder="Article" name="article" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeArticlesHandler}/>
                                        </div>


                                        <button className="btn btn-success" onClick={this.saveOrUpdateNews}>Save</button>
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

export default CreateNewsComponent



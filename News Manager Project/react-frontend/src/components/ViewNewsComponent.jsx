import React, { Component } from 'react'
import NewsService from '../services/NewsService'

class ViewNewsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            news: {}
        }
    }



    
    componentDidMount(){
        NewsService.getNewsById(this.state.id).then( res => {
            this.setState({news: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-12 offset-md-0">
                    <h3 className = "text-center"> View News Details</h3>
                    <div className = "card-body">
                                        <div className = "row">
                                            <label><th>Author: </th>  </label>
                                            <label> { this.state.news.author }</label>
                                        </div>
                                        <div className = "row">
                                            <label> <th>Date:</th>  </label>
                                            <label> { this.state.news.date }</label>
                                        </div>
                                        <div className = "row">
                                            <label> <th>Headline:</th> </label>
                                            <label> { this.state.news.headlines }</label>
                                        </div>
                                        <div className = "row">
                                            <label> <th>Description:</th> </label>
                                            <label> { this.state.news.description }</label>
                                        </div>
                                        <div className = "row">
                                            <label> <th>Read_more:</th> </label>
                                            <div> <a href="#">{ this.state.news.read_more }</a></div>
                                        </div>
                                        <div className = "row">
                                            <label> <th>Article:</th> </label>
                                            <div> { this.state.news.article }</div>
                                        </div>
                                        </div>

                </div>
            </div>
        )
    }
}

export default ViewNewsComponent

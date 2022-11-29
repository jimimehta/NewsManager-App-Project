import React, { Component } from 'react'

class FooterComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                 
        }
    }

    
    render() {
        return (
            <div>
                <footer className = "navbar navbar-expand-md navbar-dark bg-dark">
                    <span className="text-muted">APP Project Created By Jimi Mehta @40225526</span>
                </footer>
            </div>
        )
    }
}

export default FooterComponent

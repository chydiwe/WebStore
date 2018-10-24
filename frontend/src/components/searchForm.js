import React, {Component} from 'react'

export default class SearchForm extends Component{

    // validation method and search request method

    render() {
        return(
            <div className="searchForm">
                <label htmlFor="searchField" >Search</label>
                <input id="searchField" type="text" placeholder="Find product..." />
                <button>Search</button>
            </div>
        );
    }

}
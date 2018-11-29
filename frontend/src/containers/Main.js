import React, {Component} from 'react';
import '../App.css';
import Catalog from "../components/itemcatalog";

export default class Main extends Component {
    render() {
        return (
            <div className="App">
                <div className='MainBody'>
                    <div className='MainContent'>
                        <Catalog/>
                    </div>
                </div>
            </div>
        );
    }
}

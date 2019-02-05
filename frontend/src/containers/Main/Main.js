import React, {Component} from 'react';
import './style.css';
import Catalog from "../../components/Catalog/ItemsCatalog";

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

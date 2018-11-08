import React, { Component } from 'react';
import Catalog from "../components/itemcatalog";
export class MainBody extends Component{
    render(){
        return(
            <div className='MainBody'>
                <div className='MainContent'>
                    <Catalog/>
                </div>
            </div>
        )
    }
}
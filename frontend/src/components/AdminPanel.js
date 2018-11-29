import React from 'react'
import connect from "react-redux/es/connect/connect";
import {addItemOnServer} from "../action/addItemonserver";
import "./css/AdminPanel.css"
import fetch from "cross-fetch";

class AdminPanel extends React.Component {
    constructor(props) {
        super(props)
        this.sendItem = this.sendItem.bind(this)
        this.state = {
            listManufacturer: [],
            listCategory: [],
            listItems: [],
            category: '',
            manufacturer: ''
        }
        this.getManufacturer = this.getManufacturer.bind(this);
        this.getCategory = this.getCategory.bind(this);
        this.sendItem = this.sendItem.bind(this);
    }

    getManufacturer() {
        fetch(`http://localhost:8080/api/products/manufacturers`, {method: 'GET'})
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (response.status === 200) {
                    this.setState({
                        listManufacturer: json
                    })
                }
                else {
                    alert("ERROR")
                }
            })
    }

    getCategory() {
        fetch(`http://localhost:8080/api/products/categories`, {method: 'GET'})
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (response.status === 200) {
                    this.setState({
                        listCategory: json
                    })
                }
                else {
                    alert("ERROR")
                }
            })
    }



    sendItem(e) {
        e.preventDefault()
        const str = `name=${this._nameItem.value}&categoryId=${this._category.value}&manufacturerId=${this._manufacturer.value}&cost=${this._costItem.value}&quantity=${this._quanItem.value}`
        this.props.addItem(str)
    }

    componentDidMount() {
        this.getManufacturer();
        this.getCategory();
        //this.getItems();
    }

    render() {
        return <div className='adminPanel'>
            <div className='adminAddItem'>
                <p>Добавление товара</p>
                <input ref={(node) => this._nameItem = node} type="text" placeholder='name'/>
                <select className='categoryItem' ref={(node) => this._category = node}>
                    {this.state.listCategory.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <select className='manufacturerItem' ref={(node) => this._manufacturer = node}>
                    {this.state.listManufacturer.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input ref={(node) => this._costItem = node} type="text" placeholder='cost'/>
                <input ref={(node) => this._quanItem = node} type="text" placeholder='quantity'/>
                <input ref={(node) => this._shortInfo = node} type="text" placeholder='shortInfo'/>
                <input ref={(node) => this._imgUrl = node} type="text" placeholder='images'/>
                <input type="submit" onClick={this.sendItem} value='Добавить'/>
            </div>

        </div>
    }


}

const mapStateToProps = (store) => {
    return {
        itemAdd: store.itemAdd,
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        addItem: (item) => dispatch(addItemOnServer(item)),
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(AdminPanel)
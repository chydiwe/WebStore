import React from 'react'
import connect from "react-redux/es/connect/connect";
import {addCategory, addItem, delCategory, delItem} from "../action/adminPanel";
import "./css/AdminPanel.css"
import fetch from "cross-fetch";

class AdminPanel extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            listManufacturer: [],
            listCategory: [],
            listProducts: [],
            category: '',
            manufacturer: '',
            status: ['active', 'hidden']
        }
        this.getManufacturer = this.getManufacturer.bind(this);
        this.getCategory = this.getCategory.bind(this);
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

    getProducts() {
        fetch(`http://localhost:8080/api/products`, {method: 'GET'})
            .then((response) => {
                if (response.status === 200) {
                    response.json().then(response => this.setState({
                        listProducts: response
                    }))
                }
                else {
                    alert("ERROR")
                }
            })
    }

    componentDidMount() {
        this.getManufacturer();
        this.getCategory();
        this.getProducts();
    }

    render() {
        return <div className='adminMenu'>
            <div className='MenuChanger'>
                <button onClick={() => {
                    this.setState({status: ['active', 'hidden']})
                }}>Работа с продуктами
                </button>
                <button onClick={() => {
                    this.setState({status: ['hidden', 'active']})
                }}>Работа с категориями
                </button>
            </div>
            <Item status={this.state.status[0]} delItem={this.props.delItem} addItem={this.props.addItem}
                  listCategory={this.state.listCategory}
                  sendItem listManufacturer={this.state.listManufacturer} listProducts={this.state.listProducts}/>
            <Category status={this.state.status[1]} addCategory={this.props.addCategory}
                      delCategory={this.props.delCategory}
                      listCategory={this.state.listCategory}/>
        </div>
    }


}

class Category extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        const {listCategory, addCategory, delCategory, status} = this.props;
        return (
            <div className={`adminPanel ${status}`}>
                <div className="addCategory"><p>Добавление категории</p>
                    <input ref={(node) => this._category = node} type="text" placeholder='Название категории'/>
                    <button type="button"
                            onClick={() => addCategory(this._category.value)}>Добавить категорию
                    </button>
                </div>
                <div className="delCategory">
                    <p>Удаление категории</p>
                    <select className='categoryItem' ref={(node) => this._categorys = node}>
                        {listCategory.map((item, index) =>
                            <option key={index} value={item.id}>{item.name}</option>
                        )}
                    </select>
                    <button onClick={() => delCategory(this._categorys.value)} type='button'>Удалить</button>
                </div>
            </div>
        );
    }

}

class Item extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        const {listCategory, listManufacturer, listProducts, addItem, delItem, status} = this.props;
        return <div className={`adminPanel ${status}`}>
            <div className='Admin AddItem'>
                <p>Добавление товара</p>
                <input ref={(node) => this._nameItem = node} type="text" placeholder='name'/>
                <select className='categoryItem' ref={(node) => this._category = node}>
                    {listCategory.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <select className='manufacturerItem' ref={(node) => this._manufacturer = node}>
                    {listManufacturer.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input ref={(node) => this._costItem = node} type="text" placeholder='cost'/>
                <input ref={(node) => this._quanItem = node} type="text" placeholder='quantity'/>
                <input ref={(node) => this._shortInfo = node} type="text" placeholder='shortInfo'/>
                <input ref={(node) => this._imgUrl = node} type="text" placeholder='images'/>
                <button type="button"
                        onClick={() => addItem(`name=${this._nameItem.value}&categoryId=${this._category.value}&manufacturerId=${this._manufacturer.value}&cost=${this._costItem.value}&quantity=${this._quanItem.value}`)}>Добавить
                </button>
            </div>
            <div className="Admin DelItem">
                <p>Удаление товаров</p>
                <select className='listProducts' ref={(node) => this._products = node}>
                    {listProducts.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <button onClick={() => delItem(this._products.value)} type="button">Удалить</button>
            </div>
        </div>
    }
}

const mapStateToProps = (store) => {
    return {
        status: store.status,
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        addItem: (item) => dispatch(addItem(item)),
        delItem: (id) => dispatch(delItem(id)),
        addCategory: (str) => dispatch(addCategory(str)),
        delCategory: (id) => dispatch(delCategory(id))
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(AdminPanel)
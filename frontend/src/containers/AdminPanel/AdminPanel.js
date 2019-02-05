import React from 'react'
import connect from "react-redux/es/connect/connect";
import {addCategory, addImg, addInfo, addItem, delCategory, delItem} from "../../action/adminPanel";
import "./style.css"
import fetch from "cross-fetch";
import {OrderControl} from "../../components/AdminPanel/OrderControl";
import {CategoryControl} from "../../components/AdminPanel/CategoryControl";
import {ItemControl} from "../../components/AdminPanel/ItemControl";

class AdminPanel extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            listManufacturer: [],
            listCategory: [],
            listProducts: [],
            category: '',
            manufacturer: '',
            status: ['active', 'hidden', 'hidden']
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
                    this.setState({status: ['active', 'hidden', 'hidden']})
                }}>Работа с продуктами
                </button>
                <button onClick={() => {
                    this.setState({status: ['hidden', 'active', 'hidden']})
                }}>Работа с категориями
                </button>
                <button onClick={() => {
                    this.setState({status: ['hidden', 'hidden', 'active']})
                }}>Работа с заказами
                </button>
            </div>
            <ItemControl status={this.state.status[0]} delItem={this.props.delItem} addItem={this.props.addItem}
                  listCategory={this.state.listCategory}
                  addInfo={this.props.addInfo}
                  sendItem listManufacturer={this.state.listManufacturer} listProducts={this.state.listProducts}
                  addImg={this.props.addImg}/>
            <CategoryControl status={this.state.status[1]} addCategory={this.props.addCategory}
                      delCategory={this.props.delCategory}
                      listCategory={this.state.listCategory}
            />
            <OrderControl status={this.state.status[2]}/>
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
        delCategory: (id) => dispatch(delCategory(id)),
        addImg: (id, url) => dispatch(addImg(id, url)),
        addInfo: (id, info) => dispatch(addInfo(id, info))
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(AdminPanel)
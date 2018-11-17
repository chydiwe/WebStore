import React, {Component} from 'react'
import '../App.css'
import connect from "react-redux/es/connect/connect";
import getCatalog from '../action/catalog'
import notFound from './img/notfound.png'
import fetch from "cross-fetch";

const Item_catalog = ({item, i, addInBucket = f => f}) =>

    <div key={i} className='item_catalog card_catalog'>
        <img src={notFound} className='item_img'/>
        <h2 className='item_name'>{item.name}</h2>
        <li className='item_price'>Цена:{item.cost}</li>
        <button className='item_add' onClick={() => addInBucket(item)}>Добавить</button>
    </div>


class Catalog extends Component {
    constructor(props) {
        super(props)
        this.addInBucket = this.addInBucket.bind(this)
    }

    addInBucket(item) {
        fetch(`http://localhost:8080/api/users/bucket?userId=1&productId=${item.id}&amount=1`, {method: 'POST'})
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (response.status === 200) {

                }
                else {
                    alert("ERROR")
                }
            })
    }

    componentWillMount() {
        this.props.getCatalogItems()
    }

    render() {
        const catalog = this.props.catalog;
        return <div className='catalog'>
            {catalog.map((item, i) => <Item_catalog item={item} key={i} addInBucket={this.addInBucket}/>)}

        </div>

    }
}


const mapStateToProps = store => {
    return {
        catalog: store.catalog
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getCatalogItems: () => dispatch(getCatalog()),
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(Catalog)
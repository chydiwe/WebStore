import React, {Component} from 'react'
import '../App.css'
import connect from "react-redux/es/connect/connect";

import notFound from './img/notfound.png'
import fetch from "cross-fetch";
import {Link} from "react-router-dom";

const Item_catalog = ({item, i, addInBucket = f => f}) =>

    <div key={i} className='item_catalog card_catalog'>
        <Link to={`/item?id=${item.id}`}><img src={notFound} className='item_img'/></Link>
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
        const {user}=this.props
        fetch(`http://localhost:8080/api/users/bucket?userId=${user.id}&productId=${item.id}&amount=1`, {method: 'POST'})
                .then((response)=>{
                    if(response.status===200){
                        console.log('addtoBucket')
                    }
                })
            .catch((error)=>console.log(error))
    }
    render() {
        const catalog = this.props.catalog;
        return <div className='catalog'>
            {catalog?catalog.map((item, i) => <Item_catalog item={item} key={i} addInBucket={this.addInBucket}/>):<div className='loader'></div>}

        </div>

    }
}


const mapStateToProps = (store) => {
    return {
        user: store.session.user,
        catalog: store.catalog
    }
}



export default connect(mapStateToProps, null)(Catalog)
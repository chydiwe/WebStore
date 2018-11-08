import React, {Component} from 'react'
import '../App.css'
import connect from "react-redux/es/connect/connect";
import getCatalog from '../action/catalog'
import notFound from './img/notfound.png'
const Item_catalog = ({item,i}) =>

    <div key={i} className='item_catalog card_catalog'>
        <img src={notFound} className='item_img'/>
        <h2 className='item_name'>{item.name}</h2>
        <li className='item_price'>Цена:{item.cost}</li>
        <button className='item_add'>Добавить</button>
    </div>



class Catalog extends Component {
    componentWillMount(){
        this.props.getCatalogItems()
    }
    render() {
        const catalog=this.props.catalog;
        return <div className='catalog'>
            {catalog.map((item,i)=><Item_catalog item={item} key={i}/>)}

        </div>

    }
}


const mapStateToProps = store => {
    return {
     catalog:store.catalog
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getCatalogItems: () => dispatch(getCatalog()),
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(Catalog)
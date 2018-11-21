import React, {Component} from 'react'
import connect from "react-redux/es/connect/connect";
import "./css/SingleProductPage.css"
import getItem from '../action/getItem'

class SingleProductPage extends Component {
    constructor(props) {
        super(props);
        this.state={
            data:{}
        }
    }

    componentWillMount() {
      this.props.getItem(this.props.location.search).then((response)=>this.setState({
          data:response
      }))

    }

    render() {
        const data = {
            img: '/url',
            name: 'Pen',
            cost: 100,
            shortInfo: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit.\n' +
                'Minima praesentium asperiores nostrum dolore tenetur iusto\n' +
                'iure sunt unde inventore veniam incidunt, odit dolor voluptatum\n' +
                'hic vitae sequi iste dolorem odio?',
            quantity:50,
            manufacturer:{name:'RusTool'}
        }
        return (
            <div className="product-container">
                <h2 id="product-title"> {this.state.data.name} </h2>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <img src={this.state.data.url} alt="Product image" id="product-image"/>
                        </td>
                        <td>
                            <div>
                                <p className="product-price">Цена, руб.: <span>{this.state.data.cost}</span></p>
                                <input type="text" id="product-amount"/>
                                <label htmlFor="product-amount">шт.</label>
                                <button className="button">Добавить в корзину</button>
                            </div>
                            <hr/>
                            <div className="product-about-block">
                                <p className="quantity">Количество товара на
                                    складе: <span>{this.state.data.quantity}</span></p>
                                <p className="product-manufacturer">Производитель: <span>{(this.state.data.manufacturer===undefined)?'':this.state.data.manufacturer.name}</span>
                                </p>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <hr/>
                <div className="descr-container">
                    <h3>Описание товара</h3>
                    <p id="product-short-info">
                        {data.shortInfo}
                    </p>
                </div>

            </div>
        );
    }
}


const mapDispatchToProps = dispatch => {
   return {
      getItem: (url) => dispatch(getItem(url)),
     }
};

export default connect(null, mapDispatchToProps)(SingleProductPage)
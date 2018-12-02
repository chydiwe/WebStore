import React, {Component} from 'react'
import connect from "react-redux/es/connect/connect";
import "./css/SingleProductPage.css"
import getItem from '../action/getItem'
import fetch from "cross-fetch";
import {sessionService} from "redux-react-session";

class SingleProductPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: {
                name: '',
                price: 0
            }
        }
        this.addToBucket = this.addToBucket.bind(this)
    }

    addToBucket() {
        if ((this._count.value > this.state.data.quantity) && (this._count.value>0)) {
            alert("К сожалению на скалде нет столько товаров")
        }
        else {

            this.props.user.then(response=>fetch(`http://localhost:8080/api/users/bucket?userId=${response.id}&productId=${this.state.data.id}&amount=${this._count.value}`, {method: 'POST'})
                .then((response) => {
                    if (response.status === 200) {
                        console.log('addtoBucket')
                    }
                })
                .catch((error) => console.log(error)))
        }
    }

    componentDidMount() {
        this.props.getItem(this.props.location.search).then((response) => this.setState({
            data: response
        }))

    }

    render() {
        return (
            <div className="product-container">
                <table>
                    <tbody>
                    <tr>

                        <td>
                            <h2 id="product-title"> {this.state.data.name} </h2>

                            <img src={this.state.data.url} alt="Product image" id="product-image"/>
                        </td>
                        <td>
                            <div>
                                <p className="product-price">Цена, руб.: <span>{this.state.data.cost}</span></p>
                                <input type="text" id="product-amount" ref={(node) => this._count = node}/>
                                <label htmlFor="product-amount">шт.</label>
                                <button type='button' onClick={this.addToBucket} className="button">Добавить в корзину
                                </button>
                            </div>
                            <hr/>
                            <div className="product-about-block">
                                <p className="quantity">Количество товара на
                                    складе: <span>{this.state.data.quantity}</span></p>
                                <p className="product-manufacturer">Производитель: <span>{(this.state.data.manufacturer === undefined) ? '' : this.state.data.manufacturer.name}</span>
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
                        {this.state.data.shortInfo}
                    </p>
                </div>

            </div>
        );
    }
}

const mapStateToProps = store => {
    return {
        user: sessionService.loadUser(),
    }
};
const mapDispatchToProps = dispatch => {
    return {
        getItem: (url) => dispatch(getItem(url)),
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(SingleProductPage)
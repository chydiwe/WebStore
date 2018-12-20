import React, {Component} from 'react'
import notFound from "./img/notfound.png";
import "./css/OrderPage.css"
import fetch from "cross-fetch";
import {sessionService} from "redux-react-session";
import {ArrowDown} from "./svg/icons"
import {MyPlacemark} from "./Map"
import connect from "react-redux/es/connect/connect";
import {Link} from 'react-router-dom'
const ShoppingCartItem = ({item}) =>
    <tr className="shopping-cart-item">
        <td>
            <img src={item.images!==undefined &&item.images[0]!==undefined?item.images[0].image:notFound} alt=""/>
        </td>
        <td className="description">
            <p>
                <span>{item.product.name}</span><br/>
                <span>{item.product.category.name}</span><br/>
                <span>{item.product.manufacturer.name}</span>
            </p>
        </td>
        <td className="quantity">
            <p>{item.amount}</p>
        </td>
        <td className="total-price">{item.amount * item.product.cost} руб.</td>
    </tr>

const DELIVERY_PRICE = 400;


class OrderDeliveryAndPayment extends Component {
    constructor(props) {
        super(props)
        this.state = {
            displayBucket: false,
            delivery: false,
            address: "",
        }
        this.changeBucketDisplay = this.changeBucketDisplay.bind(this);
        this.handleOptionChange = this.handleOptionChange.bind(this);
        this.updateAddress = this.updateAddress.bind(this);
        this.sendOrder = this.sendOrder.bind(this);
    }

    changeBucketDisplay = () => {
        document.querySelector(".drop-trigger svg").classList.toggle("arrow-spin")
        this.setState({
            displayBucket: !this.state.displayBucket
        })
    }
    handleOptionChange = () => {
        this.setState({
            delivery: !this.state.delivery
        })
    }
    updateAddress = (e) => {
        this.setState({
            address: e.target.value
        })
    }


    componentDidMount() {
        this.props.user
            .then(response => {
                this.setState({userId: response.id})
                fetch(`http://localhost:8080/api/users/bucket?userId=${response.id}`)
                    .then(response => response.json())
                    .then(response => this.setState({products: response}))
            })


    }

    sendOrder() {

        fetch(`http://localhost:8080/api/orders?customer=${this.state.userId}&delivery=${this.state.delivery ? 2 : 1}&payment=1&comment=1${this.state.address}`, {method: 'POST'})
            .then(response => response.status !== 200 ? console.log(response) : console.log('succes'))

    }

    render() {
        return (
            <div>{this.state.products ? <div className="order-container">
                <div className="drop-trigger" onClick={this.changeBucketDisplay}>
                    <h1>Ваша корзина</h1>
                    <ArrowDown id="drop-arrow"/>
                </div>
                <div className={"drop-block " + (this.state.displayBucket ? "" : "collapsed")}>
                    <table>
                        <tbody>
                        <tr className="hat">
                            <td>Продукт</td>
                            <td>Описание</td>
                            <td>Количество</td>
                            <td>Цена</td>
                        </tr>
                        {
                            this.state.products.map((item) =>
                                <ShoppingCartItem item={item}/>)
                        }
                        </tbody>
                    </table>
                    <div className="order-price-block">
                        <div className="float-right">
                            <p id="total-sum"> Стоимость товаров в корзине:
                                <span>
                                    {this.state.products.map(item => item.product.cost * item.amount).reduce((sum, current) => {
                                        return sum + current
                                    }, 0)}
                                </span>
                            </p>
                        </div>
                        <div className="clear-float">{/**/}</div>
                    </div>
                </div>

                <hr className="divider"/>

                <div id="delivery-block" className="delivery-block">
                    <div className="delivery-content">
                        <h2>Выберите способ получения товара:</h2>

                        <div className="radio">
                            <label>
                                <input type="radio" value="option1"
                                       checked={!this.state.delivery}
                                       onChange={this.handleOptionChange}/>
                                <span className="checkmark">{/**/}</span>
                                Самовывоз
                            </label><br/>
                            <label>
                                <input type="radio" value="option2"
                                       checked={this.state.delivery}
                                       onChange={this.handleOptionChange}/>
                                <span className="checkmark">{/**/}</span>
                                Доставка
                            </label>
                        </div>

                        <hr className="divider"/>

                        {this.state.delivery ?
                            <div className="delivery-content-addition">
                                <h3>Куда доставить:</h3>
                                <label>Введите адрес
                                    <input id="delivery-address" type="text" onChange={this.updateAddress}/>
                                </label>
                                <p><i>Доставка осуществляется только <span>в пределах МКАД </span>.</i></p>
                                <p><i>Доставка осуществляется по фиксированной цене<span> {DELIVERY_PRICE} руб.</span>.</i>
                                </p>
                                {/*<p><i>При заказе от <span>1600 рублей</span> доставка осуществляется бесплатно.</i></p>*/}
                            </div>
                            :
                            <div className="delivery-content-addition">
                                <h3>Как доехать:</h3>
                                <ul>
                                    <li><p> На метро: м. Театральная, м. Охотный ряд </p></li>
                                    <li><p> На автобусе: М3, M10, 904, 144 до остановки Манежная площадь </p></li>
                                </ul>
                            </div>
                        }

                    </div>
                    <div id="map-block" className="map-block">
                        <p>Самовывоз осуществляется по указанному адресу:</p>
                        <MyPlacemark/>
                    </div>
                </div>

                <hr className="divider"/>

                <div className="payment-block">
                    <h2>Итого:
                        <span>
                            {this.state.products.map(item => item.product.cost * item.amount).reduce((sum, current) => {
                                return sum + current
                            }, 0) + (this.state.delivery ? DELIVERY_PRICE : 0)}
                        </span> руб.</h2>
                    <p><i>(Оплата заказа осуществляется картой)</i></p>
                    <Link to='/'><button type='button' onClick={() => this.sendOrder()} className="button">Оплатить</button></Link>
                </div>


            </div> : <div className='loader'></div>}</div>
        )
    }
}


const mapStateToProps = (store) => {
    return {
        user: sessionService.loadUser(),

    }
}


export default connect(mapStateToProps, null)(OrderDeliveryAndPayment)
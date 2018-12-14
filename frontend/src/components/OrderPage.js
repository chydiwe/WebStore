import React, {Component} from 'react'
import "./css/OrderPage.css"
import notFound from "./img/notfound.png";
import connect from "react-redux/es/connect/connect";
import fetch from "cross-fetch";
import {sessionService} from 'redux-react-session';
import {Link} from "react-router-dom";
import {Cross, Minus, Plus} from "./svg/icons"

const ShoppingCartItem = ({item, change}) =>
    <tr className="shopping-cart-item">
        <td>
            <img src={item.product.images[0] || notFound} alt=""/>
        </td>
        <td className="description">
            <p>
                <span>{item.product.name}</span><br/>
                <span>{item.product.category.name}</span><br/>
                <span>{item.product.manufacturer.name}</span>
            </p>
        </td>

        <td className="quantity">
            <div onClick={() => change('+')}>
                <Plus/>
            </div>
            <p>{item.amount}</p>
            <div onClick={() => change('-')}>
                <Minus/>
            </div>
        </td>

        <td className="total-price">{item.amount * item.product.cost} руб.</td>

        <td onClick={() => change('del')}>
            <Cross/>
        </td>
    </tr>


class OrderPage extends Component {
    constructor(props) {
        super(props);
        this.quantityChange = this.quantityChange.bind(this);
        this.nextStepControl = this.nextStepControl.bind(this);
      this.state={
          products:[]
      }
    }

    quantityChange(item, key, op) {
        const nProd = this.state.products;
        if (op === '+') {
            item.amount++;
            nProd[key] = item;
        }
        if (op === '-') {
            item.amount--;
            nProd[key] = item;
        }
        if (op === 'del') {
            nProd.splice(key, 1)
        }
        this.setState({
            products: nProd
        })
    }

    componentDidMount() {
        this.props.user
            .then(response => fetch(`http://localhost:8080/api/users/bucket?userId=${response.id}`)
                .then(response => response.json())
                .then(response => this.setState({products:response})))
    }

    componentWillUpdate() {

    }

    nextStepControl(e) {
        if (Array.isArray(this.state.products) && this.state.products.length !== 0) {
            let result = window.confirm("Это окончательный вариант корзины?");
            if (result) {
                /* отправка новой корзины на сервер */
                this.state.products.forEach((item) => {
                    this.props.user.then(response => fetch(`http://localhost:8080/api/users/bucket?userId=${response.id}&productId=${item.product.id}&amount=${item.amount}`, {method: 'POST'})
                        .then((response) => {
                            if (response.status === 200) {
                                console.log(`Product: ${item.product.name}  id: ${item.product.id}  amount:${item.amount} added to Bucket`)
                            }
                        })
                        .catch((error) => console.log(error)))
                })
            } else { e.preventDefault() }
        } else {
            e.preventDefault()
            alert("Ваша корзина пуста!")
        }
    }

    render() {
        return (
            <div className="order-container">
                 <div>
                    <h1>Ваша корзина</h1>
                    <table>
                        <tbody>
                        <tr className="hat">
                            <td>Продукт</td>
                            <td>Описание</td>
                            <td>Количество</td>
                            <td>Цена</td>
                            <td>{/**/}</td>
                        </tr>
                        {this.state.products.length!==0?this.state.products.map((item, key) =>
                            <ShoppingCartItem item={item} change={(op) => this.quantityChange(item, key, op)}/>)
                        :<p>В корзине пусто</p>}
                        </tbody>
                    </table>

                    <div className="order-accept-block">
                        <div className="float-right">
                            <p id="total-sum"> Итого:
                                <span>
                                    {this.state.products.map(item => item.product.cost * item.amount).reduce((sum, current) => {
                                        return sum + current
                                    }, 0)}
                                </span>
                            </p>
                            <Link to='/delivery-and-payment' onClick={this.nextStepControl}>
                                <button className="button">Далее</button>
                            </Link>
                        </div>
                        <div className="clear-float">{/**/}</div>
                    </div>
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

export default connect(mapStateToProps, null)(OrderPage)
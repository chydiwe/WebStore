import React, {Component} from 'react'
import "./css/OrderPage.css"
import notFound from "./img/notfound.png";

const Cross = () =>
    <svg xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 21.9 21.9" width="25px" height="25px">
        <path d="M14.1,11.3c-0.2-0.2-0.2-0.5,0-0.7l7.5-7.5c0.2-0.2,0.3-0.5,0.3-0.7s-0.1-0.5-0.3-0.7l-1.4-1.4C20,0.1,19.7,0,19.5,0  c-0.3,0-0.5,0.1-0.7,0.3l-7.5,7.5c-0.2,0.2-0.5,0.2-0.7,0L3.1,0.3C2.9,0.1,2.6,0,2.4,0S1.9,0.1,1.7,0.3L0.3,1.7C0.1,1.9,0,2.2,0,2.4  s0.1,0.5,0.3,0.7l7.5,7.5c0.2,0.2,0.2,0.5,0,0.7l-7.5,7.5C0.1,19,0,19.3,0,19.5s0.1,0.5,0.3,0.7l1.4,1.4c0.2,0.2,0.5,0.3,0.7,0.3  s0.5-0.1,0.7-0.3l7.5-7.5c0.2-0.2,0.5-0.2,0.7,0l7.5,7.5c0.2,0.2,0.5,0.3,0.7,0.3s0.5-0.1,0.7-0.3l1.4-1.4c0.2-0.2,0.3-0.5,0.3-0.7  s-0.1-0.5-0.3-0.7L14.1,11.3z" fill="#bfbfbf"/>
    </svg>

const Minus = () =>
    <svg xmlns="http://www.w3.org/2000/svg" version="1.1" x="0px" y="0px" viewBox="0 0 52 52" width="40px" height="40px">
        <g>
            <path d="M26,0C11.664,0,0,11.663,0,26s11.664,26,26,26s26-11.663,26-26S40.336,0,26,0z M26,50C12.767,50,2,39.233,2,26   S12.767,2,26,2s24,10.767,24,24S39.233,50,26,50z" fill="#bfbfbf"/>
            <path d="M39,25H13c-0.552,0-1,0.447-1,1s0.448,1,1,1h26c0.552,0,1-0.447,1-1S39.552,25,39,25z" fill="#bfbfbf"/>
        </g>
    </svg>

const Plus = () =>
    <svg xmlns="http://www.w3.org/2000/svg" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 52 52" width="40px" height="40px">
        <g>
            <path d="M26,0C11.664,0,0,11.663,0,26s11.664,26,26,26s26-11.663,26-26S40.336,0,26,0z M26,50C12.767,50,2,39.233,2,26   S12.767,2,26,2s24,10.767,24,24S39.233,50,26,50z" fill="#bfbfbf"/>
            <path d="M38.5,25H27V14c0-0.553-0.448-1-1-1s-1,0.447-1,1v11H13.5c-0.552,0-1,0.447-1,1s0.448,1,1,1H25v12c0,0.553,0.448,1,1,1   s1-0.447,1-1V27h11.5c0.552,0,1-0.447,1-1S39.052,25,38.5,25z" fill="#bfbfbf"/>
        </g>
    </svg>


class ShoppingCartItem extends Component {
    constructor(props){
        super(props);

        let it = this.props.item;
        this.state = {...it};
        this.state.itemCountSum = it.product.cost * it.amount;

        this.quantityAdd = this.quantityAdd.bind(this);
        this.quantityReduce = this.quantityReduce.bind(this);
    }

    quantityAdd(){
        if (this.state.amount !== this.state.product.quantity) {
            this.setState({
                amount: ++this.state.amount,
                itemCountSum: this.state.amount * this.state.product.cost
            });

            let temp = this.props.order.state.products.slice();
            temp.forEach(item => {
                if (item.product.id === this.state.product.id) {
                    item.amount = this.state.amount;
                }
            }, this);

            let sum = temp.map( item => item.product.cost * item.amount ).reduce((sum, current) => { return sum + current }, 0);

            this.props.order.setState({
                products: temp,
                listSum: sum,
                totalSum: sum + this.props.order.state.deliverySum
            });
        }
    }

    quantityReduce(){
        if (this.state.amount !== 1) {
            this.setState({
                amount: --this.state.amount,
                itemCountSum: this.state.amount * this.state.product.cost
            });

            let temp = this.props.order.state.products.slice();
            temp.forEach(item => {
                if (item.product.id === this.state.product.id) {
                    item.amount = this.state.amount;
                }
            }, this);

            let sum = temp.map( item => item.product.cost * item.amount ).reduce((sum, current) => { return sum + current }, 0);

            this.props.order.setState({
                products: temp,
                listSum: sum,
                totalSum: sum + this.props.order.state.deliverySum
            });
        }
    }

    render() {
        return(
            <tr className="shopping-cart-item">
                <td>
                    <img src={ this.state.product.images[0] || notFound } alt=""/>
                </td>
                <td className="description">
                    <p>
                        <span>{ this.state.product.name }</span><br/>
                        <span>{ this.state.product.category.name }</span><br/>
                        <span>{ this.state.product.manufacturer.name }</span>
                    </p>
                </td>

                <td className="quantity">
                    <div onClick={this.quantityAdd}>
                        <Plus />
                    </div>
                    <p>{this.state.amount}</p>
                    <div onClick={this.quantityReduce}>
                        <Minus />
                    </div>
                </td>

                <td className="total-price">{ this.state.itemCountSum } руб.</td>

                <td>
                    <Cross />
                </td>
            </tr>
        );
    }
}



export default class OrderPage extends Component{
    constructor() {
        super();

        /* вызов метода запроса инфы о корзине пользователя по его id*/

        this.state = {
            products: [
                {
                    product: {
                        id: 2,
                        name: "Степлер Croco",
                        category: {
                            id: 2,
                            name: "Степлеры"
                        },
                        manufacturer: {
                            id: 2,
                            name: "EngTools",
                            logo: null,
                            info: null
                        },
                        shortInfo: null,
                        cost: 100,
                        quantity: 5,
                        comments: [],
                        images: [ "http://img.3259404.ru/3259404.ru/brandshop/maped/groups/small_image/groupId346.png" ]
                    },
                    amount: 5
                },
                {
                    product: {
                        id: 3,
                        name: "Гелевая ручка",
                        category: {
                            id: 2,
                            name: "Ручки"
                        },
                        manufacturer: {
                            id: 2,
                            name: "EngTools",
                            logo: null,
                            info: null
                        },
                        shortInfo: null,
                        cost: 100,
                        quantity: 50,
                        comments: [],
                        images: []
                    },
                    amount: 4
                },
                {
                    product: {
                        id: 4,
                        name: "Тетрадь Восход",
                        category: {
                            id: 2,
                            name: "Тетради"
                        },
                        manufacturer: {
                            id: 2,
                            name: "EngTools",
                            logo: null,
                            info: null
                        },
                        shortInfo: null,
                        cost: 100,
                        quantity: 10,
                        comments: [],
                        images: []
                    },
                    amount: 2
                }
            ]
        };

        this.state.listSum = this.state.products.map( item => item.product.cost * item.amount ).reduce((sum, current) => { return sum + current }, 0);
        this.state.deliverySum = 450;
        this.state.totalSum = this.state.listSum + this.state.deliverySum;
    }

    render() {
        const order = this; /* чтобы из дочерних компонентов менять state родителя */

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

                            { Array.isArray(this.state.products) && (this.state.products.length > 0) ?
                                this.state.products.map( item => (<ShoppingCartItem item={ item } order={ order } />)) :
                                    <tr colSpan={6} className="shopping-cart-item">{/* */}</tr>
                            }

                        </tbody>
                    </table>

                    <div className="order-accept-block">
                        <div className="float-right">
                            <p id="goods-cost">Стоимость товаров: <span>{ this.state.listSum }</span></p>
                            <p id="deliver-price">Стоимость доставки: <span>{ this.state.deliverySum }</span></p>
                            <p id="total-sum">Итого: <span>{ this.state.totalSum }</span></p>
                            <button className="button">Оформить заказ</button>
                        </div>
                        <div className="clear-float">{/**/}</div>
                    </div>

                </div>
            </div>
        );
    }
}

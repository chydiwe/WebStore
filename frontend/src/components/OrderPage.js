import React, {Component} from 'react'
import "./css/OrderPage.css"
import notFound from "./img/notfound.png";

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

        /* вызов метода запроса полной инфы о товаре по id, сохранение результата в переменную productInfo */
        /* let productInfo = ... */
        // const {id, name, manufacturer, cost, images} = productInfo;

        this.state = {
            quantity: 1,
            itemCountSum: 100 /* props.cost */
        };
        this.quantityAdd = this.quantityAdd.bind(this);
        this.quantityReduce = this.quantityReduce.bind(this);
    }

    quantityAdd(){
        if (this.state.quantity !== 50) {
            this.setState({
                quantity: ++this.state.quantity,
                itemCountSum: this.state.quantity * 100 /* cost */
            });
        }
    }

    quantityReduce(){
        if (this.state.quantity !== 1) {
            this.setState({
                quantity: --this.state.quantity,
                itemCountSum: this.state.quantity * 100 /* cost */
            });
        }
    }

    render() { /* {item,i} */
        return(
            <tr className="item"> {/* key={i} */}
                <td className="image"><img src={notFound} alt=""/></td>
                <td className="description">
                    <span>Our Legacy</span>
                    <span>Brushed Scarf</span>
                    <span>Brown</span>
                </td>

                <td className="quantity">
                    <div onClick={this.quantityAdd}>
                        <Plus/>
                    </div>
                    <p>{this.state.quantity}</p>
                    <div onClick={this.quantityReduce}>
                        <Minus />
                    </div>
                </td>

                <td className="total-price">{ this.state.itemCountSum } руб.</td>

                <td className="delete-btn">
                    <img src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w18h181338911473cross.png" alt=""/>
                </td>
            </tr>
        );
    }
}



export default class OrderPage extends Component{

    render() {
        return (
            <div className="order-container">
                <div>

                    <div className="title">
                        Корзина
                    </div>
                    <table>
                        <tbody>
                            <tr className="hat">
                                <td>Продукт</td>
                                <td>Описание</td>
                                <td>Количество</td>
                                <td>Цена</td>
                                <td></td>
                            </tr>

                            <ShoppingCartItem />


                        </tbody>
                    </table>

                    <div className="order-accept-block">
                        <div className="float-right">
                            <p id="goods-cost">Стоимость товаров: <span>1300</span></p>
                            <p id="deliver-price">Стоимость доставки: <span>450</span></p>
                            <p id="total-sum">Итого: <span>1750</span></p>
                            <button className="button">Оформить заказ</button>
                        </div>
                        <div className="clear-float"></div>
                    </div>

                </div>
            </div>
        );
    }
}

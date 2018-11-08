import React, {Component} from 'react'
import "./css/OrderPage.css"
import minus from './img/minus.png'

export default class OrderPage extends Component{





    render(productIdList) {
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

                        <tr className="item">
                            <td className="image"><img src="https://p.fast.ulmart.ru/p/small/427/42717/4271777_1s.jpg" alt=""/></td>
                            <td className="description">
                                <span>Common Projects</span>
                                <span>Bball High</span>
                                <span>White</span>
                            </td>
                            <td className="quantity">
                                <button className="plus-btn" type="button" name="button">
                                    <img src="http://s1.iconbird.com/ico/0912/fugue/w16h161349012884plus.png" alt=""/>
                                </button>
                                <input type="text" name="name" value="1"/>
                                    <button className="minus-btn" type="button" name="button">
                                        <img src="http://s1.iconbird.com/ico/0912/fugue/w16h161349012761minus.png" alt=""/>
                                    </button>
                            </td>
                            <td className="total-price">$549</td>
                            <td className="delete-btn">
                                <img src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w18h181338911473cross.png" alt=""/>
                            </td>
                        </tr>

                        <tr className="item">
                            <td className="image"><img src="https://p.fast.ulmart.ru/p/small/427/42717/4271777_1s.jpg" alt=""/></td>
                            <td className="description">
                                <span>Maison Margiela</span>
                                <span>Future Sneakers</span>
                                <span>White</span>
                            </td>
                            <td className="quantity">
                                <button className="plus-btn" type="button" name="button">
                                    <img src="http://s1.iconbird.com/ico/0912/fugue/w16h161349012884plus.png" alt="" />
                                </button>
                                <input type="text" name="name" value="1"/>
                                    <button className="minus-btn" type="button" name="button">
                                        <img src="http://s1.iconbird.com/ico/0912/fugue/w16h161349012761minus.png" alt="" />
                                    </button>
                            </td>
                            <td className="total-price">$870</td>
                            <td className="delete-btn">
                                <img src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w18h181338911473cross.png" alt=""/>
                            </td>

                        </tr>

                        <tr className="item">
                            <td className="image"><img src="https://p.fast.ulmart.ru/p/small/427/42717/4271777_1s.jpg" alt=""/></td>
                            <td className="description">
                                <span>Our Legacy</span>
                                <span>Brushed Scarf</span>
                                <span>Brown</span>
                            </td>
                            <td className="quantity">
                                <button className="plus-btn" type="button" name="button">
                                    <img src="http://s1.iconbird.com/ico/0912/fugue/w16h161349012884plus.png" alt=""/>
                                </button>
                                <input type="text" name="name" value="1"/>
                                    <button className="minus-btn" type="button" name="button">
                                        <img src="http://s1.iconbird.com/ico/0912/fugue/w16h161349012761minus.png" alt=""/>
                                    </button>
                            </td>
                            <td className="total-price">$349</td>
                            <td className="delete-btn">
                                <img src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w18h181338911473cross.png" alt=""/>
                            </td>

                        </tr>
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

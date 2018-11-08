import React, {Component} from 'react'
// import {registration} from "../action/registerUser";
// import connect from "react-redux/es/connect/connect";
import "./css/SingleProductPage.css"


class SingleProductPage extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }


    render() {
        return(
            <div className="product-container">
                <h2 id="product-title">Product title</h2>
                <table>
                    <tr>
                        <td>
                            <img src="" alt="Product image" id="product-image" />
                        </td>
                        <td>
                            <div>
                                <p>Цена, руб.: <span id="product-price"></span></p>
                                <input type="text" id="product-amount" />
                                    <label htmlFor="product-amount">шт.</label>

                                    <button className="button">Добавить в корзину</button>
                            </div>
                            <div className="product-about-block">
                                <p>Количество товара на складе: <span id="quantity"></span></p>
                                <p>Производитель: <span id="product-manufacturer"></span></p>

                            </div>
                        </td>
                    </tr>
                </table>

                <div className="descr-container">
                    <h3>Описание товара</h3>
                    <p id="product-short-info">
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                        Minima praesentium asperiores nostrum dolore tenetur iusto
                        iure sunt unde inventore veniam incidunt, odit dolor voluptatum
                        hic vitae sequi iste dolorem odio?
                    </p>
                </div>

            </div>
        );
    }
}

// const mapStateToProps = store => {
//     return {
//         isRegist: store.isRegist
//     }
// };
// const mapDispatchToProps = dispatch => {
//     return {
//         regUser: (user) => dispatch(registration(user)),
//     }
// };
//
// export default connect(mapStateToProps, mapDispatchToProps)(Register)
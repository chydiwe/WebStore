import React, {Component} from 'react'
// import {registration} from "../action/registerUser";
// import connect from "react-redux/es/connect/connect";
import "./css/SingleProductPage.css"


class SingleProductPage extends Component {
    constructor(props) {
        super(props);
        this.calculateSum = this.calculateSum.bind(this);
    }


    render() {
        return(
            <div className="product-container">
                <h2 id="product-title">{/* this.props.name || "Product title here" */} Product title </h2>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <img src="" alt="Product image" id="product-image" />
                            </td>
                            <td>
                                <div>
                                    <p className="product-price">Цена, руб.: <span>{/* this.props.cost */}</span></p>
                                    <input type="text" id="product-amount" onChange={} value={/* 1 */} />
                                    <label htmlFor="product-amount">шт.</label>
                                    <button className="button">Добавить в корзину</button>
                                </div>
                                <hr/>
                                <div className="product-about-block">
                                    <p className="quantity">Количество товара на складе: <span>{/* this.props.quantity */}</span></p>
                                    <p className="product-manufacturer" >Производитель: <span>{/* this.props.manufacturer */}</span></p>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <hr/>
                <div className="descr-container">
                    <h3>Описание товара</h3>
                    <p id="product-short-info">
                        {/* this.props.shortInfo */}
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
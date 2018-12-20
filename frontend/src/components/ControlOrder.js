import React, {Component} from 'react'
import fetch from "cross-fetch";

export default class Order extends Component {
    constructor(props) {
        super(props)
        this.state = {
            ordersPayments: [],
            ordersStatus: [],
        }
    }

    componentDidMount() {
        fetch(`http://localhost:8080/api/orders/payments/statuses`).then(response =>
            response.status === 200 ?
                response.json().then(response => this.setState({ordersPayments: response})) : alert(response.message))
        fetch(`http://localhost:8080/api/orders/statuses`).then(response =>
            response.status === 200 ?
                response.json().then(response => this.setState({ordersStatus: response})) : alert(response.message))
    }

    setStatusPayment(id, status) {
        fetch(`http://localhost:8080/api/orders?id=${id}&paymentStatus=${status}`, {method: 'POST'}).then(response =>
            response.status !== 200 ? alert(response.message) : console.log('successPayment'))
    }

    setStatusOrder(id, status) {
        fetch(`http://localhost:8080/api/orders?id=${id}&orderStatus=${status}`, {method: 'POST'}).then(response =>
            response.status !== 200 ? alert(response.message) : console.log('successOrder'))
    }

    render() {
        const {status} = this.props, {ordersStatus, ordersPayments} = this.state
        return <div className={`adminPanel ${status}`}>
            <div className="Admin DelItem">
                <p>Изменить статус оплаты</p>
                <select className='listProducts' ref={(node) => this._statusPaymentId = node}>
                    {ordersPayments.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input placeholder='Номер заказа' ref={(node) => this._idStatusPay = node} type="text"/>
                <button onClick={() => this.setStatusPayment(this._idStatusPay.value,this._statusPaymentId.value)} type="button">Изменить статус
                </button>
            </div>
            <div className="Admin DelItem">
                <p>Изменить статус заказа</p>
                <select className='listProducts' ref={(node) => this._statusOrderId = node}>
                    {ordersStatus.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input placeholder='Номер заказа' ref={(node) => this._idStatusOrder = node} type="text"/>
                <button onClick={() => this.setStatusOrder(this._idStatusOrder.value, this._statusOrderId.value)} type="button">Изменить
                    статус
                </button>
            </div>
        </div>
    }
}
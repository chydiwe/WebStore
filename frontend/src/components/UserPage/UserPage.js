import React, {Component} from 'react'
import "./style.css"
import notFound from "../../img/notFound_mini.png";
import connect from "react-redux/es/connect/connect";
import {sessionService} from "redux-react-session";


const UserItem = ({item}) =>
    <tr className="">   
        <td>
            {item.orderStatus===null?'Ожидание':item.orderStatus.name}
        </td>
        <td>
            {item.delivery.name}
        </td>
        <td>
            {item.payment.name}

        </td>
        <td>{item.paymentStatus===null?'Ожидание':item.paymentStatus.name}</td>

        <td>
        {item.dateOpened}
    </td>
        <td>
            {item.dataFinished===undefined?'Ожидание':item.dataFinished}
        </td>
        <td>{item.totalCost}</td>
        <td>{item.userComment===undefined?'':item.userComment}</td>
    </tr>


class UserPage extends Component {
    setEditable = (event) => {
        document.getElementById("change").style.display = "none";
        document.getElementById("save").style.display = "block";
        this.setState({
            formDisabled: false
        })
    }
    handleInput = (event) => {
        this.setState({
            user: {
                ...this.state.user,
                [event.target.id]: event.target.value
            }
        })
    }
    updateUserInfo = () => {
        console.dir(this.state)
        this.setState({
            formDisabled: true
        })
        document.getElementById("change").style.display = "block";
        document.getElementById("save").style.display = "none";
    }

    constructor(props) {
        super(props);
        this.state = {
            user: {},
            formDisabled: true,
            formDisabled_True: true,
            history: []
        };

    }

    componentDidMount() {
        this.props.user.then(response => {
            this.setState({user: response});
            fetch(`http://localhost:8080/api/orders?userId=${this.state.user.id}`)
                .then(response => response.status === 200 ? response.json().then(response => this.setState({history: response})) : alert(response.message))
        })
    }

    render() {

        return (
            <div className="container">
                <div>
                    <div className="panel-body">
                        <div className="text-center" id="author">
                            <img src={notFound} alt=""/>
                            <h3> {this.state.user.name} </h3>
                            <button className="button">Загрузить фото</button>
                        </div>
                        <table className='infoUser'>
                            <h4 className="text-center"> Личная информация </h4>
                            <tbody>
                            <tr>
                                <td>Фамилия:</td>
                                <td><input type="text" className="input" id="surname" value={this.state.user.surname}
                                           disabled={this.state.formDisabled_True}/></td>
                            </tr>
                            <tr>
                                <td>Имя:</td>
                                <td><input type="text" className="input" id="name" value={this.state.user.name}
                                           disabled={this.state.formDisabled_True}/></td>
                            </tr>
                            <tr>
                                <td>Отчество:</td>
                                <td><input type="text" className="input" id="patronymic"
                                           value={this.state.user.patronymic} disabled={this.state.formDisabled_True}/>
                                </td>
                            </tr>
                            <tr>
                                <td>E-mail:</td>
                                <td><input type="text" className="input" id="email" onChange={this.handleInput}
                                           value={this.state.user.email} disabled={this.state.formDisabled}/></td>
                            </tr>
                            <tr>
                                <td>Страна:</td>
                                <td><input type="text" className="input" id="country" onChange={this.handleInput}
                                           value={this.state.user.country} disabled={this.state.formDisabled}/></td>
                            </tr>
                            <tr>
                                <td>Город:</td>
                                <td><input type="text" className="input" id="city" onChange={this.handleInput}
                                           value={this.state.user.city} disabled={this.state.formDisabled}/></td>
                            </tr>
                            <tr>
                                <td>Телефон:</td>
                                <td><input type="text" className="input" id="phoneNumber" onChange={this.handleInput}
                                           value={this.state.user.phoneNumber} disabled={this.state.formDisabled}/></td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <button className="button" id="change" onClick={this.setEditable}
                            disabled={!this.state.formDisabled}>Редактировать
                    </button>
                    <button className="button save" id="save" onClick={this.updateUserInfo}
                            disabled={this.state.formDisabled}>Сохранить
                    </button>
                    <h4>Ваша история заказов</h4>
                    <div className=" history-orders">
                        <table>
                            <tbody>
                            <tr>
                                <td>Статус заказа</td>
                                <td>Тип доставки</td>
                                <td>Тип оплаты</td>
                                <td>Статус оплаты</td>
                                <td>Дата покупки</td>
                                <td>Дата получения</td>
                                <td>Стоимость заказа</td>
                                <td>Комментарий к заказу</td>
                            </tr>
                            {this.state.history.map((item) => <UserItem item={item}/>)}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>)
    }
}

const mapStateToProps = (store) => {
    return {
        user: sessionService.loadUser(),

    }
}

export default connect(mapStateToProps, null)(UserPage)
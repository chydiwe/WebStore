
import React, {Component} from 'react'
import "./css/UserPage.css"
import notFound from "./img/notFound_mini.png";


const UserItem = ({item}) =>
    <tr className="">
        <td>
            <img src={item.product}/>
        </td>
        <td>
            <p>
                {item.description}
            </p>
        </td>
        <td>
            {item.quantity}
        </td>
        <td>{item.price}</td>
        <td>{item.receiving}</td>
    </tr>


export default class UserPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user:{
                image: notFound,
                id:"1",
                email: "vasyyyan@mail.ru",
                country:"Россия",
                city:"Баянск",
                name: "Васян",
                surname: "Обосрись",
                patronymic: "Улдарович",
                phoneNumber: "88005553535",
                history: [
                    {
                        product: notFound,
                        description: "product",
                        quantity: "2",
                        price: "320",
                        receiving: "20.11.2005"
                    },
                    {
                        product: notFound,
                        description: "product",
                        quantity: "1",
                        price: "340",
                        receiving: "20.10.2005"
                    },
                    {
                        product: notFound,
                        description: "product",
                        quantity: "1",
                        price: "300",
                        receiving: "25.10.2005"
                    }]
            },
            formDisabled:true,
            formDisabled_True:true

        };

    }


    setEditable = (event) => {
        document.getElementById("change").style.display="none";
        document.getElementById("save").style.display="block";
        this.setState({
            formDisabled: false
        })
    }
    handleInput = (event)=>{
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
        document.getElementById("change").style.display="block";
        document.getElementById("save").style.display="none";
    }

    render() {

        return (
            <div className="container">
                <div>
                    <div className="panel-body">
                        <div className="text-center" id="author">
                            <img src={this.state.user.image} alt=""/>
                            <h3> {this.state.user.name} </h3>
                            <button className="button">Загрузить фото</button>
                        </div>
                        <table className='infoUser'>
                            <h4 className="text-center"> Личная информация </h4>
                            <tbody>
                            <tr>
                                <td>Фамилия:</td>
                                <td><input type="text" className="input" id="surname"  value={this.state.user.surname} disabled={this.state.formDisabled_True}/></td>
                            </tr>
                            <tr>
                                <td>Имя:</td>
                                <td><input type="text" className="input" id="name" value={this.state.user.name} disabled={this.state.formDisabled_True}/></td>
                            </tr>
                            <tr>
                                <td>Отчество:</td>
                                <td><input type="text" className="input" id="patronymic" value={this.state.user.patronymic} disabled={this.state.formDisabled_True}/></td>
                            </tr>
                            <tr>
                                <td>E-mail:</td>
                                <td><input type="text" className="input" id="email" onChange={this.handleInput} value={this.state.user.email} disabled={this.state.formDisabled}/></td>
                            </tr>
                            <tr>
                                <td>Страна:</td>
                                <td><input type="text" className="input" id="country" onChange={this.handleInput} value={this.state.user.country} disabled={this.state.formDisabled}/></td>
                            </tr>
                            <tr>
                                <td>Город:</td>
                                <td><input type="text" className="input" id="city" onChange={this.handleInput} value={this.state.user.city} disabled={this.state.formDisabled}/></td>
                            </tr>
                            <tr>
                                <td>Телефон:</td>
                                <td><input type="text" className="input" id="phoneNumber" onChange={this.handleInput} value={this.state.user.phoneNumber} disabled={this.state.formDisabled}/></td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <button className="button" id="change" onClick={this.setEditable} disabled={!this.state.formDisabled}>Редактировать</button>
                    <button className="button save" id="save" onClick={this.updateUserInfo} disabled ={this.state.formDisabled}>Сохранить</button>
                    <h4>Ваша история заказов</h4>
                    <div className=" history-orders">
                        <table>
                            <tbody>
                            <tr>
                                <td>Продукт</td>
                                <td>Описание</td>
                                <td>Количество</td>
                                <td>Цена</td>
                                <td>Дата получения</td>
                            </tr>
                            {this.state.user.history.map((item) => <UserItem item={item}/>)}

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>)
    }
}

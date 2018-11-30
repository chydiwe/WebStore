import React, {Component} from 'react'
import "./css/UserPage.css"
import notFound from "./img/notFound_mini.png";

const Data =
    {
        image: notFound,
        surname: "Веселый",
        name: "Васян",
        patronymic: "Улдарович",
        email: "vasyyyan@mail.ru",
        country: "Россия",
        city: "Бутейко",
        birthday: "01.01.2000",
        contact: "+79995553434",
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
    }

const UserItem = ({item}) =>
    <tr className="">
        <td>1
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

    }


    render() {

        return (
            <div className="container">
                <div id="main">
                    <div className="panel-body">
                        <div className="text-center" id="author">
                            <img src={Data.image} alt=""/>
                            <h3> {Data.name} </h3>
                        </div>


                        <table className='infoUser'>
                            <h4 className="text-center"> Личная информация </h4>
                            <tbody>
                            <tr>
                                <td className="active">Фамилия:</td>
                                <td>{Data.surname}</td>
                            </tr>
                            <tr>
                                <td className="active">Имя:</td>
                                <td>{Data.name}</td>
                            </tr>
                            <tr>
                                <td className="active">Отчество:</td>
                                <td>{Data.patronymic}</td>
                            </tr>
                            <tr>
                                <td className="active">E-mail:</td>
                                <td>{Data.email}</td>
                            </tr>
                            <tr>
                                <td className="active">Страна:</td>
                                <td>{Data.country}</td>
                            </tr>
                            <tr>
                                <td className="active">Город:</td>
                                <td>{Data.city}</td>
                            </tr>

                            <tr>
                                <td className="active">Телефон:</td>
                                <td>{Data.contact}</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <input type="submit" name="submit" className="button" value="Редактировать"/>
                    <h4 className="text-center">Ваша история заказов</h4>
                    <div className=" history-orders">
                        <table className=" table_blur">
                            <tbody>
                            <tr>
                                <td>Продукт</td>
                                <td>Описание</td>
                                <td>Количество</td>
                                <td>Цена</td>
                                <td>Дата получения</td>
                            </tr>
                            {Data.history.map((item) => <UserItem item={item}/>)}


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>)
    }
}


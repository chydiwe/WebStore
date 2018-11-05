import React, {Component} from 'react'
import "./css/FooterSite.css"

export default class FooterSite extends Component {
    render() {
        return(
            <footer>
                <hr className="divider" />
                    <table>
                        <tr>
                            <td>
                                <h5>Начать</h5>
                                <ul>
                                    <li><a href="">Домой</a></li>
                                    <li><a href="">Подписаться</a></li>
                                    <li><a href="">Загрузки</a></li>
                                </ul>
                            </td>
                            <td>
                                <h5>О нас</h5>
                                <ul>
                                    <li><a href="">Информация о компании</a></li>
                                    <li><a href="">Наши контакты</a></li>
                                    <li><a href="">Отзывы</a></li>
                                </ul>
                            </td>
                            <td>
                                <h5>Поддержка</h5>
                                <ul>
                                    <li><a href="">FAQ</a></li>
                                    <li><a href="">Помощь</a></li>
                                    <li><a href="">Форумы</a></li>
                                </ul>
                            </td>
                            <td>
                                <h5>Права</h5>
                                <ul>
                                    <li><a href="">Условия обслуживания</a></li>
                                    <li><a href="">Условия пользования</a></li>
                                    <li><a href="">Политика конфиденциальности</a></li>
                                </ul>
                            </td>
                        </tr>
                    </table>
            </footer>
        )
    }
}
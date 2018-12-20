import React, {Component} from 'react'
import "./css/Page404.css"
import notFoundSVG from "./img/404.svg";

export default class Page404 extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div className="notFound">
                <img src={notFoundSVG} alt="не найдено"/>
                <h3>Кажется, этой страницы не существует...</h3>
            </div>
        )
    }
}
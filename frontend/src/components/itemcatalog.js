import React, {Component} from 'react'
import '../App.css'
import Pen_Pilot from './img/pen_pilot.jpg'
import Notebook_red from './img/notebook.jpg'
import Pencil from './img/карандаш.jpg'
import glue from './img/Клей-роллер.jpg'
import holepuncher from './img/дырокол.jpg'
import stapler from './img/степлер.jpg'
import calculator from './img/калькулятор.jpg'
import stock from './img/накопитель.jpg'
import cut from './img/ножницы.jpg'
import correct from './img/замазка.jpg'

const data = [
    {
        "name": "Ручка Pilot синяя",
        "pict": Pen_Pilot,

        "price": "35",
        "decription": "Увеличенная длина письма, сменный стержень"
    },
    {
        "name": "Бизнес-тетрадь Attache Office Creative",
        "pict": Notebook_red,
        "price": "60",
        "decription": "Пластиковая клетчатая тетрадь в клетку с наличием спирали 120 листов А5"
    },
    {
        "name": "Карандаш ",
        "pict": Pencil,
        "price": " 20",
        "decription": "Карандаш стандартной твердости с наличием ластика"
    },
    {
        "name": "Клей-роллер Комус ",
        "pict": glue,
        "price": " 55",
        "decription": "Предназначен для картона и бумаги"
    },
    {
        "name": "Дырокол ",
        "pict": holepuncher,
        "price": " 490",
        "decription": "Пробиваемость до 40 листов"
    },
    {
        "name": "Степлер ",
        "pict": stapler,
        "price": " 260",
        "decription": "Степлер для скрепления до 25 листов"
    },
    {
        "name": "Настольный калькулятор Citizen ",
        "pict": calculator,
        "price": " 200",
        "decription": "Калькулятор содержит основные математические функции"
    },
    {
        "name": "Накопитель бумаг ",
        "pict": stock,
        "price": " 80",
        "decription": "Предназначен для А4 до 100 листов"
    },
    {
        "name": "Ножницы ",
        "pict": cut,
        "price": " 110",
        "decription": "Ножницы с пластиковой рукоятью"
    },
    {
        "name": "Корректирующий карандаш ",
        "pict": correct,
        "price": " 90",
        "decription": "Корректирующий карандаш на 100 мл"
    }, {
        "name": "Корректирующий карандаш ",
        "pict": correct,
        "price": " 90",
        "decription": "Корректирующий карандаш на 100 мл"
    }, {
        "name": "Корректирующий карандаш ",
        "pict": correct,
        "price": " 90",
        "decription": "Корректирующий карандаш на 100 мл"
    },

]

const Item_catalog = ({item, i}) =>

    <div key={i} className='item_catalog card_catalog'>
        <img className='item_img' src={item.pict}/>
        <h2 className='item_name'>{item.name}</h2>
        <li className='item_price'>Цена:{item.price}</li>
        <button className='item_add'>Добавить</button>
    </div>


export class Catalog extends Component {
    render() {
        return <div className='catalog'>
            {data.map((item, i) => <Item_catalog item={item} i={i}/>
            )}
        </div>

    }
}
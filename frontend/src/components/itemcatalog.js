import React, {Component} from 'react'
import Pen_Pilot from '../../../../test/frontend/src/components/pen_pilot.jpg'
import Notebook_red from '../../../../test/frontend/src/components/notebook.jpg'
import Pencil from '../../../../test/frontend/src/components/карандаш.jpg'
import glue from '../../../../test/frontend/src/components/Клей-роллер.jpg'
import holepuncher from '../../../../test/frontend/src/components/дырокол.jpg'
import stapler from '../../../../test/frontend/src/components/степлер.jpg'
import calculator from '../../../../test/frontend/src/components/калькулятор.jpg'
import stock from '../../../../test/frontend/src/components/накопитель.jpg'
import cut from '../../../../test/frontend/src/components/ножницы.jpg'
import correct from '../../../../test/frontend/src/components/замазка.jpg'


const Item_catalog =({item,i})=>
    <div key={i} className='item_catalog'>
        <h1>{item.name}</h1>
        <img src={item.pict}/>
        <li>Цена:{item.price}</li>
        <li>Описание товара:{item.decription} </li>
    </div>

export class Catalog extends Component {

    render() {
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
            },

        ]

        return <div className='catalog'>
            {data.map((item, i) =><Item_catalog item={item} i={i}/>
            )}
        </div>

}}
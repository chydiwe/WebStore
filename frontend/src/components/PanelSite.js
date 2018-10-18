import React from 'react'
import ReactDOM from 'react-dom'
import logo from './logo_side.png'
import fetch from "cross-fetch";

export class PanelSite extends React.Component {
    constructor(props) {
        super(props);
        this.sideMenu = this.sideMenu.bind(this);
        this.handelClick = this.handelClick.bind(this)
    }

    sideMenu(e) {
        e.preventDefault();
        const side = ReactDOM.findDOMNode(this._dropMenu);
        if (side.classList.contains('dropMenuOn')) {
            side.classList.remove('dropMenuOn');
            side.classList.add('dropMenuOff')
        }
        else {
            side.classList.remove('dropMenuOff');
            side.classList.add('dropMenuOn')
        }
    }

    handelClick(e) {
        e.preventDefault();
        const name = ReactDOM.findDOMNode(this._loginEl).value,
            pass = ReactDOM.findDOMNode(this._passEl).value;
        this.props.logIn({name, pass})

    }

    render() {
        const {user, logOut} = this.props;
        return (<div className='panelSite'>
            <div className='MainPart'>Название компании?Или меню</div>
            <div className='UserPart'>
                <img src={logo} onClick={this.sideMenu} className='side_icon'/>
                <div ref={(node) => {
                    this._dropMenu = node
                }} className='dropMenuOff'>  {user.isLogin === true ?
                    <ul>
                        <li>Имя:{user.name}</li>
                        <li>Корзина:{user.message}</li>
                        <li><input type="submit" onClick={logOut} value='выход'/></li>
                    </ul>
                    :
                    <form action="">
                        <input ref={(node) => {
                            this._loginEl = node
                        }} type="text"/><br/>
                        <input ref={(node) => {
                            this._passEl = node
                        }} type='password'/><br/>
                         <input type='submit' onClick={this.handelClick} value='Вход'/>


                    </form>
                }
                </div>
            </div>
        </div>)
    }
}


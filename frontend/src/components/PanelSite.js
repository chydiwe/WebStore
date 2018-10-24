import React from 'react'
import ReactDOM from 'react-dom'
import SearchForm from './searchForm.js'
import profileIcon from '../appImages/defaultProfileIcon.png'
import logo from '../appImages/logo.png'
import IconShoppingCart from '../appImages/SVGIcons/InterfaceIcons'
//import fetch from "cross-fetch";
//import {Route, Link, Switch} from "react-router-dom";
import {Link} from "react-router-dom";

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
        return (
            <div className='panelSite'>

                <img src={logo} className="logo" alt="Logo"/>
                <SearchForm />
                <div className="orderPlace">
                    <Link to='/order'>
                        <IconShoppingCart />
                    </Link>
                </div>


                <div className='userPart'>
                    <img src={profileIcon} onClick={this.sideMenu} className='side_icon' alt="profile-img"/>
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

                            <table>
                                <tr>
                                    <td>
                                        <label htmlFor="emailLogInField">E-mail</label>
                                    </td>
                                    <td>
                                        <input id="emailLogInField" className="logInField" ref={(node) => {
                                            this._loginEl = node
                                        }} type="text"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label htmlFor="passLogInField">Password</label>
                                    </td>
                                    <td>
                                        <input id="passLogInField" className="logInField" ref={(node) => {
                                            this._passEl = node
                                        }} type='password'/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colSpan="2">
                                        <Link to='/register' className="link">Register</Link><br/>
                                        <Link to='' className="link">Recover password</Link>
                                        <input type='submit' onClick={this.handelClick} value='LogIn'/>
                                    </td>
                                </tr>
                            </table>


                    </form>
                    }
                    </div>
                </div>
            </div>
        );
    }
}


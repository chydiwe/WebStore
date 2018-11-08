import React from 'react'
import ReactDOM from 'react-dom'
import profileIcon from './img/logo_side.png'
import iconFind from './img/search.png'
import trashIcon from './img/trash.png'
import {Link} from "react-router-dom";
import {logOut, userLogIn} from "../action/user-profile";
import connect from "react-redux/es/connect/connect";
import "./css/PanelSite.css"
import logo from './img/logo.png'

function blurMain() {
    let app = document.querySelector('.panelSite + div')
    if (app.classList.contains('AppBlur')) app.classList.remove('AppBlur')
    else app.classList.add('AppBlur')
}

class PanelSite extends React.Component {
    constructor(props) {
        super(props);
        this.sideMenu = this.sideMenu.bind(this);
        this.handelClick = this.handelClick.bind(this)
    }

    sideMenu(nodeEl, classOn, classOff, isBlur) {

        const side = ReactDOM.findDOMNode(nodeEl);
        if (side.classList.contains(classOn)) {
            side.classList.remove(classOn);
            side.classList.add(classOff)
        }
        else {
            side.classList.remove(classOff);
            side.classList.add(classOn)
        }
        if (isBlur) {
            blurMain()
        }
    }

    componentWillMount() {
        console.log(this.props)
    }

    handelClick(e) {
        e.preventDefault();
        const name = ReactDOM.findDOMNode(this._loginEl).value,
            pass = ReactDOM.findDOMNode(this._passEl).value;
        this.props.userLogin({name, pass})

    }

    render() {
        const {userLogout, user} = this.props;
        return (
            <div className='panelSite'>
                <div className='nameFirm'><img className='logo' src={logo} alt=""/>MAGIC STATIONARY</div>
                <div className='category'
                     onClick={(node, classOn, classOff, isBlur) => this.sideMenu(this._categoryMenu, 'category_menuOn', 'category_menuOff', true)}>Категории
                    <div ref={(node) => this._categoryMenu = node} className='category_menuOff'>
                        <div className='category'>
                            <div>Бумажная продукция</div>
                            <div>Письменные принадлежности</div>
                            <div>Офисные принадлежности</div>
                            <div>Школьные принадлежности</div>
                            <div>Подарочная упаковка</div>
                        </div>
                    </div></div>
                <div className='searchMenu'><input type="text" placeholder='Найти'/>
                    <button><img src={iconFind} alt=""/></button>
                </div>
                <div className="RightMenu">
                    <div className='dropdown'>
                        <img src={profileIcon} className='dropbtn'
                             onClick={(node, classOn, classOff, isBlur) => this.sideMenu(this._dropMenu, 'dropMenuOn', 'dropMenuOff', false)}/>{user.isLogin ?
                        <p>Профиль</p> : <p>Войти</p>}
                        <div ref={(node) => {
                            this._dropMenu = node
                        }} className='dropMenuOff'>  {user.isLogin === true ?
                            <ul className='profile'>
                                <li>Имя:{user.name}</li>
                                <li><input type="submit" onClick={userLogout} value='выход'/></li>
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
                                <Link to='/register'>
                                    <button>регистрация</button>
                                </Link>

                            </form>
                        }
                        </div>
                    </div>
                    <div className="trash"><img src={trashIcon} alt=""/><p>Корзина</p></div>
                </div>
            </div>
        )
    }
}

const mapStateToProps = store => {
    return {
        user: store.user,
    }
};
const mapDispatchToProps = dispatch => {
    return {
        userLogin: (user) => dispatch(userLogIn(user)),
        userLogout: () => dispatch(logOut())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(PanelSite)
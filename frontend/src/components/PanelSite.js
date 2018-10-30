import React from 'react'
import ReactDOM from 'react-dom'
import profileIcon from './logo_side.png'
import iconFind from './search.png'
import burgerIcon from './icon.png'
import trashIcon from './trash.png'
import {Link} from "react-router-dom";
import {logOut, userLogIn} from "../action/user-profile";
import connect from "react-redux/es/connect/connect";


class PanelSite extends React.Component {
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
        const {userLogout,user} = this.props;
        return (
            <div className='panelSite'>

            <div className='MainPart'>
                <div className='burgerMenu'><img src={burgerIcon} alt=""/></div>
                <div className='nameFirm'>LOGO</div>
                <div className='searchMenu'><input type="text" placeholder='Найти'/>
                    <button><img src={iconFind} alt=""/></button>
                </div>
                <div className="RightMenu">
                    <div className='dropdown' onClick={this.sideMenu}>
                        <img src={profileIcon} className='dropbtn'/><p>Войти</p>
                        <div ref={(node) => {
                            this._dropMenu = node
                        }} className='dropMenuOff'>  {user.isLogin === true ?
                            <ul>
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


        </div>)
    }
}

const mapStateToProps = store => {
    return {
        user: store.user,
        catalog: store.catalog
    }
};
const mapDispatchToProps = dispatch => {
    return {
        userLogin: (user) => dispatch(userLogIn(user)),
        userLogout: () => dispatch(logOut())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(PanelSite)
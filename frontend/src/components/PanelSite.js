import React from 'react'
import ReactDOM from 'react-dom'
import {bindActionCreators} from 'redux';
import profileIcon from './img/logo_side.png'
import iconFind from './img/search.png'
import trashIcon from './img/trash.png'
import {Link, withRouter} from "react-router-dom";
import connect from "react-redux/es/connect/connect";
import "./css/PanelSite.css"
import logo from './img/logo.png'
import * as sessionActions from '../action/user-profile';
import fetch from "cross-fetch";
import {getCatalog, searchItem} from '../action/catalog'




class PanelSite extends React.Component {
    constructor(props) {
        super(props);
        this.sideMenu = this.sideMenu.bind(this);
        this.handelClick = this.handelClick.bind(this);
        this.mouseEnter = this.mouseEnter.bind(this);
        this.mouseLeave = this.mouseLeave.bind(this);
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
        if (isBlur) {this.blurMain()
        }
    }

    blurMain(){

    }
    mouseEnter() {
        console.log(" mouse enter ");
        this.sideMenu(this._categoryMenu, 'category_menuOn', 'category_menuOff', true)
    }

    mouseLeave() {
        console.log(" mouse leave ");
        this.sideMenu(this._categoryMenu, 'category_menuOn', 'category_menuOff', true)

        // Старый метод Макса
        // onClick={ (node, classOn, classOff, isBlur) => this.sideMenu(this._categoryMenu, 'category_menuOn', 'category_menuOff', true)}

    }


    handelClick(history) {
        this.props.actions.userLogIn(this._loginEl.value,this._passEl.value)

    }
    render() {
        const {user,getCatalogItems,category,searchItem} = this.props,{logOut}=this.props.actions, Submitbutton = withRouter(({history}) => (
            <button type="button" onClick={() => this.handelClick(history)}>Вход</button>));
        return (
            <div className='panelSite'>
                {/*logo*/}
                < div onClick={()=>this.props.getCatalogItems()} className='nameFirm'>
                    <Link to='/'><img className='logo' src={logo} alt="logo"/>MAGIC STATIONARY</Link>
                </div>
                {/*end logo*/}

                {/*category*/}
                <div className='category' onMouseEnter={ this.mouseEnter } onMouseLeave={ this.mouseLeave }>Категории
                    <div ref={(node) => this._categoryMenu = node} className='category_menuOff'>
                        <div className='category'>
                            {category?category.map((item,index)=>
                                <div onClick={()=>getCatalogItems(item.id)} key={index}><Link to='/'>{item.name}</Link></div>
                            ):<div></div>}
                        </div>

                    </div>
                </div>
                {/*end category*/}

                {/*search input*/}
                <div className='searchMenu'><input ref={node=>this._search=node} type="text" placeholder='Найти'/>
                    <Link className='searchIcon' to='/'><button type='button' onClick={()=>searchItem(this._search.value)}><img src={iconFind} alt=""/></button></Link>
                </div>
                {/*end search*/}

                <div className="RightMenu">
                    <div className='dropdown'>
                        <img src={profileIcon} className='dropbtn'
                             onClick={(node, classOn, classOff, isBlur) => this.sideMenu(this._dropMenu, 'dropMenuOn', 'dropMenuOff', false)}/>
                        {user.isLogin ? <p>Профиль</p> : <p>Войти</p>}
                        <div ref={(node) => {
                            this._dropMenu = node
                        }} className='dropMenuOff'>
                            {user.isLogin === true ?
                            <ul className='profile'>
                                <Link to='/profile'> <li>Имя:{user.name}</li></Link>
                                <li>
                                    <button onClick={logOut}>Выйти</button>
                                </li>
                            </ul>
                            :
                            <form action="">
                                <p>Почта или Логин</p>
                                <input ref={(node) => {
                                    this._loginEl = node
                                }} type="text"/>
                                <p>Пароль</p>
                                <input ref={(node) => {
                                    this._passEl = node
                                }} type='password'/>
                                <Submitbutton/>
                                <Link to='/register'>
                                    <button>регистрация</button>
                                </Link>

                            </form>
                        }
                        </div>
                    </div>

                    <div className="trash">
                        <Link to='/order-page'>
                            <img src={trashIcon} alt=""/>
                        </Link>
                        <p>Корзина</p>
                    </div>

                </div>
            </div>
        )
    }
}

const mapStateToProps = store => {
    return {
        user: store.session.user,
        category:store.category
    }
};
const mapDispatchToProps = dispatch => {
    return {
        actions: bindActionCreators(sessionActions, dispatch),
        getCatalogItems: (id) => dispatch(getCatalog(id)),
        searchItem:(name)=>dispatch(searchItem(name))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(PanelSite)
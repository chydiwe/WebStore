import React from 'react'
import ReactDOM from 'react-dom'

export class PanelSite extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            dropMenu: false
        }
        this.sideMenu = this.sideMenu.bind(this)
        this.handelClick = this.handelClick.bind(this)
    }

    sideMenu(e) {
        e.preventDefault();
        this.setState({
            dropMenu: !this.state.dropMenu
        })
    }

    handelClick(e) {
        e.preventDefault();
        const name = ReactDOM.findDOMNode(this._loginEl).value,
            pass = ReactDOM.findDOMNode(this._passEl).value;
        this.props.logIn({name,pass})
    }

    render() {
        const {user, logOut} = this.props;
        return (<div className='panelSite'>
            <div className='MainPart'>panelSite</div>
            <div className='UserPart'>
                <button onClick={this.sideMenu}>USER</button>

               <div className={this.state.dropMenu ? 'dropMenuOn' : 'dropMenuOff'}>  {user.isLogin === true ?
                        <ul>
                            <li>{user.name}</li>
                            <li>{user.message}</li>
                            <li><input type="submit" onClick={logOut} value='выход'/></li>
                        </ul>
                     :
                    <form action="">
                        <input ref={(node) => {
                            this._loginEl = node
                        }} type="text"/><br/>
                        <input ref={(node) => {
                            this._passEl = node
                        }} type="password"/>
                        <input type="submit" onClick={this.handelClick}/>
                    </form>
               }
                   </div>
            </div>
        </div>)
    }
}

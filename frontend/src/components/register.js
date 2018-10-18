import React, {Component} from 'react'
import {connect} from 'react-redux'
import {registration} from '../action/registerUser'

class FormRegister extends Component {
    constructor(props) {
        super(props)
        this.handlerClick=this.handlerClick.bind(this)
    }

    handlerClick(e) {
        const url = `email=${this._mail.value}&password=101010&name=admin&surname=admin&patronymic=admin`

        e.preventDefault();
        this.props.regUser(url)

    }

    render() {
        return <div className='registrationForm'>
            <input ref={(node)=>{this._mail=node}} type="text"/>
            <button onClick={this.handlerClick}>test</button>
        </div>
    }
}

const mapStateToProps = store => {
    return {
        isRegist: store.isRegist
    }
};
const mapDispatchToProps = dispatch => {
    return {
        regUser: (user) => dispatch(registration(user)),
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(FormRegister)
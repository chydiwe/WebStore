import React, {Component} from "react";
import {confirmToken} from "../action/confirmMailToken";
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'

class ConfirmMail extends Component {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        this.props.confirmToken(this.props.location.search)
    }

    render() {
        return <div className="App">
            <header className="App-header">
                {this.props.isConfirm ? <p>MAIL CONFIRMED <Redirect to='/'/></p> : <p>IS WRONG TOKEN </p>}
            </header>

        </div>
    }
}

const mapStateToProps = store => {
    return {
        isConfirm: store.confirmToken.isConfirm
    }
};
const mapDispatchToProps = dispatch => {
    return {
        confirmToken: (token) => dispatch(confirmToken(token)),
    }
};
export default connect(mapStateToProps, mapDispatchToProps)(ConfirmMail)
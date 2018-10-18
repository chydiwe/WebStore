import React,{Component} from "react";
import {confirmToken} from "../action/confirmMailToken";
import {connect} from'react-redux'
import {Redirect} from 'react-router-dom'
class ConfirmMail extends Component {
    constructor(props) {
        super(props)
    }
    componentDidMount(){
      this.props.confirmToken(this.props.match.params.tokenUser)
    }
    render() {
        return <div className="App">
            <header className="App-header">
                {this.props.isConfirm?<p>MAIL CONFIRMED {this.props.match.params.tokenUser}<Redirect to='/'/></p>:<p>IS FRONG TOKEN</p>}

             </header>

        </div>
    }
}
const mapStateToProps=store=>{
    return{  isConfirm:store.isConfirm
}
};
const mapDispatchToProps = dispatch => {
    return {
        confirmToken: (token) => dispatch(confirmToken(token)),
    }
};
export default connect(mapStateToProps,mapDispatchToProps)(ConfirmMail)
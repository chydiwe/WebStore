import React, {Component} from 'react';
import '../App.css';
import {MainBody} from "../components/MainBody";
import {PanelSite} from "../components/PanelSite";
import {connect} from 'react-redux'
import {logOut, userLogIn} from "../action/user-profile";

class Main extends Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <PanelSite user={this.props.user} logIn={this.props.userLogin} logOut={this.props.userLogout}/>
                </header>

                <MainBody/>
            </div>
        );
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

export default connect(mapStateToProps, mapDispatchToProps)(Main)
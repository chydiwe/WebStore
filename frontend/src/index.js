import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main'
import {history, store} from './store/configureStore'
import {Route, Switch} from 'react-router-dom'
import {ConnectedRouter,} from 'react-router-redux'
import ConfirmMail from "./containers/confirmMail";
import Register from "./components/RegisterPage";
import PanelSite from "./components/PanelSite";
import adminPanel from "./components/AdminPanel"
import FooterSite from "./components/FooterSite";
import OrderPage from "./components/OrderPage";
import UserPage from "./components/UserPage";
class Pages extends React.Component {
    render() {
        return(
            <div className="App-header">
                <PanelSite/>
                <Route exact path='/' component={Main}/>
                <Route path='/order-page' component={OrderPage}/>
                <Route path='/confirming' component={ConfirmMail}/>
                <Route path='/register' component={Register}/>
                <Route path='/adminpanel' component={adminPanel}/>
                <Route path='/profile' component={UserPage}/>
                <FooterSite/>
            </div>
        )
    }
}

ReactDOM.render(
    <Provider store={store}>
        <div>
            <ConnectedRouter history={history}>
                <Switch>
                    <Pages/>
                </Switch>
            </ConnectedRouter>
        </div>

    </Provider>,
    document.getElementById('root')
);


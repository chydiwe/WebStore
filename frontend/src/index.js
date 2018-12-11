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
import Page404 from "./components/Page404";

class Pages extends React.Component {
    render() {
        return (
            <div className="App-header">
                <PanelSite/>
                <Switch>
                    <Route exact path='/' component={Main}/>
                    <Route path='/order-page' component={OrderPage}/>
                    <Route path='/confirming' component={ConfirmMail}/>
                    <Route path='/register' component={Register}/>
                    <Route path='/adminpanel' component={adminPanel}/>
                    <Route path='/profile' component={UserPage}/>
                    <Route component={Page404}/>
                </Switch>
                <FooterSite/>
            </div>
        )
    }
}

ReactDOM.render(
    <Provider store={store}>
        <div>
            <ConnectedRouter history={history}>
                <Pages/>
            </ConnectedRouter>
        </div>

    </Provider>,
    document.getElementById('root')
);


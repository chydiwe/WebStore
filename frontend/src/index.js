import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main'
import {history, store} from './store/configureStore'
import {Route, Switch} from 'react-router-dom'
import {ConnectedRouter,} from 'react-router-redux'
import ConfirmMail from "./containers/confirmMail";
import FormRegister from './components/register'
ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <Switch>
                <Route exact path='/' component={Main}/>
                <Route path='/confirming:token=:tokenUser' component={ConfirmMail}/>
                <Route path='/register' component={FormRegister}/>
            </Switch>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

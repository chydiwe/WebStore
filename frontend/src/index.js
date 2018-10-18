import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main'
import {history, store} from './store/configureStore'
import {Route,Switch} from 'react-router-dom'
import {ConnectedRouter,} from 'react-router-redux'
import ConfirmMail from "./containers/confirmMail";

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <Switch>
            <Route  exact  path='/' component={Main}/>
            <Route path='/confirm:tokenUser' component={ConfirmMail}/>
            </Switch>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

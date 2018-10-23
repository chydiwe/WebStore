import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main'
import {history, store} from './store/configureStore'
import {Route, Switch} from 'react-router-dom'
import {ConnectedRouter,} from 'react-router-redux'
import ConfirmMail from "./containers/confirmMail";
import Register from "./components/registerForm";
ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <Switch>
                <Route exact path='/' component={Main}/>
                <Route path='/confirming' component={ConfirmMail}/>
                <Route path='/register' component={Register}/>
            </Switch>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

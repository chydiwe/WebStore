import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main'
import {history, store} from './store/configureStore'
import {Route} from 'react-router-dom'
import {ConnectedRouter,} from 'react-router-redux'

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <Route path='/' component={Main}/>

        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

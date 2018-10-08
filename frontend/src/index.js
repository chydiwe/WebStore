
import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main'
import {store} from './store/configureStore'


ReactDOM.render(
    <Provider store={store}>
        <Main/>
    </Provider>,
    document.getElementById('root')
);

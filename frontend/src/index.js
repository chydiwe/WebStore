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
import PanelSite from "./components/PanelSite";

class Pages extends React.Component {
    render() {
        return <header className="App-header">
            <PanelSite/>
            <Route exact path='/' component={Main}/>
            <Route path='/confirming' component={ConfirmMail}/>
            <Route path='/register' component={Register}/>
        </header>
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


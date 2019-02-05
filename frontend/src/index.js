import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import './index.css';
import Main from './containers/Main/Main'
import {history, store} from './store/configureStore'
import {Route, Switch} from 'react-router-dom'
import {ConnectedRouter,} from 'react-router-redux'
import ConfirmMail from "./components/confirmMail/confirmMail";
import Register from "./components/RegisterPage/RegisterPage";
import PanelSite from "./components/PanelSite/PanelSite";
import adminPanel from "./containers/AdminPanel/AdminPanel"
import FooterSite from "./components/FooterSite/FooterSite";
import OrderPage from "./components/Order/OrderPage";
import UserPage from "./components/UserPage/UserPage";
import SingleProductPage from "./components/ProductPage/SingleProductPage";
import OrderDeliveryPayment from "./containers/Order/OrderDeliveryAndPayment";
import {getCatalog} from "./action/catalog";
import getCategory from "./action/category";
import Page404 from "./components/404Page/Page404";

class Pages extends React.Component {
componentDidMount(){
    store.dispatch(getCategory())
    store.dispatch(getCatalog())
}
    render() {

        return(
            <div className="App-header">
                <PanelSite/>
                <Switch>
                <Route exact path='/' component={Main}/>
                <Route path='/order-page' component={OrderPage}/>
                <Route path='/confirming' component={ConfirmMail}/>
                <Route path='/register' component={Register}/>
                <Route path='/adminpanel' component={adminPanel}/>
                <Route path='/profile' component={UserPage}/>
                <Route path='/item' component={SingleProductPage}/>
                <Route path='/delivery-and-payment' component={OrderDeliveryPayment} />
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

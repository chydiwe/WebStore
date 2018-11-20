import {combineReducers} from 'redux'
import {catalog} from "./catalog";
import {routerReducer} from 'react-router-redux'
import {confirmToken} from "./confirmMail";
import {addItem} from "./addItemonserver";
import {sessionReducer} from 'redux-react-session';
export const rootReducer = combineReducers({
    catalog: catalog,
    confirmToken: confirmToken,
    addItem: addItem,
    routing: routerReducer,
    session: sessionReducer
});

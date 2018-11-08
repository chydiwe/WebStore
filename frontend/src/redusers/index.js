import {combineReducers} from 'redux'
import {userProf} from './user-profile'
import {catalog} from "./catalog";
import {routerReducer} from 'react-router-redux'
import {confirmToken} from "./confirmMail";
import {addItem} from "./addItemonserver";

export const rootReducer = combineReducers({
    user: userProf,
    catalog: catalog,
    confirmToken: confirmToken,
    addItem: addItem,
    routing: routerReducer,

});

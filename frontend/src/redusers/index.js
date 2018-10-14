import {combineReducers} from 'redux'
import {userProf} from './user-profile'
import {catalog} from "./catalog";
import {routerReducer} from 'react-router-redux'

export const rootReducer = combineReducers({
    user: userProf,
    catalog: catalog,
    routing: routerReducer,
})

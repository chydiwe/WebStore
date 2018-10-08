import {combineReducers} from 'redux'
import {userProf} from './user-profile'
import {catalog} from "./catalog";

export const rootReducer = combineReducers({user: userProf, catalog:catalog})

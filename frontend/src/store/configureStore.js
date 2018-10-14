import {applyMiddleware, createStore} from 'redux'
import {rootReducer} from "../redusers";
import thunk from 'redux-thunk'
import logger from 'redux-logger'
import createHistory from 'history/createBrowserHistory'
import {routerMiddleware} from 'react-router-redux'

export const history=createHistory()
const middleware=routerMiddleware(history)
export const store = createStore(rootReducer,applyMiddleware(middleware,logger,thunk))


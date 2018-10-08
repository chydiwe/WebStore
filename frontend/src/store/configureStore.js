import {applyMiddleware, createStore} from 'redux'
import {rootReducer} from "../redusers";
import thunk from 'redux-thunk'
import logger from 'redux-logger'
export const store = createStore(rootReducer,applyMiddleware(logger,thunk))


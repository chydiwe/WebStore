import {applyMiddleware, createStore} from 'redux'
import {rootReducer} from "../redusers";
import thunk from 'redux-thunk'
import logger from 'redux-logger'
import createHistory from 'history/createBrowserHistory'
import {routerMiddleware} from 'react-router-redux'
import {sessionService} from 'redux-react-session';
export const history = createHistory();
const middleware = routerMiddleware(history);
export const store = createStore(rootReducer,applyMiddleware(middleware,logger,thunk));


const options = { refreshOnCheckAuth: true, redirectPath: '/', driver: 'COOKIES'};

sessionService.initSessionService(store, options)
    .then(() => console.log('Redux React Session is ready and a session was refreshed from your storage'))
    .catch(() => console.log('Redux React Session is ready and there is no session in your storage'));


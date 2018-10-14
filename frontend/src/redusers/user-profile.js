import {GET_USER_INFO, GET_USER_INFO_FAILED, GET_USER_INFO_SUCCES, LOGOUT_USER} from "../action/user-profile";

const
    initialState = {
        user: {
            name: '',
            message: 'none',
            isLogin: false
        }
    }

export function userProf(state = initialState, action) {
    switch (action.type) {
        case GET_USER_INFO:
            return {...state}

        case GET_USER_INFO_SUCCES:
            return {...state, ...action.payload}
        case GET_USER_INFO_FAILED: {
            return {...state, message: action.payload}
        }
        case LOGOUT_USER:
            return {...action.payload}
        default:
            return state
    }

}
import {GET_CATEGORY_INFO, GET_CATEGORY_INFO_FAILED, GET_CATEGORY_INFO_SUCCES} from '../action/category'

const initialState = [{}]

export function category(state = initialState, action) {
    switch (action.type) {
        case GET_CATEGORY_INFO: {
            return state
        }
        case GET_CATEGORY_INFO_SUCCES : {
            return action.payload
        }
        case GET_CATEGORY_INFO_FAILED: {
            return
        }
        default:
            return state
    }
    return state
}
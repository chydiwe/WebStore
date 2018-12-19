import {GET_CATALOG_INFO, GET_CATALOG_INFO_FAILED, GET_CATALOG_INFO_SUCCES} from '../action/catalog'

const initialState = {}

export function catalog(state = initialState, action) {
    switch (action.type) {
        case GET_CATALOG_INFO: {
            return state
        }
        case GET_CATALOG_INFO_SUCCES : {
            return action.payload
        }
        case GET_CATALOG_INFO_FAILED: {
            return
        }
        default:
            return state
    }
    return state
}
import {POST_STATUS,
    POST_STATUS_SUCCES,
    POST_STATUS_FAILED} from "../action/adminPanel";

const initialState = {
    status: false
};

export function STATUS(state = initialState, action) {
    switch (action.type) {
        case POST_STATUS:
            return {...state};

        case POST_STATUS_SUCCES:
            return {status: action.payload};
        case POST_STATUS_FAILED: {
            return {status: action.payload}
        }
        default:
            return state
    }

}
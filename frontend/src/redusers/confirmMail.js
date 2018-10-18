import {POST_MAIL_TOKEN, POST_MAIL_TOKEN_FAILED, POST_MAIL_TOKEN_SUCCES} from "../action/confirmMailToken";

const initialState = {
    isConfirm: false
};

export function confirmToken(state = initialState, action) {
    switch (action.type) {
        case POST_MAIL_TOKEN:
            return {...state};

        case POST_MAIL_TOKEN_FAILED:
            return {isConfirm: action.payload};
        case POST_MAIL_TOKEN_SUCCES: {
            return {isConfirm: action.payload}
        }
        default:
            return state
    }

}
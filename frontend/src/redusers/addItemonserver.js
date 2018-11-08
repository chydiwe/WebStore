import {POST_ADDITEM,
    POST_ADDITEM_SUCCES,
    POST_ADDITEM_FAILED} from "../action/addItemonserver";

const initialState = {
    itemAdd: []
};

export function addItem(state = initialState, action) {
    switch (action.type) {
        case POST_ADDITEM:
            return {...state};

        case POST_ADDITEM_SUCCES:
            return {itemAdd: action.payload};
        case POST_ADDITEM_FAILED: {
            return {itemAdd: action.payload}
        }
        default:
            return state
    }

}
import fetch from 'cross-fetch'

export const POST_ADDITEM='POST_ADDITEM',
    POST_ADDITEM_SUCCES='POST_ADDITEM_SUCCES',
    POST_ADDITEM_FAILED='POST_ADDITEM_FAILED';

export function addItemOnServer(item) {
    return dispatch => {
        dispatch({
            type: POST_ADDITEM,
            payload: 'LOADING'

        });
        return fetch(`http://localhost:8080/api/products?${item}`, {method: 'POST'})
            .then(response => Promise.all([response,]))
            .then(([response, json]) => {
                console.log(response);
                if (response.status === 200) {
                    dispatch({
                        type: POST_ADDITEM_SUCCES,
                        payload:{itemAdd:true}
                    })
                }
                else {
                    dispatch({
                        type: POST_ADDITEM_FAILED,
                        payload:{itemAdd:false}
                    })
                }
            })
    }
}


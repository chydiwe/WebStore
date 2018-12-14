import fetch from 'cross-fetch'

export const GET_CATEGORY_INFO_FAILED = 'GET_CATEGORY_INFO_FAILED',
    GET_CATEGORY_INFO_SUCCES = 'GET_CATEGORY_INFO_SUCCES',
    GET_CATEGORY_INFO = 'GET_CATEGORY_INFO'

export default function getCategory() {
    return dispatch => {
        return fetch(`http://localhost:8080/api/products/categories`, {method: 'GET'})
            .then((response) => {
                if (response.status === 200) {
                    response.json().then((response)=>  {console.log(response);
                        dispatch({
                            type: GET_CATEGORY_INFO_SUCCES,
                            payload: response
                        })})
                }
                else {//ERROR RETURN
                    dispatch({
                        type: GET_CATEGORY_INFO_FAILED,
                        payload: []
                    })
                }
            })
    }
}

import fetch from 'cross-fetch'

export const GET_CATALOG_INFO_FAILED = 'GET_CATALOG_INFO_FAILED',
    GET_CATALOG_INFO_SUCCES = 'GET_CATALOG_INFO_SUCCES',
    GET_CATALOG_INFO = 'GET_CATALOG_INFO'

export default function getCatalog() {
    return dispatch => {

        return fetch(`http://localhost:8080/api/products/page`, {method: 'GET'})
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (response.status === 200) {
                    dispatch({
                        type: GET_CATALOG_INFO_SUCCES,
                        payload: json
                    })
                }
                else {//ERROR RETURN
                    dispatch({
                        type: GET_CATALOG_INFO_FAILED,
                        payload: []
                    })
                }
            })
    }
}


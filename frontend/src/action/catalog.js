import fetch from 'cross-fetch'

export const GET_CATALOG_INFO_FAILED = 'GET_CATALOG_INFO_FAILED',
    GET_CATALOG_INFO_SUCCES = 'GET_CATALOG_INFO_SUCCES',
    GET_CATALOG_INFO = 'GET_CATALOG_INFO'

export default function getCatalog(id) {
    return dispatch => {
        return fetch(`${id?`http://localhost:8080/api/products?categoryId=${id}`:'http://localhost:8080/api/products'}`, {method: 'GET'})
            .then((response) => {
                if (response.status === 200) {
                  response.json().then((response)=>  {console.log(response);
                      dispatch({
                      type: GET_CATALOG_INFO_SUCCES,
                      payload: response
                  })})
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

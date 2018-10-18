import fetch from 'cross-fetch'

export const GET_USER_INFO_FAILED = 'GET_USER_INFO_FAILED',
    GET_USER_INFO_SUCCES = 'GET_USER_INFO_SUCCES',
    GET_USER_INFO = 'GET_USER_INFO',
    LOGOUT_USER = 'LOGOUT_USER';

export function userLogIn(user) {
    return dispatch => {
        dispatch({
            type: GET_USER_INFO,
            payload: 'LOADING'

        });
        return fetch(`http://localhost:8080/api/users?email=${user.name}&password=${user.pass}`, {method: 'GET'})
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                console.log(response);
                if (response.status === 200) {
                    dispatch({
                        type: GET_USER_INFO_SUCCES,
                        payload: {...json,isLogin: true}
                    })
                }
                else {//ERROR RETURN
                    dispatch({
                        type: GET_USER_INFO_FAILED,
                        payload: response.status
                    })
                }
            })
    }
}


export function logOut() {
    return dispatch => {
        dispatch({
            type: LOGOUT_USER,
            payload: {}
        })
    }
}

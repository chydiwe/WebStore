export const GET_USER_INFO_FAILED = 'GET_USER_INFO_FAILED',
    GET_USER_INFO_SUCCES = 'GET_USER_INFO_SUCCES',
    GET_USER_INFO = 'GET_USER_INFO',
    LOGOUT_USER='LOGOUT_USER'

export function userLogIn(user) {
    return dispatch => {
        dispatch({
            type: GET_USER_INFO,
            payload: 'LOADING'

        })
        setTimeout(() => {
            dispatch({
                type: GET_USER_INFO_SUCCES,
                payload:{name:user.name,
                message:user.message,
                isLogin:true}
            })
        }, 1000)
    }
}

        export function logOut() {
            return dispatch=> {
               dispatch({ type: LOGOUT_USER,
                   payload:{}})
            }
        }

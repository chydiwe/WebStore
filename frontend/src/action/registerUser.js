const POST_REGISTRATION_INFO = 'POST_REGISTRATION_INFO',
    POST_REGISTRATION_SUCCES = 'POST_REGISTRATION_SUCCES',
    POST_REGISTRATION_FAILED = 'POST_REGISTRATION_FAILED';


export function registration(userInfo) {

    return dispatch => {
        dispatch({
            type: POST_REGISTRATION_INFO,
            payload: 'LOADING'
        })
        return fetch(`http://localhost:8080/api/users?${userInfo}`, {method: 'POST'})
            .then(response => Promise.all([response,]))
            .then(([response, json])=>{
                if(response.status===200){
                    console.log(response)
                    dispatch({
                        type: POST_REGISTRATION_SUCCES,
                        payload: response.status
                    })
                }
                else dispatch({
                    type: POST_REGISTRATION_FAILED,
                    payload: response.status
                })

            })
    }
}
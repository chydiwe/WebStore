import fetch from 'cross-fetch'

export const POST_MAIL_TOKEN='POST_MAIL_TOKEN',
    POST_MAIL_TOKEN_SUCCES='POST_MAIL_TOKEN_SUCCES',
    POST_MAIL_TOKEN_FAILED='POST_MAIL_TOKEN_FAILED';

export function confirmToken(token) {
    return dispatch => {
        dispatch({
            type: POST_MAIL_TOKEN,
            payload: 'LOADING'

        });
        return fetch(`http://localhost:8080/api/users${token}`, {method: 'POST'})
            .then(response => Promise.all([response,]))
            .then(([response, json]) => {
                console.log(response);
                if (response.status === 200) {
                    dispatch({
                        type: POST_MAIL_TOKEN_SUCCES,
                        payload:true
                    })
                }
                else {
                    dispatch({
                        type: POST_MAIL_TOKEN_FAILED,
                        payload:{isConfirm:false}
                    })
                }
            })
    }
}


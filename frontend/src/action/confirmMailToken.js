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
        return fetch(`http://localhost:8080/api/users?token=${token}`, {method: 'POST'})
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                console.log(response);
                if (response.status === 200) {
                    dispatch({
                        type: POST_MAIL_TOKEN_SUCCES,
                        payload:{isConfirm:true}
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


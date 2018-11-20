import fetch from 'cross-fetch'
import {sessionService} from 'redux-react-session';
export const GET_USER_INFO_FAILED = 'GET_USER_INFO_FAILED',
    GET_USER_INFO_SUCCES = 'GET_USER_INFO_SUCCES',
    GET_USER_INFO = 'GET_USER_INFO',
    LOGOUT_USER = 'LOGOUT_USER';

export function userLogIn(user,history) {
    return () => {
        return fetch(`http://localhost:8080/api/users?email=${user.name}&password=${user.pass}`, {method: 'GET'})
            .then(response => response.json())
            .then((response) => {
                const {id}=response;
                sessionService.saveSession({id})
                    .then(()=>{
                        response.isLogin=true;
                        sessionService.saveUser(response)
                            .then(()=>{
                                history.push('/');
                            }).catch(err => console.error(err));
                    }).catch(err => console.error(err));
            })
    }
}


export function logOut(history) {
    return () => {
        sessionService.deleteSession();
        sessionService.deleteUser();
        history.push('/');
    }

};

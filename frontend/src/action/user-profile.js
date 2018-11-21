import fetch from 'cross-fetch'
import {sessionService} from 'redux-react-session';


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


export function logOut() {
    return () => {
        sessionService.deleteSession();
        sessionService.deleteUser();
    }

};

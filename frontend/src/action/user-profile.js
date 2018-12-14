import fetch from 'cross-fetch'
import {sessionService} from 'redux-react-session';


export function userLogIn(user, pass) {
    console.log(user, pass)
    return () => {
        return fetch(`http://localhost:8080/api/users?email=${user}&password=${pass}`, {method: 'GET'})
            .then((response) => {
                if (response.status === 200)
                    response.json().then(response => {
                        sessionService.saveSession(response.id)
                        response.isLogin=true;
                        sessionService.saveUser(response)
                    })
                else alert(response.message)
            })
    }
}


export function logOut() {
    return () => {
        sessionService.deleteSession();
        sessionService.deleteUser();
    }

};

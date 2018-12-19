import fetch from 'cross-fetch'

export const POST_STATUS = 'POST_STATUS',
    POST_STATUS_SUCCES = 'POST_STATUS_SUCCES',
    POST_STATUS_FAILED = 'POST_STATUS_FAILED';

export function addItem(item) {
    window.location.reload()
    return dispatch => {
        dispatch({
            type: POST_STATUS
        })
        return fetch(`http://localhost:8080/api/products?${item}`, {method: 'POST'})
            .then((response) => {
                if (response.status === 200) {
                    dispatch({
                        type: POST_STATUS_SUCCES,
                        payload: {status: true}
                    })
                }
                else {
                    dispatch({
                        type: POST_STATUS_FAILED,
                        payload: {status: false}
                    })
                }
            })
    }
}

export function delItem(id) {
    window.location.reload()
    return dispatch => {
        dispatch({
            type: POST_STATUS
        })

        return fetch(`http://localhost:8080/api/products?id=${id}`, {method: 'DELETE'})
            .then((response) => {
                if (response.status === 200)
                    dispatch({
                        type: POST_STATUS_SUCCES
                    })
                else alert(`error:${response.message}`)

            })
    }
}

export function addImg(id,url) {
    window.location.reload()
    return dispatch => {
        dispatch({
            type: POST_STATUS
        })

        return fetch(`http://localhost:8080/api/products/images?id=${id}&URI=${url}`, {method: 'POST'})
            .then((response) => {
                if (response.status === 200)
                    dispatch({
                        type: POST_STATUS_SUCCES
                    })
                else alert(`error:${response.message}`)

            })
    }
}
export function addInfo(id,shortInfo) {
    return dispatch => {
        dispatch({
            type: POST_STATUS
        })

        return fetch(`http://localhost:8080/api/products?id=${id}&info=${shortInfo}`, {method: 'POST'})
            .then((response) => {
                if (response.status === 200)
                    dispatch({
                        type: POST_STATUS_SUCCES
                    })
                else alert(`error:${response.message}`)

            })
    }
}
export function addCategory(name) {
    window.location.reload()
    return dispatch => {
        dispatch({
            type: POST_STATUS
        })
        return fetch(`http://localhost:8080//api/products/categories?name=${name}`, {method: 'POST'})
            .then(response => {
                if (response.status === 200)
                    dispatch({
                        type: POST_STATUS_SUCCES
                    })
                else alert(`error:${response.message}`)
            })
    }

}


export function delCategory(name) {
    window.location.reload()
    return dispatch => {
        dispatch({
            type: POST_STATUS
        })
        return fetch(`http://localhost:8080//api/products/categories?id=${name}`, {method: 'DELETE'})
            .then(response => {
                if (response.status === 200)
                    dispatch({
                        type: POST_STATUS_SUCCES
                    })
                else alert(`error:${response.message}`)
            })
    }

}

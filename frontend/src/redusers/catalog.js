const
    GET_CATALOG_REQUEST = 'GET_CATALOG_REQUEST ',
    GET_CATALOG_FAILED = 'GET_CATALOG_FAILED',
    GET_CATALOG_SUCCES = 'GET_CATALOG_SUCCES',
    initialState = [{
        name: '1',
        price: '1',
        about: 'help',
    },{
        name: '2',
        price: '2',
        about: 'help2',
    },{
        name: '3',
        price: '3',
        about: '',
    },{
        name: '4',
        price: '4',
        about: '',
    }]


export function catalog(state = initialState, action) {
    switch (action.type) {
        case GET_CATALOG_REQUEST:{
            console.log("fff")
        }
        case GET_CATALOG_SUCCES: {
            return state
        }
        case GET_CATALOG_FAILED: {
            throw(Error)
        }
    }
    return state
}
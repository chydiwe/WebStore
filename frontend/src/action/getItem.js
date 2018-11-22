


export default function getItem(url){
    return()=>{
        return fetch(`http://localhost:8080/api/products${url}`,{method:'GET'})
            .then((response)=>response.json())
            .then((response)=>{
                return response
            })
    }
}
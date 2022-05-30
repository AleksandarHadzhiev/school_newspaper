import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080/user";

class UserServices{

    findByUsername(username,token){
        return axios.get(USER_API_BASE_URL+"/sign/in/"+username,{ headers: {"Authorization" : `Bearer ${token}`}})
    }

    editInformation(person,token){
        return axios.put(USER_API_BASE_URL+"/edit/information",person,{ headers: {"Authorization" : `Bearer ${token}`}});
    }

    createAccount(person){
        return axios.post(USER_API_BASE_URL+"/create/profile",person);
    }

    deleteAccount(id,token){
        return axios.delete(USER_API_BASE_URL+"/delete/profile/"+id,{ headers: {"Authorization" : `Bearer ${token}`}});
    }
}

export default new UserServices();

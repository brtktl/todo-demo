import axios from 'axios'
import API_URL from '../../constants'
export const AUTHENTICATED_SESSION_NAME = 'authenticatedUser'
class AuthenticationService{

    //It sets up axios auth header for the next requests
    registerSuccessfulLogin(username,password){
        let basicAuthHeaderString = 'Basic ' + window.btoa(`${username}:${password}`);
        sessionStorage.setItem(AUTHENTICATED_SESSION_NAME,username)
        this.setupAxiosInterceptors(basicAuthHeaderString)
    }

    registerSuccessfulLoginForJwt(username,token) {
        sessionStorage.setItem(AUTHENTICATED_SESSION_NAME, username)
        this.setupAxiosInterceptors(this.createJWTToken(token))
    }
    createJWTToken(token) {
        return 'Bearer ' +  token
    }
    executeJwtAuthenticationService(username, password) {
        return axios.post(`${API_URL}/authenticate`, {
            username,
            password
        })
    }

    executeRegisterService(username,password){
        return axios.post(`${API_URL}/register`, {
            username,
            password
        })
    }
    

    logout(){
        sessionStorage.removeItem(AUTHENTICATED_SESSION_NAME)
    }

    isUserAuthenticated(){
        let user = sessionStorage.getItem(AUTHENTICATED_SESSION_NAME)
        if(user === null){
            return false
        }else{
            return true
        }
    }
    getAuthenticatedUsername(){
        let user = sessionStorage.getItem(AUTHENTICATED_SESSION_NAME)
        if(user === null){
            return ''
        }else{
            return user
        }
    }

    setupAxiosInterceptors(token){
        axios.interceptors.request.use(
            (config) => {
                if(this.isUserAuthenticated){
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default new AuthenticationService()
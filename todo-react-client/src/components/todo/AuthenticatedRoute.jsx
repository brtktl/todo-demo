import React,{Component} from 'react'
import AuthenticationService from './AuthenticationService'
import {Route,Redirect} from 'react-router-dom'

class AuthenticatedRoute extends Component {
    render(){
        if(AuthenticationService.isUserAuthenticated()) return <Route {...this.props}/>
            return <Redirect to="/"/>

    }
}
export default AuthenticatedRoute
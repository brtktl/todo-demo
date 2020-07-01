import { withRouter } from 'react-router';
import React,{Component} from 'react'
import  {Link} from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'


class HeaderComponent extends Component {
    render(){
        const isAuthenticated = AuthenticationService.isUserAuthenticated()
        let username=''
        if(isAuthenticated) username = AuthenticationService.getAuthenticatedUsername()
        console.log(isAuthenticated)
        return  <header>
                   <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><div className="navbar-brand">Todo</div></div>
                        <ul className="navbar-nav">
                            {isAuthenticated && <li ><Link className="nav-link" to={`/welcome/${username}`}>Home</Link></li>}
                            {isAuthenticated &&<li ><Link className="nav-link" to="/todos">Todos</Link></li>}
                        </ul>
                        <ul  className="navbar-nav navbar-collapse justify-content-end">
                            {!isAuthenticated && <li ><Link className="nav-link" to="/">Login</Link></li>}
                            {!isAuthenticated && <li ><Link className="nav-link" to="/register">Register</Link></li>}
                            {isAuthenticated && <li><Link onClick={AuthenticationService.logout} className="nav-link" to="/logout">Logout</Link></li>}
                         </ul>
                   </nav>
                </header>
    }
}

export default withRouter(HeaderComponent);
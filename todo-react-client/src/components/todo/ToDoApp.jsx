import React,{Component} from 'react'
import  {BrowserRouter as Router,Route,Switch,Link} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute'
import LoginComponent from './LoginComponent'
import RegisterComponent from './RegisterComponent'
import ListTodosComponent from './ListTodosComponent'
import HeaderComponent from './HeaderComponent'
import FooterComponent from './FooterComponent'
import LogoutComponent from './LogoutComponent'
import TodoComponent from './TodoComponent'

class ToDoApp extends Component {
    render(){
        return(
            <div className="Todo">
                <Router>
                    <HeaderComponent/>
                    <Switch>
                        <Route path="/" exact component={LoginComponent}/>
                        <Route path="/register" exact component={RegisterComponent}/>
                        <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent}/>
                        <AuthenticatedRoute path="/todos" exact component={ListTodosComponent}/>
                        <AuthenticatedRoute path="/logout" exact component={LogoutComponent}/>
                        <AuthenticatedRoute path="/todos/:id" exact component={TodoComponent}/>
                        <Route path="" component={ErrorComponent}/>
                    </Switch>
                    <FooterComponent/>
                </Router>
            </div>
        )
    }
}

class WelcomeComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            welcomeMessage : ''
        }
    }
    render(){
        return  <div>
                    <h1> Welcome! </h1>
                    <div className="container">
                        Welcome to TODO APP {this.props.match.params.name}. <Link to="/todos">Access Todos From Here.</Link>
                    </div>
                   
                    <div className="container">
                        {this.state.welcomeMessage}
                    </div>

                </div>
    }

  
}

function ErrorComponent()
{
    return <div>Error Occurred. Contact Support. </div>
}



export default ToDoApp
import React,{Component} from 'react'
import AuthenticationService from './AuthenticationService.js'

class LoginComponent extends Component{
    constructor(props){
        super(props)
        this.state = {
            username : 'hayda_bre',
            password : '123456',
            hasLoginFailed : false,
            showSuccessMessage: false
        }
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
    }
    handleChange(event){
        this.setState(
             {[event.target.name] : event.target.value }
        )
    }
    loginClicked()
    {
        AuthenticationService
        .executeJwtAuthenticationService(this.state.username, this.state.password)
        .then((response) => {
            AuthenticationService.registerSuccessfulLoginForJwt(this.state.username,response.data.token)
            this.props.history.push(`/welcome/${this.state.username}`)
        }).catch( () =>{
            this.setState({showSuccessMessage:false})
            this.setState({hasLoginFailed:true})
        })
    }
    render(){
        return (
            <div className="container">
            <h1>Login</h1>
                {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Creditentials</div>}
                <div className="form-group row d-flex justify-content-center">
                    <div className="col-4 ">
                        <label htmlFor="">Username</label>
                        <input type="text" className="form-control" name="username" value={this.state.username} onChange={this.handleChange}/>
                    </div>
                </div>
                <div className="form-group row d-flex justify-content-center">
                    <div className="col-4">
                        <label htmlFor="">Password</label>
                        <input type="password" className="form-control" name="password" value={this.state.password} onChange={this.handleChange}/>
                    </div>
                </div>
                <button className="btn btn-success " onClick={this.loginClicked}>Login</button>
            </div>
        )
    }

}


export default LoginComponent
import React,{Component} from 'react'
import AuthenticationService from './AuthenticationService.js'

class RegisterComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            username : '',
            password : '',
            errorMsg : '',
        }
        this.handleChange = this.handleChange.bind(this)
        this.registerClicked = this.registerClicked.bind(this)
    }
    handleChange(event){
        this.setState(
             {[event.target.name] : event.target.value }
        )
    }
    registerClicked()
    {
            AuthenticationService.executeRegisterService(this.state.username,this.state.password)
            .then(response => {
                if(response.status == 200){
                    setTimeout(()=> {
                        AuthenticationService.executeJwtAuthenticationService(this.state.username, this.state.password)
                        .then((response) => {
                            AuthenticationService.registerSuccessfulLoginForJwt(this.state.username,response.data.token)
                            this.props.history.push(`/welcome/${this.state.username}`)}).catch(err =>this.setState({hasRegisterFailed:true,errorMsg:err.response.data}))
                    },300)
                }
            })
            .catch(err =>this.setState({hasRegisterFailed:true,errorMsg:err.response.data} ))
    }
    render(){
        return (
            <div className="container">
            <h1>Register</h1>
                {this.state.errorMsg !='' && <div className="alert alert-warning">{this.state.errorMsg}</div>}
                <div className="form-group row d-flex justify-content-center">
                    <div className="col-4 ">
                        <label htmlFor="">Username</label>
                        <input type="text" className="form-control" name="username"  onChange={this.handleChange}/>
                    </div>
                </div>
                <div className="form-group row d-flex justify-content-center">
                    <div className="col-4">
                        <label htmlFor="">Password</label>
                        <input type="password" className="form-control" name="password"  onChange={this.handleChange}/>
                    </div>
                </div>
                <button className="btn btn-success" onClick={this.registerClicked}>Login</button>
            </div>
        )
    }
}

export default RegisterComponent
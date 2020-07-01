import React,{Component} from 'react'
import moment from 'moment';
import {Formik, Form,Field, ErrorMessage} from 'formik'
import TodoDataService from '../../api/todo/TodoDataService.js'
import AuthenticationService from './AuthenticationService.js'
import DatePicker from './DatePicker.jsx'
class TodoComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id : this.props.match.params.id,
            description : 'Learn Forms',
            targetDate : moment(new Date()).format('YYYY-MM-DD')
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }
    onSubmit(values){
        let username = AuthenticationService.getAuthenticatedUsername();
        TodoDataService.updateTodo(username,this.state.id,{
            id: this.state.id,
            description:values.description,
            targetDate:values.targetDate,
        }).then(() =>   this.props.history.push(`/todos`)    )
    }
    validate(values){
        let errors = {}
        console.log(values)
        if(!values.description){
            errors.description = "Enter a description"
        }else if(!moment(values.targetDate).isValid()){
            errors.targetDate = "Enter a valid date"
        }
        return errors

    }
    componentDidMount(){
        let username = AuthenticationService.getAuthenticatedUsername();
        TodoDataService.getTodo(username,this.state.id)
        .then(
            response => this.setState({
                description : response.data.description,
                targetDate  : response.data.targetDate,
            })
        )
    }
    render(){
            let {description,targetDate} = this.state
            return <div>
                        <h1>Todo</h1>
                        <div className="container">
                            <Formik
                                initialValues = {{
                                    description,
                                    targetDate
                                }}
                                onSubmit={this.onSubmit}
                                validate = {this.validate}
                                validateOnChange = {false}
                                enableReinitialize = {true}

                            >
                                {
                                    (props) => (
                                        <Form>
                                            <ErrorMessage name="description" component="div" className="alert alert-warning"/>
                                            <ErrorMessage name="targetDate" component="div" className="alert alert-warning"/>
                                            <fieldset className="form-group">
                                                <label>Description</label>
                                                <Field className="form-control" type="text" name="description"/>
                                            </fieldset>
                                            <fieldset className="form-group">
                                                <label>Date</label>
                                                <DatePicker dateFormat="dd-MM-yyyy" name="targetDate" className="form-control" />
                                            </fieldset>
                                            <button  className="btn btn-success">Save</button>     
                                        </Form>
                                    )
                                }
                            </Formik>
                        </div>
                    </div>
    }

}

export default TodoComponent
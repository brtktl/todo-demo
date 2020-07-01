import React,{Component} from 'react'
import TodoDataService from '../../api/todo/TodoDataService.js';
import AuthenticationService from './AuthenticationService.js';
import moment from 'moment';
import {Formik, Form,Field, ErrorMessage} from 'formik'
class ListTodosComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            todos : [],
            message: null,
        }
        this.deleteTodoClicked = this.deleteTodoClicked.bind(this);
        this.updateTodoClicked = this.updateTodoClicked.bind(this);
        this.refreshTodos = this.refreshTodos.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.handleCheckbox = this.handleCheckbox.bind(this)
    }
    handleCheckbox({target}){
        if (target.checked){
            target.removeAttribute('checked');
            TodoDataService.doTodo(target.id)
            console.log(target.id);
         } else {
            target.setAttribute('checked', true);
            TodoDataService.undoTodo(target.id)
         }  
    }
    componentDidMount(){
        this.refreshTodos();
    }
    refreshTodos(){
        let username = AuthenticationService.getAuthenticatedUsername();
        TodoDataService.getAllTodos(username)
        .then(
            response => this.setState({todos : response.data})
        )
    }
    onSubmit(values){
        let username = AuthenticationService.getAuthenticatedUsername();
        TodoDataService.saveTodo(username,{
            description:values.description,
            targetDate: new Date(),

        })
        .then(
            response => {
                setTimeout(()=>{this.refreshTodos();this.setState({'message':`Todo added successfully`})},1000)}
        )
    }
    render(){
        let description = ''
        return  <div>
                    <div className="container">
                        <Formik initialValues={{description}} onSubmit={this.onSubmit}>
                        {
                                    (props) => (
                                        <Form>
                                            <ErrorMessage name="description" component="div" className="alert alert-warning"/>
                                            <ErrorMessage name="targetDate" component="div" className="alert alert-warning"/>
                                            <fieldset className="form-group mt-3">
                                                <div className="row">
                                                    <div className="col-9">
                                                        <Field placeholder="Add Task" className="form-control" type="text" name="description"/>
                                                    </div>
                                                    <div className="col-3">
                                                        <button  className="btn btn-success btn-block">Save</button>   
                                                    </div>
                                                </div>
                                                
                                            </fieldset>  
                                        </Form>
                                    )
                                }
                        </Formik>
                    </div>
                    <h1>List Todos</h1>
                    {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                    <div className="container">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>description</th>
                                    <th>date</th>
                                    <th>isDone</th>
                                    <th>update</th>
                                    <th>delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.todos.map(todo =>
                                    <tr key={todo.id}>
                                        <td>{todo.description}</td>
                                        <td>{moment(todo.targetDate).format('DD-MM-YYYY')}</td>
                                        <td><input type="checkbox" className="form-control " name="isDone" defaultChecked={todo.done}  id={todo.id} onClick={this.handleCheckbox}/></td>
                                        <td><button className="btn btn-success" onClick={() => this.updateTodoClicked(todo.id)}>Update</button></td>
                                        <td><button className="btn btn-warning" onClick={() => this.deleteTodoClicked(todo.id)}>Delete</button></td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>
    }
    deleteTodoClicked(id){
        let username = AuthenticationService.getAuthenticatedUsername();
        TodoDataService.deleteTodo(username,id)
        .then(
            response => {
                this.setState({'message':`Todo with id=${id} delete successful`})
                this.refreshTodos()
            }
        )

    }
    updateTodoClicked(id){
        this.props.history.push(`/todos/${id}`)
    }
}

export default ListTodosComponent
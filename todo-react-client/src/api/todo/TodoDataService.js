import axios from 'axios'
import API_URL from '../../constants.js'
class TodoDataService {
    getAllTodos(name){
        console.log(axios.get(`${API_URL}/users/${name}/todos`))
       return axios.get(`${API_URL}/users/${name}/todos`)
    }

    deleteTodo(name,id){
        return axios.delete(`${API_URL}/users/${name}/todos/${id}`)

    }
    updateTodo(name,id,todo){
        return axios.put(`${API_URL}/users/${name}/todos/${id}`,todo)
    }
    getTodo(name,id){
        return axios.get(`${API_URL}/users/${name}/todos/${id}`)
    }
    saveTodo(name,todo){
        return axios.post(`${API_URL}/users/${name}/todos`,todo)
    }

    doTodo(id){
        return axios.get(`${API_URL}/do-todo/${id}`)
    }
    undoTodo(id){
        return axios.get(`${API_URL}/undo-todo/${id}`) 
    }
}



export default new TodoDataService()
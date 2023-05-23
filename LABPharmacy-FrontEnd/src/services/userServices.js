import api from '../api/api'
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Consulta a brasilapi para obter dados a partir do cep do usuário
 * @param {*} cep 
 * @returns dados do edereço do usuário
 */
export async function callApiCep(cep) {
    try {
        const response = await axios
            .get(`https://brasilapi.com.br/api/cep/v1/${cep}`)
        return response.data
    } catch (err) {
      toast.error("Preencha um CEP válido e nesse formato, sem pontos e sem hífen: 89222479")
    }
}
/**
 * 
 * @returns lista de usuários cadastrados
 */
export async function getRegisteredUsers() {
    try {
        const response = await api.get('/usuario')
        return response.data.data
    } catch (err) {
        toast.error("Erro ao desconhecido ao buscar usuários")
    }

}

/**
 * Api para cadastro de novos usuários
 * @param {*} user 
 */

export async function postNewUser(user) {
    try {
        await api.post("/usuario/cadastro", user, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                toast.success("Usuário cadastrado com sucesso!");
            })
            .catch(error => {
                toast.error("Erro desconhecido ao cadastrar usuário: " + error.message)
            });
    } catch (err) {
        toast.error("Erro ao desconhecido: " + err.message)
    }
}

/**
 * Api para atualização de novos usuários
 * @param {*} user 
 */
export async function updateUser(user) {
    try {
        await api.put(`/usuario/${user.id}`, user, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                toast.success("Usuário atualizado com sucesso!");
            })
            .catch(error => {
                toast.error("Erro desconhecido ao atualizar usuário: " + error.message)
            });
    } catch (err) {
        toast.error("Erro ao desconhecido: " + err.message)
    }
}


/**
 * Api para deletar usuário
 * @param {*} id 
 */
export async function deleteUser(id){
    try{
        await api.delete(`/usuario/${id}`)
        toast.success("Usuário Deletado com sucesso!")
    } catch (err) {
        toast.error("Erro ao desconhecido: " + err.message)
    }
}

/**
 * Api para get usuários pela id
 * @param {*} id 
 */
export async function findUserById(id){
    try{
        const response  = await api.get(`/usuario/${id}`)
        return response.data.data
    } catch (err) {
        toast.error("Erro ao desconhecido: " + err.message)
    }
}

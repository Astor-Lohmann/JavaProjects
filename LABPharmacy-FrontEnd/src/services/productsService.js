import api from '../api/api'
import axios from 'axios'
import { toast } from 'react-toastify'

export async function getRegisteredProducts() {
  try {
    const response = await api.get('/produtos')
    return response.data.data
  } catch (err) {
    toast.error('Erro ao desconhecido: ' + err.message)
  }
}

export async function getAllRegisteredProducts() {
  try {
    const response = await api.get('/produtos/all')
    return response.data.data
  } catch (err) {
    toast.error('Erro ao desconhecido: ' + err.message)
  }
}

export async function postNewProduct(product) {
  try {
    await api
      .post('/produtos', product, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then((response) => {
        toast.success('Produto cadastrado com sucesso!')
      })
      .catch((error) => {
        toast.error('Erro desconhecido ao cadastrar produto: ' + error.message)
      })
  } catch (err) {
    toast.error('Erro ao desconhecido: ' + err.message)
  }
}

export async function updateProduct(product) {
  try {
    await api
      .put(`/produtos/${product.id}`, product, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then((response) => {
        toast.success('Usuário atualizado com sucesso!')
      })
      .catch((error) => {
        toast.error('Erro desconhecido ao atualizar usuário: ' + error.message)
      })
  } catch (err) {
    toast.error('Erro ao desconhecido: ' + err.message)
  }
}
export async function deleteProduct(id) {
  try {
    await api.delete(`/produtos/${id}`)
    toast.success('Produto Deletado com sucesso!')
  } catch (err) {
    toast.error('Erro ao desconhecido: ' + err.message)
  }
}

export async function findProductById(id) {
  try {
    const response = await api.get(`/produtos/${id}`)
    return response.data.data
  } catch (err) {
    toast.error('Erro ao desconhecido: ' + err.message)
  }
}

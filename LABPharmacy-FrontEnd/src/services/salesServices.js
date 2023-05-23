import api from '../api/api'
import { toast } from "react-toastify";



export async function getRegisteredSales() {
  try {
    const response = await api.get('/vendas')
    return response.data.data
  } catch (err) {
    toast.error("Erro desconhecido: " + err.message)
  }

}

export async function postNewSale(sale) {
  try {
    await api.post("/vendas/cadastro", sale, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        toast.success("Venda cadastrada com sucesso!");
      })
      .catch(error => {
        toast.error("Erro desconhecido ao cadastrar venda: " + error.message)
      });
  } catch (err) {
    toast.error("Erro desconhecido: " + err.message)
  }
}

export async function updateSale(sale) {
  try {
    await api.put(`/vendas/${sale.id}`, sale, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        toast.success("Registro de venda atualizado com sucesso!");
      })
      .catch(error => {
        toast.error("Erro desconhecido ao atualizar registro de venda: " + error.message)
      });
  } catch (err) {
    toast.error("Erro desconhecido: " + err.message)
  }
}

export async function deleteSale(id) {
  try {
    await api.delete(`/vendas/${id}`)
    toast.success("Resgistro de venda deletado com sucesso!")
  } catch (err) {
    toast.error("Erro desconhecido: " + err.message)
  }
}
export async function findSaleById(id) {
  try {
    const response = await api.get(`/vendas/${id}`)
    return response.data.data
  } catch (err) {
    toast.error("Erro desconhecido: " + err.message)
  }
}
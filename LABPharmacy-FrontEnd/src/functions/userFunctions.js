import { toast } from "react-toastify";
import { dateFormatterDDMMAAAA } from '../functions/generalFunctions'
import { deleteUser,  getRegisteredUsers } from '../services/userServices'
import { getRegisteredSales } from "../services/salesServices";
import { getRegisteredProducts } from "../services/productsService";


export async function newUserValidation(user) {

  const registeredUsers = await getRegisteredUsers();
  let isValid = true
  if (registeredUsers.filter(item => item.email === user.email).length !== 0) {
    toast.error('Endereço de email já cadastrado.')
    isValid = false
  }
  if (registeredUsers.filter(item => item.cpf === user.cpf).length !== 0) {
    toast.error('Cpf já cadastrado.')
    isValid = false
  }
  if (!passwordValidation(user)) {
    isValid = false
  }
  return isValid

}

export function passwordValidation(user) {
  let check = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
  if (!user.password.match(check)) {
    toast.error('Senha invalida: é obrigatório ter no mínimo 8 caracteres, letra maiúscula, minúscula e número.')
    return false
  } else {
    return true
  }
}


export function userResponseFormat(response) {
  const userFomattedBirthDate = userDateFormatter(response)
  const sellectedUsers = userFomattedBirthDate.filter(u => {
    if (u.type === "ADMIN") {
      u.type = "Administrador";
      return u
    } if (u.type === "SELLER") {
      u.type = "Vendedor";
      return u
    }
    return null
  })

  return sellectedUsers
}
export function userResponseFormatSaleForm(response) {
  const userFomattedBirthDate = userDateFormatter(response)
  const sellectedUsers = userFomattedBirthDate.filter(u => {
    if (u.type === "ADMIN") {
      u.type = "Administrador";
      return u
    } if (u.type === "SELLER") {
      u.type = "Vendedor";
      return u
    }if (u.type === "BUYER") {
      u.type = "Comprador";
      return u
    }
    return null
  })

  return sellectedUsers
}
export function searchUsers(users, search) {
  const filter = []
  users.map(user => {
    let cont = 0
    search.split(" ").map((word) => {
      if (user.id.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        user.fullName.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        user.cpf.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        user.birth.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        user.email.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        user.contactNumber.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        user.type.toLocaleLowerCase().includes(word.toLocaleLowerCase())) {
        return cont++
      } else {
        return null
      }
    })
    if (cont === search.split(" ").length) {
      filter.push(user)
    }
    return null
  })
  return filter
}

function userDateFormatter(user) {
  user.map(u => {
    u.birth = dateFormatterDDMMAAAA(u.birth)
    return u
  })
  return user
}

export async function verificarSeExistemVinculos(id){
  const salesList = await getRegisteredSales()
  const productsList = await getRegisteredProducts()
  const vendasVinculadas = salesList.filter(item=>{return item.sellerId === id})
  const produtosVinculados= productsList.filter(item=>{return item.userId === id})

  
  if(vendasVinculadas.length>0 || produtosVinculados.length>0){
    return toast.error("Não é possível excluir usuários com registros de venda ou produto vinculado.")
  }
  
  await deleteUser(id)

}


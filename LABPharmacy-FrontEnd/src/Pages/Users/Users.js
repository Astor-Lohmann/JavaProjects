import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { DefaltLayout } from "../../Components/MainStyle/DafaultLayout";
import FormTest from "../../Components/Forms/UserForm";
import { List } from "../../Components/ListContents/ListContents";
import { note, trash } from "../../utils/Svgs";
import NumPage from "../../Components/NumPage/NumPage";
import { toast } from "react-toastify";
import useModal from "../../utils/useModal";
import Modal from "../../utils/Modal";
import { userResponseFormat, searchUsers, passwordValidation, verificarSeExistemVinculos } from '../../functions/userFunctions'
import { deleteUser, findUserById, getRegisteredUsers, updateUser } from '../../services/userServices'
import { getRegisteredSales } from "../../services/salesServices";
import { ConfirmToast } from 'react-confirm-toast';
import { IMaskInput } from "react-imask";
import ReactInputMask from "react-input-mask";

export default function Users() {
  (() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }
        form.classList.add('was-validated')
      }, false)
    })
  })()

  const [updateList, setUpdateList] = useState(true)
  const userDefault = { fullName: "", cpf: "", birth: "", email: "", contactNumber: "", type: "", password: "", cep: "", street: "", number: "", neighborhood: "", city: "", state: "", complement: "", latitude: "", longitude: "", }
  const [numPage, setNumpage] = useState(1)
  const [users, setUsers] = useState("")
  const [FilteredUsers, setFilteredUsers] = useState([])
  const [search, setSearch] = useState("");
  const { isOpen, toggle, close } = useModal();
  const [selectedUser, setSelectedUser] = useState(userDefault);


  let cont = 0;
  const itensForPage = 30;
  const KeyWordinSearch = search?.split(" ")


  useEffect(() => {
    (async () => {
      try {
        const response = await getRegisteredUsers();
        const formattedResponse = userResponseFormat(response)
        response && setUsers(formattedResponse);
      } catch (err) {
        toast.error("Erro ao carregar usuários")
      }
    })()
  }, [updateList])


  useEffect(() => {
    users && setFilteredUsers(searchUsers(users, search))
  }, [search, users])


  async function userEdit(id) {
    const userId = parseInt(id)
    const response = await findUserById(userId)

    response && setSelectedUser({
      id:response.id,
      fullName: response.fullName,
      cpf: response.cpf,
      birth: response.birth,
      email: response.email,
      contactNumber: response.contactNumber,
      type: response.type,
      password: response.password,
      cep: response.idAddress.cep,
      street: response.idAddress.street,
      number: response.idAddress.number,
      neighborhood: response.idAddress.neighborhood,
      city: response.idAddress.city,
      state: response.idAddress.state,
      complement: response.idAddress.complement,
      latitude: response.idAddress.latitude,
      longitude: response.idAddress.longitude,
    })
    toggle()
  }

  async function updateUserInfo(event) {
    event.preventDefault()
    const result = passwordValidation(selectedUser)
    result && await updateUser(selectedUser)
    result && toggle()
    result && setUpdateList(updateList => !updateList)
  }

  async function userDelete(id) {
    const idUsuarioLogado = JSON.parse(localStorage.getItem('userId') && localStorage.getItem('userId'))
    idUsuarioLogado === id ? toast.error("Não é possível deletar o usuário atualmente logado no sistema") : await deleteUser(id)
    setUpdateList(updateList => !updateList)
  }

  function ClearForm() {
    setSelectedUser(userDefault)
  }

  return (
    <DefaltLayout>
      <h1>Cadastro de Novo Usuário</h1>
      <FormTest setUpdateList={setUpdateList} />
      <h1>Lista de Usuários</h1>
      <input
        placeholder="Pesquise um Usuário..."
        style={{ borderRadius: "0.5em", border: "none" }}
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <List>
        <div>
          <p style={{ width: '2%' }}>ID</p>
          <p style={{ width: '20%' }}>Nome Completo</p>
          <p style={{ width: '15%' }}>CPF</p>
          <p style={{ width: '10%' }}>Data de Nascimento</p>
          <p style={{ width: '20%' }}>E-mail</p>
          <p style={{ width: '15%' }}>Celular</p>
          <p style={{ width: '10%' }}>Tipo de Usuário</p>
        </div>
        {FilteredUsers && FilteredUsers.map((user) => {
          cont++
          if ((cont > numPage * itensForPage - itensForPage) && (cont <= numPage * itensForPage)) {
            return (
              <div key={user.id}>
                <span
                  style={{ width: '2%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.id.toString().replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span
                  style={{ width: '23%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.fullName.replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span
                  style={{ width: '15%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.cpf.replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span
                  style={{ width: '10%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.birth.replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span
                  style={{ width: '25%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.email.replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span
                  style={{ width: '15%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.contactNumber.replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span
                  style={{ width: '10%' }}
                  dangerouslySetInnerHTML={{
                    __html: user.type.replace(
                      new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"), function (match) {
                        return `<span class="highlight">${match}</span>`;
                      }
                    ),
                  }}
                />
                <span className="clicavel" style={{ marginLeft: "0.5em" }} onClick={() => { userEdit(user.id) }}>{note}</span>

                <ConfirmToast
                  asModal={true}
                  childrenClassName='margin-top-10'
                  customCancel='Não'
                  customConfirm='Sim'
                  customFunction={() => userDelete(user.id)}
                  message='Tem certeza que deseja deletar usuário?'
                  position='top-left' //will be ignored cause asModal=true
                  showCloseIcon={false}
                  theme='light'
                >
                  <span className="clicavel" style={{ marginLeft: "0.25em" }} >{trash}</span>
                </ConfirmToast>

                <Modal isOpen={isOpen} toggle={toggle} close={close}>
                  <form className="row g-3 needs-validation" noValidate>
                    <h2>Edite os Dados do Usuário</h2>
                    <div className="col-md-6">
                      <label htmlFor="validationCustom01" className="form-label">Nome Completo</label>
                      <input value={selectedUser.fullName} onChange={(e) => setSelectedUser({ ...selectedUser, fullName: e.target.value })} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o Nome Completo!
                      </div>
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="validationCustom02" className="form-label">CPF</label>
                      <IMaskInput mask="000.000.000-00" value={selectedUser.cpf} onChange={(e) => setSelectedUser({ ...selectedUser, cpf: e.target.value })} type="text" minLength={11} maxLength={11} className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha um CPF válido!
                      </div>
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="validationCustomUsername" className="form-label">Data De Nascimento</label>
                      <div className="input-group has-validation">
                        <input value={selectedUser.birth} onChange={(e) => setSelectedUser({ ...selectedUser, birth: e.target.value })} type="date" className="form-control" aria-describedby="inputGroupPrepend" />
                        <div className="invalid-feedback">
                          Preencha a Data de Nascimento!
                        </div>
                      </div>
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="validationCustom05" className="form-label">Celular</label>
                      <ReactInputMask mask='(99) 9 9999-9999' value={selectedUser.contactNumber} onChange={(e) => setSelectedUser({ ...selectedUser, contactNumber: e.target.value })} type="text" className="form-control" />
                      <div className="invalid-feedback">
                        Preencha o Celular!
                      </div>
                    </div>
                    <div className="col-md-5">
                      <label htmlFor="validationCustom03" className="form-label">E-mail</label>
                      <input value={selectedUser.email} onChange={(e) => setSelectedUser({ ...selectedUser, email: e.target.value })} type="email" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o Email!
                      </div>
                    </div>
                    <div className="col-md-5">
                      <label htmlFor="validationCustom03" className="form-label">Escolha uma Senha para o Usuário</label>
                      <input value={selectedUser.password} onChange={(e) => setSelectedUser({ ...selectedUser, password: e.target.value })} type="password" className="form-control" />
                      <div className="invalid-feedback">
                        Preencha a senha!
                      </div>
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="validationCustom04" className="form-label">Tipo do Usuário</label>
                      <select value={selectedUser.type} onChange={(e) => setSelectedUser({ ...selectedUser, type: e.target.value })} className="form-select" required>
                        <option defaultValue="" >Selecione...</option>
                        <option value="ADMIN">Administrador</option>
                        <option value="SELLER">Vendedor</option>
                        <option value="BUYER">Comprador</option>
                      </select>
                      <div className="invalid-feedback">
                        Selecione o Tipo de Usuário!
                      </div>
                    </div>
                    <div className="col-12">
                    </div>
                    <h2>Dados de Endereço</h2>

                    <div className="col-md-2">
                      <label htmlFor="validationCustom03" className="form-label">CEP</label>
                      <input value={selectedUser.cep} onChange={(e) => setSelectedUser({ ...selectedUser, cep: e.target.value })} maxLength={8} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o CEP!
                      </div>
                    </div>
                    <div className="col-md-3">
                      <label htmlFor="validationCustom03" className="form-label">Estado</label>
                      <input value={selectedUser.state} onChange={(e) => setSelectedUser({ ...selectedUser, state: e.target.value })} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o Estado!
                      </div>
                    </div>
                    <div className="col-md-7">
                      <label htmlFor="validationCustom03" className="form-label">Cidade</label>
                      <input value={selectedUser.city} onChange={(e) => setSelectedUser({ ...selectedUser, city: e.target.value })} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha a Cidade!
                      </div>
                    </div>
                    <div className="col-md-4">
                      <label htmlFor="validationCustom03" className="form-label">Bairro</label>
                      <input value={selectedUser.neighborhood} onChange={(e) => setSelectedUser({ ...selectedUser, neighborhood: e.target.value })} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o Bairro
                      </div>
                    </div>
                    <div className="col-md-4">
                      <label htmlFor="validationCustom03" className="form-label">Logradouro</label>
                      <input value={selectedUser.street} onChange={(e) => setSelectedUser({ ...selectedUser, street: e.target.value })} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o Logradouro!
                      </div>
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="validationCustom03" className="form-label">Número</label>
                      <input value={selectedUser.number} onChange={(e) => setSelectedUser({ ...selectedUser, number: e.target.value })} type="text" className="form-control" required />
                      <div className="invalid-feedback">
                        Preencha o Número!
                      </div>
                    </div>
                    <div className="col-md-2">
                      <label htmlFor="validationCustom03" className="form-label">Complemento</label>
                      <input value={selectedUser.complement} onChange={(e) => setSelectedUser({ ...selectedUser, complement: e.target.value })} type="text" className="form-control" />
                      <div className="invalid-feedback">
                        Preencha o Complemento!
                      </div>
                    </div>
                    <div className="col-6" >
                      <button className="btn btn-success" type="button" onClick={ClearForm}>Limpar</button>
                    </div>
                    <div className="col-6" >
                      <button className="btn btn-success" type="submit" onClick={updateUserInfo} >Atualizar</button>
                    </div>

                  </form>


                </Modal>
              </div>

            )
          }
          return null
        }

        )}
      </List>
      <NumPage numPage={numPage} setNumpage={setNumpage} itens={FilteredUsers} itensForpage={itensForPage} />

    </DefaltLayout>
  );
}

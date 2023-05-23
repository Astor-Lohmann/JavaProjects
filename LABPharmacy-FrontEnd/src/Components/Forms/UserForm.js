import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from 'react';
import { newUserValidation  } from "../../functions/userFunctions";
import {callApiCep, postNewUser} from '../../../src/services/userServices'
import ReactInputMask from "react-input-mask";
import { IMaskInput } from "react-imask";

export default function FormTest({setUpdateList}) {
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
          form.classList.add('was-validated')
        } else {
          form.classList.remove('was-validated')
          form.classList.add('.needs-validation')
        }
      }, false)
    })
  })()

  const userDefault = { fullName: "", cpf: "", birth: "", email: "", contactNumber: "", type: "", password: "", cep: "", street: "", number: "", neighborhood: "", city: "", state: "", complement: "", latitude: "", longitude: "", }
  const [newUser, setNewUser] = useState(userDefault)

  useEffect(() => {
    (async ()=>{
      if (newUser.cep.length === 8) {
        const response = await callApiCep(newUser.cep);
        setNewUser({ ...newUser, state: (!newUser.state ? response.state: newUser.state), city: !newUser.city ? response.city: newUser.city, neighborhood: !newUser.neighborhood ? response.neighborhood : newUser.neighborhood, street: !newUser.street ? response.street : newUser.street })
      }}
    )()
  }, [newUser.cep])

 async function userRegister(e) {
    e.preventDefault();
    const result = await newUserValidation(newUser)
    if(result){
      await postNewUser(newUser) 
      ClearForm() 
    } 
    setUpdateList(updateList=>!updateList)
  }

  function ClearForm() {
    setNewUser(userDefault)
  }

  return (
    <form onSubmit={userRegister} className="row g-3 needs-validation" noValidate>
      <div className="col-md-6">
        <label aria-labelledby="Nome Completo" htmlFor="validationCustom01" className="form-label">Nome Completo</label>
        <input id="validationCustom01" value={newUser.fullName} onChange={(e) => setNewUser({ ...newUser, fullName: e.target.value })} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o Nome Completo!
        </div>
      </div>
      <div className="col-md-2">
        <label aria-labelledby="CPF" htmlFor="validationCustom02" className="form-label">CPF</label>
        <IMaskInput mask="000.000.000-00" id="validationCustom02" value={newUser.cpf} onChange={(e) => setNewUser({ ...newUser, cpf: e.target.value })} type="text" minLength={11} maxLength={14} className="form-control"  required />
        <div className="invalid-feedback">
          Preencha um CPF válido!
        </div>
      </div>
      <div className="col-md-2">
        <label aria-labelledby="Data De Nascimento" htmlFor="validationCustomUsername" className="form-label">Data De Nascimento</label>
        <div className="input-group has-validation">
          <input id="validationCustomUsername" value={newUser.birth} onChange={(e) => setNewUser({ ...newUser, birth: e.target.value })} type="date" className="form-control" aria-describedby="inputGroupPrepend" />
          <div className="invalid-feedback">
            Preencha a Data de Nascimento!
          </div>
        </div>
      </div>
      <div className="col-md-2">
        <label aria-labelledby="Celular" htmlFor="validationCustom05" className="form-label">Celular</label>
        <ReactInputMask id="validationCustom05" mask='(99) 9 9999-9999' value={newUser.contactNumber} onChange={(e) => setNewUser({ ...newUser, contactNumber: e.target.value })} type="text" className="form-control"  ></ReactInputMask>
        <div className="invalid-feedback">
          Preencha o Celular!
        </div>
      </div>
      <div className="col-md-5">
        <label aria-labelledby="E-mail" htmlFor="validationCustom06" className="form-label">E-mail</label>
        <input id="validationCustom06" value={newUser.email} onChange={(e) => setNewUser({ ...newUser, email: e.target.value })} type="email" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o Email!
        </div>
      </div>
      <div className="col-md-5">
        <label aria-labelledby="Escolha uma Senha para o Usuário" htmlFor="validationCustom07" className="form-label">Escolha uma Senha para o Usuário</label>
        <input id="validationCustom07" value={newUser.password} onChange={(e) => setNewUser({ ...newUser, password: e.target.value })} type="password" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha a senha!
        </div>
      </div>
      <div className="col-md-2">
        <label aria-labelledby="Tipo do Usuário" htmlFor="validationCustom08" className="form-label">Tipo do Usuário</label>
        <select id="validationCustom08" value={newUser.type} onChange={(e) => setNewUser({ ...newUser, type: e.target.value })} className="form-select"  required>
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
        <label aria-labelledby="CEP" htmlFor="validationCustom09" className="form-label">CEP</label>
        <input id="validationCustom09" value={newUser.cep} onChange={(e) => setNewUser({ ...newUser, cep: e.target.value })} maxLength={8} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o CEP!
        </div>
      </div>
      <div className="col-md-3">
        <label aria-labelledby="Estado" htmlFor="validationCustom10" className="form-label">Estado</label>
        <input id="validationCustom10" value={newUser.state} onChange={(e) => setNewUser({ ...newUser, state: e.target.value })} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o Estado!
        </div>
      </div>
      <div className="col-md-7">
        <label aria-labelledby="Cidade" htmlFor="validationCustom11" className="form-label">Cidade</label>
        <input id="validationCustom11" value={newUser.city} onChange={(e) => setNewUser({ ...newUser, city: e.target.value })} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha a Cidade!
        </div>
      </div>
      <div className="col-md-4">
        <label aria-labelledby="Bairro" htmlFor="validationCustom12" className="form-label">Bairro</label>
        <input id="validationCustom12" value={newUser.neighborhood} onChange={(e) => setNewUser({ ...newUser, neighborhood: e.target.value })} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o Bairro
        </div>
      </div>
      <div className="col-md-4">
        <label aria-labelledby="Logradouro" htmlFor="validationCustom13" className="form-label">Logradouro</label>
        <input id="validationCustom13" value={newUser.street} onChange={(e) => setNewUser({ ...newUser, street: e.target.value })} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o Logradouro!
        </div>
      </div>
      <div className="col-md-2">
        <label aria-labelledby="Número" htmlFor="validationCustom14" className="form-label">Número</label>
        <input id="validationCustom14" value={newUser.number} onChange={(e) => setNewUser({ ...newUser, number: e.target.value })} type="text" className="form-control"  required />
        <div className="invalid-feedback">
          Preencha o Número!
        </div>
      </div>
      <div className="col-md-2">
        <label aria-labelledby="Complemento" htmlFor="validationCustom15" className="form-label">Complemento</label>
        <input id="validationCustom15" value={newUser.complement} onChange={(e) => setNewUser({ ...newUser, complement: e.target.value })} type="text" className="form-control"  />
        <div className="invalid-feedback">
          Preencha o Complemento!
        </div>
      </div>
      <div className="col-6" >
        <button className="btn btn-success" type="button" onClick={ClearForm}>Limpar Formulário</button>
      </div>
      <div className="col-6" >
        <button className="btn btn-success" type="submit">Cadastrar</button>
      </div>

    </form>
  );
}
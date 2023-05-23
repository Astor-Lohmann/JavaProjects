import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import { DefaltLayout } from '../../Components/MainStyle/DafaultLayout'
import SalesForm from '../../Components/Forms/SalesForm'
import NumPage from '../../Components/NumPage/NumPage'
import { note, trash } from '../../utils/Svgs'
import { toast } from 'react-toastify'
import { List } from '../../Components/ListContents/ListContents'
import Modal from '../../utils/Modal'
import useModal from '../../utils/useModal'
import { searchSales, filterSaleById } from '../../functions/salesFunctions'
import {
  deleteSale,
  findSaleById,
  getRegisteredSales,
  updateSale,
} from '../../services/salesServices'
import { getRegisteredUsers } from '../../services/userServices'
import { getAllRegisteredProducts } from '../../services/productsService'
import { userResponseFormatSaleForm } from '../../functions/userFunctions'
import { ConfirmToast } from 'react-confirm-toast'

export default function Sales() {
  const [updateList, setUpdateList] = useState(true)
  const [numPage, setNumpage] = useState(1)
  const [sales, setSales] = useState('')
  const [FilteredSales, setFilteredSales] = useState([])
  const saleDefault = {
    sellerId: '',
    productId: '',
    price: '',
    quantity: '',
    saleDate: '',
    totalPrice: 0,
    paymentMethod: '',
  }
  const [search, setSearch] = useState('')
  const { isOpen, toggle, close } = useModal()
  const [selectedSale, setSelectedSale] = useState(saleDefault)
  const [Users, setUsers] = useState('')
  const [Products, setProducts] = useState('')

  let cont = 0

  const itensForPage = 30

  useEffect(() => {
    sales && setFilteredSales(searchSales(sales, search, Users, Products))
    console.log(FilteredSales)
  }, [search, sales])

  useEffect(() => {
    (async () => {
      try {
        const responseSales = await getRegisteredSales()
        const responseUsers = await getRegisteredUsers()
        const formattedResponse = userResponseFormatSaleForm(responseUsers)
        responseUsers && setUsers(formattedResponse)
        const responseProducts = await getAllRegisteredProducts()
        responseProducts && setProducts(responseProducts)
        responseProducts && console.log(responseProducts)
        responseSales && setFilteredSales(responseSales)
        console.log(responseSales);
        responseSales && setSales(responseSales)
      } catch (err) {
        toast.error('Erro ao carregar informações de vendas')
      }
    })()
  }, [updateList])

  async function saleEdit(id) {
    const saleId = parseInt(id)
    const response = await findSaleById(saleId)
    setSelectedSale(response)
    toggle()
  }

  async function updateSaleInfo(event) {
    event.preventDefault()
    await updateSale(selectedSale)
    setUpdateList((updateList) => !updateList)
    toggle()
  }
  async function saleDelete(id) {
    await deleteSale(id)
    setUpdateList((updateList) => !updateList)
  }

  function ClearForm() {
    setSelectedSale(saleDefault)
  }

  const KeyWordinSearch = search?.split(' ')

  return (
    <DefaltLayout>
      <h1>Cadastro de Nova Venda</h1>
      <SalesForm setUpdateList={setUpdateList} updateList={updateList} />
      <h1 style={{ marginTop: '1em' }}>Lista de Vendas</h1>
      <input
        placeholder='Pesquise uma Venda...'
        style={{ borderRadius: '0.5em', border: 'none' }}
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <List>
        <div>
          <p style={{ width: '5%' }}>ID</p>
          <p style={{ width: '20%' }}>Comprador</p>
          <p style={{ width: '18%' }}>Vendedor</p>
          <p style={{ width: '10%' }}>Produto</p>
          <p style={{ width: '8%' }}>Preço Unitário</p>
          <p style={{ width: '8%' }}>Quantidade</p>
          <p style={{ width: '15%' }}>Forma de Pagamento</p>
          <p style={{ width: '8%' }}>Valor Total</p>
        </div>
        {FilteredSales
          ? FilteredSales.map((sale) => {
              cont++
              if (
                cont > numPage * itensForPage - itensForPage &&
                cont <= numPage * itensForPage
              ) {
                return (
                  <div key={sale.id}>
                    <span
                      style={{ width: '5%' }}
                      dangerouslySetInnerHTML={{
                        __html: sale.id
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                            function (match) {
                              return `<span class="highlight">${match}</span>`
                            }
                          ),
                      }}
                    />
                    <span
                      style={{ width: '20%' }}
                      dangerouslySetInnerHTML={{
                        __html: Users.filter(
                          (u) => u.id === sale.buyerId
                        )[0]?.fullName.replace(
                          new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                          function (match) {
                            return `<span class="highlight">${match}</span>`
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: '20%' }}
                      dangerouslySetInnerHTML={{
                        __html: Users.filter(
                          (u) => u.id === sale.sellerId
                        )[0]?.fullName.replace(
                          new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                          function (match) {
                            return `<span class="highlight">${match}</span>`
                          }
                        )??  "Esse vendedor foi excluido do Sistema"
                      }}
                    />
                    <span
                      style={{ width: '10%' }}
                      dangerouslySetInnerHTML={{
                        __html: Products.filter(
                          (p) => p.id === sale.productId
                        )[0]?.productName?.replace(
                          new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                          function (match) {
                            return `<span class="highlight">${match}</span>`
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: '8%' }}
                      dangerouslySetInnerHTML={{
                        __html:"R$ " + sale.price
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                            function (match) {
                              return `<span class="highlight">${match}</span>`
                            }
                          ),
                      }}
                    />
                    <span
                      style={{ width: '8%' }}
                      dangerouslySetInnerHTML={{
                        __html: sale.quantity
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                            function (match) {
                              return `<span class="highlight">${match}</span>`
                            }
                          ),
                      }}
                    />
                    <span
                      style={{ width: '15%' }}
                      dangerouslySetInnerHTML={{
                        __html: sale.paymentMethod.replace(
                          new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                          function (match) {
                            return `<span class="highlight">${match}</span>`
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: '8%' }}
                      dangerouslySetInnerHTML={{
                        __html:"R$ " + sale.totalPrice
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join('|')})`, 'gi'),
                            function (match) {
                              return `<span class="highlight">${match}</span>`
                            }
                          ),
                      }}
                    />
                    <span
                      className='clicavel'
                      style={{ marginLeft: '0.5em' }}
                      onClick={() => {
                        saleEdit(sale.id)
                      }}
                    >
                      {note}
                    </span>
                    <ConfirmToast
                      asModal={true}
                      childrenClassName='margin-top-10'
                      customCancel='Não'
                      customConfirm='Sim'
                      customFunction={() => saleDelete(sale.id)}
                      message='Tem certeza que deseja deletar a venda?'
                      position='top-left' //will be ignored cause asModal=true
                      showCloseIcon={false}
                      theme='light'
                    >
                      <span
                        className='clicavel'
                        style={{ marginLeft: '0.25em' }}
                      >
                        {trash}
                      </span>
                    </ConfirmToast>

                    <Modal isOpen={isOpen} toggle={toggle} close={close}>
                      <h2>Edite os Dados da Venda</h2>
                      <form
                        className='row g-3 needs-validation'
                        noValidate
                        onSubmit={updateSaleInfo}
                      >
                        <div className='col-md-6'>
                          <label
                            htmlFor='validationCustom01'
                            className='form-label'
                          >
                            Vendedor
                          </label>
                          <select
                            className='form-control'
                            id='user'
                            required
                            onChange={(e) => {
                              Users.map((user) => {
                                if (user.fullName === e.target.value) {
                                  setSelectedSale({
                                    ...selectedSale,
                                    sellerId: user.id,
                                  })
                                }
                              })
                            }}
                          >
                            <option selected disabled value=''>
                              Selecione o vendedor...
                            </option>
                            {Users.map((user) => {
                              if (user.type === 'Administrador') {
                                return <option>{user.fullName}</option>
                              }
                              ;<option>{user.nome}</option>
                            })}
                          </select>
                          <div className='invalid-feedback'>
                            Selecione o Vendedor!
                          </div>
                        </div>
                        <div className='col-md-4'>
                          <label
                            htmlFor='validationCustom03'
                            className='form-label'
                          >
                            Produto
                          </label>
                          <select
                            className='form-control'
                            id='product'
                            required
                            value={
                              Products.filter(
                                (p) => p.id === selectedSale.productId
                              )[0]?.productName
                            }
                            onChange={(e) => {
                              Products.map((p) => {
                                if (p.productName === e.target.value) {
                                  setSelectedSale({
                                    ...selectedSale,
                                    productId: p.id,
                                  })
                                }
                              })
                            }}
                          >
                            <option selected disabled value=''>
                              Selecione o Produto...
                            </option>
                            {Products.map((product) => (
                              <option
                                value={product.productName}
                                key={product.id}
                              >
                                {product.productName}
                              </option>
                            ))}
                          </select>
                          <div className='invalid-feedback'>
                            Selecione o Produto!
                          </div>
                        </div>
                        <div className='col-md-2'>
                          <label
                            htmlFor='validationCustom04'
                            className='form-label'
                          >
                            Preço Unitário em R$
                          </label>
                          <input
                            type='text'
                            className='form-control'
                            id='price'
                            required
                            value={selectedSale.price
                              .toString()
                              .replace(',', '.')}
                            onChange={(e) =>
                              setSelectedSale({
                                ...selectedSale,
                                price: e.target.value,
                              })
                            }
                          />
                          <div className='invalid-feedback'>
                            Preencha o Preço Unitário!
                          </div>
                        </div>
                        <div className='col-md-2'>
                          <label
                            htmlFor='validationCustom05'
                            className='form-label'
                          >
                            Quantidade
                          </label>
                          <input
                            type='number'
                            className='form-control'
                            id='quantity'
                            required
                            value={selectedSale.quantity}
                            onChange={(e) =>
                              setSelectedSale({
                                ...selectedSale,
                                quantity: parseInt(e.target.value),
                              })
                            }
                          />
                          <div className='invalid-feedback'>
                            Preencha a Quantidade!
                          </div>
                        </div>
                        <div className='col-md-3'>
                          <label
                            htmlFor='validationCustom06'
                            className='form-label'
                          >
                            Forma de Pagamento
                          </label>
                          <select
                            className='form-control'
                            id='payment'
                            required
                            value={selectedSale.paymentMethod}
                            onChange={(e) =>
                              setSelectedSale((prev) => ({
                                ...prev,
                                paymentMethod: e.target.value,
                              }))
                            }
                          >
                            <option selected disabled value=''>
                              Selecione a Forma de Pagamento...
                            </option>
                            <option selected disabled value=''>
                              Selecione a Forma de Pagamento...
                            </option>
                            <option value='CREDIT_CARD'>
                              Cartão de Crédito
                            </option>
                            <option value='DEBIT_CARD'>Cartão de Débito</option>
                            <option value='BANK_TRANSFER'>
                              Transferência Bancária
                            </option>
                            <option value='PIX'>PIX</option>
                          </select>
                          <div className='invalid-feedback'>
                            Selecione a Forma de Pagamento!
                          </div>
                        </div>
                        <div className='col-md-3'>
                          <label
                            htmlFor='validationCustom07'
                            className='form-label'
                          >
                            Valor Total em R$
                          </label>
                          <input
                            type='text'
                            className='form-control'
                            id='validationCustom01'
                            required
                            value={(
                              selectedSale.price * selectedSale.quantity
                            ).toFixed(2)}
                          />
                          <div className='invalid-feedback'>
                            Preencha a Quantidade e o Preço Unitário!
                          </div>
                        </div>
                        <div className='col-6'>
                          <button
                            className='btn btn-success'
                            type='button'
                            onClick={ClearForm}
                          >
                            Limpar
                          </button>
                        </div>
                        <div className='col-6'>
                          <button
                            className='btn btn-success'
                            type='submit'
                            onClick={updateSaleInfo}
                          >
                            Atualizar
                          </button>
                        </div>
                      </form>
                    </Modal>
                  </div>
                )
              }
            })
          : null}
      </List>
      <NumPage
        numPage={numPage}
        setNumpage={setNumpage}
        itens={FilteredSales}
        itensForpage={itensForPage}
      />
    </DefaltLayout>
  )
}

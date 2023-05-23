import { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { users } from "../../mocks/users";
import { getRegisteredSales, postNewSale } from "../../services/salesServices";
import { getRegisteredProducts } from "../../services/productsService";
import { getRegisteredUsers } from "../../services/userServices";
import { toast } from "react-toastify";
import { userResponseFormatSaleForm } from "../../functions/userFunctions";
import { async } from "q";


export default function SalesForm({ updateList, setUpdateList }) {
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




  const [Users, setUsers] = useState([])
  const [Products, setProducts] = useState([])
  const saleDefault = { sellerId: "", buyerId:"", productId:"", price:"", quantity:"", totalPrice:"", paymentMethod:"", sellerName:"", buyerName:"", productName:"" }
  const [sale, setSale] = useState(saleDefault)

  useEffect(() => {
    (async () => {
      try {
        const responseUsers = await getRegisteredUsers();
        const formattedResponse = userResponseFormatSaleForm(responseUsers)
        responseUsers && setUsers(formattedResponse);
        const responseProducts = await getRegisteredProducts();
        responseProducts && setProducts(responseProducts);
      } catch (err) {
        toast.error("Erro ao carregar usuários ou produtos")
      }
    })()
  }, [])
  

  useEffect(() => {
    setSale({ ...sale, totalPrice: sale.price * sale.quantity })
  }, [sale.price, sale.quantity])




  async function SaleRegister(e) {
    e.preventDefault();
    const saleToRegister= { sellerId: sale.sellerId, buyerId:sale.buyerId, productId:sale.productId, price:sale.price, quantity:sale.quantity, saleDate: new Date().getTime(), totalPrice:sale.totalPrice, paymentMethod:sale.paymentMethod }
    let estoqueAtual = Products.filter(p => p.id === saleToRegister.productId)[0].stockQuantity
    if(saleToRegister.quantity > estoqueAtual){
      return toast.error(`A quantidade não pode ser maior que ${estoqueAtual} unidade(s).`)
    }
    await postNewSale(saleToRegister)
    ClearForm()
    setUpdateList(!updateList)
  }

  function ClearForm() {
    setSale(saleDefault)

  }

  return (
    <form className="row g-3 needs-validation" noValidate onSubmit={SaleRegister}>
      <div className="col-md-4">
        <label htmlFor="validationCustom01" className="form-label">Comprador</label>
        <select className="form-control"
          id="user"
          required
          value={sale.buyerName}
          onChange={(e) => {
            const buyerFullName = e.target.value
            const filteredUsers = Users.filter(u=>u.fullName == buyerFullName)
            if(filteredUsers.length>0){
              setSale({...sale, buyerId:parseInt(filteredUsers[0].id), buyerName:buyerFullName})
            }
          }
          }
          >
          <option selected disabled value="">Selecione o comprador...</option>
          {Users.map(user => {
            if (user.type == "Comprador") {
              return <option key={`buyer${user.id}`} value={user.fullName}>{user.fullName}</option>

            }
          })}
        </select>
        <div className="invalid-feedback">
          Selecione o Comprador!
        </div>
      </div>
      <div className="col-md-4">
        <label htmlFor="validationCustom02" className="form-label">Vendedor</label>
        <select className="form-control"
          id="seller"
          required
          value={sale.sellerName}
          onChange={(e) => {
            const sellerFullName = e.target.value
            const filteredUsers = Users.filter(u=>u.fullName == sellerFullName)
            if(filteredUsers.length>0){
              setSale({...sale, sellerId:parseInt(filteredUsers[0].id), sellerName:sellerFullName})
            }
          }
          }
        >

          <option selected disabled value="">Selecione o Vendedor...</option>
          {Users.map(user => {
            if (user.type === 'Administrador') {
              return <option key={`seller${user.id}`} value={user.fullName}>{user.fullName}</option>

            }
            <option >{user.nome}</option>
          })}
        </select>
        <div className="invalid-feedback">
          Selecione o Vendedor!
        </div>
      </div>
      <div className="col-md-4">
        <label htmlFor="validationCustom03" className="form-label">Produto</label>
        <select className="form-control"
          id="product"
          required
          value={sale.productName}
          onChange={(e) => {
            const productName = e.target.value
            const filteredProduct = Products.filter(p=>p.productName == productName)
            if(filteredProduct.length>0){
              setSale({...sale, productId:parseInt(filteredProduct[0].id), productName:productName, price:parseFloat(filteredProduct[0].price)})
            }
          }
          }>
          <option selected disabled value="" >Selecione o Produto...</option>
          {Products.map(products => (
            <option key={`produto${products.id}`} value={products.productName} >{products.productName}</option>

          ))}
        </select>
        <div className="invalid-feedback">
          Selecione o Produto!
        </div>
      </div>

      <div className="col-md-2">
        <label htmlFor="validationCustom04" className="form-label">Preço Unitário em R$</label>
        <input type="text" className="form-control" id="price" required value={sale.price.toString().replace(",", ".")} />
        <div className="invalid-feedback">
          Preencha o Preço Unitário!
        </div>
      </div>
      <div className="col-md-2">
        <label htmlFor="validationCustom05" className="form-label">Quantidade</label>
        <input type="number" className="form-control" id="quantity" required value={sale.quantity} onChange={(e) => setSale((prev) => ({ ...prev, quantity: parseInt(e.target.value) }))} />
        <div className="invalid-feedback">
          Preencha a Quantidade!
        </div>
      </div>

      <div className="col-md-3">
        <label htmlFor="validationCustom06" className="form-label">Forma de Pagamento</label>
        <select className="form-control" id="payment" required value={sale.paymentMethod} onChange={(e) => setSale((prev) => ({ ...prev, paymentMethod: e.target.value }))}>
          <option selected disabled value="">Selecione a Forma de Pagamento...</option>
          <option value="CREDIT_CARD">Cartão de Crédito</option>
          <option value="DEBIT_CARD">Cartão de Débito</option>
          <option value="BANK_TRANSFER">Transferência Bancária</option>
          <option value="PIX">PIX</option>
        </select>
        <div className="invalid-feedback">
          Selecione a Forma de Pagamento!
        </div>
      </div>
      <div className="col-md-3">
        <label htmlFor="validationCustom07" className="form-label">Valor Total em R$</label>
        <input type="text" className="form-control" id="validationCustom01" required  value={(sale.price * sale.quantity).toFixed(2)} />
        <div className="invalid-feedback">
          Preencha a Quantidade e o Preço Unitário!
        </div>
      </div>




      <div className="col-6" >
        <button className="btn btn-success" type="reset" onClick={ClearForm}>Limpar Formulário</button>
      </div>
      <div className="col-6">
        <button className="btn btn-success" type="submit" >Registrar Venda</button>
      </div>

    </form>
  );
}
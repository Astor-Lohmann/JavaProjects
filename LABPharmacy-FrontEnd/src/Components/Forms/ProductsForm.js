import { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import { getRegisteredProducts, postNewProduct } from '../../services/productsService'
import { toast } from 'react-toastify'

export default function ProductsForm({setUpdateList, updateList}) {
  (() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach((form) => {
      form.addEventListener(
        'submit',
        (event) => {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
            form.classList.add('was-validated')
          } else {
            form.classList.remove('was-validated')
            form.classList.add('.needs-validation')
          }
        },
        false
      )
    })
  })()

  const productDefault = {productName:"", laboratoryName:"", productImage:"", dosage:"", productDescription:"", price:"", productType:"", stockQuantity:""}

  const [product, setProduct] = useState(productDefault)
  const [products, setProducts] = useState("");

  useEffect(()=>{
    setUpdateList(updateList=>!updateList)
  },[])
  
  
  useEffect(()=>{
    (async()=>{
      const responseProducts = await getRegisteredProducts();
      responseProducts && setProducts(responseProducts);
    })()
  },[updateList])



  async function ProductRegister(e) {
    e.preventDefault();
    let cont = 0;
    products.map((p)=>{
      if(p.productName === product.productName){
        cont++
      }
    })
    if(cont === 0){
      const id = JSON.parse(localStorage.getItem('userId')&&localStorage.getItem('userId'))
      await postNewProduct({...product, idUser: id&&id}) 
      ClearForm()
      setUpdateList(updateList=>!updateList)
    }else{
      toast.error("Ja tem um produto com esse nome cadastrado no sistema")
    }

  }

  function ClearForm() {
    setProduct(productDefault)
  }
  

  return (
    <form
      className='row g-3 needs-validation'
      noValidate
      onSubmit={ProductRegister}
    >
      <div className='col-md-6'>
        <label htmlFor='validationCustom01' className='form-label'>
          Nome do Medicamento
        </label>
        <input
          type='text'
          className='form-control'
          id='validationCustom01'
          value={product.productName}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              productName: e.target.value,
            }))
          }}
          required
        />
        <div className='invalid-feedback'>Preencha o Nome do Medicamento!</div>
      </div>
      <div className='col-md-3'>
        <label htmlFor='validationCustom02' className='form-label'>
          Laboratório
        </label>
        <input
          type='text'
          className='form-control'
          id='validationCustom02'
          value={product.laboratoryName}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              laboratoryName: e.target.value,
            }))
          }}
          required
        />
        <div className='invalid-feedback'>Preencha o Laboratório!</div>
      </div>
      <div className='col-md-3'>
        <label htmlFor='validationCustomUsername' className='form-label'>
          Dosagem
        </label>
        <div className='input-group has-validation'>
          <input
            type='text'
            className='form-control'
            id='validationCustomUsername'
            aria-describedby='inputGroupPrepend'
            value={product.dosage}
            onChange={(e) => {
              setProduct((prev) => ({
                ...prev,
                dosage: e.target.value,
              }))
            }}
            required
          />
          <div className='invalid-feedback'>Preencha a Dosagem!</div>
        </div>
      </div>
      <div className='col-md-5'>
        <label htmlFor='validationCustom05' className='form-label'>
          Digite a URL da imagem
        </label>
        <input
          type='text'
          className='form-control'
          id='validationCustom05'
          value={product.productImage}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              productImage: e.target.value,
            }))
          }}
          required
        />
        <div className='invalid-feedback'>Preencha a URL!</div>
      </div>
      <div className='col-md-3'>
        <label htmlFor='validationCustom04' className='form-label'>
          Tipo do Medicamento
        </label>
        <select
          className='form-select'
          id='validationCustom04'
          value={product.productType}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              productType: e.target.value,
            }))
          }}
          required
        >
          <option defaultValue='Selecione...'>Selecione...</option>
          <option value='Controlado'>Controlado</option>
          <option value='Comum'>Comum</option>
        </select>
        <div className='invalid-feedback'>Selecione o Tipo de Medicamento!</div>
      </div>
      <div className='col-md-2'>
        <label htmlFor='validationCustom05' className='form-label'>
          Preço Unitário
        </label>
        <input
          type='text'
          className='form-control'
          id='validationCustom05'
          value={product.price.toString().replace(",", ".")}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              price: e.target.value,
            }))
          }}
          required
        />
        <div className='invalid-feedback'>Preencha Preço Unitário!</div>
      </div>
      <div className='col-md-2'>
        <label htmlFor='validationCustom03' className='form-label'>
          Quantidade
        </label>
        <input
          type='number'
          className='form-control'
          id='validationCustom03'
          value={product.stockQuantity}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              stockQuantity: e.target.value,
            }))
          }}
          required
        />
        <div className='invalid-feedback'>Preencha a Quantidade!</div>
      </div>
      <div className='mb-3'>
        <label htmlFor='validationTextarea' className='form-label'>
          Descrição
        </label>
        <textarea
          className='form-control'
          id='validationTextarea'
          maxLength={255}
          value={product.productDescription}
          onChange={(e) => {
            setProduct((prev) => ({
              ...prev,
              productDescription: e.target.value,
            }))
          }}
          required
        ></textarea>
        <div className='invalid-feedback'>
          Preencha uma Descrição do Medicamento!
        </div>
      </div>
      <div className='col-6'>
        <button className='btn btn-success' type='reset' onClick={ClearForm}>
          Limpar Formulário
        </button>
      </div>
      <div className='col-6'>
        <button className='btn btn-success' type='submit'>
          Cadastrar
        </button>
      </div>
    </form>
  )
}

import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { DefaltLayout } from "../../Components/MainStyle/DafaultLayout";
import ProductsForm from "../../Components/Forms/ProductsForm";
import { List } from "../../Components/ListContents/ListContents";
import NumPage from "../../Components/NumPage/NumPage";
import { note, trash } from "../../utils/Svgs";
import { toast } from "react-toastify";
import { deleteProduct, findProductById, getRegisteredProducts, updateProduct } from "../../services/productsService";
import {searchProducts} from "../../functions/productFunction";
import Modal from "../../utils/Modal";
import useModal from "../../utils/useModal";
import { ConfirmToast } from "react-confirm-toast";

export default function Products() {
  const [updateList, setUpdateList] = useState(false)
  const [numPage, setNumpage] = useState(1);
  const [products, setProducts] = useState("");
  const [FilteredProducts, setFilteredProducts] = useState([]);
  const [search, setSearch] = useState("");
  const { isOpen, toggle, close } = useModal();
  const productDefault = {productName:"", laboratoryName:"", productImage:"", dosage:"", productDescription:"", price:"", productType:"", stockQuantity:""}
  const [selectedProduct, setSelectedProduct] = useState(productDefault);
  

  let cont = 0;

  const itensForPage = 30;

  useEffect(() => {
    (async () => {
      try {
        const responseProducts = await getRegisteredProducts();
        responseProducts && setProducts(responseProducts);
      } catch (err) {
        toast.error("Erro ao carregar produtos");
      }
    })();
  }, [updateList]);

  useEffect(() => {
    products && setFilteredProducts(searchProducts(products, search));
  }, [search, products]);

  async function productEdit(id) {
    const productId= parseInt(id)
    const prod = await findProductById(productId)
    prod && setSelectedProduct(prod)
    toggle()
  }

  async function updateProductInfo(event) {
    event.preventDefault()
    console.log(selectedProduct);
    await updateProduct({...selectedProduct, idUser:1})
    setUpdateList(updateList=>!updateList)
    toggle()
  }

  const KeyWordinSearch = search?.split(" ");

  async function productDelete(id) {
    await deleteProduct(id)
    setUpdateList(updateList=>!updateList)
  }

  function ClearForm() {
    setSelectedProduct(productDefault)
  }

  return (
    <DefaltLayout>
      <h1>Cadastro de Novo Medicamento</h1>
      <ProductsForm setUpdateList={setUpdateList} updateList={updateList}/>
      <h1 style={{ marginTop: "1em" }}>Lista de Produtos</h1>
      <input
        placeholder="Pesquise um Produto..."
        style={{ borderRadius: "0.5em", border: "none" }}
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <List>
        <div>
          <p style={{ width: "5%" }}>ID</p>
          <p style={{ width: "18%" }}>Medicamento</p>
          <p style={{ width: "10%" }}>Dosagem</p>
          <p style={{ width: "10%" }}>Tipo</p>
          <p style={{ width: "10%" }}>Preço Unitário</p>
          <p style={{ width: "30%" }}>Descrição</p>
          <p style={{ width: "8%" }}>Quantidade</p>
        </div>
        {FilteredProducts
          ? FilteredProducts.map((product) => {
              cont++;
              if (
                cont > numPage * itensForPage - itensForPage &&
                cont <= numPage * itensForPage
              ) {
                return (
                  <div key={product.id}>
                    <span
                      style={{ width: "5%" }}
                      dangerouslySetInnerHTML={{
                        __html: product.id
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                            function (match) {
                              return `<span class="highlight">${match}</span>`;
                            }
                          ),
                      }}
                    />
                    <span
                      style={{ width: "20%" }}
                      dangerouslySetInnerHTML={{
                        __html: product.productName.replace(
                          new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                          function (match) {
                            return `<span class="highlight">${match}</span>`;
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: "10%" }}
                      dangerouslySetInnerHTML={{
                        __html: product.dosage.replace(
                          new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                          function (match) {
                            return `<span class="highlight">${match}</span>`;
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: "10%" }}
                      dangerouslySetInnerHTML={{
                        __html: product.productType.replace(
                          new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                          function (match) {
                            return `<span class="highlight">${match}</span>`;
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: "10%" }}
                      dangerouslySetInnerHTML={{
                        __html:"R$ " + product.price
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                            function (match) {
                              return `<span class="highlight">${match}</span>`;
                            }
                          ),
                      }}
                    />
                    <span
                      className="description"
                      style={{ width: "30%" }}
                      dangerouslySetInnerHTML={{
                        __html: product.productDescription.replace(
                          new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                          function (match) {
                            return `<span class="highlight">${match}</span>`;
                          }
                        ),
                      }}
                    />
                    <span
                      style={{ width: "8%" }}
                      dangerouslySetInnerHTML={{
                        __html: product.stockQuantity
                          .toString()
                          .replace(
                            new RegExp(`(${KeyWordinSearch?.join("|")})`, "gi"),
                            function (match) {
                              return `<span class="highlight">${match}</span>`;
                            }
                          ),
                      }}
                    />
                    <span className="clicavel" style={{ marginLeft: "0.5em" }} onClick={() => { productEdit(product.id) }}>
                      {note}
                    </span>
                    <ConfirmToast
                      asModal={true}
                      childrenClassName='margin-top-10'
                      customCancel='Não'
                      customConfirm='Sim'
                      customFunction={() => productDelete(product.id)}
                      message='Tem certeza que deseja deletar o produto?'
                      position='top-left' //will be ignored cause asModal=true
                      showCloseIcon={false}
                      theme='light'
                    >
                      <span className="clicavel" style={{ marginLeft: "0.25em" }} >{trash}</span>
                    </ConfirmToast>

                    <Modal isOpen={isOpen} toggle={toggle} close={close}>
                      <h2>Edite os Dados do Produto</h2>
                      <form
                        className="row g-3 needs-validation"
                        noValidate
                        onSubmit={updateProductInfo}
                      >
                        <div className="col-md-6">
                          <label
                            htmlFor="validationCustom01"
                            className="form-label"
                          >
                            Nome do Medicamento
                          </label>
                          <input
                            type="text"
                            className="form-control"
                            id="validationCustom01"
                            value={selectedProduct.productName}
                            onChange={(e) => {
                              setSelectedProduct((prev) => ({
                                ...prev,
                                productName: e.target.value,
                              }));
                            }}
                            required
                          />
                          <div className="invalid-feedback">
                            Preencha o Nome do Medicamento!
                          </div>
                        </div>
                        <div className="col-md-3">
                          <label
                            htmlFor="validationCustom02"
                            className="form-label"
                          >
                            Laboratório
                          </label>
                          <input
                            type="text"
                            className="form-control"
                            id="validationCustom02"
                            value={selectedProduct.laboratoryName}
                            onChange={(e) => {
                              setSelectedProduct((prev) => ({
                                ...prev,
                                laboratoryName: e.target.value,
                              }));
                            }}
                            required
                          />
                          <div className="invalid-feedback">
                            Preencha o Laboratório!
                          </div>
                        </div>
                        <div className="col-md-3">
                          <label
                            htmlFor="validationCustomUsername"
                            className="form-label"
                          >
                            Dosagem
                          </label>
                          <div className="input-group has-validation">
                            <input
                              type="text"
                              className="form-control"
                              id="validationCustomUsername"
                              aria-describedby="inputGroupPrepend"
                              value={selectedProduct.dosage}
                              onChange={(e) => {
                                setSelectedProduct((prev) => ({
                                  ...prev,
                                  dosage: e.target.value,
                                }));
                              }}
                              required
                            />
                            <div className="invalid-feedback">
                              Preencha a Dosagem!
                            </div>
                          </div>
                        </div>
                        <div className="col-md-5">
                          <label
                            htmlFor="validationCustom05"
                            className="form-label"
                          >
                            Digite a URL da imagem
                          </label>
                          <input
                            type="text"
                            className="form-control"
                            id="validationCustom05"
                            value={selectedProduct.productImage}
                              onChange={(e) => {
                                setSelectedProduct((prev) => ({
                                  ...prev,
                                  productImage: e.target.value,
                                }));
                              }}
                            required
                          />
                          <div className="invalid-feedback">
                            Preencha a URL!
                          </div>
                        </div>
                        <div className="col-md-3">
                          <label
                            htmlFor="validationCustom04"
                            className="form-label"
                          >
                            Tipo do Medicamento
                          </label>
                          <select
                            className="form-select"
                            id="validationCustom04"
                            value={selectedProduct.productType}
                            onChange={(e) => {
                              setSelectedProduct((prev) => ({
                                ...prev,
                                productType: e.target.value,
                              }));
                            }}
                            required
                          >
                            <option defaultValue="Selecione...">
                              Selecione...
                            </option>
                            <option value="Controlado">Controlado</option>
                            <option value="Comum">Comum</option>
                          </select>
                          <div className="invalid-feedback">
                            Selecione o Tipo de Medicamento!
                          </div>
                        </div>
                        <div className="col-md-2">
                          <label
                            htmlFor="validationCustom05"
                            className="form-label"
                          >
                            Preço Unitário
                          </label>
                          <input
                            type="text"
                            className="form-control"
                            id="validationCustom05"
                            value={selectedProduct.price}
                            onChange={(e) => {
                              setSelectedProduct((prev) => ({
                                ...prev,
                                price: e.target.value,
                              }));
                            }}
                            required
                          />
                          <div className="invalid-feedback">
                            Preencha Preço Unitário!
                          </div>
                        </div>
                        <div className="col-md-2">
                          <label
                            htmlFor="validationCustom03"
                            className="form-label"
                          >
                            Quantidade
                          </label>
                          <input
                            type="number"
                            className="form-control"
                            id="validationCustom03"
                            value={selectedProduct.stockQuantity}
                            onChange={(e) => {
                              setSelectedProduct((prev) => ({
                                ...prev,
                                stockQuantity: e.target.value,
                              }));
                            }}
                            required
                          />
                          <div className="invalid-feedback">
                            Preencha a Quantidade!
                          </div>
                        </div>
                        <div className="mb-3">
                          <label
                            htmlFor="validationTextarea"
                            className="form-label"
                          >
                            Descrição
                          </label>
                          <textarea
                            className="form-control"
                            id="validationTextarea"
                            value={selectedProduct.productDescription}
                            onChange={(e) => {
                              setSelectedProduct((prev) => ({
                                ...prev,
                                productDescription: e.target.value,
                              }));
                            }}
                            required
                          ></textarea>
                          <div className="invalid-feedback">
                            Preencha uma Descrição do Medicamento!
                          </div>
                        </div>
                        <div className="col-6">
                          <button className="btn btn-success" type="button" onClick={ClearForm}>
                            Limpar
                          </button>
                        </div>
                        <div className="col-6">
                          <button className="btn btn-success" type="submit">
                            Atualizar
                          </button>
                        </div>
                      </form>
                    </Modal>
                  </div>
                );
              }
            })
          : null}
      </List>
      <NumPage
        numPage={numPage}
        setNumpage={setNumpage}
        itens={FilteredProducts}
        itensForpage={itensForPage}
      />
    </DefaltLayout>
  );
}

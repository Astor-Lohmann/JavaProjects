import { postNewSale, updateSale } from "./salesServices";
import { toast } from "react-toastify";
import api from "../api/api";
import axios from 'axios';

// jest.mock("../api/api");
jest.mock("react-toastify");

const sale = {
  id_seller: 1,
  id_product: 1,
  price: 10.0,
  quantity: 2,
  sale_date: "04-04-2023",
  total_price: 20.0,
  payment_method: "CREDIT_CARD",
};

// describe("postNewSale", () => {
//   it("calls api.post with the correct parameters", async () => {
//     await postNewSale(sale);

//     expect(api.post).toHaveBeenCalledWith("/vendas", sale, {
//       headers: {
//         "Content-Type": "application/json",
//       },
//     });
//   });

//   it("shows success toast message when sale is successfully posted", async () => {


//     api.post.mockResolvedValueOnce({});

//     await postNewSale(sale);

//     expect(toast.success).toHaveBeenCalledWith("Venda cadastrada com sucesso!");
//   });

//   it("shows error toast message when sale post fails", async () => {

//     const errorMessage = "Unknown error";
//     api.post.mockRejectedValueOnce({ message: errorMessage });

//     await postNewSale(sale);

//     expect(toast.error).toHaveBeenCalledWith(
//       "Erro desconhecido ao cadastrar venda: " + errorMessage
//     );
//   });

//   it("shows error toast message when postNewSale function throws an error", async () => {
 

//     const errorMessage = "Unknown error";
//     api.post.mockRejectedValueOnce({ message: errorMessage });

//     await postNewSale(sale);

//     expect(toast.error).toHaveBeenCalledWith(
//       "Erro desconhecido ao cadastrar venda: " + errorMessage
//     );
//   });
// });

// describe('updateSale', () => {
//     beforeEach(() => {
//       jest.clearAllMocks();
//     });
  
//     it('should call the API with the correct arguments', async () => {
//       api.put.mockResolvedValueOnce();
  
//       await updateSale(sale);
  
//       expect(api.put).toHaveBeenCalledTimes(1);
//       expect(api.put).toHaveBeenCalledWith(`/vendas/${sale.id}`, sale, {
//         headers: {
//           'Content-Type': 'application/json'
//         }
//       });
//     });
// });
// const request = require('supertest');
// const app = require('../App');


const apiUrl = 'http://localhost:8001/api/vendas/cadastro';


describe('postNewSale function', () => {
    beforeEach(() => {
      jest.spyOn(global, 'fetch');
      jest.spyOn(console, 'error').mockImplementation(() => {});
      jest.spyOn(console, 'log').mockImplementation(() => {});
    });
  
    afterEach(() => {
      global.fetch.mockRestore();
      console.error.mockRestore();
      console.log.mockRestore();
    });
  
    test('should send a POST request to the API with sale data', async () => {

      const mockResponse = { status: 201 };
      global.fetch.mockResolvedValueOnce(mockResponse);

  
      await postNewSale(sale, apiUrl);
  
      expect(global.fetch).toHaveBeenCalledTimes(1);
      expect(global.fetch).toHaveBeenCalledWith(apiUrl, {
        method: 'POST',
        body: JSON.stringify(sale),
        headers: {
          'Content-Type': 'application/json'
        }
      });
    });
}); 
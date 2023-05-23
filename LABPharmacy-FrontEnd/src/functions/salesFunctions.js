export function searchSales(sales, search, Users, Products) {
  const filter = [];

  sales.map((sale) => {
    let cont = 0;
    search.split(" ").map((word) => {
      if (
        sale.id
          .toString()
          .toLocaleLowerCase()
          .includes(word.toLocaleLowerCase()) ||
        sale.price.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        Users.filter(
          (u) => u.id === sale.buyerId
        )[0]?.fullName.toLocaleLowerCase().includes(word.toLocaleLowerCase())||
        Products.filter(
          (p) => p.id === sale.productId
        )[0]?.productName.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        Users.filter(
          (u) => u.id === sale.sellerId
        )[0]?.fullName.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        sale.quantity.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        sale.saleDate.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        sale.totalPrice.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
        sale.paymentMethod.toLocaleLowerCase().includes(word.toLocaleLowerCase())) {
        return cont++;
      } else {
        return null;
      }
    });
    if (cont == search.split(" ").length) {
      filter.push(sale);
    }
    return null;
  });
  return filter;
}

export function filterSaleById(id, sales) {
  const sale = sales.filter((s) => s.id === id);
  // const sellingDate = dateFormatterDDMMAAAA(sale[0].saleDate);

  const saleUpdate = {
    id: sale[0].id,
    sellerId: sale[0].sellerId,
    productId: sale[0].productId,
    price: sale[0].price,
    quantity: sale[0].quantity,
    saleDate: sale[0].saleDate,
    paymentMethod: sale[0].paymentMethod,
    totalPrice: sale[0].totalPrice,
  };

  return saleUpdate;
}

export function searchProducts(products, search) {
    const filter = []
    products.map(product => {
      let cont = 0
      search.split(" ").map((word) => {
        if (
            product.id.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
            product.productName.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
            product.dosage.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
            product.productType.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
            product.price.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
            product.productDescription.toLocaleLowerCase().includes(word.toLocaleLowerCase()) ||
            product.stockQuantity.toString().toLocaleLowerCase().includes(word.toLocaleLowerCase())) {
          return cont++
        } else {
          return null
        }
      })
      if (cont === search.split(" ").length) {
        filter.push(product)
      }
      return null
    })
    return filter
  }


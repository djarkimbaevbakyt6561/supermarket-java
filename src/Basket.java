public class Basket implements BasketInterface {
    Product[] products = new Product[100];
    int index = 0;
    @Override

    public void addProduct(Product product){
        if(index < 100){
            products[index++] = product;

        } else {
            System.out.println("Превышен лимит!");
        }
    }

    public void deleteProduct(long id){
        Product[] newProducts = new Product[100];
        int newIndex = 0;

        for (Product product : products) {
            if (product != null && !(product.id == id)) {
                newProducts[newIndex++] = product;
            }
        }
        index = newIndex;
        products = newProducts;
    }
    public void deleteProducts(long[] ids) {
        Product[] newProducts = new Product[100];
        int newIndex = 0;

        for (Product product : products) {
            boolean shouldDelete = false;
            for (long id : ids) {
                if (product != null && product.id == id) {
                    shouldDelete = true;
                    break;
                }
            }
            if (!shouldDelete) {
                newProducts[newIndex++] = product;
            }
        }
        index = newIndex;
        products = newProducts;
    }
}

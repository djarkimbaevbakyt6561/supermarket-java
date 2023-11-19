public interface BasketInterface {
    void deleteProduct(long id);
    void deleteProducts(long[] ids);
    void addProduct(Product product);
}

package technifutur.java.order_dispatch.model.entity;

public class StockResponse {

    private String productId;
    private int quantity;
    private int quantityInStock;


    public StockResponse(String productId, int quantity, int quantityInStock) {
        this.productId = productId;
        this.quantity = quantity;
        this.quantityInStock = quantityInStock;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean isAvailableInStock(StockResponse stockResponse) {
        return stockResponse.getQuantity() >= this.quantityInStock;
    }

}

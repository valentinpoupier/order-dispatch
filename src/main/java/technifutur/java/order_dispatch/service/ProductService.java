package technifutur.java.order_dispatch.service;

import technifutur.java.order_dispatch.model.dto.ProductDTO;

public interface ProductService {
    long createNew(ProductDTO product);
}

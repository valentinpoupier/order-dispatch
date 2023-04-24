package technifutur.java.order_dispatch.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import technifutur.java.order_dispatch.model.dto.ProductDTO;
import technifutur.java.order_dispatch.model.entity.Product;
import technifutur.java.order_dispatch.repository.ProductRepository;
import technifutur.java.order_dispatch.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
private final ProductRepository repository;
public ProductServiceImpl(ProductRepository repository){
    this.repository = repository;
}
    @Override
    public long createNew(ProductDTO dto) {
       Product entity = new Product();
       entity.setProduct_id(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStockQuantity(dto.getStockQuantity());
        repository.save(entity);
        return entity.getProduct_id();
    }

    
    @RabbitListener
    public void processCreateBookMessage(ProductDTO dto){
        createNew(dto);
    }
    
}

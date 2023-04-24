package technifutur.java.order_dispatch.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.RabbitExceptionTranslator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technifutur.java.order_dispatch.listener.ProducMQListener;
import technifutur.java.order_dispatch.model.dto.ProductDTO;
import technifutur.java.order_dispatch.model.entity.Product;
import technifutur.java.order_dispatch.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    //private final ProducMQListener productListener;
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        //return ResponseEntity.ok(Arrays.asList("Blop", "BiBlop", "Plop"));
        return null;
    }

//    @GetMapping(path = "/poke")
//    public ResponseEntity<List> getAllPokemon() {
//        URI uri = URI.create("https://pokeapi.co/api/v2/ability/?limit=20&offset=20");
////        return ResponseEntity.ok(restTemplate.getForObject(uri, List.class));
//    }

}

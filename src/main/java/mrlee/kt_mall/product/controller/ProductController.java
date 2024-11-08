package mrlee.kt_mall.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mrlee.kt_mall.product.dto.SaveProduct;
import mrlee.kt_mall.product.dto.SaveProductMarket;
import mrlee.kt_mall.product.dto.SaveProductMarketLink;
import mrlee.kt_mall.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Void> productAdd(@Valid @RequestBody SaveProduct saveProduct) {
        productService.addProduct(saveProduct);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("{productId}/markets")
    public ResponseEntity<Void> productMarketAdd(@PathVariable("productId") Long productId,
                                                 @Valid @RequestBody List<SaveProductMarket> saveProductMarketList) {
        productService.addProductMarket(productId, saveProductMarketList);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("/markets/link")
    public ResponseEntity<Void> productMarketLink(@RequestBody SaveProductMarketLink saveProductMarketLink) {
        productService.linkProductMarket(saveProductMarketLink);
        return new ResponseEntity<>(OK);
    }
}

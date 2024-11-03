package mrlee.kt_mall.product.controller;

import lombok.RequiredArgsConstructor;
import mrlee.kt_mall.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public void productAdd() {
    }

    @PatchMapping("/")
    public void productModify() {
    }

    @GetMapping("/{id}")
    public void productDetails() {
    }

    @GetMapping("/")
    public void productList() {
    }
}

package mrlee.kt_mall.product.service;

import lombok.RequiredArgsConstructor;
import mrlee.kt_mall.member.entity.Member;
import mrlee.kt_mall.member.repository.MemberRepository;
import mrlee.kt_mall.product.dto.SaveProductMarketLink;
import mrlee.kt_mall.product.market.MarketLinker;
import mrlee.kt_mall.product.dto.SaveProduct;
import mrlee.kt_mall.product.dto.SaveProductMarket;
import mrlee.kt_mall.product.entity.Product;
import mrlee.kt_mall.product.entity.ProductMarket;
import mrlee.kt_mall.product.repository.ProductMarketRepository;
import mrlee.kt_mall.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMarketRepository productMarketRepository;
    private final MemberRepository memberRepository;
    private final MarketLinker marketLinker;

    public void addProduct(SaveProduct saveProduct) {
        productRepository.save(saveProduct.toProductEntity());
    }

    public void addProductMarket(Long productId, List<SaveProductMarket> saveProductMarketList) {
        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        List<ProductMarket> productMarkets = saveProductMarketList.stream()
                .map(SaveProductMarket::toProductMarket)
                .toList();
        product.mappingProductMarket(productMarkets);
    }

    public void linkProductMarket(SaveProductMarketLink saveProductMarketLink) {
        Member member = memberRepository.findByIdJoinFetchMemberMarket(saveProductMarketLink.getMemberId()).orElseThrow(IllegalArgumentException::new);
        List<ProductMarket> productMarkets = productMarketRepository.findAllById(saveProductMarketLink.getProductMarketIds());

        productMarkets.forEach(ProductMarket::isLinked);

        for (ProductMarket productMarket : productMarkets) {
            marketLinker.linkMarketProduct(member, productMarket);
        }
    }
}

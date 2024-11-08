package mrlee.kt_mall.product.market;

import mrlee.kt_mall.member.entity.Member;
import mrlee.kt_mall.product.entity.ProductMarket;

import java.io.IOException;

/**
 * 상품 연동 관련 기능 목록
 * 상품 통합 관리: 쇼핑몰 상품 가져오기, 일괄 등록/수정, 원클릭 상품 판매/품절
 * 주문 관리: 운송장 자동 출력, 클레임 주문 동기화, 원클릭 배송정보 전송
 * 재고 관리: 재고 자동 차감, 품절 관리, 안전재고 설정
 * 문의 관리: 문의 수집, 답변 저장, 쇼핑몰로 전송
 */
public interface MarketLink {
    String createProduct(Member member, ProductMarket productMarket) throws IOException;

    void updateProduct() throws IOException;
}

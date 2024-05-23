<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp" %>

<article>
    <form>
        <h2> Product Detail </h2>
        <div class="field" style="border-top:2px solid blueviolet;">
            <label>상품 분류</label><div>${kind}</div>
        </div>
        <div class="field" style="border-top:1px solid blueviolet;">
            <label>상품 이름</label><div>${productVO.name}</div>
        </div>
        <div class="field" style="border-top:1px solid blueviolet;">
            <label>원가</label>
            <div>
                <fmt:formatNumber value="${productVO.price1}"/>
            </div>
        </div>
        <div class="field" style="border-top:2px solid blueviolet;">
            <label>판매 가격</label>
            <div>
                <fmt:formatNumber value="${productVO.price2}"/>
            </div>
        </div>
        <div class="field" style="border-top:2px solid blueviolet;">
            <label>마진</label>
            <div>              
                <fmt:formatNumber value="${productVO.price1}"/>
            </div>
        </div>
        <div class="field" style="border-top:2px solid blueviolet;">
            <label>상세 설명</label><div>${productVO.content}</div>
        </div>
        <div class="field" style="border-top:2px solid blueviolet;">
            <label>상세 이미지</label>
            <div><img src="product_images/${productVO.savefilename}" width="300"></div>
        </div>
        <div class="btn">
            <input type="button" value="수정" onclick="go_mod('${productVO.pseq}')">
            <input type="button" value="삭제" onclick="deleteProduct('${productVO.pseq}')">
            <input type="button" value="목록" onclick="location.gref='shop.do?command=adminProductList'">
        </div>
      </form>
</article>


<%@ include file="/admin/footer.jsp" %>











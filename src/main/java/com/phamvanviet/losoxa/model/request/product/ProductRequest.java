package com.phamvanviet.losoxa.model.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @Size(min = 2, max = 100, message = "Tên sản phẩm từ 2 đến 100 ký tự")
    private String name;

    @Min(value=0, message="Giá từ 0 VNĐ")
    private String unitPrice;

    @Min(value=0, message="Số lượng từ 0")
    private String quantity;

    private String productImage;

    @NotNull(message = "Bạn hãy chọn một thể loại")
    private Long categoryId;

    private String description;
}

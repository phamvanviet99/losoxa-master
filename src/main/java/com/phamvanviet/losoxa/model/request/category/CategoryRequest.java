package com.phamvanviet.losoxa.model.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @Size(min = 1, max = 50, message = "Must be between 1 and 50 characters")
    private String name;

    private String categoryImage;

}

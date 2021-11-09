package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;

@NoArgsConstructor
@Getter
@Setter
public class ProductInputDTO {
    //TODO: change not null & range for int
    @NotBlank
    private String name;
    @NotNull
    private int brandId;
    @NotNull
    private int categoryId;
    @NotNull
    @Range(min=1, max = 1000000000)
    private long price;
    private String description;
    @NotNull
    @Range(min=1, max = 100000000)
    private int stock;

    public ProductInputDTO(Product product) {
        this.name = product.getName();
        this.brandId = product.getBrand().getId();
        this.categoryId = product.getCategory().getId();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stock = product.getStock();
    }
}

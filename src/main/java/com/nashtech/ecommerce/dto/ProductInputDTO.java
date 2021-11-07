package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;

@NoArgsConstructor
@Getter
@Setter
public class ProductInputDTO {
    @NotBlank
    private String name;
    @NotEmpty
    private int brandId;
    @NotEmpty
    private int categoryId;
    @NotEmpty
    @Size(max = 1000000000)
    private long price;
    private String description;
    @NotEmpty
    @Size(max = 100000000)
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

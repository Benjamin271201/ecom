package com.nashtech.ecommerce.service.dto;

import com.nashtech.ecommerce.domain.Address;
import lombok.*;

@NoArgsConstructor @Getter @Setter
public @Data class AddressDTO {
    private String addressLine;
    private String district;
    private String city;
    private String province;

    public AddressDTO(Address address) {
        this.addressLine = address.getAddressLine();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.province = address.getProvince();
    }
}

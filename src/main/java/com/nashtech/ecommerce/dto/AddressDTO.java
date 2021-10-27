package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Address;
import lombok.*;

@NoArgsConstructor @Getter @Setter
public @Data class AddressDTO {
    private int customerId;
    private String addressLine;
    private String district;
    private String city;
    private String province;

    public AddressDTO(Address address) {
        this.customerId = address.getCustomer().getId();
        this.addressLine = address.getAddressLine();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.province = address.getProvince();
    }
}

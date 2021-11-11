package com.nashtech.ecommerce.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class TransactionRequest implements Serializable {
    @NotNull
    private int customerId;
    @NotNull
    private int addressId;
}

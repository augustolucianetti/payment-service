package br.com.fiap.paymentservice.model;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Payment {

    private String type;
    private String transactionId;
    private BigInteger cardNumber;
    private String validateDateCard;
    private String flag;
}

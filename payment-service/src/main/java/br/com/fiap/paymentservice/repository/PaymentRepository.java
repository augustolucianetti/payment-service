package br.com.fiap.paymentservice.repository;

import br.com.fiap.paymentservice.model.Payment;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {


    List<Payment> payments = new ArrayList<>();

    public String save(Payment payment) {
        payments.add(payment);
        return payment.getTransactionId();
    }

    public Payment findById(String id) {

        for(Payment payment : payments) {
            if (payment.getTransactionId().equals(id)) {
                return payment;
            }
        }

        return null;
    }

    public Payment update(Payment payment, String id) {
        Payment response= null;
        for(Payment item : payments) {
            if (item.getTransactionId().equals(id)) {
                item.setType(payment.getType());
                item.setCardNumber(payment.getCardNumber());
                item.setFlag(payment.getFlag());
                item.setValidateDateCard(payment.getValidateDateCard());
                response = item;
            }
        }
        return response;
    }

    public void delete(String id) {
        Payment removerPayment = null;
        for(int i =0; i <payments.size(); i++) {
            if (payments.get(i).getTransactionId().equals(id)) {
                removerPayment = payments.get(i);
                break;
            }
        }
        if (removerPayment != null)
        payments.remove(removerPayment);
    }
}

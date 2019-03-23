package br.com.fiap.paymentservice.restcontroller;

import br.com.fiap.paymentservice.model.Payment;
import br.com.fiap.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable String id) {

        Payment payment = paymentRepository.findById(id);
        if (payment != null) {
            return new ResponseEntity(payment, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Payment payment) {
        paymentRepository.save(payment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(payment.getTransactionId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Payment payment, @PathVariable String id) {
        Payment response = paymentRepository.update(payment, id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable  String id) {
        paymentRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

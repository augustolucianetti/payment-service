package br.com.fiap.paymentservice.restcontroller;

import br.com.fiap.paymentservice.exceptions.BadRequestException;
import br.com.fiap.paymentservice.exceptions.NotFoundException;
import br.com.fiap.paymentservice.model.Payment;
import br.com.fiap.paymentservice.model.ResponseException;
import br.com.fiap.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable String id) {

        int compare = id.compareTo( String.valueOf( new BigInteger("10") ) );

        if (compare == 1) {
            return new BadRequestException().handleAllExceptions(new ResponseException( LocalDateTime.now(), "Parametro id náo encontrado", HttpStatus.BAD_REQUEST));
        }

        Payment payment = paymentRepository.findById(id);
        if (payment != null) {
            return new ResponseEntity(payment, HttpStatus.OK);
        } else {
            throw new NotFoundException( "payment with id ".concat( id ).concat( " not found" ) );
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Payment payment) {

        if (payment.getTransactionId() == null) {
            throw new NotFoundException("id of Order not passed");
        }
        paymentRepository.save(payment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(payment.getTransactionId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Payment payment, @PathVariable String id) {

        Payment paymentDatabase = paymentRepository.findById( id );
        if (paymentDatabase == null) {
            throw new NotFoundException("Payment not found");
        }
        Payment response = paymentRepository.update(payment, id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable  String id) {
        Payment paymentDatabase = paymentRepository.findById( id );
        if (paymentDatabase == null) {
            throw new NotFoundException("Payment not found");
        }
        paymentRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

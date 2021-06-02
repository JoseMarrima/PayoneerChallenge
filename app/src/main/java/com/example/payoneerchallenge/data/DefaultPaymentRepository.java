package com.example.payoneerchallenge.data;

import com.example.payoneerchallenge.data.remote.PaymentService;
import com.example.payoneerchallenge.model.Response;
import com.example.payoneerchallenge.util.ResponseHandler;

import io.reactivex.rxjava3.core.Single;

public class DefaultPaymentRepository implements IPaymentRepository {

    private final PaymentService service;
    public DefaultPaymentRepository(PaymentService service) {
        this.service = service;
    }

    @Override
    public Single<Response> getAllPaymentMethods() {
        return service.getAllPaymentMethods();
    }
}
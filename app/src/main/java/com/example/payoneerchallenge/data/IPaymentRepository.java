package com.example.payoneerchallenge.data;

import com.example.payoneerchallenge.model.Response;

import io.reactivex.rxjava3.core.Single;

public interface IPaymentRepository {
    Single<Response> getAllPaymentMethods();
}

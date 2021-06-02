package com.example.payoneerchallenge.data.remote;

import com.example.payoneerchallenge.model.Response;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PaymentService {
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Single<Response> getAllPaymentMethods();
}

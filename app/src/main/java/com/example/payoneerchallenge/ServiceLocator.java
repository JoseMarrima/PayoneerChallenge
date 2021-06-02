package com.example.payoneerchallenge;

import com.example.payoneerchallenge.data.DefaultPaymentRepository;
import com.example.payoneerchallenge.data.IPaymentRepository;
import com.example.payoneerchallenge.data.remote.PaymentService;
import com.example.payoneerchallenge.util.Constants;
import com.example.payoneerchallenge.util.ResponseHandler;

import org.jetbrains.annotations.NotNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    @NotNull
    private PaymentService getPaymentService() {
        return  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(Constants.URL)
                .build()
                .create(PaymentService.class);
    }

    public IPaymentRepository getRepository() {
        return new DefaultPaymentRepository(getPaymentService());
    }


}

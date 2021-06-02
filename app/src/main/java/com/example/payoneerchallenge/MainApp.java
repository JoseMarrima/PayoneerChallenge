package com.example.payoneerchallenge;

import android.app.Application;

import com.example.payoneerchallenge.data.IPaymentRepository;

public class MainApp extends Application {
    public static IPaymentRepository paymentRepository = ServiceLocator.getInstance().getRepository();
}

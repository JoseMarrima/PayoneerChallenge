package com.example.payoneerchallenge.data;

import com.example.payoneerchallenge.data.remote.PaymentService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class DefaultPaymentRepositoryTest {

    @Mock
    PaymentService paymentService;

    private DefaultPaymentRepository defaultPaymentRepository;

    @Before
    public void setUp() {
        defaultPaymentRepository = new DefaultPaymentRepository(paymentService);
    }
}
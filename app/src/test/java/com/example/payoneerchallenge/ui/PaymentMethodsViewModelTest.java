package com.example.payoneerchallenge.ui;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.payoneerchallenge.data.DefaultPaymentRepository;
import com.example.payoneerchallenge.model.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.concurrent.ThreadFactory;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentMethodsViewModelTest {

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Mock
    private DefaultPaymentRepository paymentRepository;

    private PaymentMethodsViewModel paymentMethodsViewModel;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerSupplier -> Schedulers.trampoline());
        paymentRepository = Mockito.mock(DefaultPaymentRepository.class);
        paymentMethodsViewModel = new PaymentMethodsViewModel(paymentRepository);
    }

    @Test
    public void testFetchPaymentMethodsSuccess() {
        when(paymentRepository.getAllPaymentMethods()).thenReturn(Single.just(new Response()));
        Assert.assertNotNull(paymentMethodsViewModel.responseLiveData);
    }

}
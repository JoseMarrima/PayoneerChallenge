package com.example.payoneerchallenge.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.payoneerchallenge.data.IPaymentRepository;
import org.jetbrains.annotations.NotNull;
import kotlin.Suppress;

public class PaymentMethodsViewModelFactory implements ViewModelProvider.Factory {

    private final IPaymentRepository repository;

    public PaymentMethodsViewModelFactory(IPaymentRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @NotNull
    @Override
    @Suppress(names = "UNCHECKED_CAST")
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PaymentMethodsViewModel.class)) {
            return (T) new PaymentMethodsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

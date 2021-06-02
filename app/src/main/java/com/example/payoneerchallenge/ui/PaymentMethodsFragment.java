package com.example.payoneerchallenge.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.payoneerchallenge.MainApp;
import com.example.payoneerchallenge.R;
import com.example.payoneerchallenge.data.IPaymentRepository;
import com.example.payoneerchallenge.databinding.PaymentMethodsFragmentBinding;

import java.util.Objects;

public class PaymentMethodsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        PaymentMethodsFragmentBinding binding = DataBindingUtil.inflate(LayoutInflater
                        .from(Objects.requireNonNull(container).getContext()),
                R.layout.payment_methods_fragment, container, false);

        IPaymentRepository repository = MainApp.paymentRepository;
        PaymentMethodsViewModelFactory factory = new PaymentMethodsViewModelFactory(repository);
        PaymentMethodsViewModel mViewModel = new ViewModelProvider(this, factory).get(PaymentMethodsViewModel.class);
        PaymentMethodsAdapter paymentMethodsAdapter = new PaymentMethodsAdapter();

        binding.recyclerView.setAdapter(paymentMethodsAdapter);

        mViewModel.responseLiveData.observe(this.getViewLifecycleOwner(), listResource -> {
            switch (listResource.status) {
                case SUCCESS:
                    paymentMethodsAdapter.submitList(listResource.data);
                    Log.d("TAG", "onCreateView: SUCCESS" + listResource.message);
                    break;
                case ERROR:
                    Log.d("TAG", "onCreateView: ERROR " + listResource.message);
                    break;
                case LOADING:
                    Log.d("TAG", "onCreateView: LOADING LOADING");
                    break;
            }
        });

        return binding.getRoot();
    }

}
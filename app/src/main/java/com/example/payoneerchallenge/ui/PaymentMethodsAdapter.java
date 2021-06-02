package com.example.payoneerchallenge.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payoneerchallenge.R;
import com.example.payoneerchallenge.databinding.ListItemPaymentMethodBinding;
import com.example.payoneerchallenge.model.ApplicableItem;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class PaymentMethodsAdapter extends ListAdapter<ApplicableItem, PaymentMethodsAdapter.PaymentMethodsViewHolder> {

    protected PaymentMethodsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @NotNull
    @Override
    public PaymentMethodsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        final ListItemPaymentMethodBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.list_item_payment_method, parent, false);

        return new PaymentMethodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PaymentMethodsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class PaymentMethodsViewHolder extends RecyclerView.ViewHolder {
        private final ListItemPaymentMethodBinding binding;
        public PaymentMethodsViewHolder(ListItemPaymentMethodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected void bind(ApplicableItem applicableItem) {
            binding.setApplicationItem(applicableItem);
        }
    }

    public static final DiffUtil.ItemCallback<ApplicableItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ApplicableItem>() {

                @Override
                public boolean areItemsTheSame(@NonNull @NotNull ApplicableItem oldItem, @NonNull @NotNull ApplicableItem newItem) {
                    return oldItem.getCode().equals(newItem.getCode());
                }

                @Override
                public boolean areContentsTheSame(@NonNull @NotNull ApplicableItem oldItem, @NonNull @NotNull ApplicableItem newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };
}

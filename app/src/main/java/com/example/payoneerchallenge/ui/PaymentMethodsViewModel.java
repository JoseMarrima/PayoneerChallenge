package com.example.payoneerchallenge.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payoneerchallenge.data.IPaymentRepository;
import com.example.payoneerchallenge.model.ApplicableItem;
import com.example.payoneerchallenge.model.Response;
import com.example.payoneerchallenge.util.Resource;
import com.example.payoneerchallenge.util.ResponseHandler;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PaymentMethodsViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final ResponseHandler<List<ApplicableItem>> responseHandler = new ResponseHandler<>();
    private final IPaymentRepository paymentRepository;
    private final MutableLiveData<Resource<List<ApplicableItem>>> _response = new MutableLiveData<>();
    LiveData<Resource<List<ApplicableItem>>> responseLiveData = _response;

    public PaymentMethodsViewModel(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        getAllPaymentMethods();
    }

    private void getAllPaymentMethods() {
        disposables.add(
                paymentRepository.getAllPaymentMethods()
                        .map((Function<Response, List<ApplicableItem>>) response ->
                                response.getNetworks().getApplicable())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable -> _response.postValue(Resource.loading(null)))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<ApplicableItem>>() {
                            @Override
                            public void onSuccess(@NonNull List<ApplicableItem> applicableItems) {
                                _response.postValue(responseHandler.handleSuccess(applicableItems));
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                _response.postValue(responseHandler.handleException(e));
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
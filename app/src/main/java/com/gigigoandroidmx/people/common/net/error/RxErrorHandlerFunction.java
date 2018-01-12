package com.gigigoandroidmx.people.common.net.error;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * @author Omar Bacilio - January 11, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class RxErrorHandlerFunction <T, E extends ResponseError>
        implements Function<Throwable, ObservableSource<? extends T>> {

    private final Class<E> errorClass;

    public RxErrorHandlerFunction(Class<E> errorClass) {
        this.errorClass = errorClass;
    }

    @Override
    public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response response = httpException.response();
            Gson gson = new Gson();
            String message = response.errorBody().string();
            ResponseError responseError = gson.fromJson(message, errorClass);

            return Observable.error(new ResponseState(responseError.getError(), response.code()));
        }

        return Observable.error(throwable);
    }
}

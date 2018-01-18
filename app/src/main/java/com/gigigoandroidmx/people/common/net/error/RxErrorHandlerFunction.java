package com.gigigoandroidmx.people.common.net.error;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

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
public class RxErrorHandlerFunction<T, E extends ResponseError>
        implements Function<Throwable, ObservableSource<? extends T>> {

    private final Class<E> errorClass;

    public RxErrorHandlerFunction(Class<E> errorClass) {
        this.errorClass = errorClass;
    }

    @Override
    public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            if (httpException != null && httpException.response() != null) {
                Response response = httpException.response();

                if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    return Observable.error(new UnauthorizedException());
                }

                return Observable.error(getResponseState(response));
            }
        }

        return Observable.error(throwable);
    }

    private ResponseState getResponseState(Response response) throws IOException {
        Gson gson = new Gson();
        String errorMessage;

        String jsonError = response.errorBody().string();
        ResponseError responseError = gson.fromJson(jsonError, errorClass);

        if (responseError != null && responseError.hasErrorMessage()) {
            errorMessage = responseError.getError();
        } else {
            errorMessage = HttpErrorHandling.fromInt(response.code()).toString();
        }

        return new ResponseState(errorMessage, response.code());
    }
}

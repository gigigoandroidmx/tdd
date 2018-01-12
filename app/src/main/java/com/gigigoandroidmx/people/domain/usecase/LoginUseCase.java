package com.gigigoandroidmx.people.domain.usecase;

import com.gigigoandroidmx.kmvp.UseCase;
import com.gigigoandroidmx.people.domain.repository.ListUsersRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import okhttp3.ResponseBody;

/**
 * @author Omar Bacilio - January 12, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginUseCase extends UseCase<ResponseBody, LoginUseCase.Params> {

    private final ListUsersRepository repository;

    public LoginUseCase(ListUsersRepository repository, Scheduler executorThread, Scheduler uiThread) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<ResponseBody> createObservableUseCase(Params parameters) {
        return repository.login(parameters.email);
    }

    public static final class Params {
        private final String email;

        public Params(String email) {
            this.email = email;
        }

        public static Params withEmail(String email) {
            return new Params(email);
        }
    }
}

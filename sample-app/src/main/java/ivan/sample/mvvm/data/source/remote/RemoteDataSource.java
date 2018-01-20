package ivan.sample.mvvm.data.source.remote;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ivan.sample.mvvm.data.model.Repo;
import ivan.sample.mvvm.data.source.DataSource;
import ivan.sample.mvvm.data.source.local.LocalDataSource;

public enum RemoteDataSource implements DataSource {
    INSTANT;

    private final API.Service service;

    RemoteDataSource() {
        service = API.Client.INSTANT.obtain().create(API.Service.class);
    }

    @Override
    public Flowable<List<Repo>> queryListRepos(String user) {
        return service.queryListRepos(user)
                .doOnNext(LocalDataSource.INSTANCE::insertAll)
                .doOnError(throwable -> LocalDataSource.INSTANCE.queryListRepos(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

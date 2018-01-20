package ivan.sample.mvvm.data.source;

import java.util.List;

import io.reactivex.Flowable;
import ivan.sample.mvvm.App;
import ivan.sample.mvvm.data.model.Repo;
import ivan.sample.mvvm.data.source.local.LocalDataSource;
import ivan.sample.mvvm.data.source.remote.RemoteDataSource;
import ivan.sample.mvvm.utils.NetworkUtils;


public enum DataRepository implements DataSource {
    INSTANT;

    @Override
    public Flowable<List<Repo>> queryListRepos(String user) {
        if (NetworkUtils.isConnected(App.INSTANCE))
            return RemoteDataSource.INSTANT.queryListRepos(user);
        else
            return LocalDataSource.INSTANCE.queryListRepos(user);
    }

}

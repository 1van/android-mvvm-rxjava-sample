package ivan.sample.mvvm.data.source.local;

import android.arch.persistence.room.Room;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.schedulers.Schedulers;
import ivan.sample.mvvm.App;
import ivan.sample.mvvm.data.model.Repo;
import ivan.sample.mvvm.data.source.DataSource;


public enum LocalDataSource implements DataSource {
    INSTANCE;

    private final DB.Dao dao;

    LocalDataSource() {
        ObjectHelper.requireNonNull(App.INSTANCE, "LocalDataSource is not initialized");
        DB db = Room.databaseBuilder(App.INSTANCE, DB.class, DB.DB_NAME).build();
        dao = db.dao();
    }

    @Override
    public Flowable<List<Repo>> queryListRepos(String user) {
        return dao.queryListRepos(String.format("%%%s%%", user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void insertAll(List<Repo> repos) {
        Observable.just(repos)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(dao::insertAll);
    }

}

package ivan.sample.mvvm.repo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;
import ivan.sample.mvvm.data.model.Repo;
import ivan.sample.mvvm.data.source.DataRepository;
import ivan.sample.mvvm.data.source.local.LocalDataSource;


public class RepoViewModel extends AndroidViewModel {

    public RepoViewModel(@NonNull Application application) {
        super(application);
    }

    public Flowable<List<Repo>> loadListRepos(String user) {
        return DataRepository.INSTANT.queryListRepos(user);
    }

    public void insertRepos2DB(List<Repo> repos) {
        LocalDataSource.INSTANCE.insertAll(repos);
    }

}


package ivan.sample.mvvm.data.source;

import java.util.List;

import io.reactivex.Flowable;
import ivan.sample.mvvm.data.model.Repo;


public interface DataSource {

    Flowable<List<Repo>> queryListRepos(String user);

}

package ivan.sample.mvvm.repo;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import ivan.sample.mvvm.R;
import ivan.sample.mvvm.databinding.ActivityRepoBinding;


public class RepoActivity extends AppCompatActivity {

    private ActivityRepoBinding binding;
    private RepoViewModel viewModel;
    private RepoRvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo);
        viewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
        binding.setViewModel(viewModel);

        setupRv();
        setupBtn();

    }

    private void setupRv() {
        rvAdapter = new RepoRvAdapter();
        binding.rv.setAdapter(rvAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupBtn() {
        RxView.clicks(binding.btn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .flatMap(o -> viewModel.loadListRepos("1Van").toObservable())
                .subscribe(repos -> {
                    rvAdapter.setData(repos);
                    rvAdapter.notifyDataSetChanged();
                }, Throwable::printStackTrace);
    }

}

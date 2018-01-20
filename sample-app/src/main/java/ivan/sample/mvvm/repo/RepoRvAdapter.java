package ivan.sample.mvvm.repo;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ivan.sample.mvvm.R;
import ivan.sample.mvvm.data.model.Repo;
import ivan.sample.mvvm.databinding.RvItemRepoBinding;


public class RepoRvAdapter extends RecyclerView.Adapter {

    private List<Repo> data;

    public void setData(List<Repo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RvItemRepoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_item_repo, parent, false);
        return new RecyclerView.ViewHolder(binding.getRoot()) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RvItemRepoBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setItem(data.get(position));
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

}

package com.example.bootcampodev7.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bootcampodev7.data.entity.ToDos;
import com.example.bootcampodev7.databinding.CardDesignBinding;
import com.example.bootcampodev7.ui.fragment.HomeFragmentDirections;
import com.example.bootcampodev7.ui.viewmodel.HomeViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ToDoRvAdapter extends RecyclerView.Adapter<ToDoRvAdapter.CardDesignHolder> {

    private List<ToDos> toDosList;
    private Context mContext;
    private HomeViewModel viewModel;

    public ToDoRvAdapter(List<ToDos> toDosList, Context mContext, HomeViewModel viewModel) {
        this.toDosList = toDosList;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public class CardDesignHolder extends RecyclerView.ViewHolder {
        private CardDesignBinding designBinding;

        public CardDesignHolder(CardDesignBinding designBinding) {
            super(designBinding.getRoot());
            this.designBinding = designBinding;
        }
    }

    @NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardDesignBinding binding =
                CardDesignBinding.inflate(LayoutInflater.from(mContext),
                        parent, false);
        return new CardDesignHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHolder holder, int position) {
        ToDos toDo = toDosList.get(position);
        CardDesignBinding binding = holder.designBinding;

        binding.cardText.setText(toDo.getName());
        binding.card.setOnClickListener(v -> {
            HomeFragmentDirections.HomeToDetail homeToDetail =
                    HomeFragmentDirections.homeToDetail(toDo);
            Navigation.findNavController(v).navigate(homeToDetail);
        });

        binding.cardDelete.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(mContext)
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete this To-Do?")
                    .setPositiveButton("YES", (d, i) -> {
                        viewModel.deleteToDos(toDo.getId());
                        Snackbar.make(v, "To-Do deleted!", Snackbar.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (d, i) -> {

                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        Log.e("Deneme Size", String.valueOf(toDosList.size()));
        return toDosList.size();
    }

}

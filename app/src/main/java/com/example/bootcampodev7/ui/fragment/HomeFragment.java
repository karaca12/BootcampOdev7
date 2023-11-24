package com.example.bootcampodev7.ui.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.bootcampodev7.R;
import com.example.bootcampodev7.databinding.FragmentHomeBinding;
import com.example.bootcampodev7.ui.adapter.ToDoRvAdapter;
import com.example.bootcampodev7.ui.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.toDosList.observe(getViewLifecycleOwner(), toDos -> {

            ToDoRvAdapter adapter = new ToDoRvAdapter(toDos, requireContext(), viewModel);
            binding.recyclerView.setAdapter(adapter);
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.homeToSave);
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchToDos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchToDos(newText);
                return true;
            }
        });

        OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Snackbar.make(binding.materialToolbar, "Do you want to leave the app?",
                        Snackbar.LENGTH_SHORT).setAction("YES", v -> {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }).show();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), backPressedCallback);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadToDos();
    }
}
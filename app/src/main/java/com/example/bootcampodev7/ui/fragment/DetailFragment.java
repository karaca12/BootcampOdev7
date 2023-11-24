package com.example.bootcampodev7.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bootcampodev7.R;
import com.example.bootcampodev7.data.entity.ToDos;
import com.example.bootcampodev7.databinding.FragmentDetailBinding;
import com.example.bootcampodev7.ui.viewmodel.DetailViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.qualifiers.ApplicationContext;

@AndroidEntryPoint
public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        DetailFragmentArgs bundle = DetailFragmentArgs.fromBundle(getArguments());
        ToDos recievedToDo = bundle.getToDo();

        binding.inputUpdate.setText(recievedToDo.getName());


        binding.buttonUpdate.setOnClickListener(v -> {

            new MaterialAlertDialogBuilder(getContext())
                    .setTitle("Update")
                    .setMessage("Are you sure you want to update this To-Do?")
                    .setPositiveButton("YES", (d, i) -> {
                        String name = binding.inputUpdate.getText().toString();
                        viewModel.updateToDos(recievedToDo.getId(), name);
                        Navigation.findNavController(v).navigate(R.id.detailToHome);
                        Snackbar.make(v, "To-Do updated!", Snackbar.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (d, i) -> {

                    })
                    .show();
        });

        OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Snackbar.make(binding.textView, "Do you want to discard your changes and go back?",
                        Snackbar.LENGTH_SHORT).setAction("YES", v -> {
                    setEnabled(false);
                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                }).show();
            }
        };

        binding.imageViewDetailBack.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), backPressedCallback);


        return binding.getRoot();
    }
}
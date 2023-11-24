package com.example.bootcampodev7.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.bootcampodev7.data.repo.ToDoDaoRepository;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SaveViewModel extends ViewModel {
    public ToDoDaoRepository toDoDaoRepository;

    @Inject
    public SaveViewModel(ToDoDaoRepository toDoDaoRepository) {
        this.toDoDaoRepository = toDoDaoRepository;
    }

    public void saveToDos(String name) {
        toDoDaoRepository.saveToDos(name);
    }
}

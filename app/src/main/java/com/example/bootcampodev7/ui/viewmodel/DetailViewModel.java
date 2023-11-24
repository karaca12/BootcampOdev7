package com.example.bootcampodev7.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.bootcampodev7.data.repo.ToDoDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {
    public ToDoDaoRepository toDoDaoRepository;

    @Inject
    public DetailViewModel(ToDoDaoRepository toDoDaoRepository) {
        this.toDoDaoRepository = toDoDaoRepository;
    }

    public void updateToDos(int id, String name) {
        toDoDaoRepository.updateToDos(id, name);
    }
}

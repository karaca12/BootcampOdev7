package com.example.bootcampodev7.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bootcampodev7.data.entity.ToDos;
import com.example.bootcampodev7.data.repo.ToDoDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    public ToDoDaoRepository toDoDaoRepository;
    public MutableLiveData<List<ToDos>> toDosList;

    @Inject
    public HomeViewModel(ToDoDaoRepository toDoDaoRepository) {
        this.toDoDaoRepository = toDoDaoRepository;
        loadToDos();
        toDosList = toDoDaoRepository.toDosList;
    }

    public void searchToDos(String searchInput){
        toDoDaoRepository.searchToDos(searchInput);
    }
    public void deleteToDos(int id){
        toDoDaoRepository.deleteToDos(id);
    }
    public void loadToDos(){
        toDoDaoRepository.loadToDos();
    }
}

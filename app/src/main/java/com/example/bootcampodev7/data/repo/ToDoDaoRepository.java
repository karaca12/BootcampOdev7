package com.example.bootcampodev7.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.bootcampodev7.data.entity.ToDos;
import com.example.bootcampodev7.room.ToDosDao;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ToDoDaoRepository {
    public MutableLiveData<List<ToDos>> toDosList = new MutableLiveData<>();
    private ToDosDao toDosDao;

    public ToDoDaoRepository(ToDosDao toDosDao) {
        this.toDosDao = toDosDao;
    }

    public void loadToDos() {
        toDosDao.loadToDos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ToDos> toDos) {
                        toDosList.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void searchToDos(String searchInput) {
        toDosDao.searchToDos(searchInput).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ToDos> toDos) {
                        toDosList.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void saveToDos(String name) {
        ToDos newToDo = new ToDos(0, name);
        toDosDao.saveToDos(newToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void updateToDos(int id, String name) {
        ToDos updatedToDo = new ToDos(id, name);
        toDosDao.updateToDos(updatedToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void deleteToDos(int id) {
        ToDos deletedToDo = new ToDos(id, "");
        toDosDao.deleteToDos(deletedToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        loadToDos();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}

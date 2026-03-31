package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


public class MainViewModel extends AndroidViewModel {

    private NoteDataBase noteDataBase;
    private int count = 0;
    private MutableLiveData<Integer> countLD = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        noteDataBase = NoteDataBase.getInstance(getApplication());
    }

    public void remove(Note note){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                noteDataBase.notesDao().remove(note.getId());
            }
        });
        thread.start();
    }

    public LiveData<List<Note>> getNotes(){
        return noteDataBase.notesDao().getNotes();
    }

    public void showCount(){
        count++;
        countLD.setValue(count);
    }

    public LiveData<Integer> getCount(){
        return countLD;
    }
}

package com.example.todolist;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Random;

public class DataBase {
    private ArrayList<Note> notes = new ArrayList<>();
    private static DataBase instance = null;

    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }

    private DataBase() {
        Random random = new Random();
        for(int i = 0; i < 7; i++){
            Note note = new Note(i,"Note " + i,random.nextInt(3));
            notes.add(note);
        }
    }

    public void add(Note note){
        notes.add(note);
    }

    public void remove(int id){
        notes.removeIf(note -> note.getId() == id);
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}

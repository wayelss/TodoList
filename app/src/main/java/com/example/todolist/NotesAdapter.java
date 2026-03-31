package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private List<Note> notes = new ArrayList<>();
    private OnNoteCliclListener onNoteCliclListener;

    public void setOnNoteCliclListener(OnNoteCliclListener onNoteCliclListener) {
        this.onNoteCliclListener = onNoteCliclListener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    @NonNull
    @Override //в этом методе показываем как создавать View из макета
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false);
        return new NotesViewHolder(view);
    }

    @Override //с помощью этого метода понимаем, что нам нужно отобразить на экране
    public void onBindViewHolder(NotesViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.textViewNote.setText(note.getText());

        int colorResId;
        switch (note.getPriority()){
            case 0:
                colorResId = android.R.color.holo_green_light;
                break;
            case 1:
                colorResId = android.R.color.holo_orange_light;
                break;
            default:
                colorResId = android.R.color.holo_red_light;
        }
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(),colorResId);
        viewHolder.textViewNote.setBackgroundColor(color);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onNoteCliclListener != null) onNoteCliclListener.onNoteClick(note);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNote;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }

    interface OnNoteCliclListener{

        void onNoteClick(Note note);


    }
}

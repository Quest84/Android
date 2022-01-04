package com.quest.mioctavaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quest.mioctavaapp.R;
import com.quest.mioctavaapp.adapters.NoteAdapter;
import com.quest.mioctavaapp.models.Board;
import com.quest.mioctavaapp.models.Note;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;

public class NoteActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>> {

    private ListView listView;
    private FloatingActionButton fab;

    private NoteAdapter adapter;
    private RealmList<Note> notes;
    private Realm realm;

    private int boardId;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        realm = Realm.getDefaultInstance();

        /* Se recupera el ID que le llega */
        if (getIntent().getExtras() != null)
            boardId = getIntent().getExtras().getInt("id");

        /* Si en lugar de buscar ID, se busca por "color" ej. se usa Find All*/
        board = realm.where(Board.class).equalTo("id", boardId).findFirst();
        /* Si no funciona mirar video 17*/
        board.addChangeListener(this);
        notes = board.getNotes();

        /* Se le da el titulo al activity */
        this.setTitle(board.getTitle());

        /* Se instancian los elementos */
        fab = (FloatingActionButton) findViewById(R.id.fabAddNote);
        listView = (ListView) findViewById(R.id.listViewNote);
        adapter = new NoteAdapter(this, notes, R.layout.list_view_note_items);

        listView.setAdapter(adapter);

        /* Se añado un evento click para el FAB */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCreatingNote("Add new note", "Type a note for " + board.getTitle() + ".");
            }
        });
    }


    /* CRUD Actions */
    private void createNewNote(String note){
        /* Para añadir una nota tiene que ir dentro de una transaccion */
        realm.beginTransaction();
        Note _note = new Note(note);
        realm.copyToRealm(_note);
        board.getNotes().add(_note);
        realm.commitTransaction();
    }

    private void editNote(String newNoteDescription, Note note){
        realm.beginTransaction();
        note.setDescription(newNoteDescription);
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    private void deleteNote(Note note){
        realm.beginTransaction();
        note.deleteFromRealm();
        realm.commitTransaction();
    }

    private void deleteAll(){
        realm.beginTransaction();
        board.getNotes().deleteAllFromRealm();
        realm.commitTransaction();
    }

    /* Dialogs */

    private void showAlertForCreatingNote(String title, String message) {
        /* Creamos el builder */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewNote);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String note = input.getText().toString().trim();
                if (note.length() > 0)
                    createNewNote(note);
                else
                    Toast.makeText(getApplicationContext(), "The note can't be empty", Toast.LENGTH_LONG).show();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertForEditingNote(String title, String message, final Note note) {
        /* Creamos el builder */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewNote);
        input.setText(note.getDescription());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String noteDescription = input.getText().toString().trim();
                if (noteDescription.length() == 0)
                    Toast.makeText(getApplicationContext(), "The name is required to edit the current Board", Toast.LENGTH_LONG).show();
                else if (noteDescription.equals(note.getDescription()))
                    Toast.makeText(getApplicationContext(), "The name is the same that it was before", Toast.LENGTH_LONG).show();
                else
                    editNote(noteDescription, note);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /* Events */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete_all_notes:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_note_activity, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_Note:
                deleteNote(notes.get(info.position));
                return true;
            case R.id.edit_Note:
                showAlertForEditingNote("Edit Note", "Change the name of the Note", notes.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onChange(RealmResults<Board> boards) {
        adapter.notifyDataSetChanged();
    }
}

package quest.myapplication.app;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import quest.myapplication.models.Board;
import quest.myapplication.models.Note;

public class MyApplication extends Application {
    /* Lo primero que hace la aplicación es
    * comprobar cual es el ultimo ID usado
    * Este codigo es el primero en iniciar */

    // Atomic Integer tiene un metodo para obtener el ultimo ID
    // e incrementarlo
    public static AtomicInteger BoardID = new AtomicInteger();
    public static AtomicInteger NoteID = new AtomicInteger();

    @Override
    public void onCreate() {
        setupRealmConfig();
        super.onCreate();

        Realm realm = Realm.getDefaultInstance();
        BoardID = getIdByTable(realm, Board.class);
        NoteID = getIdByTable(realm, Note.class);
        realm.close();

    }

    private void setupRealmConfig(){
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    // Esta es la consulta para seleccionar todos los objetos de cualquier clase
    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}

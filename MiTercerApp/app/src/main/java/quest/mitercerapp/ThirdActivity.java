package quest.mitercerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private EditText editTextContacts;
    private EditText editTextEmail;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;
    private ImageButton imgBtnContacts;
    private ImageButton imgBtnEmail;


    // Codigo nuclear
    private final int PHONE_CALL_CODE = 100;
    private final int PICTURE_FROM_CAMERA = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon_round);

        // Activar Flecha ir Atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Instanciando los componentes para poder usar sus métodos
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        editTextContacts = (EditText) findViewById(R.id.editTextContacts);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        imgBtnPhone = (ImageButton) findViewById(R.id.imgButtonPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imgButtonWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imgButtonCamera);
        imgBtnContacts = (ImageButton) findViewById(R.id.imgButtonContacts);
        imgBtnEmail = (ImageButton) findViewById(R.id.imgButtonEmail);

        /*Botón para la llamada con permisos*/
        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    // Comprobar version actual de Android que está corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // Comprobar si ha aceptado, no ha aceptado, o nunca se le ha preguntado
                        if (CheckPermission(Manifest.permission.CALL_PHONE)){
                            // Ha aceptado
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+phoneNumber));
                            if(ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED);
                            startActivity(i);
                        } else {
                            // Ha denegado o es la primera vez que se le pregunta
                            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                // Si no se le ha preguntado aún
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);

                            } else {
                                // Ha denegado o es la primera vez que se le pregunta
                                Toast.makeText(ThirdActivity.this, "Please,, enable the request permission", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);;
                                i.addCategory((Intent.CATEGORY_DEFAULT));
                                i.setData(Uri.parse("package:" + getPackageName()));

                                // La banderas sirven para: En caso de que salgas de la activity principal y luego retrosedas, vueltas a la misma ventana donde te quedaste
                                // Igual cuando te logueas y despues vuelves a la aplicación
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }

                        }


                        // Pregunta los permisos en tiempo de ejecución
                        //requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
                } else {
                    Toast.makeText(ThirdActivity.this,"Ingresa un numero", Toast.LENGTH_LONG).show();
                }
            }

            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*Botón para WEB*/
        imgBtnWeb.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                String email = "QuestionSleep@gmail.com";
                if (url != null && !url.isEmpty()){
                    // Web
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(("http://" + url)));;
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://" + url));

                    // Contactos
                    Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));

                    // Email
                    Intent intentEmail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));

                    // Email Completo (?)
                    Intent intentMail = new Intent(Intent.ACTION_SEND, Uri.parse(email));
                    // Intent Explicito - Por que se le indica con que aplicacion va a abrir el Intent
                    //intentMail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    intentMail.setType("plain/text");
                    intentMail.putExtra(Intent.EXTRA_SUBJECT, "Mail's Title");
                    intentMail.putExtra(Intent.EXTRA_TEXT, "Please be Patient, I have Autism");
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[] {"Ryuzaki1416@gmail.com"});
                    // Intent Implicito por que da la opción de elegir la aplicación para abrir el intent
                    startActivity(intentMail.createChooser(intentMail, "Elige el cliente de correo: "));

                    // Telefono 2(?), Sin permisos requeridos
                    Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 5564334620"));

                    //startActivity(intentMail);
                    //startActivity(intentContacts);
                    //startActivity(intentMail);
                    //startActivity(intentPhone);



                } else {
                    Toast.makeText(ThirdActivity.this, "Ingresa una URL", Toast.LENGTH_LONG).show();
                }
            }
        }));

        /*Botón de Agenda*/
        imgBtnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = editTextContacts.getText().toString();
                if (contact != null && !contact.isEmpty()){
                    Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                    startActivity(intentContacts);
                } else {
                    Toast.makeText(ThirdActivity.this, "Escribe un contacto", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*Botón de Email*/
        imgBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                if (email != null & !email.isEmpty()){
                    Intent intentEmail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));
                    startActivity(intentEmail);
                } else {
                    Toast.makeText(ThirdActivity.this, "Escribe un Email", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*Botón de Camara*/
        imgBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera, PICTURE_FROM_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case PICTURE_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK){
                    String result = data.toUri(0);
                    Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();;
                } else {
                    Toast.makeText(this, "There was an error with the picture", Toast.LENGTH_LONG).show();;
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Estamos en el caso del telefono - RequestCode es la petición del permiso?
        // String[] es un vector donde se llama a la primera posición por que solo hay un permiso
        // Podrian incluirse más de un permiso
        //
        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {

                    // Comprobar si ha sido aceptado o denegada la petición de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // Concedido
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    } else{
                        // Denegado
                        Toast.makeText(ThirdActivity.this, "Denegaste el acceso", Toast.LENGTH_LONG).show();
                    }

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    // Verifica que el permiso haya sido concedido
    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }


}

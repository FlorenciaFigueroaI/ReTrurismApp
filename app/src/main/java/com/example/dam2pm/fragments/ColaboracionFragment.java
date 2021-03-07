package com.example.dam2pm.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dam2pm.R;
import com.example.dam2pm.activities.MainActivity;
import com.example.dam2pm.singleton.MySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class ColaboracionFragment extends Fragment {

    private static final int IMG_REQUEST = 1;
    ImageView imgVwFotografia;
    FloatingActionButton fltActBtn;
    Button btnEnviarFoto;
    Button btnCancelarFoto;
    TextView txtVwEjemplo;
    EditText txtTitulo;
    EditText txtDescripcion;
    EditText txtCiudad;
    EditText txtAnyo;

    MediaPlayer mp;

    Bitmap bitmap;
    PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    private final String URL = "http://192.168.8.107/retrurism/upload.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colaboracion, container, false);

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtCiudad = view.findViewById(R.id.txtCiudad);
        txtAnyo = view.findViewById(R.id.txtAnyo);
        txtVwEjemplo = view.findViewById(R.id.txtEjemplo);
        imgVwFotografia = view.findViewById(R.id.imgVwEjemplo);
        btnEnviarFoto = view.findViewById(R.id.btnEnviarFoto);
        btnCancelarFoto = view.findViewById(R.id.btnCancelarEnvioFoto);

        fltActBtn = view.findViewById(R.id.fltActBtn);
        fltActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarImagen();

            }
        });

        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirFoto();
                setPendingIntent();
                crearCanalNotificacion();
                crearNotificacion();

            }
        });

        btnCancelarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return view;

    }

    // Notificación
    private void setPendingIntent() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(getActivity(), 1, intent, 0);


    }

    private void crearCanalNotificacion() { // para versiones posteriores a O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence charSequence = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, charSequence, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }


    }


    private void crearNotificacion() { // para versiones anteriores a O
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_photo_camera_24);
        builder.setContentTitle("Notificación ReTrurism");
        builder.setContentText("¡Se ha subido una nueva fotografía, pincha para verla!");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.GREEN, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});

        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(requireActivity());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());

    }

    private void subirFoto() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String resp = jsonObject.getString("response");
                            Toast.makeText(getContext(), resp, Toast.LENGTH_SHORT).show();
                            imgVwFotografia.setImageResource(0);

                            fltActBtn.setVisibility(View.GONE);
                            txtVwEjemplo.setVisibility(View.GONE);

                            efectoSonido();
                            startActivity(new Intent(getActivity(), MainActivity.class));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }

        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("image", imageToString(bitmap));
                params.put("titulo", txtTitulo.getText().toString().trim());
                params.put("descripcion", txtDescripcion.getText().toString().trim());
                params.put("ciudad", txtCiudad.getText().toString().trim());
                params.put("anyo", String.valueOf(Integer.parseInt(txtAnyo.getText().toString().trim())));

                return params;
            }
        };


        MySingleton.getInstance(requireActivity().getApplicationContext()).addToRequestQue(stringRequest);

    }

    // método para cargar la imagen
    private void seleccionarImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode== RESULT_OK && data!=null){

           Uri path = data.getData();
           try {
                ImageDecoder.Source source;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    source = ImageDecoder.createSource(requireActivity().getApplicationContext().getContentResolver(), path);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    imgVwFotografia.setImageBitmap(bitmap);
                    imgVwFotografia.setVisibility(View.VISIBLE);
                    fltActBtn.setVisibility(View.GONE);
                    txtVwEjemplo.setVisibility(View.GONE);


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(getActivity(), "Rellene los campos para poder enviar la fotografía", Toast.LENGTH_SHORT).show();
        }


    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream); // formato y calidad de la foto
        byte[] imgByte =  byteArrayOutputStream.toByteArray(); // convertimos en bytes

        return Base64.encodeToString(imgByte, Base64.DEFAULT);


    }

    // método para producir el sonido
    public void efectoSonido() {
        mp = MediaPlayer.create(getActivity(), R.raw.sonido_botones);
        mp.start();

    }


}
package com.example.usuario.utn_llego;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;


import static android.R.string.cancel;
import static android.R.string.no;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    TextView src;
    Button buscar;
    boolean error=false;

    Spinner s;
    static long SEGUNDOS=5000;
    static long DELAY=1000*60;
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.information:
                //intent information
                InfoDialogFragment info= new InfoDialogFragment();
                FragmentManager fm = getSupportFragmentManager();

                info.show(fm,"fragment_edit_name");
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        src=(TextView)findViewById(R.id.webText);
        buscar=(Button)findViewById(R.id.fetch);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        s = (Spinner) findViewById(R.id.spinner01);
        String[] arraySpinner = new String[] {
                "VIMBERG, FEDERICO ALEJANDRO",
                "VILLAGRA, PABLO EZEQUIEL",
                "STURTZ, GUSTAVO ELIAS",
                "SATTLER, MARICEL NOEMI",
                "NAVARRO BENITEZ, MARIA FLORENCIA",
                "GANDULFO, MARIA ITATI",
                "GERVASONI, JULIO CESAR",
                "STELLA, OSCAR EGIDIO",
                "SATO, FERNANDO ALBERTO",
                "FILIPUZZI, FERNANDO RAFAEL",
                "YARCE, GUSTAVO ADALBERTO",
                "VICARIO, SEBASTIAN",
                "SALA, FRANCISCO ALBERTO",
                "LENCINA, ADRIAN MANUEL",
                "JERICHAU, ALEJANDRO",
                "VIVAS, ALBERTO VICENTE",
                "ALMADA, MARCELO ISMAEL",
                "ARCUSIN, SILVIO IVAN",
                "BADINO, MARCO ANTONIO",
                "ALBASTRO, GUSTAVO CARLOS",
                "BALDUCCI, DIEGO MARIO",
                "BALLA, LILIANA MARIA",
                "BEADE, IGNACIO LEONEL",
                "BELTRAME, CELSO DARIO",
                "BENAVENTO, MARIO CARLOS",
                "BERASATEGUI AGUIRRE, IMANOL EUGENIO",
                "BOLLA, GUSTAVO LUIS",
                "BRODER, JAVIER EXEQUIEL",
                "BURGOS, ENRIQUE SERGIO",
                "CABALLERO, RAUL MANUEL",
                "CABRAL, ALEJANDRO ARIEL",
                "CARBONEL DE FERREYRA, ALICIA",
                "CARDOSO, JOSE INOCENCIO",
                "CASSANO, ARTURO MANUEL",
                "CATTANEO, NATALIA ALICIA",
                "CIS, ALEJANDRO JAVIER",
                "DACHARY, ALEJANDRO RAUL",
                "DE ZAN, MARICEL VANESA",
                "FANGAUF, CARLOS JOAQUIN",
                "FAURE, WALTER MARTIN",
                "FRUND, JOSE LUIS",
                "GAITAN, MARIA MERCEDES",
                "GARCIA, GLORIA LILIANA",
                "HOLLMAN, HORACIO PASCUAL",
                "KLIMOVSKY, ERNESTO",
                "KRENZ, MONICA FERNANDA",
                "LOPEZ, MARIO RAUL",
                "MAGGIOLINI, LUCAS MATIAS",
                "MAGGIOLO, GUSTAVO DANIEL",
                "MARCUZZI, PAULA ADRIANA",
                "MARTIN, MILTON TADEO",
                "MARTINEZ, SERGIO RUBEN",
                "MENDEZ, ANDRES RAUL",
                "MERCAICH SARTORI, EDITH WALQUI",
                "MIGUEL, ALEJANDRO MARTIN",
                "MUSTO, DIANA CRISTINA",
                "PAÑONI, SERGIO HECTOR",
                "PASINATO, HUGO DARIO",
                "PEREZ, DANIEL RICARDO",
                "PIÑEYRO SANTUCCI, GUSTAVO",
                "RAMOS, HECTOR LUIS",
                "REMEDI, ALBERTO CARLOS",
                "ROMERO, GUSTAVO NORBERTO",
                "RUGNA, MARIA CRISTINA",
                "SHOJI, HUGO RAUL",
                "SOLDINI, MAGALI JUDIT",
                "SOLIARD, RICARDO RODOLFO",
                "SOLIER ZANDOMENI, HERNAN MARCELO",
                "TERLIN, OMAR ANDRES",
                "VINCITORIO, FABIO MIGUEL",
                "VIOLA, HUGO GUILLERMO",
                "YUGDAR, GRACIELA ELIZABETH",
                "ZAMBONI, EDUARDO ALBERTO",
                "ZAPATA, DANIEL CONRADO",
                "AGUERO, HUGO MANUEL",
                "AGUIRRE, HECTOR RAUL",
                "BALLAY, GABINO",
                "BONINI, LAURA CARINA",
                "CAVALLO, FLAVIO GUALBERTO",
                "CUESTA, MARIA SOLEDAD",
                "ENRIQUE, SILVIA MABEL",
                "FACELLO, PEDRO HECTOR",
                "FAURE, EDGARDO RAUL",
                "FONTANA, BRUNO DAMIAN",
                "FUCKS, ARIEL",
                "GAREIS, CLAUDIA LORENA",
                "GIMENEZ, CARLOS MANUEL",
                "GONANO, CESAR HERMENEGILDO",
                "GRUNEVALTT, LUIS ALBERTO",
                "LARA, GABRIELA BEATRIZ",
                "MARIZZA, IVAN JUAN ALBERTO",
                "MARTINEZ, DAMIAN GUILLERMO MATIAS",
                "MARTINEZ, ROMINA VANESA",
                "MAZZOLA, SILVINA NOEMI",
                "MENNA, OSCAR RAMON",
                "MONTIEL, MARIANA ITATI",
                "MOREIRA, ESTELA ANTONIA CL.",
                "PORTILLO, ANDREA VERONICA",
                "RETAMAL, JUAN CARLOS",
                "RETAMAL, SERGIO JAVIER",
                "RIPARI, MARIA VERONICA",
                "SALOMONE, PABLO ANTONIO",
                "SCHWARTZ, GABRIEL ERNESTO",
                "SILVANO, ENRIQUE RICARDO",
                "SOLANAS, JULIA PATRICIA",
                "SOSA, MARIA LUZ",
                "TOFALO, SILVIA PATRICIA",
                "VALLI, EDUARDO RAUL",
                "VALLI, PATRICIA CORA",
                "VASQUEZ, BENJAMIN EDUARDO",
                "VILLARRUEL, SALVADOR"
        };

        //Ordenar Alfabeticamente
        sortStringExchange(arraySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpinner);
        s.setAdapter(adapter);

         mBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher_round))
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setTicker("Llegó")
                        .setAutoCancel(true)
                        .setContentTitle("Llegó")
                        .setContentText("Hello World!");

         mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.postDelayed(r, 1000);

            }
        });

    }

    private class Downloader extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
             String result=null;

            try{
                URL url= new URL(urls[0]);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                InputStream in=conn.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                String line=null;
                while((line=reader.readLine())!=null){
                    result+=line;
                }
                conn.disconnect();

            }
            catch(Exception e){
                Log.e("Error",e.toString());
                error=true;
            }

            return result;
        }

        @Override
        protected void onPostExecute(final String result) {

        if(!error) {

    if (result.contains(s.getSelectedItem().toString())) {
        mBuilder.setContentText(s.getSelectedItem().toString());
        mBuilder.setTicker("Llegó " + s.getSelectedItem().toString());
        mNotificationManager.notify(1, mBuilder.build());


        Toast.makeText(getApplicationContext(), "Llegó",
                Toast.LENGTH_LONG).show();
        handler.removeCallbacks(r);
    } else {
        //src.setText("No llego");
        Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_SHORT).show();
        handler.postDelayed(r, 1000 * 30);
           }

        } else
            Toast.makeText(getApplicationContext(), "Verifique su conexión", Toast.LENGTH_SHORT).show();

        }//Post
    }//Downloader

    final Handler handler = new Handler();
    final Runnable r = new Runnable() {
        public void run() {
            Downloader d=new Downloader();
            d.execute("http://www.frp.utn.edu.ar/asis_doc/index.php");
        }
    };


    public static void sortStringExchange( String  x [ ] )
    {
        int i, j;
        String temp;

        for ( i = 0;  i < x.length - 1;  i++ )
        {
            for ( j = i + 1;  j < x.length;  j++ )
            {
                if ( x [ i ].compareToIgnoreCase( x [ j ] ) > 0 )
                {                                             // ascending sort
                    temp = x [ i ];
                    x [ i ] = x [ j ];    // swapping
                    x [ j ] = temp;

                }
            }
        }
    }

   static public class InfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage( getString(R.string.app_version)+"\n Programación: Eric Luna \n Contacto: luna.eric23@gmail.com")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }



}
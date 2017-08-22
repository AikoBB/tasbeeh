package com.aigerimbb.android.tasbeeh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aigerimbb.android.tasbeeh.database.DB_Connection;
import com.aigerimbb.android.tasbeeh.model.Tasbeeh;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DB_Connection db_connection;
    TextView counter;
    RelativeLayout bottom_layout;
    Spinner spinner;
    TextView tasbeehName, tasbeehContent;
    ImageView prev, next, add, edit, delete;
    int spinner_select=0;
    int last_counter=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar topToolBar = (Toolbar)findViewById(R.id.my_toolbar);
        //TextView tb_tv=(TextView)findViewById(R.id.tb_tv);
        //tb_tv.setText(R.string.app_name);
        setSupportActionBar(topToolBar);

        db_connection = new DB_Connection(this);
        Log.w("***","123");
        /**Check database exists***/
        File db = getApplicationContext().getDatabasePath(DB_Connection.DBNAME);
        if (db.exists() == false) {
            db_connection.getReadableDatabase();

            //copy database
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy database error", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        ArrayList<String> arr = new ArrayList<String>();
        arr = db_connection.getTasbeehNames();

        bottom_layout = (RelativeLayout) findViewById(R.id.bottom_layout);
        bottom_layout.setClickable(true);
        bottom_layout.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<String> staticAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(staticAdapter);
        spinner.setOnItemSelectedListener(this);

        tasbeehName = (TextView) findViewById(R.id.tasbeehName);
        tasbeehName.setText(spinner.getSelectedItem().toString());

        prev = (ImageView) findViewById(R.id.prev);
        prev.setClickable(true);
        prev.setOnClickListener(this);

        next = (ImageView) findViewById(R.id.next);
        next.setClickable(true);
        next.setOnClickListener(this);

        add = (ImageView) findViewById(R.id.add);
        add.setClickable(true);
        add.setOnClickListener(this);

        edit = (ImageView) findViewById(R.id.edit);
        edit.setClickable(true);
        edit.setOnClickListener(this);

        delete = (ImageView) findViewById(R.id.delete);
        delete.setClickable(true);
        delete.setOnClickListener(this);
        Tasbeeh tasbeeh = db_connection.getTasbeeh(spinner.getSelectedItem().toString());

        counter = (TextView) findViewById(R.id.counter);
        counter.setText(Integer.toString(tasbeeh.getMax_count()));

        tasbeehContent = (TextView) findViewById(R.id.tasbeehContent);
        String str=tasbeeh.getName_tr()+"\n\n*****************************\n\n"+tasbeeh.getContent();
        tasbeehContent.setText(str);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void info(View v) {
        startActivity(new Intent(getApplicationContext(), MenuItem.class));
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DB_Connection.DBNAME);
            String outFileName = DB_Connection.DBLOCATION + DB_Connection.DBNAME;
            OutputStream out = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                out.write(buff, 0, length);
            }
            out.flush();
            out.close();
            Log.v("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        Tasbeeh tasbeeh;
        switch (v.getId()) {
            case R.id.bottom_layout:
                spinner_select=(int)spinner.getSelectedItemId();
                /* when bottom relative layout is pressed, counter field should be decreased by 1*/
                String str_counter = counter.getText().toString();
                int int_counter = Integer.parseInt(str_counter);
                if (int_counter <= 0) {
                    Vibrator vibrate=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    vibrate.vibrate(500l);
                    tasbeeh = db_connection.getTasbeeh(spinner.getSelectedItem().toString());
                    counter.setText(Integer.toString(tasbeeh.getMax_count()));
                } else {
                    int_counter--;
                    str_counter = Integer.toString(int_counter);
                    counter.setText(str_counter);
                }
                //bottom_layout.setBackgroundResource(R.drawable.border);
                break;
            case R.id.prev:
                /* prev image is pressed, then index of selected item in the spinner should be decrease*/

                int index = (int) spinner.getSelectedItemId();
                if ((--index) >= 0) {
                    spinner.setSelection(index);

                } else {
                    spinner.setSelection(0);


                }
                tasbeeh = db_connection.getTasbeeh(spinner.getSelectedItem().toString());
                counter.setText(Integer.toString(tasbeeh.getMax_count()));
                String str=tasbeeh.getName_tr()+"\n\n*****************************\n\n"+tasbeeh.getContent();
                tasbeehContent.setText(str);
                spinner_select=(int)spinner.getSelectedItemId();
                break;

            case R.id.next:
                 /* next image is pressed, then index of selected item in the spinner should be increase*/
                index = (int) spinner.getSelectedItemId();
                if ((++index) <= (spinner.getAdapter().getCount() - 1)) {
                    spinner.setSelection(index);

                } else {
                    spinner.setSelection(spinner.getAdapter().getCount() - 1);
                }
                tasbeeh = db_connection.getTasbeeh(spinner.getSelectedItem().toString());
                counter.setText(Integer.toString(tasbeeh.getMax_count()));
                String str_=tasbeeh.getName_tr()+"\n\n*****************************\n\n"+tasbeeh.getContent();
                tasbeehContent.setText(str_);
                spinner_select=(int)spinner.getSelectedItemId();
                break;
            case R.id.add:
                spinner_select=(int)spinner.getSelectedItemId();
                Intent insert_activity = new Intent(v.getContext(), InsertActivity.class);
                v.getContext().startActivity(insert_activity);
                break;
            case R.id.edit:
                spinner_select=(int)spinner.getSelectedItemId();
                Intent edit_activity = new Intent(v.getContext(), EditActivity.class);
                edit_activity.putExtra("OldName",spinner.getSelectedItem().toString());
                 tasbeeh=db_connection.getTasbeeh(spinner.getSelectedItem().toString());
                edit_activity.putExtra("Name", tasbeeh.getName());

                edit_activity.putExtra("Translation", tasbeeh.getName_tr());
                edit_activity.putExtra("Content", tasbeeh.getContent());
                edit_activity.putExtra("MaxSize", tasbeeh.getMax_count());
                v.getContext().startActivity(edit_activity);

                break;
            case R.id.delete:
                spinner_select=(int)spinner.getSelectedItemId();
                String title = tasbeehName.getText().toString() + " Тасбихти очурууну каалайсызбы?";
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder
                        .setTitle(title)
                        .setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                               //finish();

                                MainActivity.this.db_connection.deleteTasbeeh(spinner.getSelectedItem().toString());
                                ArrayAdapter<String> staticAdapter = new ArrayAdapter<String>(MainActivity.this,
                                        android.R.layout.simple_spinner_item, MainActivity.this.db_connection.getTasbeehNames());

                                // Specify the layout to use when the list of choices appears
                                staticAdapter
                                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                // Apply the adapter to the spinner

                                MainActivity.this.spinner.setAdapter(staticAdapter);
                                Toast.makeText(getApplicationContext(),"Тандалган тасбих очурулду!",Toast.LENGTH_LONG).show();
                            }
                        });
                alertBuilder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        //MainActivity.this.finish();
                        Toast.makeText(getApplicationContext()," Тандалган тасбих очурулгон жок!",Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = alertBuilder.create();
                //dialog.setTitle();
                dialog.show();
                break;

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        tasbeehName.setText(spinner.getSelectedItem().toString());
        Tasbeeh tasbeeh = db_connection.getTasbeeh(spinner.getSelectedItem().toString());
        if(last_counter==0)
        counter.setText(Integer.toString(tasbeeh.getMax_count()));
        else{

            counter.setText(Integer.toString(last_counter));
            last_counter=0;
        }
        String str=tasbeeh.getName_tr()+"\n\n*****************************\n\n"+tasbeeh.getContent();
        tasbeehContent.setText(str);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        spinner_select=(int)spinner.getSelectedItemId();
        last_counter=Integer.parseInt(counter.getText().toString());
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void onPause(){
        spinner_select=(int)spinner.getSelectedItemId();
        last_counter=Integer.parseInt(counter.getText().toString());
        super.onPause();
    }
    public void onResume(){
        super.onResume();
        ArrayAdapter<String> staticAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, db_connection.getTasbeehNames());

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        System.out.println(spinner_select);
        spinner.setAdapter(staticAdapter);
        spinner.setSelection(spinner_select);
    }
}

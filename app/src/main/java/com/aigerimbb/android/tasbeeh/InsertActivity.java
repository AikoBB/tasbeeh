package com.aigerimbb.android.tasbeeh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aigerimbb.android.tasbeeh.database.DB_Connection;


public class InsertActivity extends AppCompatActivity implements View.OnClickListener{
    DB_Connection db_connection;
    Button cancel_btn,ok_btn;
    EditText tasbeeh_name, hadith, translation, max_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar topToolBar = (Toolbar)findViewById(R.id.my_toolbar);
        TextView tb_tv=(TextView)findViewById(R.id.tb_tv);
        tb_tv.setText(R.string.insert_layout_title);
        setSupportActionBar(topToolBar);
        db_connection=new DB_Connection(this);

        /* reference to cancel button and add listener*/
        cancel_btn=(Button)findViewById(R.id.Cancel_btn);
        cancel_btn.setOnClickListener(this);

        /*reference to ok button and add listenere */
        ok_btn=(Button)findViewById(R.id.Ok_btn);
        ok_btn.setOnClickListener(this);

        /*reference to  EdiTexts*/
        tasbeeh_name=(EditText)findViewById(R.id.tasbeeh_name);
        hadith=(EditText)findViewById(R.id.hadith);
        translation=(EditText)findViewById(R.id.translation);
        max_size=(EditText)findViewById(R.id.max_size);


    }

    public void info(View v) {
        startActivity(new Intent(getApplicationContext(), MenuItem.class));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Cancel_btn:
                this.finish();
                break;
            case R.id.Ok_btn:
                String str=tasbeeh_name.getText().toString()+" "
                            +hadith.getText().toString()+" "
                            +max_size.getText().toString()+" "
                            +translation.getText().toString();
                if(tasbeeh_name.length()<=0||max_size.length()<=0){
                    Toast.makeText(this,"Керектуу текст талааларын толтурунуз!!!",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean result = db_connection.insertTasbeeh(tasbeeh_name.getText().toString(), translation.getText().toString(), hadith.getText().toString(), Integer.parseInt(max_size.getText().toString()));
                    if (true)
                        Toast.makeText(this, "Жаны тасбих кошулду!", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(this, "Жаны тасбих кошулган жок!", Toast.LENGTH_LONG).show();
                    this.finish();
                }
        }
    }
}

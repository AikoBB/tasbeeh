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

/**
 * Created by AigerimB on 04.11.2016.
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    DB_Connection db_connection;
    Button cancel_btn,ok_btn;
    EditText tasbeeh_name, hadith, translation, max_size;
    int int_max_size=0;
    String int_name=null,int_Translation=null,int_content=null,int_oldname=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        android.support.v7.app.ActionBar bar=getSupportActionBar();
        Toolbar topToolBar = (Toolbar)findViewById(R.id.my_toolbar);
        TextView tb_tv=(TextView)findViewById(R.id.tb_tv);
        tb_tv.setText(R.string.edit_layout_title);
        setSupportActionBar(topToolBar);
        db_connection=new DB_Connection(this);
        /*** get intent EXTRAs**/
        Intent intent=this.getIntent();
        int_name=intent.getStringExtra("Name");
        int_Translation=intent.getStringExtra("Translation");
        int_content=intent.getStringExtra("Content");
        int_max_size=intent.getIntExtra("MaxSize",0);
        int_oldname=intent.getStringExtra("OldName");
         /* reference to cancel button and add listener*/
        cancel_btn=(Button)findViewById(R.id.Cancel_btn);
        cancel_btn.setOnClickListener(this);

        /*reference to ok button and add listenere */
        ok_btn=(Button)findViewById(R.id.Ok_btn);
        ok_btn.setOnClickListener(this);

        /*reference to  EdiTexts and set texts from intent extra*/
        tasbeeh_name=(EditText)findViewById(R.id.tasbeeh_name);
        tasbeeh_name.setText(int_name);
        hadith=(EditText)findViewById(R.id.hadith);
        hadith.setText(int_content);
        translation=(EditText)findViewById(R.id.translation);
        translation.setText(int_Translation);
        max_size=(EditText)findViewById(R.id.max_size);
        max_size.setText(Integer.toString(int_max_size));

    }


    public void info(View v) {
       startActivity(new Intent(getApplicationContext(), MenuItem.class));
    }
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.Ok_btn:

                    String name=tasbeeh_name.getText().toString();
                    String hadith_content=hadith.getText().toString();
                    String trans=translation.getText().toString();
                    String counter=max_size.getText().toString();

                    if(name.length()<=0||hadith_content.length()<=0||trans.length()<=0||counter.length()<=0){
                        Toast.makeText(this,"Баардык текст талааларын толтурунуз!!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        db_connection.updateTasbeeh(int_oldname,name,trans,hadith_content,Integer.parseInt(counter));
                        if(true)
                            Toast.makeText(this,"Тасбих озгортулду!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(this,"Тасбих озгортулгон жок!"+int_oldname,Toast.LENGTH_LONG).show();

                        finish();
                    }
                    break;
                case R.id.Cancel_btn:
                    finish();
                    break;

            }

    }
}

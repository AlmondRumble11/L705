package com.example.l705;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    Context context = null;
    Button write;
    Button read;
    String teksti;
    EditText tiedosto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        editText = (EditText) findViewById(R.id.tekstikentta);
        read = (Button)findViewById(R.id.buttonRead);
        write = (Button)findViewById(R.id.buttonWrite);
        tiedosto = (EditText)findViewById(R.id.tiedosto);


    }

    public void readFile(View v) {
        try {
            //System.out.println("Anna ladattavan tiedoston nimi");
            //Scanner scan = new Scanner(System.in);
            String a = tiedosto.getText().toString();
            if (a.isEmpty())
                a = "testi.txt";
            InputStream ins = context.openFileInput(a);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            while ((s = br.readLine()) != null) {
                //System.out.println(s);
                editText.setText(s);

            }


            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }finally {
            System.out.println("Luettu");
        }
    }

    public void writeFile(View v) {
        try {
            //System.out.print("Anna kirjoitettavan tiedoston nimi");
            //Scanner scan = new Scanner(System.in);
            String a = tiedosto.getText().toString();
            if (a.isEmpty())
                a = "testi.txt";
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(a, Context.MODE_PRIVATE));
            teksti = editText.getText().toString();
            //String s ="";
            //s = "dskofkodsnfjkdskodfjkjo\newkfjewofjieowf\newkfjkoewjfioefj";
            ows.write(teksti);
            ows.close();
            editText.setText("");
            tiedosto.setText("");
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }finally{
            System.out.println("Kirjoitettu");
        }
    }
}

package ru.mirea.maiorov.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editTextDate, editTextDescription;
    Button buttonSave;

    private static final String FILE_NAME = "HistoryOfRussia.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDate = findViewById(R.id.editTextDate);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFile();
            }
        });
    }

    private void saveDataToFile() {
        String date = editTextDate.getText().toString();
        String description = editTextDescription.getText().toString();

        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

            outputStreamWriter.write("Дата в истории России: " + date + "\n");
            outputStreamWriter.write("Описание: " + description + "\n");

            outputStreamWriter.close();
            fos.close();

            Toast.makeText(MainActivity.this, "Данные сохранены в файл", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("InternalStorage", "Error writing to file " + e.getMessage());
            Toast.makeText(MainActivity.this, "Ошибка при записи в файл", Toast.LENGTH_SHORT).show();
        }
    }
}

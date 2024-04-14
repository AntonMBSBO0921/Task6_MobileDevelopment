package ru.mirea.maiorov.notebook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText editTextFileName, editTextQuote;
    Button buttonSave, buttonLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextQuote = findViewById(R.id.editTextQuote);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFile();
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataFromFile();
            }
        });
    }

    private void saveDataToFile() {
        String fileName = editTextFileName.getText().toString();
        String quote = editTextQuote.getText().toString();

        if (!fileName.isEmpty() && !quote.isEmpty()) {
            try {
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                fos.write(quote.getBytes());
                fos.close();

                Toast.makeText(this, "Данные сохранены в файл", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Ошибка при сохранении данных в файл", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Пожалуйста, введите название файла и цитату", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataFromFile() {
        String fileName = editTextFileName.getText().toString();

        if (!fileName.isEmpty()) {
            try {
                FileInputStream fis = openFileInput(fileName);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                editTextQuote.setText(stringBuilder.toString());
                br.close();

                Toast.makeText(this, "Данные загружены из файла", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Ошибка при загрузке данных из файла", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Пожалуйста, введите название файла", Toast.LENGTH_SHORT).show();
        }
    }
}

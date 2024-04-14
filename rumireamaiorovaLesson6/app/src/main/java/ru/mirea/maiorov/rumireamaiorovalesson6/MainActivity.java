package ru.mirea.maiorov.rumireamaiorovalesson6;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextGroupNumber, editTextListNumber, editTextFavoriteMovie;
    Button buttonSave;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextGroupNumber = findViewById(R.id.editTextGroupNumber);
        editTextListNumber = findViewById(R.id.editTextListNumber);
        editTextFavoriteMovie = findViewById(R.id.editTextFavoriteMovie);
        buttonSave = findViewById(R.id.buttonSave);

        loadSavedData();

        buttonSave.setOnClickListener(v -> saveData());
    }

    private void loadSavedData() {
        String groupNumber = sharedPreferences.getString("groupNumber", "");
        String listNumber = sharedPreferences.getString("listNumber", "");
        String favoriteMovie = sharedPreferences.getString("favoriteMovie", "");

        editTextGroupNumber.setText(groupNumber);
        editTextListNumber.setText(listNumber);
        editTextFavoriteMovie.setText(favoriteMovie);
    }

    private void saveData() {
        String groupNumber = editTextGroupNumber.getText().toString();
        String listNumber = editTextListNumber.getText().toString();
        String favoriteMovie = editTextFavoriteMovie.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("groupNumber", groupNumber);
        editor.putString("listNumber", listNumber);
        editor.putString("favoriteMovie", favoriteMovie);
        editor.apply();
    }
}

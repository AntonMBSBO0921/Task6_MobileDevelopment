package ru.mirea.maiorov.securesharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences secureSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            secureSharedPreferences.edit().putString("poetName", "Любимый поэт - Александр Сергеевич Пушкин").apply();

            String poetName = secureSharedPreferences.getString("poetName", "Любимый поэт не указан");
            TextView textViewPoetName = findViewById(R.id.textViewPoetName);
            textViewPoetName.setText(poetName);

            ImageView imageViewPoetPhoto = findViewById(R.id.imageViewPoetPhoto);
            imageViewPoetPhoto.setImageResource(R.drawable.images);

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

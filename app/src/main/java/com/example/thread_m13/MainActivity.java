package com.example.thread_m13;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TaskCallback {
    private TextView statusText;
    private ProgressBar progressBar;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        progressBar = findViewById(R.id.progressBar);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            new DownloadTask(this).execute("https://example.com/largefile.zip");
        });
    }

    @Override
    public void onTaskStarted() {
        progressBar.setVisibility(View.VISIBLE);
        statusText.setText("Downloading...");
        startButton.setEnabled(false);
    }

    @Override
    public void onTaskProgress(Integer progress) {
        progressBar.setProgress(progress);
        statusText.setText("Progress: " + progress + "%");
    }

    @Override
    public void onTaskFinished(String result) {
        progressBar.setVisibility(View.GONE);
        statusText.setText("Download Complete: " + result);
        startButton.setEnabled(true);
    }
}

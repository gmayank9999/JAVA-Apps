package com.example.email_demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editSubject, editMessage;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editSubject = findViewById(R.id.editSubject);
        editMessage = findViewById(R.id.editMessage);
        sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(v -> sendEmail());
    }

    private void sendEmail() {
        String recipient = editEmail.getText().toString().trim();
        String subject = editSubject.getText().toString().trim();
        String message = editMessage.getText().toString().trim();

        if (recipient.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(intent, "Send email using:"));
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}

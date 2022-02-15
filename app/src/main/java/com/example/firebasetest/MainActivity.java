package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PostProcessor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        Button button = findViewById(R.id.change_activity_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmailPasswordActivity.class);
                startActivity(intent);
            }
        });

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        DatabaseReference anotherRef = database.getReference("another one");



        anotherRef.setValue("asdgaedtrftjhd");

        anotherRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = snapshot.getValue(String.class);
                textView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("firebase: ", "Failed to read value.", error.toException());
            }
        });


        String userId1 = "42";
        String username1 = "juamtrix";

        String userId2 = "12";
        String username2 = "jonny";

        User user1 = new User(username1, "asdf");
        User user2 = new User(username2, "gmail");

        database.getReference().child("users").child(userId1).setValue(user1);
        database.getReference().child("users").child(userId2).setValue(user2);


        DatabaseReference userRef = database.getReference("users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> userList = new ArrayList<>();
                for(DataSnapshot ds: snapshot.getChildren()){
                    String user = ds.child("username").getValue(String.class);
                    userList.add(user);
                }
                String users = "";
                for(String user: userList){
                    users += user+"\n";
                }
                textView.setText(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message
                Log.w("firebase: ", "loadPost:onCancelled", error.toException());
            }
        });

    }
}
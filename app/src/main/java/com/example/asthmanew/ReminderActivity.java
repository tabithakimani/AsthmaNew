package com.example.asthmanew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReminderActivity extends AppCompatActivity {

    private static final String TAG = "AddToDatabase";

    private Button btnSubmit,btnView;
    private EditText inputMed, inputDate,inputDose,nameR, sexR, soundInput;
    private String userID;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        btnSubmit = (Button) findViewById(R.id.button2);
        btnView = findViewById(R.id.button3);
        inputDate = (EditText) findViewById(R.id.date1);
        inputDose = (EditText) findViewById(R.id.dose1);
        inputMed = (EditText) findViewById(R.id.medicine1);
        nameR = (EditText) findViewById(R.id.nameRegistration);
        sexR = (EditText) findViewById(R.id.sexRegistration);
        soundInput = findViewById(R.id.sound);


        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReminderActivity.this, ViewData.class);
                startActivity(intent);
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Submit pressed.");
                String date = inputDate.getText().toString();
                String dose = inputDose.getText().toString();
                String medicine = inputMed.getText().toString();
                String name = nameR.getText().toString();
                String sex = sexR.getText().toString();
                String sound_level = soundInput.getText().toString();



                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "date: " + date + "\n" +
                        "dose: " + dose + "\n" +
                        "medicine: " + medicine +
                        "name: " + name + "\n" +
                        "sex: " + sex + "\n" +
                        "\n"
                );

                //handle the exception if the EditText fields are null
                if(!date.equals("") && !medicine.equals("") && !dose.equals("") && !sex.equals("") && !name.equals("") && !sound_level.equals("")){
                    Reminder reminder = new Reminder(date, medicine,dose,name,sex,sound_level);
                    myRef.child("users").child(userID).setValue(reminder);
                    toastMessage("New Information has been saved.");
                    inputDate.setText("");
                    inputMed.setText("");
                    inputDose.setText("");
                    sexR.setText("");
                    nameR.setText("");

                }else{
                    toastMessage("Fill out all the fields");
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}

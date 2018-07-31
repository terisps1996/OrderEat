package project.ordereat.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import project.ordereat.Admin.AdminHome;
import project.ordereat.Common.Common;
import project.ordereat.Model.User;
import project.ordereat.R;

public class SignIn extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference  table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if textViews are blank
                if(!Common.isEmpty(edtPhone) && !Common.isEmpty(edtPassword)){
                    //Check internet
                    if (Common.isConnectedToInternet(getBaseContext())) {
                        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                        mDialog.setMessage("Please wait...");
                        mDialog.show();
                        table_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Check if user not exist in database
                                if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                    //Get user information
                                    mDialog.dismiss();
                                    User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                    user.setPhone(edtPhone.getText().toString());

                                    if (user.getPassword().equals(edtPassword.getText().toString())) {

                                        //If user has admin rights
                                        if (user.getType().equals("admin")) {
                                            Toast.makeText(SignIn.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                            Intent adminHomeIntent = new Intent(SignIn.this, AdminHome.class);
                                            Common.currentUser = user;
                                            startActivity(adminHomeIntent);
                                            finish();

                                            //If user has common rights
                                        }else if (user.getType().equals("common")){
                                            Toast.makeText(SignIn.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                            Intent homeIntent = new Intent(SignIn.this, Home.class);
                                            Common.currentUser = user;
                                            startActivity(homeIntent);
                                            finish();
                                        }

                                    }else {
                                        Toast.makeText(SignIn.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    mDialog.dismiss();
                                    Toast.makeText(SignIn.this, "User does not exist", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        //No internet Connection
                    }else{
                        Toast.makeText(SignIn.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    edtPhone.setError("Enter Phone");
                    edtPassword.setError("Enter Password");
                }
            }
        });
    }


}

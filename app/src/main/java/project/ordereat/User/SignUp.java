package project.ordereat.User;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import project.ordereat.Common.Common;
import project.ordereat.Model.User;
import project.ordereat.R;

public class SignUp extends AppCompatActivity {
    MaterialEditText edtPhone,edtName,edtPassword;
    Button btnSignUp;
    String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance().getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if textViews are blank
                if(!Common.isEmpty(edtName) && !Common.isEmpty(edtPassword) && !Common.isEmpty(edtPhone)) {
                    //Check Internet
                    if (Common.isConnectedToInternet(getBaseContext())) {
                        final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                        mDialog.setMessage("Please wait....");
                        mDialog.show();
                        table_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Check if already user phone
                                if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                    mDialog.dismiss();
                                    Toast.makeText(SignUp.this, "The phone number is already registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    mDialog.dismiss();
                                    //New user with common rights
                                    User user = new User(edtName.getText().toString(), edtPassword.getText().toString(), "common");
                                    table_user.child(edtPhone.getText().toString()).setValue(user);
                                    Toast.makeText(SignUp.this, "Successfully registered !", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                mDialog.dismiss();
                                Log.d(TAG, database.toString());
                            }
                        });

                    } else {
                        Toast.makeText(SignUp.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    edtName.setError("Enter Name");
                    edtPhone.setError("Enter Phone");
                    edtPassword.setError("Enter Password");
                }
            }
        });
    }
}

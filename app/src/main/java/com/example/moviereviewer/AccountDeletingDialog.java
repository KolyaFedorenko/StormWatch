package com.example.moviereviewer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountDeletingDialog extends CustomDialog {

    private ConstraintLayout constraintDeleteAccount;
    private EditText dialogEditDeletingUser;
    private String login;
    private boolean deletionConfirmed = false;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public AccountDeletingDialog(String login){
        this.login = login;
    }

    @Override
    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.delete_account_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        findViews(dialog);
        useViews(dialog);
        dialog.show();
    }

    @Override
    public void findViews(Dialog dialog) {
        constraintDeleteAccount = dialog.findViewById(R.id.constraintDeleteAccount);
        dialogEditDeletingUser = dialog.findViewById(R.id.dialogEditDeletingUser);
    }

    @Override
    public void useViews(Dialog dialog) {
        Context context = dialog.getContext();
        constraintDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogEditDeletingUser.getVisibility() == View.GONE){
                    dialogEditDeletingUser.setVisibility(View.VISIBLE);
                } else {
                    dialogEditDeletingUser.setVisibility(View.GONE);
                }
            }
        });

        dialogEditDeletingUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(login)){
                    dialogEditDeletingUser.setBackground(context.getDrawable(R.drawable.rounded_button_black));
                    dialogEditDeletingUser.setTextColor(context.getColor(R.color.black));
                    dialogEditDeletingUser.setFocusable(false);
                    dialogEditDeletingUser.setClickable(true);
                    dialogEditDeletingUser.setInputType(InputType.TYPE_NULL);
                    deletionConfirmed = true;
                }
            }
        });

        dialogEditDeletingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletionConfirmed) Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
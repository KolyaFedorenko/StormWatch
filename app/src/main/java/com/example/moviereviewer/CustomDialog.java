package com.example.moviereviewer;

import android.app.Activity;
import android.app.Dialog;

public abstract class CustomDialog {
    public abstract void showDialog(Activity activity);
    public abstract void findViews(Dialog dialog);
    public abstract void useViews(Dialog dialog);
}

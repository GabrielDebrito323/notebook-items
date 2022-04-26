package com.ed.globallogik.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ed.globallogik.R;

public class CustomAlert extends ConstraintLayout {

    View view;
    TextView txtMessage;
    OnButtonPressed onButtonPressed;
    Button btn;

    public CustomAlert(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.error_body_layout, this, true);

        btn = view.findViewById(R.id.button_error_body_layout);
        txtMessage = view.findViewById(R.id.text_view_error_body_layout);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed.onClick();
            }
        });
    }

    public void setOnButtonPressedListener(OnButtonPressed onButtonPressed){
        this.onButtonPressed = onButtonPressed;
    }

    public interface OnButtonPressed {
        void onClick();
    }

}

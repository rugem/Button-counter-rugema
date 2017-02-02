package edu.cofc.csci490.button_counter_rugema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonCounterActivity extends AppCompatActivity implements View.OnClickListener{

    //define instance and other variables for widgets
    private Button down_button, up_button, clear_btn;
    private TextView countNumberText;
    private static final String SAVED_NUMBER_UP ="savedNumberUp";
    private static final String SAVED_NUMBER_DOWN ="savedNumberDown";
    private int count_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_counter);

        down_button = (Button) findViewById(R.id.count_down_button);
        up_button = (Button) findViewById(R.id.count_up_button);
        clear_btn = (Button) findViewById(R.id.clear_button);
        countNumberText = (TextView) findViewById(R.id.counterTextView);

        //sets listeners
        clear_btn.setOnClickListener(this);
        up_button.setOnClickListener(this);
        down_button.setOnClickListener(this);

        //checks the previously destroyed instance and then updates it
        if(savedInstanceState != null){
            count_number = savedInstanceState.getInt(SAVED_NUMBER_UP);
            count_number = savedInstanceState.getInt(SAVED_NUMBER_DOWN);
        }else{
            //initializes instance with default value
            count_number = 0;
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.count_up_button:
                updateTextView(countUp());
                break;
            case R.id.count_down_button:
                updateTextView(countDown());
                break;
            case R.id.clear_button:
                updateTextView(0);
                count_number=0;
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_NUMBER_UP, countUp());
        outState.putInt(SAVED_NUMBER_DOWN, countDown());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateTextView(savedInstanceState.getInt(SAVED_NUMBER_UP));
        updateTextView(savedInstanceState.getInt(SAVED_NUMBER_DOWN));
    }

    public void updateTextView(int update){
        countNumberText.setText(Integer.toString(update));
    }

    private int countUp() {
        return ++count_number;
    }

    private int countDown(){
        return count_number>0 ? --count_number:count_number;
    }
}


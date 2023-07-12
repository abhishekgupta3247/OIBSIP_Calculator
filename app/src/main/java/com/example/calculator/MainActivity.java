package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

//to calculate the result we import 2things



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV, solutionTV;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        //assigning IDs using method
        assignId(buttonC,R.id.button_c);
        assignId(buttonBracketOpen,R.id.button_open_bracket);
        assignId(buttonBracketClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);

    }

    //created a method to assign IDs to all buttons except resultTV and solutionTV
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        //Test Buttons
//        solutionTV.setText(buttonText);

        //we'll take string to calculate the data whatever will be there in solutionTV
        String dataToCalculate = solutionTV.getText().toString();


        // AC C = don't want to concatenate
        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTV.setText(resultTV.getText());
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1); //trimmed the last character
        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }



        //it will concatinate the texts
//        dataToCalculate = dataToCalculate + buttonText; //SHIFTED ABOVE
        solutionTV.setText(dataToCalculate); //now we r setting to see(show concatination there(in the solnTV))
        //in above lines of code we set rules for AC and C


        //After setting try catch
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTV.setText(finalResult);
        }
    }

    //WE'LL CREATE A METHOD TO CALCULATE whatever is in the solutionTV
    //it will take data nd return the calculated data
    String getResult(String data){
//        return "Calculated";
        //to evaluate the data we import a lib nd pasted it to build.gradle
        //implementation 'com.faendir.rhino:rhino-android:1.5.2'

        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch(Exception e){
            return "Err";
        }


    }
}
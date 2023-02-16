package com.example.calculator20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;

    MaterialButton buttonC,buttonBracOpen,buttonBracClose;
    MaterialButton buttonDivide,buttonMultiply,buttonAdd,buttonSubtract,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignID(buttonC,R.id.button_C);
        assignID(buttonBracOpen,R.id.button_openbrac);
        assignID(buttonBracClose,R.id.button_closebrac);
        assignID(buttonDivide,R.id.button_divide);
        assignID(buttonMultiply,R.id.button_multiply);
        assignID(buttonAdd,R.id.button_add);
        assignID(buttonSubtract,R.id.button_subtrsct);
        assignID(buttonEquals,R.id.button_equals);
        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);
        assignID(buttonAC,R.id.button_clear);
        assignID(buttonDot,R.id.button_point);
    }

    void assignID(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            if(dataToCalculate.length() == 0){
                dataToCalculate = "0";
            }

        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getresult(dataToCalculate);
        if (!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }


        }

        String getresult(String data){
            try {
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initSafeStandardObjects();
                String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
                if(finalResult.endsWith(".0")){
                    finalResult = finalResult.replace(".0","");
                }
                return finalResult;
            }catch (Exception e){
                return "err";
            }
    }
}
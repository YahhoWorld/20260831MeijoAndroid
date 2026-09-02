package jp.ac.meijou.android.s251205059;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205059.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;

    private float firstNum=0;
    private float secondNum=0;
    private boolean isEditingFirst=true;
    private boolean isEditingSecond=false;
    private char op;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main3);

        binding= ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        prefDataStore=PrefDataStore.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // intent
        var intent=getIntent();
        String sentText=intent.getStringExtra("editText");
        binding.output.setText(sentText);


        // ok,cancel
        binding.buttonOk.setOnClickListener(view->{
            var okIntent=new Intent();
            okIntent.putExtra("result",binding.output.getText().toString());
            setResult(RESULT_OK,okIntent);
            finish();
        });
        binding.buttonCancel.setOnClickListener(view->{
            setResult(RESULT_CANCELED);
            finish();
        });



        // calc

        binding.button0.setOnClickListener(view->{
            handleClickNumButton(0);
        });
        binding.button1.setOnClickListener(view->{
            handleClickNumButton(1);
        });
        binding.button2.setOnClickListener(view->{
            handleClickNumButton(2);
        });
        binding.button3.setOnClickListener(view->{
            handleClickNumButton(3);
        });
        binding.button4.setOnClickListener(view->{
            handleClickNumButton(4);
        });
        binding.button5.setOnClickListener(view->{
            handleClickNumButton(5);
        });
        binding.button6.setOnClickListener(view->{
            handleClickNumButton(6);
        });
        binding.button7.setOnClickListener(view->{
            handleClickNumButton(7);
        });
        binding.button8.setOnClickListener(view->{
            handleClickNumButton(8);
        });
        binding.button9.setOnClickListener(view->{
            handleClickNumButton(9);
        });

        binding.buttonAdd.setOnClickListener(view ->{
            handleClickOp('+');
        });
        binding.buttonMult.setOnClickListener(view ->{
            handleClickOp('*');
        });
        binding.buttonMinus.setOnClickListener(view ->{
            handleClickOp('-');
        });
        binding.buttonDiv.setOnClickListener(view ->{
            handleClickOp('/');
        });

        binding.buttonAc.setOnClickListener(view->{
            handleClickAc();
        });

        binding.buttonEq.setOnClickListener(view->
                handleClickEq()
        );
    }

    private void handleClickNumButton(int num){
        if(isEditingFirst){
            firstNum*=10;
            firstNum+=num;
            binding.output.setText(""+firstNum);
        }
        else if(isEditingSecond){
            secondNum*=10;
            secondNum+=num;
            binding.output.setText(""+secondNum);
        }
    }

    private void handleClickOp(char op){
        if(!isEditingFirst)return;
        this.op=op;
        isEditingFirst=false;
        isEditingSecond=true;
    }

    private void handleClickAc(){
        isEditingFirst=true;
        isEditingSecond=false;
        firstNum=0;
        secondNum=0;
        this.op=' ';
        binding.output.setText("");
    }

    private  void handleClickEq(){
        if(!isEditingSecond)return;

        float result=0;

        switch (op){
            case '+':
                result=firstNum+secondNum;
                break;

            case '-':
                result=firstNum-secondNum;
                break;

            case '*':
                result=firstNum*secondNum;
                break;

            case '/':
                // ゼロ除算はjavaがinfinityにしてくれるので任せる.
                result=firstNum/secondNum;
                break;
        }

        binding.output.setText(""+result);

        isEditingSecond=false;
    }
}
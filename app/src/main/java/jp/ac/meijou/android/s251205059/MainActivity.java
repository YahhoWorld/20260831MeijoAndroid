package jp.ac.meijou.android.s251205059;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Optional;

import jp.ac.meijou.android.s251205059.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefDataStore=PrefDataStore.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



//        TextView textView=findViewById(R.id.text_view);
////        textView.setText(R.string.text);
//        textView.setText(R.string.text);
//        ImageView imageView=findViewById(R.id.imageView);
////        imageView.setImageResource(R.drawable.outline_accessible_forward_24);
//        imageView.setImageResource(R.drawable.outline_accessible_forward_24);
//        binding.changeButton.setOnClickListener(view->{
//            binding.textView.setText(binding.editTextText.getText());
//            Button b=(Button)view;
//            b.setTextColor(getColor(R.color.black));
//        });


        binding.saveButton.setOnClickListener(view->{
            String text=binding.editTextText.getText().toString();
            Log.d("meijo","saved");
            prefDataStore.setString("text",text);
        });
        prefDataStore.getString("text").ifPresent(
                text->binding.textView.setText(text)
        );


        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String text=editable.toString();
                binding.textView.setText(text);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }


}
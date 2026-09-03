package jp.ac.meijou.android.s251205059;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s251205059.databinding.ActivityMain6Binding;

public class MainActivity6 extends AppCompatActivity {

    private ActivityMain6Binding binding;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main6);
        binding=ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        connectivityManager=getSystemService(ConnectivityManager.class);

//        var currentNetwork=connectivityManager.getActiveNetwork();
//        updateTransport(currentNetwork);
//        updateIpAddress(currentNetwork);
        update();
        binding.update.setOnClickListener(v->{
           update();
        });
    }

    private void update(){
        var currentNetwork=connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
    }

    private void updateTransport(Network network){
        var caps=connectivityManager.getNetworkCapabilities(network);
        if(caps!=null){
            String transport;
            if(caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                transport="Mobile";
            }
            else if(caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                transport="WiFi";
            }
            else {
                transport="Other";
            }
            binding.netState.setText(transport);
        }
    }

    private  void updateIpAddress(Network network){
        var linkProperties=connectivityManager.getLinkProperties(network);
        if(linkProperties!=null){
            var address=linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));

            binding.ipadr.setText(address);
        }
    }
}
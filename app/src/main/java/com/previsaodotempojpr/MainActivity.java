package com.previsaodotempojpr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private String currentCity = "Joinville";
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() != null && !result.getContents().isEmpty()) {
                    this.currentCity = result.getContents();
                    updateFragmentsWithNewCity(this.currentCity);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 1) {
                        tab.setText("Mapa");
                    } else {
                        tab.setText("Previsão");
                    }
                }
        ).attach();

        FloatingActionButton fab = findViewById(R.id.fab_scan_qrcode);
        fab.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
            options.setPrompt("Aponte para o QR code da cidade");
            options.setBeepEnabled(true);
            barcodeLauncher.launch(options);
        });
    }

    private void updateFragmentsWithNewCity(String newCity) {
        // Itera sobre os fragmentos que o FragmentManager da atividade conhece
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            // Verifica se o fragmento é o da lista de previsão
            if (fragment instanceof WeatherListFragment) {
                ((WeatherListFragment) fragment).updateCity(newCity);
            }
            // Verifica se o fragmento é o do mapa
            if (fragment instanceof MapFragment) {
                ((MapFragment) fragment).updateCity(newCity);
            }
        }
    }

    public String getCurrentCity() {
        return currentCity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
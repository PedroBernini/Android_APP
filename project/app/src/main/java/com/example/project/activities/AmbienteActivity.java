package com.example.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.ambiente.Ambiente;
import com.example.project.fragments.DiscFragment;
import com.example.project.fragments.EquipesFragment;
import com.example.project.fragments.GeralFragment;
import com.example.project.fragments.PessoasFragment;
import com.example.project.model.Ambientes;

public class AmbienteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private int position;
    public static Ambiente ambiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent in = getIntent();
        if (in != null) {
            this.position = in.getIntExtra("position", 0);
            ambiente = Ambientes.ambientes.get(position);
            View headerView = navigationView.getHeaderView(0);
            TextView nomeAmbiente = (TextView) headerView.findViewById(R.id.nome_do_ambiente);
            TextView subtituloAmbiente = (TextView) headerView.findViewById(R.id.subtitulo_do_ambiente);
            nomeAmbiente.setText(ambiente.getNomeAmbiente());
            subtituloAmbiente.setText(ambiente.getTipoAmbiente());
        }

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            GeralFragment f1 = new GeralFragment();
            fragmentTransaction.add(R.id.frame_principal, f1, "geral_tag");
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ambiente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_geral) {
            Toast.makeText(this, "Geral", Toast.LENGTH_SHORT).show();
            Fragment fragGeral = fragmentManager.findFragmentByTag("geral_tag");
            if(fragGeral == null){
                fragGeral = new GeralFragment();
            }
            replaceFragment(fragGeral, "geral_tag");
        }
        else if (id == R.id.nav_pessoas) {
            Toast.makeText(this, "Pessoas", Toast.LENGTH_SHORT).show();
            Fragment fragPessoas = fragmentManager.findFragmentByTag("pessoas_tag");
            if(fragPessoas == null){
                fragPessoas = new PessoasFragment();
            }
            replaceFragment(fragPessoas, "pessoas_tag");
        }
        else if (id == R.id.nav_equipes) {
            Toast.makeText(this, "Equipes", Toast.LENGTH_SHORT).show();
            Fragment fragEquipes = fragmentManager.findFragmentByTag("equipes_tag");
            if(fragEquipes == null){
                fragEquipes = new EquipesFragment();
            }
            replaceFragment(fragEquipes, "equipes_tag");
        }
        else if (id == R.id.nav_DISC) {
            Toast.makeText(this, "Sobre o DISC", Toast.LENGTH_SHORT).show();
            Fragment fragDisc = fragmentManager.findFragmentByTag("disc_tag");
            if(fragDisc == null){
                fragDisc = new DiscFragment();
            }
            replaceFragment(fragDisc, "disc_tag");
        }
        else if (id == R.id.nav_sair) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_principal, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}

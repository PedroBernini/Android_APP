package com.example.project.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activities.AmbienteActivity;
import com.example.project.activities.EquipeActivity;
import com.example.project.ambiente.Equipe;
import com.example.project.model.Ambientes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EquipesAdapter extends BaseAdapter {

    private Context context;
    private List<Equipe> equipes;

    public EquipesAdapter(Context context, List<Equipe> equipes) {
        this.context = context;
        this.equipes = equipes;
    }

    @Override
    public int getCount() {
        return equipes.size();
    }

    @Override
    public Object getItem(int position) {
        return equipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Equipe equipe = equipes.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_equipe, null);
            holder = new ViewHolder();
            holder.txtNomeEquipe = (TextView) convertView.findViewById(R.id.txtNomeEquipe);
            holder.txtCaracteristicaPrimaria = (TextView) convertView.findViewById(R.id.txtCaracteristicaPrimaria);
            holder.txtCaracteristicaSecundaria = (TextView) convertView.findViewById(R.id.txtCaracteristicaSecundaria);
            holder.btnVisualizar= (Button) convertView.findViewById(R.id.btn_visualizar_equipe);
            holder.btnRemover = (Button) convertView.findViewById(R.id.btn_remover_equipe);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        TextView txtNomeEquipe = holder.txtNomeEquipe;
        TextView txtCaracteristicaPrimaria = holder.txtCaracteristicaPrimaria;
        TextView txtCaracteristicaSecundaria = holder.txtCaracteristicaSecundaria;
        Button btnVisualizar = holder.btnVisualizar;
        Button btnRemover = holder.btnRemover;

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EquipeActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remoção de equipe");
                builder.setMessage("Tem certeza que deseja remover a equipe?");

                builder.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Ambientes").child(AmbienteActivity.ambiente.getNomeAmbiente()).child("Equipes");
                        reff.child(equipes.get(position).getNome()).removeValue();
                        Toast.makeText(context, "A equipe " + equipes.get(position).getNome() + " foi removida!", Toast.LENGTH_SHORT).show();
                        equipes.remove(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        txtNomeEquipe.setText(equipe.getNome());
        char primeiro = equipe.getCaracteristicaPrimaria();
        String caracteristicaPrimaria = "";
        switch (primeiro) {
            case 'D':
                caracteristicaPrimaria = "Característica Principal: Dominância (D)";
                break;
            case 'I':
                caracteristicaPrimaria = "Característica Principal: Influência (I)";
                break;
            case 'S':
                caracteristicaPrimaria = "Característica Principal: Estabilidade (S)";
                break;
            case 'C':
                caracteristicaPrimaria = "Característica Principal: Conformidade (C)";
                break;
        }
        txtCaracteristicaPrimaria.setText(caracteristicaPrimaria);
        char segundo = equipe.getCaracteristicaSecundaria();
        String caracteristicaSecundaria = "";
        switch (segundo) {
            case 'D':
                caracteristicaSecundaria = "Característica Secundária: Dominância (D)";
                break;
            case 'I':
                caracteristicaSecundaria = "Característica Secundária: Influência (I)";
                break;
            case 'S':
                caracteristicaSecundaria = "Característica Secundária: Estabilidade (S)";
                break;
            case 'C':
                caracteristicaSecundaria = "Característica Secundária: Conformidade (C)";
                break;
        }
        txtCaracteristicaSecundaria.setText(caracteristicaSecundaria);
        return convertView;
    }

    static class ViewHolder {
        TextView txtNomeEquipe;
        TextView txtCaracteristicaPrimaria;
        TextView txtCaracteristicaSecundaria;
        Button btnVisualizar;
        Button btnRemover;
    }
}

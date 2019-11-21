package com.example.project.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.activities.AmbienteActivity;
import com.example.project.activities.MainActivity;
import com.example.project.R;
import com.example.project.ambiente.Ambiente;
import com.example.project.model.Ambientes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AmbientesAdapter extends BaseAdapter {

    private Context context;
    private List<Ambiente> ambientes;

    public AmbientesAdapter(Context context, List<Ambiente> ambientes) {
        this.context = context;
        this.ambientes = ambientes;
    }

    @Override
    public int getCount() {
        return ambientes.size();
    }

    @Override
    public Object getItem(int position) {
        return ambientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Ambiente ambiente = ambientes.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ambiente, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imagemAmbiente);
            holder.txtNomeAmbiente = (TextView) convertView.findViewById(R.id.txtNomeAmbiente);
            holder.txtTipo = (TextView) convertView.findViewById(R.id.txtTipo);
            holder.txtEquipe = (TextView) convertView.findViewById(R.id.txtEquipe);
            holder.txtPessoas = (TextView) convertView.findViewById(R.id.txtPessoas);
            holder.btnEntrar= (Button) convertView.findViewById(R.id.btnEntrar);
            holder.btnRemover = (Button) convertView.findViewById(R.id.btn_remover_ambiente);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        ImageView image = holder.image;
        TextView txtNomeAmbiente = holder.txtNomeAmbiente;
        TextView txtTipo = holder.txtTipo;
        TextView txtEquipe = holder.txtEquipe;
        TextView txtPessoas = holder.txtPessoas;
        Button btnEntrar = holder.btnEntrar;
        Button btnRemover = holder.btnRemover;

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AmbienteActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remoção de ambiente");
                builder.setMessage("Tem certeza que deseja remover o ambiente? (Você perderá todas as pessoas e equipes!)");

                builder.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Ambientes");
                        reff.child(ambientes.get(position).getNomeAmbiente()).removeValue();
                        Toast.makeText(context, "O ambiente " + ambientes.get(position).getNomeAmbiente() + " foi removido!", Toast.LENGTH_SHORT).show();
                        Ambientes.ambientes.remove(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        String tipo = ambiente.getTipoAmbiente();
        int index = 0;
        switch (tipo) {
            case "Ambiente de Marketing":
                index = 0;
                break;
            case "Ambiente Organizacional":
                index = 1;
                break;
            case "Ambiente de Produção":
                index = 2;
                break;
            case "Ambiente de Vendas":
                index = 3;
                break;
            case "Ambiente de Suporte":
                index = 4;
                break;
        }

        TypedArray ambientesArray = context.getResources().obtainTypedArray(R.array.ambientes_array);
        image.setImageDrawable(ambientesArray.getDrawable(index));
        txtNomeAmbiente.setText(ambiente.getNomeAmbiente());
        txtTipo.setText(ambiente.getTipoAmbiente());
        txtEquipe.setText(Integer.toString(ambiente.getQtdEquipes()) + " Equipe(s)");
        txtPessoas.setText(Integer.toString(ambiente.getQtdPessoas()) + " Pessoa(s)");

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView txtNomeAmbiente;
        TextView txtTipo;
        TextView txtEquipe;
        TextView txtPessoas;
        Button btnEntrar;
        Button btnRemover;
    }
}

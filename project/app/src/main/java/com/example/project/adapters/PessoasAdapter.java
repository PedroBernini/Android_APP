package com.example.project.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.activities.PessoaActivity;
import com.example.project.ambiente.Pessoa;

import java.util.List;

public class PessoasAdapter extends BaseAdapter {

    private Context context;
    private List<Pessoa> pessoas;


    public PessoasAdapter(Context context, List<Pessoa> pessoas) {
        this.context = context;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Pessoa pessoa = pessoas.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pessoa, null);
            holder = new ViewHolder();
            holder.txtNomePessoa = (TextView) convertView.findViewById(R.id.txtNomePessoa);
            holder.txtPredominancia = (TextView) convertView.findViewById(R.id.txtPredominancia);
            holder.btnVisualizar= (Button) convertView.findViewById(R.id.btn_visualizar_pessoa);
            holder.btnRemover = (Button) convertView.findViewById(R.id.btn_remover_pessoa);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        TextView txtNomePessoa = holder.txtNomePessoa;
        TextView txtPredominancia = holder.txtPredominancia;
        Button btnVisualizar = holder.btnVisualizar;
        Button btnRemover = holder.btnRemover;

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PessoaActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtNomePessoa.setText(pessoa.getNome());
        char primeiro = pessoa.getPredominancia();
        String predominancia = "";
        switch (primeiro) {
            case 'D':
                predominancia = "Fator de predominância: Dominância (D)";
                break;
            case 'I':
                predominancia = "Fator de predominância: Influência (I)";
                break;
            case 'S':
                predominancia = "Fator de predominância: Estabilidade (S)";
                break;
            case 'C':
                predominancia = "Fator de predominância: Conformidade (C)";
                break;
        }
        txtPredominancia.setText(predominancia);
        return convertView;
    }

    static class ViewHolder {
        TextView txtNomePessoa;
        TextView txtPredominancia;
        Button btnVisualizar;
        Button btnRemover;
    }
}

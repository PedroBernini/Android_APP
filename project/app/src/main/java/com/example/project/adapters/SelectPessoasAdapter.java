package com.example.project.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.activities.PessoaActivity;
import com.example.project.ambiente.Pessoa;
import java.util.List;

public class SelectPessoasAdapter extends BaseAdapter {

    public Context context;
    private List<Pessoa> pessoas;
    private List<Pessoa> grupo;

    public SelectPessoasAdapter(Context context, List<Pessoa> pessoas) {
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
    public int getViewTypeCount() {
        return pessoas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Pessoa pessoa = pessoas.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_pessoas, null);
            holder = new ViewHolder();
            holder.checkBoxPessoa = (CheckBox) convertView.findViewById(R.id.checkBoxPessoa);
            holder.txtPredominancia = (TextView) convertView.findViewById(R.id.txtPredominancia);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        CheckBox checkBoxPessoa = holder.checkBoxPessoa;
        TextView txtPredominancia = holder.txtPredominancia;


        /*checkBoxPessoa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int id = buttonView.getId();
                    if(id == R.id.checkBoxPessoa) {
                        grupo.add(pessoas.get(position));
                        System.out.println("Grupo size: " + grupo.size());
                    } else {
                        grupo.remove(pessoas.get(position));
                        System.out.println("Grupo size: " + grupo.size());
                    }
               }
           }
        );*/

        checkBoxPessoa.setText(pessoa.getNome());

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
        CheckBox checkBoxPessoa;
        TextView txtPredominancia;
    }
}

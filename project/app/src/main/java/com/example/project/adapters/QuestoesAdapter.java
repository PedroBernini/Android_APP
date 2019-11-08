package com.example.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.project.R;
import com.example.project.activities.NovaPessoaActivity;
import com.example.project.utils.Questao;
import com.example.project.utils.Questoes;

import java.util.Arrays;
import java.util.List;

public class QuestoesAdapter extends BaseAdapter {

    public Context context;
    private List<Questao> questoes;

    public QuestoesAdapter(Context context, List<Questao> questoes) {
        this.context = context;
        this.questoes = questoes;
    }

    @Override
    public int getCount() {
        return questoes.size();
    }

    @Override
    public Object getItem(int position) {
        return questoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return questoes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Questao questao = questoes.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_questoes, null);
            holder = new ViewHolder();
            holder.radioGroup = (RadioGroup) convertView.findViewById(R.id.radioGroupQuestoes);
            holder.radioBtnD = (RadioButton) convertView.findViewById(R.id.radioBtnD);
            holder.radioBtnI = (RadioButton) convertView.findViewById(R.id.radioBtnI);
            holder.radioBtnS = (RadioButton) convertView.findViewById(R.id.radioBtnS);
            holder.radioBtnC = (RadioButton) convertView.findViewById(R.id.radioBtnC);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        RadioGroup radioGroup = holder.radioGroup;
        RadioButton radioBtnD = holder.radioBtnD;
        RadioButton radioBtnI = holder.radioBtnI;
        RadioButton radioBtnS = holder.radioBtnS;
        RadioButton radioBtnC = holder.radioBtnC;

        radioBtnD.setText(questao.getQuestaoD());
        radioBtnI.setText(questao.getQuestaoI());
        radioBtnS.setText(questao.getQuestaoS());
        radioBtnC.setText(questao.getQuestaoC());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();

                if (id == R.id.radioBtnD) {
                    NovaPessoaActivity.questoes.get(position).setPontuacaoD(1);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoI(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoS(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoC(0);
                } else if (id == R.id.radioBtnI) {
                    NovaPessoaActivity.questoes.get(position).setPontuacaoD(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoI(1);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoS(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoC(0);
                } else if (id == R.id.radioBtnS) {
                    NovaPessoaActivity.questoes.get(position).setPontuacaoD(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoI(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoS(1);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoC(0);
                } else if (id == R.id.radioBtnC) {
                    NovaPessoaActivity.questoes.get(position).setPontuacaoD(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoI(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoS(0);
                    NovaPessoaActivity.questoes.get(position).setPontuacaoC(1);
                }
            }
        });

        return convertView;
    }


    static class ViewHolder {
        RadioGroup radioGroup;
        RadioButton radioBtnD;
        RadioButton radioBtnI;
        RadioButton radioBtnS;
        RadioButton radioBtnC;
    }
}

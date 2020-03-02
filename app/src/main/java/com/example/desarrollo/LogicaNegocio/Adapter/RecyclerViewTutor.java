package com.example.desarrollo.LogicaNegocio.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class RecyclerViewTutor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Tutor> tutorList;
    Dialog dialogOptions;


    public RecyclerViewTutor(Context context, List<Tutor> tutorList) {
        this.context = context;
        this.tutorList = tutorList;
    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView parentesco;
        LinearLayout _btnTutorSelect;
        LinearLayout layoutDialogOptios;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtTutorItemNombre);
            parentesco = (TextView) itemView.findViewById(R.id.txtTutorItemParentesco);

            layoutDialogOptios = (LinearLayout) itemView.findViewById(R.id.tutorDialogOptions);

            _btnTutorSelect = (LinearLayout) itemView.findViewById(R.id.btnTutorSelect);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.tutor_items, parent, false);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);

        //Dialog
        dialogOptions = new BottomSheetDialog(context);
        dialogOptions.setContentView(R.layout.tutor_dialog_options);
        dialogOptions.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewHolder._btnTutorSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView _txtPasswordTutor = (TextView) dialogOptions.findViewById(R.id.txtPasswordTutor);
                _txtPasswordTutor.setKeyListener(null);
                final TextView _textContra = (TextView) dialogOptions.findViewById(R.id.textContra);
                final LinearLayout layoutEditarTutor = (LinearLayout) dialogOptions.findViewById(R.id.layoutEditarTutor);
                final ImageButton _btnEditarTutor = (ImageButton) dialogOptions.findViewById(R.id.btnEditarTutor);
                final Button _btnGuardarTutor = (Button) dialogOptions.findViewById(R.id.btnGuardarTutor);
                _btnEditarTutor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutEditarTutor.setVisibility(View.VISIBLE);
                        _txtPasswordTutor.setKeyListener(new EditText(context).getKeyListener());
                        _txtPasswordTutor.setText("");
                        _textContra.setText("Nueva contraseña");
                    }
                });
                _btnGuardarTutor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutEditarTutor.setVisibility(View.GONE);
                        _txtPasswordTutor.setKeyListener(null);
                        _textContra.setText("Contraseña");
                    }
                });

                dialogOptions.show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        Tutor tutor = this.tutorList.get(position);

        viewHolder.nombre.setText(tutor.getNombreTutor());
        viewHolder.parentesco.setText(tutor.getParentesto());

    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }
}

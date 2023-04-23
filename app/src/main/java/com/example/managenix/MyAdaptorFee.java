package com.example.managenix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdaptorFee extends FirebaseRecyclerAdapter<fee_model,MyAdaptorFee.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdaptorFee(@NonNull FirebaseRecyclerOptions<fee_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull fee_model fee_model) {

        holder.studname.setText(fee_model.getStudent_Name());
        holder.studid.setText(fee_model.getStudent_ID());
        holder.branch.setText(fee_model.getBranch());
        holder.status.setText(fee_model.getStatus());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu,parent,false);
        return new myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        TextView studname, studid, branch, status;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            studname = itemView.findViewById(R.id.FEENAME);
            studid = itemView.findViewById(R.id.FEEID);
            branch = itemView.findViewById(R.id.FEEBRANCH);
            status = itemView.findViewById(R.id.FEESTATUS);
        }
    }
}





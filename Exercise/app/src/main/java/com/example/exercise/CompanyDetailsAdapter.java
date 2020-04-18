package com.example.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.MyViewHolder> {


    private Context context;
    private List <CompanyDetails> companyDetailsList ;

    public CompanyDetailsAdapter(Context context,List <CompanyDetails> companyDetailsList) {
        this.context = context;
        this.companyDetailsList = companyDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_watch_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        CompanyDetails companyDetails = companyDetailsList.get(position);
        holder.companyName.setText(companyDetails.getCompanyName());
        holder.askPrice.setText(companyDetails.getAskPrice()+"");
        holder.bidPrice.setText(companyDetails.getBidPrice()+"");
        holder.lastPrice.setText(companyDetails.getLastPrice()+"");
        holder.highPrice.setText(companyDetails.getHighPrice()+"");
    }

    @Override
    public int getItemCount() {
        return companyDetailsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  companyName;
        TextView  askPrice;
        TextView  lastPrice;
        TextView  bidPrice;
        TextView  highPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

              companyName = itemView.findViewById(R.id.name);
              askPrice    = itemView.findViewById(R.id.askprice);
              lastPrice   = itemView.findViewById(R.id.lastprice);
              bidPrice    = itemView.findViewById(R.id.bidprice);
              highPrice   = itemView.findViewById(R.id.highprice);
        }
    }
}

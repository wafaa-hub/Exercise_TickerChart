package com.example.exercise.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.MyViewHolder> {


    private Context context;
    private List <Company> companyList;

    public CompanyDetailsAdapter(Context context,List <Company> companyList) {
        this.context = context;
        this.companyList = companyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_watch_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        Company company = companyList.get(position);
        holder.companyName.setText(company.getCompanyName());
        holder.askPrice.setText(company.getAskPrice()+"");
        holder.bidPrice.setText(company.getBidPrice()+"");
        holder.lastPrice.setText(company.getLastPrice()+"");
        holder.highPrice.setText(company.getHighPrice()+"");
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)      TextView  companyName;
        @BindView(R.id.askprice)  TextView  askPrice;
        @BindView(R.id.lastprice) TextView  lastPrice;
        @BindView(R.id.bidprice)  TextView  bidPrice;
        @BindView(R.id.highprice) TextView  highPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

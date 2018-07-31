package project.ordereat.Admin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import project.ordereat.Interface.ItemClickListener;
import project.ordereat.R;

public class AdminOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{

    public TextView txtOrderTable,txtOrderPrice, txtOrderStatus;
    private ItemClickListener itemClickListener;

    public AdminOrderViewHolder(View itemView) {
        super(itemView);

        txtOrderTable = (TextView) itemView.findViewById(R.id.admin_order_table);
        txtOrderPrice = (TextView) itemView.findViewById(R.id.admin_order_price);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.admin_order_status);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the Action");

        contextMenu.add(0,0,getAdapterPosition(),"Update");
        contextMenu.add(0,1,getAdapterPosition(),"Delete");
    }



}

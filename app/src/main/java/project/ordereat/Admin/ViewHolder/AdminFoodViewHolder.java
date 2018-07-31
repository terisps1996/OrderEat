package project.ordereat.Admin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.ordereat.Common.Common;
import project.ordereat.Interface.ItemClickListener;
import project.ordereat.R;

public class AdminFoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{

    public TextView admin_food_name;
    public ImageView admin_food_image;

    private ItemClickListener itemClickListener;
    public AdminFoodViewHolder(View itemView) {
        super(itemView);
        admin_food_name = (TextView)itemView.findViewById(R.id.admin_food_name);
        admin_food_image = (ImageView)itemView.findViewById(R.id.admin_food_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the action");
        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1,getAdapterPosition(), Common.DELETE);
    }

}

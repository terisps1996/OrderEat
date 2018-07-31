package project.ordereat.Admin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.ordereat.Common.Common;
import project.ordereat.Interface.ItemClickListener;
import project.ordereat.R;

public class AdminMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{


    public TextView txtMenuName_admin;
    public ImageView imageView_admin;

    private ItemClickListener itemClickListener;
    public AdminMenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName_admin = (TextView)itemView.findViewById(R.id.admin_menu_name);
        imageView_admin = (ImageView)itemView.findViewById(R.id.admin_menu_image);

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

package project.ordereat.User.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import project.ordereat.User.Cart;
import project.ordereat.Common.Common;
import project.ordereat.Database.Database;
import project.ordereat.Interface.ItemClickListener;
import project.ordereat.Model.Order;
import project.ordereat.R;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{
    public TextView txt_cart_name, txt_price;
    public ElegantNumberButton btn_quantity;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView) itemView.findViewById(R.id.cart_item_Price);
        btn_quantity = (ElegantNumberButton) itemView.findViewById(R.id.btn_quantity);

        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select Action");
        contextMenu.add(0,0,getAdapterPosition(), Common.DELETE);
    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> listData = new ArrayList<>();
    private Cart cart;

    public CartAdapter(List<Order> listData, Cart cart) {
        this.listData = listData;
        this.cart = cart;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cart);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, final int position) {
        holder.btn_quantity.setNumber(listData.get(position).getQuantity());

        holder.btn_quantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Order order = listData.get(position);
                order.setQuantity(String.valueOf(newValue));
                new Database(cart).updateCart(order);

                //Update Total Price
                int total = 0;
                List<Order> orders = new Database(cart).getCarts();
                for(Order item : orders){
                    total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(item.getQuantity()));
                    cart.txtTotalPrice.setText(total + " Lei");
                }
                //Refresh Cart
                cart.loadListFood();
            }
        });

        //Currency
        int price = (Integer.parseInt((listData.get(position).getPrice()))) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(price + " Lei");
        holder.txt_cart_name.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

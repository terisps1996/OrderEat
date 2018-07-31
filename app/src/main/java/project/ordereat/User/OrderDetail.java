package project.ordereat.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import project.ordereat.Common.Common;
import project.ordereat.R;
import project.ordereat.User.ViewHolder.OrderDetailAdapter;

public class OrderDetail extends AppCompatActivity {

    TextView order_id, order_table, order_total, order_status;
    String order_id_value = "";
    RecyclerView lstFoods;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order_id = (TextView) findViewById(R.id.order_id);
        order_status = (TextView) findViewById(R.id.order_status);
        order_table = (TextView) findViewById(R.id.order_table);
        order_total = (TextView) findViewById(R.id.order_total);

        lstFoods = (RecyclerView) findViewById(R.id.lstFoods);
        lstFoods.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lstFoods.setLayoutManager(layoutManager);

        if(getIntent()!= null){
            order_id_value = getIntent().getStringExtra("OrderId");
        }

        //Set value
        order_id.setText("Order Number : " + order_id_value);
        order_status.setText("Status - " + Common.convertCodeToStatus(Common.currentRequest.getStatus()));
        order_table.setText("Table No. " + Common.currentRequest.getTable());
        order_total.setText(Common.currentRequest.getTotal() + " Lei");

        OrderDetailAdapter adapter = new OrderDetailAdapter(Common.currentRequest.getFoods());
        adapter.notifyDataSetChanged();
        lstFoods.setAdapter(adapter);

    }


}

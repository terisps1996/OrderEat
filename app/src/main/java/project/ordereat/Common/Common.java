package project.ordereat.Common;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.EditText;

import project.ordereat.Model.Request;
import project.ordereat.Model.User;

public class Common {
    public static User currentUser;
    public static Request currentRequest;
    public static final String DELETE = "Delete";
    public static final String UPDATE = "Update";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String code){
        if(code.equals("0")){
            return "Placed";
        }else if(code.equals("1")) {
            return "Getting Ready";
        }else if(code.equals("2")){
            return "Order is Ready";
        }else{
            return "Completed";
        }
    }

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null){
                for(int i=0;i<info.length;i++){
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}

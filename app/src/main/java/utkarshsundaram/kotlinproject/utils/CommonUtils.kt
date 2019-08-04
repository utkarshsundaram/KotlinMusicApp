package utkarshsundaram.kotlinproject.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build

class CommonUtils
{
    companion object {
       fun hasPermission(permission:String,context: Context):Boolean{
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
           }
           return true
       }

        fun findUnAskedPermission(wanted:ArrayList<String>,context: Context):ArrayList<String>{
           var result =ArrayList<String>()
            for (perm in wanted) {
                if (!hasPermission(perm, context)) {
                    result.add(perm)
                }
            }
            return result;
        }

        fun showMessageOKCancel(context:Context,message:String,onClickListner:DialogInterface.OnClickListener){
            AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK",onClickListner)
                .create()
                .show()
        }
    }
}
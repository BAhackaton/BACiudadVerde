package com.urucas.utils;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import com.urucas.bamaslimpia.R;
import com.urucas.callbacks.DialogCallback;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.WindowManager.LayoutParams;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public abstract class Utils {

	public static boolean hasImageCaptureBug() {

	    // list of known devices that have the bug
	    ArrayList<String> devices = new ArrayList<String>();
	    devices.add("android-devphone1/dream_devphone/dream");
	    devices.add("generic/sdk/generic");
	    devices.add("vodafone/vfpioneer/sapphire");
	    devices.add("tmobile/kila/dream");
	    devices.add("verizon/voles/sholes");
	    devices.add("google_ion/google_ion/sapphire");

	    return devices.contains(android.os.Build.BRAND + "/" + android.os.Build.PRODUCT + "/"
	            + android.os.Build.DEVICE);

	}
	
	public static int intIDFromResource(Context context, String name, String type) {
		return context.getResources().getIdentifier(name, type, context.getPackageName());		
	}
		
	public static String stringFromResource(Context context, String name) {
		int id = context.getResources().getIdentifier(name, "string", context.getPackageName());
		return context.getResources().getString(id);
	}
	
	public static boolean isConnected(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) return true;
        
        return false;
	}
	
	public static String capitalize(String s) {
	    if (s!=null && s.length() > 0) {
	        return s.substring(0, 1).toUpperCase() + s.substring(1);
	    }
	    else return s;
	}
	
	public static String capitalize(String s, boolean allWords) {
		String[] words = s.split("\\s+");
		for(int i=0; i< words.length;i++) {
			words[i] = Utils.capitalize(words[i]);			
		}
		return Utils.join(words, " ");
	}
	
	public static String join(String[] s, String glue) {
		String res = "";
		for(int j=0; j<s.length;j++) {
			res += s[j];
			res += glue;
		}	
		return res;
	}
	
	public static boolean isValidPhoneNumber(String pn) {
		return pn.matches("\\d{8,}");
	}
	
	public static boolean isValidUser(String usr) {
		return usr.matches("\\w[a-zA-Z0-9]{3,}");
	}
	
	public static boolean isValidPassword(String pwd) {
		return pwd.matches("[a-zA-Z0-9]{5,}");
	}
	
	public static boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	        return false;
	    } else {
	        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	    }
	}

	public static void showOKAlert(Context context, String message) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(message)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();                 
               }
         });         
		 AlertDialog alert = builder.create();
		 alert.show();		 
	}
	
	public static void showOKAlert(Context context, int messageResourceID) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(messageResourceID)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();                 
               }
         });         
		 AlertDialog alert = builder.create();
		 alert.show();		 
	}
	
	public static Dialog showPreloader(Context context, String title, String message) {
		Dialog dialog = ProgressDialog.show(context, title, message, true);
    	dialog.setCancelable(true); 
    	return dialog;
	}
	
	public static void Log(String tag, String message) {
		android.util.Log.i(tag, message);
	}
	
	public static void Log(int number) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", String.valueOf(number));
	}
	
	public static void Log(String message) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", message);
	}
	
	public static void Log(float f) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", String.valueOf(f));
	}
	
	public static String getRegionCodeFromSIM(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSimCountryIso();
	}
	
	public static String getUUID(Context context) {
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();
	}
	
	public static String getEmailAccount(Context context) {
		try {
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(context).getAccounts();
		for (Account account : accounts) {
		    if (emailPattern.matcher(account.name).matches()) {
		        String possibleEmail = account.name;		        					
				return possibleEmail;			   
		    }
		}
		}catch(Exception e) {}
		return null;
	}
	
	public static String getNickFromEmail(String email) {
		if(email == null || email.length() == 0) return "";
		
		String[] nick = email.split("@");
		return nick.length == 0 ? null : nick[0];
		
	}
	
	public static float dp2pixel(Context context, int dp) {
		Resources r = context.getResources();
    	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());    	
	}
	
	public static float distanceBetween2Points(float vectorX0, float vectorY0, float vectorXP, float vectorYP) {
		float x = Math.abs(vectorXP - vectorX0);
		float y = Math.abs(vectorYP - vectorY0);
		return (float) Math.sqrt((x*x) + (y*y));
	}
	
	public static Date string2Date(String sd,String formato){
		//formato = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		//"Thu Jul 11 12:40:18 GMT-03:00 2013"
		//"EE MMM dd HH:mm:ss z YYYY"
		SimpleDateFormat format = new SimpleDateFormat(formato); 
		try {
		    Date date = (Date) format.parse(sd);
		    return date;
		} catch (ParseException e) {
		    // TODO Auto-generated catch block  
		    e.printStackTrace();
		}
		return null;
	}

	public static String date2String(Calendar d, String format) {
		
		DateFormat f = new DateFormat();
		return (String) f.format("dd-MM-yyyy", d);
	}
	
	public static void openLink(Activity activity, String url){
		if(url.equals("")){ return; };

		if (!url.startsWith("http://") && !url.startsWith("https://")) 
			url = "http://" + url;

		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(browserIntent);
	}
	
	public static void Dialog(Activity activity, String title, String msg, final DialogCallback callback) {
		
		final Dialog dialog = new Dialog(activity, R.style.fullHeightDialog);
		dialog.setContentView(R.layout.custom_dialog);
		
		TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialogTitle);
					dialogTitle.setText(title);					
					
		TextView dialogMsg = (TextView) dialog.findViewById(R.id.dialogMessage);
				 dialogMsg.setText(msg);
				 
		Button dialogCancel = (Button) dialog.findViewById(R.id.dialogCancel);
		dialogCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onCancel();
			}
		});
		Button dialogOK = (Button) dialog.findViewById(R.id.dialogOK);
		dialogOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onOK();
			}
		});
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public static void Dialog(Activity activity, int title, String msg, final DialogCallback callback) {
		
		final Dialog dialog = new Dialog(activity, R.style.fullHeightDialog);
		dialog.setContentView(R.layout.custom_dialog);
		
		TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialogTitle);
					dialogTitle.setText(title);					
					
		TextView dialogMsg = (TextView) dialog.findViewById(R.id.dialogMessage);
				 dialogMsg.setText(msg);
				 
		Button dialogCancel = (Button) dialog.findViewById(R.id.dialogCancel);
		dialogCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onCancel();
			}
		});
		Button dialogOK = (Button) dialog.findViewById(R.id.dialogOK);
		dialogOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onOK();
			}
		});
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public static void Dialog(Activity activity, String title, int msg, final DialogCallback callback) {
		
		final Dialog dialog = new Dialog(activity, R.style.fullHeightDialog);
		dialog.setContentView(R.layout.custom_dialog);
		
		TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialogTitle);
					dialogTitle.setText(title);					
					
		TextView dialogMsg = (TextView) dialog.findViewById(R.id.dialogMessage);
				 dialogMsg.setText(msg);
				 
		Button dialogCancel = (Button) dialog.findViewById(R.id.dialogCancel);
		dialogCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onCancel();
			}
		});
		Button dialogOK = (Button) dialog.findViewById(R.id.dialogOK);
		dialogOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onOK();
			}
		});
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public static void Dialog(Activity activity, int title, int msg, final DialogCallback callback) {
		
		final Dialog dialog = new Dialog(activity, R.style.fullHeightDialog);
		dialog.setContentView(R.layout.custom_dialog);
		
		TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialogTitle);
					dialogTitle.setText(title);					
					
		TextView dialogMsg = (TextView) dialog.findViewById(R.id.dialogMessage);
				 dialogMsg.setText(msg);
				 
		Button dialogCancel = (Button) dialog.findViewById(R.id.dialogCancel);
		dialogCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onCancel();
			}
		});
		Button dialogOK = (Button) dialog.findViewById(R.id.dialogOK);
		dialogOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				callback.onOK();
			}
		});
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public static void Toast(Context context, String text) {	
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
    	toast.show();
	}
	
	public static void Toast(Context context, int resourceID) {
		Toast toast = Toast.makeText(context, resourceID, Toast.LENGTH_LONG);
    	toast.show();
	}
	
	public static void Toast(Context context, String text, int length) {
		Toast toast = Toast.makeText(context, text, length);
    	toast.show();
	}
	
	public static void Toast(Context context, int resourceID, int length) {
		Toast toast = Toast.makeText(context, resourceID, length);
    	toast.show();
	}
	
	public static String getRealPathFromUri(Context context, Uri contentUri) {	
		Log.i("uri", contentUri.toString());
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
		    Cursor cursor = null;
		    try {

		    	String[] proj = { MediaStore.Images.Media.DATA };
		    	cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
		    	int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		    	cursor.moveToFirst();
		    	return cursor.getString(column_index);
		    } finally {
		        if (cursor != null) {
		            cursor.close();
		        }
		    }
		}else{
			Uri uri = contentUri;
			// DocumentProvider
		    if (DocumentsContract.isDocumentUri(context, uri)) {
		        // ExternalStorageProvider
		        if (isExternalStorageDocument(uri)) {
		            final String docId = DocumentsContract.getDocumentId(uri);
		            final String[] split = docId.split(":");
		            final String type = split[0];

		            if ("primary".equalsIgnoreCase(type)) {
		                return Environment.getExternalStorageDirectory() + "/" + split[1];
		            }

		            // TODO handle non-primary volumes
		        }
		        // DownloadsProvider
		        else if (isDownloadsDocument(uri)) {

		            final String id = DocumentsContract.getDocumentId(uri);
		            final Uri contentUri2 = ContentUris.withAppendedId(
		                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

		            return getDataColumn(context, contentUri2, null, null);
		        }
		        // MediaProvider
		        else if (isMediaDocument(uri)) {
		            final String docId = DocumentsContract.getDocumentId(uri);
		            final String[] split = docId.split(":");
		            final String type = split[0];

		            Uri contentUri2 = null;
		            if ("image".equals(type)) {
		                contentUri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		            } else if ("video".equals(type)) {
		                //contentUri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
		            } else if ("audio".equals(type)) {
		                //contentUri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		            }

		            final String selection = "_id=?";
		            final String[] selectionArgs = new String[] {
		                    split[1]
		            };

		            return getDataColumn(context, contentUri2, selection, selectionArgs);
		        }
		    }
		    // MediaStore (and general)
		    else if ("content".equalsIgnoreCase(uri.getScheme())) {
		        return getDataColumn(context, uri, null, null);
		    }
		    // File
		    else if ("file".equalsIgnoreCase(uri.getScheme())) {
		        return uri.getPath();
		    }

		    return null;
		}
	}
	/* metodos copiados */
	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @param selection (Optional) Filter used in the query.
	 * @param selectionArgs (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection,
	        String[] selectionArgs) {

	    Cursor cursor = null;
	    final String column = "_data";
	    final String[] projection = {
	            column
	    };

	    try {
	        cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
	                null);
	        if (cursor != null && cursor.moveToFirst()) {
	            final int column_index = cursor.getColumnIndexOrThrow(column);
	            return cursor.getString(column_index);
	        }
	    } finally {
	        if (cursor != null)
	            cursor.close();
	    }
	    return null;
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
	    return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
	    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
	    return "com.android.providers.media.documents".equals(uri.getAuthority());
	}
	
	
}

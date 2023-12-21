package com.example.unimart;

        import android.content.ContentValues;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;


public class DatabaseHelper extends SQLiteOpenHelper {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    public DatabaseHelper(Context context) {
        super(context, "Orders.db", null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table orders(email TEXT, food_name TEXT,generic_image INTEGER )");
    }





    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int ii) {
        MyDatabase.execSQL("drop table if exists orders");
    }

    public boolean insertObjectData(String email, String food_name, int generic_image) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("FOOD_NAME", food_name);
        contentValues.put("GENERIC_IMAGE", generic_image);


        long result =  MyDatabase.insert("orders", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;

        }
    }

    public boolean deleteOrder(String food_name) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        int result = MyDatabase.delete("orders", "food_name=?", new String[]{food_name});
        return result>0;
    }

    public void deleteMyOrder()
    {
        SQLiteDatabase ourDatabase = this.getWritableDatabase();
        ourDatabase.delete("orders",null,null);
        ourDatabase.close();

    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from orders", null);
    }

}
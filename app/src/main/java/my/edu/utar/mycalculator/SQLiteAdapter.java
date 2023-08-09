package my.edu.utar.mycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Script;
import android.support.annotation.Nullable;

public class SQLiteAdapter {

    //constant variable
    //sql commands
    public static final String MYDATABASE_TABLE="MY_TABLE_History_3";
    public static final String KEY_CONTENT="Name";
    public static final String VALUE="TotalPrice";
    public static final String VALUE_2="ServiceTax";
    public static final String VALUE_3="PriceAfterDivide";

    public static final String SCRIPT_CREATE_DATABASE =
            "create table " + MYDATABASE_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_CONTENT + " text not null, " +
                    VALUE + " float, "+
                    VALUE_2 + " integer, "+
                    VALUE_3 + " float);";

    public static final String MYDATABASE_NAME="MY_DATABASE";
    public static final int MYDATABASE_VERSION=5;


    //variables
    public Context context;
    public SQLiteHelper sqLiteHelper;
    public SQLiteDatabase sqLiteDatabase;

    //constructor
    public SQLiteAdapter(Context c){
        context=c;
    }

    //open to write
    public SQLiteAdapter openToWrite() throws android.database.SQLException{
        //instation to create the object of SQLite Helper
        sqLiteHelper=new SQLiteHelper(context,MYDATABASE_NAME,null,MYDATABASE_VERSION);

        //writing mode
        sqLiteDatabase=sqLiteHelper.getWritableDatabase();
        return this;
    }

    //open to read
    public SQLiteAdapter openToRead() throws android.database.SQLException{
        //instation to create the object of SQLite Helper
        sqLiteHelper=new SQLiteHelper(context,MYDATABASE_NAME,null,MYDATABASE_VERSION);

        //read mode
        sqLiteDatabase=sqLiteHelper.getReadableDatabase();
        return this;
    }

    //insert a data into the table with one column
    public long insert(String content){
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_CONTENT,content);

        return sqLiteDatabase.insert(MYDATABASE_TABLE,null,contentValues);
    }

    public long insert(String name,double totalPrice,double serviceTax,double priceAfterDivide){
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_CONTENT,name);
        contentValues.put(VALUE,totalPrice);
        contentValues.put(VALUE_2,serviceTax);
        contentValues.put(VALUE_3,priceAfterDivide);

        return sqLiteDatabase.insert(MYDATABASE_TABLE,null,contentValues);
    }

    //retrieve the data
    public String queueAll_Two(){
        String[]columns=new String[]{
                KEY_CONTENT,VALUE,VALUE_2,VALUE_3
        };
        Cursor cursor=sqLiteDatabase.query(MYDATABASE_TABLE,columns,null,null,null,null,null);

        String result="";

        int index_CONTENT_1=cursor.getColumnIndex(KEY_CONTENT);
        int index_CONTENT_2=cursor.getColumnIndex(VALUE);
        int index_CONTENT_3=cursor.getColumnIndex(VALUE_2);
        int index_CONTENT_4=cursor.getColumnIndex(VALUE_3);

        //to loop for all the data in the table
        for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
            result=result+cursor.getString(index_CONTENT_1)+" | "+ cursor.getString(index_CONTENT_2)+" | "
                         +cursor.getString(index_CONTENT_3)+" | "+ cursor.getString(index_CONTENT_4)+" \n";
        }

        return result;
    }

    //close the database
    public void close(){
        sqLiteHelper.close();
    }

    //clear the data before adding
    public int deleteAll(){
        return sqLiteDatabase.delete(MYDATABASE_TABLE,null,null);
    }

    //another class which extends the superclass of SQLite Helper
    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SCRIPT_CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }
    }
}

package com.jameslawler.android_feed.data;

/**
 * Created by james on 07/11/2015.
 */
public class DbRepository {

    public void Hello() {
        try {
            DB snappydb = DBFactory.open(context); //create or open an existing databse using the default name

            snappydb.put("name", "Jack Reacher");
            snappydb.putInt("age", 42);
            snappydb.putBoolean("single", true);
            snappydb.put("books", new String[]{"One Shot", "Tripwire", "61 Hours"});

            String   name   =  snappydb.get("name");
            int      age    =  snappydb.getInt("age");
            boolean  single =  snappydb.getBoolean("single");
            String[] books  =  snappydb.getArray("books", String.class);// get array of string

            snappydb.close();

        } catch (SnappydbException e) {
        }
    }
}

Added CRUD with sqlite database.

09/24/24
1. added display textview for total count, total pieces sold and remaining pieces.
- in activity_main

2. added code in mainActivity to support displays.
3. Added condition for view, update, delete and Save.
4. adjust DATABASE_VERSION = 8
5. Added column to User class (totamount, tot_no_of_pcs_sold)


09/26/24
1. Added Toast message in View function.
2. updated DATABASE_VERSION = 9 to accomodate cursor.moveToLast() for view function.
3. Added select All functionality in report window screen.
private const val DATABASE_VERSION = 10
4. added getAllUsers in databaseHelper.kt
5. update activity_report2.xml
Added Generate, home and listView
6. Added select where condition. per lot number in generate record
7. Added button to getAll and Getby lot Number
DATABASE_VERSION = 12
8. Added editLotNumber.requestFocus().
Make lot number text field the default entry point for the cursor.
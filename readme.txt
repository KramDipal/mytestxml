Added CRUD with sqlite database.

09/24/24
1. added display textview for total count, total pieces sold and remaining pieces.
- in activity_main

2. added code in mainActivity to support displays.
3. Added condition for view, update, delete and Save.
4. adjust DATABASE_VERSION = 8
<<<<<<< HEAD
5. Added column to User class (totamount, tot_no_of_pcs_sold)


09/25/24
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


09/26/24
1. Enhance search and add item routine.
- save stock per bag # and stock count
- getUser/ search by lotID: String, bgNum: String
=======
5. Added column to User class (totamount, tot_no_of_pcs_sold)
>>>>>>> parent of 84e99a6 (Enhancement 04)

09/27/24
1. Removed Search and delete button.
2. replaced with search / delete icon.

09/27/24 - 2nd iteration
1. Removed Search and delete button.
2. replaced button with search / delete / report/ save/update/ clear and exit icon.
3. Added rounded_box.xml for lot # and Search box effect


10/06/24
1.Revise design of particular ei: total no of pcs sold, total amount, remaining count in activity_main.xml
2. Added progress bar.
> created dialog-progress and showProgressDialog function



10/08/24
1. on update
> include subtract/ minus and plus adjustments.

2. update activity_main for inputType from numberdecimal to text

3. number inputype to text

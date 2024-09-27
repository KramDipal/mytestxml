package eu.tutorials.mytestxml

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 15
        private const val DATABASE_NAME = "InventoryDatabase"
        private const val TABLE_USERS = "Inventory"
        private const val KEY_ID = "id"
        private const val KEY_LOT = "lot_number"
        private const val KEY_BAG = "bag_number"
        private const val KEY_RESDATE = "restock_date"
        private const val KEY_TOP = "total_no_of_pieces"
        private const val KEY_TXNDATE = "transaction_date"
        private const val KEY_AMT = "amount"
        private const val KEY_TOTAMT = "total_amount"
        private const val KEY_NOPS = "no_of_pcs_sold"
        private const val KEY_TNOPS = "tot_no_of_pcs_sold"
        private const val KEY_TOTINC = "total_income"
        private const val KEY_REMARKS = "remarks"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_USERS ($KEY_ID INTEGER PRIMARY KEY, " +
                "$KEY_LOT TEXT, $KEY_BAG TEXT, $KEY_RESDATE TEXT,$KEY_TOP TEXT,  $KEY_TXNDATE TEXT," +
                "$KEY_AMT TEXT, $KEY_TOTAMT TEXT, $KEY_NOPS TEXT, $KEY_TNOPS TEXT, $KEY_TOTINC TEXT, $KEY_REMARKS TEXT)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_LOT, user.lot_number)
            put(KEY_BAG, user.bag_number)
            put(KEY_RESDATE, user.restock_date)
            put(KEY_TOP, user.total_no_of_pieces)
            put(KEY_TXNDATE, user.transaction_date)
            put(KEY_AMT, user.amount)
            put(KEY_TOTAMT, user.totamount)
            put(KEY_NOPS, user.no_of_pcs_sold)
            put(KEY_TNOPS, user.tot_no_of_pcs_sold)
            put(KEY_TOTINC, user.total_income)
            put(KEY_REMARKS, user.remarks)
        }
        val success = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return success
    }

    //fun getUser(txnID: String, lotID: String, txnDate: String): User? {
    fun getUser(lotID: String): User? {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(TABLE_USERS, arrayOf(KEY_ID,
            KEY_LOT,
            KEY_BAG,
            KEY_RESDATE,
            KEY_TOP,
            KEY_TXNDATE,
            KEY_AMT,
            KEY_TOTAMT,
            KEY_NOPS,
            KEY_TNOPS,
            KEY_TOTINC,
            //KEY_REMARKS), "$KEY_ID=? AND $KEY_LOT=? AND $KEY_TXNDATE=?", arrayOf(txnID, lotID, txnDate), null, null, null)
            KEY_REMARKS), "$KEY_LOT=?", arrayOf(lotID), null, null, null)
        //cursor.moveToFirst()
        cursor.moveToLast()
        val user = User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11))
        cursor.close()
        return user
    }

    fun updateUser(user: User): Int {

        Log.i("updateUser", "${user.id}, ${user.lot_number}, ${user.bag_number}, ${user.restock_date}, ${user.total_no_of_pieces}")
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_LOT, user.lot_number)
            put(KEY_BAG, user.bag_number)
            put(KEY_RESDATE, user.restock_date)
            put(KEY_TOP, user.total_no_of_pieces)
            put(KEY_TXNDATE, user.transaction_date)
            put(KEY_AMT, user.amount)
            put(KEY_TOTAMT, user.totamount)
            put(KEY_NOPS, user.no_of_pcs_sold)
            put(KEY_TNOPS, user.tot_no_of_pcs_sold)
            put(KEY_TOTINC, user.total_income)
            put(KEY_REMARKS, user.remarks)
        }
        //val success = db.update(TABLE_USERS, contentValues, "$KEY_LOT=?", arrayOf(user.id.toString()))
        val success = db.update(TABLE_USERS, contentValues, "$KEY_LOT=? AND $KEY_TXNDATE=?", arrayOf(user.lot_number, user.transaction_date))
        db.close()
        return success
    }

    fun deleteUser(txnDate: String): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_USERS, "$KEY_TXNDATE=?", arrayOf(txnDate))
        db.close()
        return success
    }

    fun getAllUsers(): List<User> {

        val UsersList = ArrayList<User>()
        val selectQuery = "SELECT * FROM $TABLE_USERS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val person = User(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    lot_number = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOT)),
                    bag_number = cursor.getString(cursor.getColumnIndexOrThrow(KEY_BAG)),
                    restock_date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_RESDATE)),
                    total_no_of_pieces = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TOP)),
                    transaction_date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TXNDATE)),
                    amount = cursor.getString(cursor.getColumnIndexOrThrow(KEY_AMT)),
                    totamount = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TOTAMT)),
                    no_of_pcs_sold = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NOPS)),
                    tot_no_of_pcs_sold = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TNOPS)),
                    total_income = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TOTINC)),
                    remarks = cursor.getString(cursor.getColumnIndexOrThrow(KEY_REMARKS))

                )
                UsersList.add(person)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return UsersList
    }

    fun getByUsers(lotNum: String): List<User> {

        val UsersList = ArrayList<User>()
        val selectQuery = "SELECT * FROM $TABLE_USERS WHERE $KEY_LOT=$lotNum"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val person = User(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    lot_number = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOT)),
                    bag_number = cursor.getString(cursor.getColumnIndexOrThrow(KEY_BAG)),
                    restock_date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_RESDATE)),
                    total_no_of_pieces = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TOP)),
                    transaction_date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TXNDATE)),
                    amount = cursor.getString(cursor.getColumnIndexOrThrow(KEY_AMT)),
                    totamount = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TOTAMT)),
                    no_of_pcs_sold = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NOPS)),
                    tot_no_of_pcs_sold = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TNOPS)),
                    total_income = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TOTINC)),
                    remarks = cursor.getString(cursor.getColumnIndexOrThrow(KEY_REMARKS))

                )
                UsersList.add(person)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return UsersList
    }
}
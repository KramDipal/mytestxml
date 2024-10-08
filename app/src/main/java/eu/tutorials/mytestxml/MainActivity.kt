package eu.tutorials.mytestxml

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
//import eu.tutorials.mytestxml.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var mProgressDialog: Dialog


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val navController = findNavController(R.id.nav_host_fragment)
        //setupActionBarWithNavController(navController)



        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //val navController = navHostFragment.navController
        //setupActionBarWithNavController(navController)


        //data binding
        //val binding: FragmentFirstBinding = FragmentFirstBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        /*val btnExit :Button = findViewById(R.id.btnExit)
        btnExit.setOnClickListener {
            Toast.makeText(this, "Button Exit", Toast.LENGTH_LONG).show()
            finish() // Close the activity
            exitProcess(0) // Exit the application
        }*/
        //data binding




        databaseHelper = DatabaseHelper(this)
        //val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        //val bottomNavReport: BottomNavigationView = findViewById(R.id.nav_save)
        //bottomNavReport.background = ContextCompat.getDrawable(this, R.drawable.save)



        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        val btnDatePicker2 :Button = findViewById(R.id.btnDatePicker2)
        val btnExit :Button = findViewById(R.id.btnExit)
        val btnSave :Button = findViewById(R.id.btnSave)
        //val btnDelete :Button = findViewById(R.id.btnDelete)
        val btnUpdate :Button = findViewById(R.id.btnUpdate)
        //val btnView :Button = findViewById(R.id.btnView)
        val btnReport : Button = findViewById(R.id.btnReport)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnSearch: Button = findViewById(R.id.btnSearch)
        val btnDeleteRec: Button = findViewById(R.id.btnDeleteRec)
        val btnQRcode: Button = findViewById(R.id.btnQRcode)


        val editLotNumber: EditText = findViewById(R.id.editLotNumber)


        val editBagNumber: EditText = findViewById(R.id.editBagNumber)
        val editNoPieces: EditText = findViewById(R.id.editNoPieces)
        val editRestockDate: TextView = findViewById(R.id.editRestockDate)
        val editSaleDate: TextView = findViewById(R.id.editSaleDate)
        val editAmount: EditText = findViewById(R.id.editAmount)
        val editNoPiecesSold: EditText = findViewById(R.id.editNoPiecesSold)
        val editTotalIncome: EditText = findViewById(R.id.editTotalIncome)
        val editRemarks: EditText = findViewById(R.id.editRemarks)

        //Displays
        val editBalance: TextView = findViewById(R.id.editbalance)
        val editotnopcsold: TextView = findViewById(R.id.editotnopcsold)
        val editotamount: TextView = findViewById(R.id.editotamount)

        val editrxno: TextView = findViewById(R.id.editrxno)
        //editrxno.isEnabled = false

        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        /*
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {


                R.id.nav_report -> {
                    Toast.makeText(this,"Report button", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, ReportActivity::class.java)
                    startActivity(intent)
                    finish()
                    // Handle Home action
                    true
                }

                R.id.nav_save -> {
                    // Handle Dashboard action
                    Toast.makeText(this,"Save button", Toast.LENGTH_LONG).show()

                    //TODO - add computation here for total pcs sold and total amount
                    //TODO - need to have select query to get the latest pcs count and amount per LOT number.
                    val isEmptyFields = checkAllTextViews()
                    Log.d("isEmptyFields", isEmptyFields.toString())

                    if(!isEmptyFields) {

                        val totalAMount: Int = if(editotamount.text.isEmpty()){
                            editAmount.text.toString().toInt()
                        } else {
                            editotamount.text.toString().toInt() + editAmount.text.toString().toInt()
                        }

                        val totalNoOfPcSold: Int = if(editotnopcsold.text.isEmpty()){
                            editNoPiecesSold.text.toString().toInt()
                        }
                        else {
                            editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString().toInt()
                        }

                        Log.d("isEmptyFields/ HERE", isEmptyFields.toString())
                        try {
                            val user = User(
                                0, editLotNumber.text.toString(),
                                editBagNumber.text.toString(), editRestockDate.text.toString(),
                                editNoPieces.text.toString(), editSaleDate.text.toString(),
                                editAmount.text.toString(), totalAMount.toString(),
                                editNoPiecesSold.text.toString(), totalNoOfPcSold.toString(),
                                editTotalIncome.text.toString(), editRemarks.text.toString()
                            )
                            databaseHelper.addUser(user)

                            Toast.makeText(this, "Record saved", Toast.LENGTH_LONG).show()
                            clearAllTextViews()
                            editLotNumber.requestFocus() //By default cursor will be at lot number text field
                        } catch (e: Exception) {
                            println("An unexpected error occurred in adding a record: ${e.message}")
                        }

                    }else{
                        Log.d("isEmptyFields-ELSE", isEmptyFields.toString())
                    }
                    true
                }

                R.id.nav_update -> {
                    // Handle Notifications action
                    Toast.makeText(this,"Update button", Toast.LENGTH_LONG).show()
                    if(editLotNumber.text.isNotEmpty() && editSaleDate.text.isNotEmpty())
                    {
                        val grandTotPcsSold =
                            editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString()
                                .toInt()
                        val editotamountupdate =
                            editotamount.text.toString().toInt() + editAmount.text.toString().toInt()

                        val user = User(
                            0, editLotNumber.text.toString(),
                            editBagNumber.text.toString(), editRestockDate.text.toString(),
                            editNoPieces.text.toString(), editSaleDate.text.toString(),
                            editAmount.text.toString(), editotamountupdate.toString(),
                            editNoPiecesSold.text.toString(), grandTotPcsSold.toString(),
                            editTotalIncome.text.toString(), editRemarks.text.toString()
                        )


                        val inBalance = user.total_no_of_pieces.toInt() - user.tot_no_of_pcs_sold.toInt()


                        editotnopcsold.text = grandTotPcsSold.toString()
                        editBalance.text = inBalance.toString()
                        editotamount.text = editotamountupdate.toString()

                        /*TODO add computation here for total pcs sold*/

                        try {
                            databaseHelper.updateUser(user) // Example: update user with ID 1

                            Toast.makeText(this, "Record updated!", Toast.LENGTH_LONG).show()
                            editLotNumber.requestFocus() //By default cursor will be at lot number text field
                        } catch (e: Exception) {
                            println("An unexpected error occurred in updating a record: ${e.message}")
                        }

                    }
                    else{
                        Toast.makeText(this, "Lot number or Sales date fields are empty!", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                R.id.nav_clear -> {
                    Toast.makeText(this, "Button Clear", Toast.LENGTH_LONG).show()
                    clearAllTextViews()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field
                    true

                }

                R.id.nav_exit ->{
                    Toast.makeText(this, "Button Exit", Toast.LENGTH_LONG).show()
                    true
                    finish() // Close the activity
                    exitProcess(0) // Exit the application

                }
                else -> false


            }
        }
         */

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
        btnDatePicker2.setOnClickListener {
            clickDatePicker2()
        }


        btnSave.setOnClickListener {

            //TODO - add computation here for total pcs sold and total amount
            //TODO - need to have select query to get the latest pcs count and amount per LOT number.
            val isEmptyFields = checkAllTextViews()
            Log.d("isEmptyFields", isEmptyFields.toString())

            if(!isEmptyFields) {

                val totalAMount: Int = if(editotamount.text.isEmpty()){
                    editAmount.text.toString().toInt()
                } else {
                    editotamount.text.toString().toInt() + editAmount.text.toString().toInt()
                }

                val totalNoOfPcSold: Int = if(editotnopcsold.text.isEmpty()){
                    editNoPiecesSold.text.toString().toInt()
                }
                else {
                   editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString().toInt()
                }

                Log.d("isEmptyFields/ HERE", isEmptyFields.toString())
                try {
                    val user = User(
                        0, editLotNumber.text.toString(),
                        editBagNumber.text.toString(), editRestockDate.text.toString(),
                        editNoPieces.text.toString(), editSaleDate.text.toString(),
                        editAmount.text.toString(), totalAMount.toString(),
                        editNoPiecesSold.text.toString(), totalNoOfPcSold.toString(),
                        editTotalIncome.text.toString(), editRemarks.text.toString()
                    )
                    databaseHelper.addUser(user)

                    Toast.makeText(this, "Record saved", Toast.LENGTH_LONG).show()
                    clearAllTextViews()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field
                } catch (e: Exception) {
                    println("An unexpected error occurred in adding a record: ${e.message}")
                }

            }else{
                Log.d("isEmptyFields-ELSE", isEmptyFields.toString())
            }

        }


        //btnDelete.setOnClickListener {
        btnDeleteRec.setOnClickListener {

            Log.d("editLotNumber", "${editLotNumber.text} ${editSaleDate.text}")

            if (editLotNumber.text.isNotEmpty()) {

                Log.d("editLotNumber", "Is not empty!!!")

                try {
                    val grandTotPcsSold =
                        editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString()
                            .toInt()
                    val editotamountupdate =
                        editotamount.text.toString().toInt() + editAmount.text.toString().toInt()

                    val inBalance = editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString().toInt()

                    editotnopcsold.text = grandTotPcsSold.toString()
                    editBalance.text = inBalance.toString()
                    editotamount.text = editotamountupdate.toString()

                    //databaseHelper.deleteUser(editLotNumber.text.toString(), editSaleDate.text.toString()) // Example: Delete user with ID 1
                    databaseHelper.deleteUser(editSaleDate.text.toString()) // Example: Delete user with ID 1
                    Toast.makeText(this, "Button Delete", Toast.LENGTH_LONG).show()
                    clearAllTextViews()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field


                } catch (e: Exception) {
                    println("An unexpected error occurred in deleting a record: ${e.message}")
                    Toast.makeText(this, "Record not found, please check Lot number and transaction date.", Toast.LENGTH_LONG).show()
                }


            }else{
                Toast.makeText(this, "Please enter Lot Number and Check Transaction Date", Toast.LENGTH_LONG).show()
            }
        }


        //Update by Lot number and Transaction date
        btnUpdate.setOnClickListener {


            if(editLotNumber.text.isNotEmpty() && editSaleDate.text.isNotEmpty())
            {


                val savedValue = sharedPreferences.getInt("savedValue", 0)
                val savedValueAmount = sharedPreferences.getInt("savedValueAmount", 0)

                Log.i("btnUpdate/editNoPiecesSold", "editNoPiecesSold: ${editNoPiecesSold.text} " +
                        "savedValue: $savedValue " +
                        "savedValueAmount: $savedValueAmount")


                //For PCS total count - check for '-' sign
                val str = editNoPiecesSold.text
                val firstByteIsNegative = str.contains('-')

                //For AMOUNT Total - check for '-' sign
                val str2 = editAmount.text
                val firstByteIsNegative2 = str2.contains('-')

                var editotamountupdate: Int
                var grandTotPcsSold: Int
                var originalString: String? = null
                var positiveString: String? = null
                var originalString2: String? = null
                var positiveString2: String? = null
                var newSalesAmountValue = 0
                var newNOPValue = 0

                //Log.i("btnUpdate/firstByte", "$firstByteIsNegative2 $firstByteIsNegative")


                editotamountupdate = if(firstByteIsNegative2) {
                    originalString = editAmount.text.toString()
                    positiveString = originalString.replace("-", "")

                    editotamount.text.toString().toInt() - positiveString.toInt()
                } else{
                    editotamount.text.toString().toInt() + editAmount.text.toString().toInt()
                }


                grandTotPcsSold = if(firstByteIsNegative){
                    originalString2 = editNoPiecesSold.text.toString()
                    positiveString2 = originalString2.replace("-", "")

                    editotnopcsold.text.toString().toInt() - positiveString2.toInt()

                }else{
                    editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString().toInt()
                }

                /*TODO - this is for less/ minus adjustment*/
                if(firstByteIsNegative2) {
                    //Assigned new value to editNoPiecesSold, this value will be then save to colum
                    // "amount" as its new value
                    newSalesAmountValue = savedValueAmount - (positiveString?.toInt() ?: 0)
                    editAmount.setText(newSalesAmountValue.toString())
                }
                else{
                    newSalesAmountValue = savedValueAmount + editAmount.text.toString().toInt()
                    editAmount.setText(newSalesAmountValue.toString())
                }

                if(firstByteIsNegative) {
                    //Assigned new value to editNoPiecesSold, this value will be then save to colum
                    // "no_of_pcs_sold" as its new value
                    newNOPValue = savedValue - (positiveString2?.toInt() ?: 0)
                    editNoPiecesSold.setText(newNOPValue.toString())
                }else{
                    newNOPValue = savedValue + editNoPiecesSold.text.toString().toInt()
                    editNoPiecesSold.setText(newNOPValue.toString())
                }
                /*TODO - this is for less/ minus adjustment*/

                val user = User(
                    0, editLotNumber.text.toString(),
                    editBagNumber.text.toString(), editRestockDate.text.toString(),
                    editNoPieces.text.toString(), editSaleDate.text.toString(),
                    editAmount.text.toString(), editotamountupdate.toString(),
                    editNoPiecesSold.text.toString(), grandTotPcsSold.toString(),
                    editTotalIncome.text.toString(), editRemarks.text.toString()
                )

                //To get the Remaining pcs: value
                val inBalance = user.total_no_of_pieces.toInt() - user.tot_no_of_pcs_sold.toInt()

                //Total no. of pcs sold:
                editotnopcsold.text = grandTotPcsSold.toString()

                //Pass inBalance to EditText View (Remaining pcs:)
                editBalance.text = inBalance.toString()

                editotamount.text = editotamountupdate.toString()

                /*TODO add computation here for total pcs sold*/

                try {
                    databaseHelper.updateUser(user) // Example: update user with ID 1

                    Toast.makeText(this, "Record updated!", Toast.LENGTH_LONG).show()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field
                } catch (e: Exception) {
                    println("An unexpected error occurred in updating a record: ${e.message}")
                }


            }
            else{
                Toast.makeText(this, "Lot number or Sales date fields are empty!", Toast.LENGTH_LONG).show()
            }

        }

        btnQRcode.setOnClickListener {
            Toast.makeText(this, "Qrcode Butoon", Toast.LENGTH_LONG).show()
        }



        //Search / View by Lot number, cursor.moveToLast will return the last transaction
        //btnView.setOnClickListener {
        btnSearch.setOnClickListener{

            //if(editLotNumber.text.isNotEmpty() && editrxno.text.isNotEmpty()) {
            if(editLotNumber.text.isNotEmpty()) {

                showProgressDialog()

                Log.i("btnView","${editrxno.text.toString()} ${editLotNumber.text.toString()} ${editSaleDate.text.toString()}")
                try {
                    val user =
                        //databaseHelper.getUser(editrxno.text.toString(), editLotNumber.text.toString(), editSaleDate.text.toString()) // Example: Get user with ID 1
                        databaseHelper.getUser(editLotNumber.text.toString()) // Example: Get user with ID 1

                    //Saved the current value assigned to editNoPiecesSold.text
                    //Later use it to add/ subtract to a new value assigned editNoPiecesSold.text
                    val valueToSave = user?.no_of_pcs_sold.toString().toInt()
                    editor.putInt("savedValue", valueToSave)
                    editor.apply()

                    val valueToSaveAmount = user?.amount.toString().toInt()
                    editor.putInt("savedValueAmount", valueToSaveAmount)
                    editor.apply()


                    editLotNumber.setText(user?.lot_number)
                    editBagNumber.setText(user?.bag_number)
                    editNoPieces.setText(user?.total_no_of_pieces)
                    editRestockDate.text = user?.restock_date
                    editSaleDate.text = user?.transaction_date
                    //editrxno.setText(user!!.id)
                    editAmount.setText(user!!.amount)
                    editNoPiecesSold.setText(user.no_of_pcs_sold)
                    editotnopcsold.text = user.tot_no_of_pcs_sold
                    editTotalIncome.setText(user.total_income)
                    editRemarks.setText(user.remarks)

                    val inBalance =
                        user.total_no_of_pieces.toInt() - user.tot_no_of_pcs_sold.toInt()


                    editotnopcsold.text = user.tot_no_of_pcs_sold
                    editBalance.text = inBalance.toString()
                    editotamount.text = user.totamount






                    Toast.makeText(this, "Record found!", Toast.LENGTH_LONG).show()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field
                } catch (e: Exception) {
                    println("An unexpected error occurred in Viewing a record: ${e.message}")
                    Toast.makeText(this, "No record found!", Toast.LENGTH_LONG).show()
                }
                //hideProgressDialog()
            }else{
                //Toast.makeText(this, "One of the following fields is empty (Lot # | Transaction ID | Transaction date)", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Please enter a LOT number", Toast.LENGTH_LONG).show()
            }

        }


        btnReport.setOnClickListener {
            Toast.makeText(this, "Button Report", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
            finish()
        }


        btnExit.setOnClickListener {
            Toast.makeText(this, "Button Exit", Toast.LENGTH_LONG).show()
            finish() // Close the activity
            exitProcess(0) // Exit the application
        }

        btnClear.setOnClickListener {
            Toast.makeText(this, "Button Clear", Toast.LENGTH_LONG).show()
            clearAllTextViews()
            editLotNumber.requestFocus() //By default cursor will be at lot number text field

        }



    }



    /*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    */

    private fun clickDatePicker(){
        /**
         * This Gets a calendar using the default time zone and locale.
         * The calender returned is based on the current time
         * in the default time zone with the default.
         */
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        //get the id of the textviews from the layout
        val tvSelectedDate:TextView = findViewById(R.id.editSaleDate)

        /**
         * Creates a new date picker dialog for the specified date using the parent
         * context's default date picker dialog theme.
         */
        val dpd = DatePickerDialog(this,{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
            /**
             * The listener used to indicate the user has finished selecting a date.
             */

            /**
             * The listener used to indicate the user has finished selecting a date.
             */

            /**
             *Here the selected date is set into format i.e : day/Month/Year
             * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
             * */
            /**
             *Here the selected date is set into format i.e : day/Month/Year
             * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
             * */
            //val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            val selectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"

            Log.i("selectedDate", selectedDate)
            // Selected date it set to the TextView to make it visible to user.
            tvSelectedDate.text = selectedDate

        },
            year,month,day)

        /**
         * Sets the maximal date supported by this in
         * milliseconds since January 1, 1970 00:00:00 in time zone.
         *
         * @param maxDate The maximal supported date.
         */
        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user from selecting today and future day.
        dpd.datePicker.maxDate = System.currentTimeMillis()// - 86400000
        dpd.show()

    }
    private fun clickDatePicker2(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        val tvRestockDate:TextView = findViewById(R.id.editRestockDate)

        val dpd = DatePickerDialog(this,{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"
            tvRestockDate.text = selectedDate

        },
            year,month,day)

        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user from selecting today and future day.
        dpd.datePicker.maxDate = System.currentTimeMillis()// - 86400000
        dpd.show()

    }

    private fun clearAllTextViews() {
        val textViewIds = listOf(R.id.editLotNumber, R.id.editRestockDate,
            R.id.editBagNumber, R.id.editNoPieces, R.id.editotnopcsold, R.id.editbalance,
            R.id.editotamount, R.id.editSaleDate, R.id.editrxno, R.id.editAmount, R.id.editNoPiecesSold,
            R.id.editTotalIncome, R.id.editRemarks)
        for (id in textViewIds) {
            val textView: TextView = findViewById(id)
            textView.text = ""
        }
    }

    private fun checkAllTextViews() : Boolean
    {
        var isEmptyFields: Boolean = false
        val textViewIds = listOf(R.id.editLotNumber, R.id.editRestockDate,
            R.id.editBagNumber, R.id.editNoPieces, R.id.editSaleDate,
            R.id.editAmount, R.id.editNoPiecesSold, R.id.editRemarks)
        for (id in textViewIds) {
            val textView: TextView = findViewById(id)

            if(textView.text.isEmpty()){
                  Toast.makeText(this, "Text Fields cannot be empty! ${textView.text}", Toast.LENGTH_LONG).show()
                isEmptyFields = true

            }
        }

        return isEmptyFields

    }

    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    fun showProgressDialog() {

        //val dialogProg = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null)
        mProgressDialog = Dialog(this)

        //val tv_progress_text = dialogProg.findViewById<TextView>(R.id.tv_progress_text)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog.setContentView(R.layout.dialog_progress)

        //mProgressDialog.findViewById<TextView>(R.id.tv_progress_text)

        // Simulate loading with a delay
        Thread {
            try {
                Thread.sleep(2000) // Simulate a long task
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            mProgressDialog.dismiss()
        }.start()

        //Start the dialog and display it on screen.
        mProgressDialog.show()
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }
}





package eu.tutorials.mytestxml

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.mytestxml.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    private lateinit var databaseHelper: DatabaseHelper


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


        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        val btnDatePicker2 :Button = findViewById(R.id.btnDatePicker2)
        val btnExit :Button = findViewById(R.id.btnExit)
        val btnSave :Button = findViewById(R.id.btnSave)
        val btnDelete :Button = findViewById(R.id.btnDelete)
        val btnUpdate :Button = findViewById(R.id.btnUpdate)
        val btnView :Button = findViewById(R.id.btnView)
        val btnReport : Button = findViewById(R.id.btnReport)
        val btnClear: Button = findViewById(R.id.btnClear)


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

                /*val totalAMount: Int = if(editotamount.text.isEmpty()){
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
                */

                Log.d("isEmptyFields/ HERE", isEmptyFields.toString())
                try {
                    /*val user = User(
                        0, editLotNumber.text.toString(),
                        editBagNumber.text.toString(), editRestockDate.text.toString(),
                        editNoPieces.text.toString(), editSaleDate.text.toString(),
                        editAmount.text.toString(), totalAMount.toString(),
                        editNoPiecesSold.text.toString(), totalNoOfPcSold.toString(),
                        editTotalIncome.text.toString(), editRemarks.text.toString()
                    )*/

                    val user = User(
                    0, editLotNumber.text.toString(),
                    editBagNumber.text.toString(), editRestockDate.text.toString(),
                    editNoPieces.text.toString(), null,
                        null, null,
                        null, null,
                        null, null)
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
        btnDelete.setOnClickListener {

            if (editLotNumber.text.isNotEmpty()) {

                try {
                    databaseHelper.deleteUser(editLotNumber.text.toString(), editBagNumber.text.toString()) // Example: Delete user with ID 1
                    Toast.makeText(this, "Record Delete", Toast.LENGTH_LONG).show()
                    clearAllTextViews()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field
                } catch (e: Exception) {
                    println("An unexpected error occurred in deleting a record: ${e.message}")
                }


            }else{
                Toast.makeText(this, "Please enter Lot Number and Bag Number", Toast.LENGTH_LONG).show()
            }
        }


        //Update by Lot number and Transaction date
        btnUpdate.setOnClickListener {

            if(editLotNumber.text.isNotEmpty() && editSaleDate.text.isNotEmpty())
            {
                /*
                val grandTotPcsSold =
                    editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString()
                        .toInt()
                val editotamountupdate =
                    editotamount.text.toString().toInt() + editAmount.text.toString().toInt()
                */

                val grandTotPcsSold: Int = if(editotnopcsold.text.isEmpty()){
                    editNoPiecesSold.text.toString().toInt()
                } else {
                    editNoPiecesSold.text.toString().toInt() + editotnopcsold.text.toString()
                        .toInt()
                }

                val editotamountupdate: Int = if(editotamount.text.isEmpty()){
                    editAmount.text.toString().toInt()
                } else {
                    editotamount.text.toString().toInt() + editAmount.text.toString().toInt()
                }



                    val user = User(
                    0, editLotNumber.text.toString(),
                    editBagNumber.text.toString(), editRestockDate.text.toString(),
                    editNoPieces.text.toString(), editSaleDate.text.toString(),
                    editAmount.text.toString(), editotamountupdate.toString(),
                    editNoPiecesSold.text.toString(), grandTotPcsSold.toString(),
                    editTotalIncome.text.toString(), editRemarks.text.toString()
                )


                val inBalance = user.total_no_of_pieces.toInt() - user.tot_no_of_pcs_sold!!.toInt()


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
        }

        //Search / View by Lot number, cursor.moveToLast will return the last transaction
        btnView.setOnClickListener {

            //if(editLotNumber.text.isNotEmpty() && editrxno.text.isNotEmpty()) {
            if(editLotNumber.text.isNotEmpty() && editBagNumber.text.isNotEmpty()) {

                //Log.i("btnView","${editrxno.text.toString()} ${editLotNumber.text.toString()} ${editSaleDate.text.toString()}")
                try {
                    val user =
                        //databaseHelper.getUser(editrxno.text.toString(), editLotNumber.text.toString(), editSaleDate.text.toString()) // Example: Get user with ID 1
                        databaseHelper.getUser(editLotNumber.text.toString(), editBagNumber.text.toString()) // Example: Get user with ID 1


                   //Log.i("user.toString().length", "${user.toString().length}")



                    //editLotNumber.setText(user?.lot_number)
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
                        user.total_no_of_pieces.toInt() - user.tot_no_of_pcs_sold!!.toInt()


                    editotnopcsold.text = user.tot_no_of_pcs_sold
                    editBalance.text = inBalance.toString()
                    editotamount.text = user.totamount


                    Toast.makeText(this, "Record found!", Toast.LENGTH_LONG).show()
                    editLotNumber.requestFocus() //By default cursor will be at lot number text field
                } catch (e: Exception) {
                    println("An unexpected error occurred in Viewing a record: ${e.message}")
                    Toast.makeText(this, "No Sales record found!", Toast.LENGTH_LONG).show()
                }
            }else{
                //Toast.makeText(this, "One of the following fields is empty (Lot # | Transaction ID | Transaction date)", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Missing LOT number | Bag NUmber", Toast.LENGTH_LONG).show()
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
        var isEmptyFields = false
        /*val textViewIds = listOf(R.id.editLotNumber, R.id.editRestockDate,
            R.id.editBagNumber, R.id.editNoPieces, R.id.editSaleDate,
            R.id.editAmount, R.id.editNoPiecesSold, R.id.editRemarks)*/

        val textViewIds = listOf(R.id.editLotNumber, R.id.editRestockDate,
        R.id.editBagNumber)

        for (id in textViewIds) {
            val textView: TextView = findViewById(id)

            if(textView.text.isEmpty()){
                  Toast.makeText(this, "Text Fields cannot be empty! ${textView.text}", Toast.LENGTH_LONG).show()
                isEmptyFields = true

            }
        }

        return isEmptyFields

    }
}





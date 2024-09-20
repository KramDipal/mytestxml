package eu.tutorials.mytestxml

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker :Button = findViewById(R.id.btnDatePicker)
        val btnExit :Button = findViewById(R.id.btnExit)
        val btnSave :Button = findViewById(R.id.btnSave)
        val btnClear: Button = findViewById(R.id.btnClear)

        var editLotNumber: EditText = findViewById(R.id.editLotNumber)
        var editBagNumber: EditText = findViewById(R.id.editBagNumber)
        var editNoPieces: EditText = findViewById(R.id.editNoPieces)
        var editSaleDate: TextView = findViewById(R.id.editSaleDate)
        var editAmount: EditText = findViewById(R.id.editAmount)
        var editNoPiecesSold: EditText = findViewById(R.id.editNoPiecesSold)
        var editTotalIncome: EditText = findViewById(R.id.editTotalIncome)
        var editRemarks: EditText = findViewById(R.id.editRemarks)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }

        btnSave.setOnClickListener {
            Toast.makeText(this, "Button Save", Toast.LENGTH_LONG).show()
        }

        btnExit.setOnClickListener {
            Toast.makeText(this, "Button Exit", Toast.LENGTH_LONG).show()
            finish() // Close the activity
            exitProcess(0) // Exit the application
        }

        btnClear.setOnClickListener {
            Toast.makeText(this, "Button Clear", Toast.LENGTH_LONG).show()
            editLotNumber.text = null
            editBagNumber.text = null
            editNoPieces.text = null
            editSaleDate.text = null
            editAmount.text = null
            editNoPiecesSold.text = null
            editTotalIncome.text = null
            editRemarks.text = null
        }
    }
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
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}



package eu.tutorials.mytestxml

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class ReportActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report2)

        databaseHelper = DatabaseHelper(this)


        val btnGenerate : Button = findViewById(R.id.btnGenerate)
        val btnGenerate2: Button = findViewById(R.id.btnGenerate2)
        val btnHome : Button = findViewById(R.id.btnHome)
        val listView: ListView = findViewById(R.id.listView)

        val editLotNumber: EditText = findViewById(R.id.repLotNum)



        //Get records by lot number
        btnGenerate.setOnClickListener {
            Toast.makeText(this, "Generate Button clicked!", Toast.LENGTH_LONG).show()

            val usersList = databaseHelper.getByUsers(editLotNumber.text.toString())
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                usersList.map { "ID: ${it.id}, Lot Number: ${it.lot_number} " +
                        "Bag Number: ${it.bag_number}, Restock Date: ${it.restock_date}, " +
                        "New Stock Total: ${it.total_no_of_pieces}, Transaction Date: ${it.transaction_date}, " +
                        "Amount: ${it.amount}, Total Amount: ${it.totamount}, " +
                        "Total num of pcs sold per day: ${it.no_of_pcs_sold}, Total num of pcs sold: ${it.tot_no_of_pcs_sold}, " +
                        "Total Revenue: ${it.total_income}, Remarks: ${it.remarks} "})
            listView.adapter = adapter
        }

        //Get ALL records
        btnGenerate2.setOnClickListener {
            val usersList = databaseHelper.getAllUsers()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                usersList.map { "ID: ${it.id}, Lot Number: ${it.lot_number} " +
                        "Bag Number: ${it.bag_number}, Restock Date: ${it.restock_date}, " +
                        "New Stock Total: ${it.total_no_of_pieces}, Transaction Date: ${it.transaction_date}, " +
                        "Amount: ${it.amount}, Total Amount: ${it.totamount}, " +
                        "Total num of pcs sold per day: ${it.no_of_pcs_sold}, Total num of pcs sold: ${it.tot_no_of_pcs_sold}, " +
                        "Total Revenue: ${it.total_income}, Remarks: ${it.remarks} "})
            listView.adapter = adapter
        }

        btnHome.setOnClickListener {
            Toast.makeText(this, "Button Home", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
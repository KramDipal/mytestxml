package eu.tutorials.mytestxml

data class User(val id: Int,
                val lot_number: String,
                val bag_number: String,
                val restock_date: String,
                val total_no_of_pieces: String,
                val transaction_date: String?,
                val amount: String?,
                val totamount: String?,
                val no_of_pcs_sold: String?,
                val tot_no_of_pcs_sold: String?,
                val total_income: String?,
                val remarks: String?)


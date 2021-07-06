package eu.example.myageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnDatePicker: Button = findViewById(R.id.btnDatePicker  ) as Button
        btnDatePicker.setOnClickListener{ view ->
            clickDatePicker(view)
        }

    }

    fun  clickDatePicker(view: View){

        var cal = Calendar.getInstance()
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val year = cal.get(Calendar.YEAR)

        //defining the listener to use

        var DatePickerListener = DatePickerDialog.OnDateSetListener()
        { selectedView, selectedYear, selectedMonth, selectedDayofMonth
            ->
            //get textview and set the date selected
            var tvSelectedDate = findViewById(R.id.tvSelectedDate) as TextView
            var selectedDate = "${selectedDayofMonth}/${selectedMonth}/${selectedYear}"
            tvSelectedDate.setText(selectedDate)
            //call function normaldatetominutes and establish date to minutes in other text view
            val minutes = normalDateToMinutesDate(
                 Date( selectedYear, selectedMonth, selectedDayofMonth) ,
                Date(day, month, year))
            var tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes) as TextView
            tvSelectedDateInMinutes.text = minutes.toString()

        }

        Locale.ENGLISH
        DatePickerDialog(this,DatePickerListener , year, month, day ).show()
    }

    private fun normalDateToMinutesDate(startDate: Date, endDate: Date): Long{
        return  ((startDate.getTime() - endDate.getTime()) / 1000) / 60
    }


}

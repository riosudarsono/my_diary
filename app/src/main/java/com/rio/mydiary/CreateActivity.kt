package com.rio.mydiary

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_create.*
import java.text.SimpleDateFormat
import java.util.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        iv_back.setOnClickListener { onBackPressed() }
        btn_save.setOnClickListener {
            onSave()
        }
        edt_date.setOnClickListener { datePicker(edt_date) }
    }

    private fun datePicker(edt: EditText) {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = monthOfYear
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            edt.setText(dateFormat.format(calendar.time))
        }
        DatePickerDialog(this, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun onSave(){
        val name = edt_name.text.toString()
        val date = edt_date.text.toString()
        val story = edt_story.text.toString()

        val intent = Intent()
        intent.putExtra("name", name)
        intent.putExtra("date", date)
        intent.putExtra("story", story)
        setResult(RESULT_OK, intent)
        finish()
    }
}
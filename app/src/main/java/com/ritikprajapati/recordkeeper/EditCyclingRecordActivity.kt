package com.ritikprajapati.recordkeeper

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.edit
import com.ritikprajapati.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditCyclingRecordBinding
    private val cyclingPreferences: SharedPreferences by lazy {getSharedPreferences("cycling", Context.MODE_PRIVATE)}
    private val height: String? by lazy { intent.getStringExtra("Height")}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val height = intent.getStringExtra("Height")

        binding.buttonSave.setOnClickListener {
            saveRecord(height)
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        cyclingPreferences.edit {
            remove("$height Record")
            remove("$height Date")
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveRecord(height: String?) {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        val  cyclingPreferences = getSharedPreferences("cycling", Context.MODE_PRIVATE)

        cyclingPreferences.edit {
            putString("$height Record", record)
            putString("$height Date", date)
        }
    }
}
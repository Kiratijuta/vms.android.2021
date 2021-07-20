package vms.android.secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra("NAME")
        resultText.setText(name)

        saveButton.setOnClickListener {
            val data = Intent()
            data.putExtra("AGE", inputAge.text.toString())
            setResult(Activity.RESULT_OK, data)
        }

    }

}
package vms.android.secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE_SECONDACT = 101

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToSecond.setOnClickListener {
            val name = inputName.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NAME", name)

//            startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_SECONDACT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == REQUEST_CODE_SECONDACT) {
            textView.text = "You are ${data?.getStringExtra("AGE")} years old"
        }
    }

}
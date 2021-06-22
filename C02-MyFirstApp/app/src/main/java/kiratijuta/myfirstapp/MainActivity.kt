package kiratijuta.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // messageTextView.text = "Yay! from onCreate"

        clickMeButton.setOnClickListener {
            var firstname = firstnameEditText.text.toString()
            var lastname = lastnameEditText.text.toString()
            var fullname = firstname + " " + lastname

            var greetingMessage = "Hello ${ fullname }" // "Hello " + fullname

            resultTextView.text = greetingMessage

//            var t = Toast.makeText(this, greetingMessage, Toast.LENGTH_SHORT)
//            t.show()
        }

    }

}
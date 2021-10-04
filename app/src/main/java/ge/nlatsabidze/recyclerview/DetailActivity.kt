package ge.nlatsabidze.recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.nlatsabidze.recyclerview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val firstKeyName: String? = intent.getStringExtra("Key")
//        binding.textView.text = firstKeyName
        binding.btnChange.setOnClickListener {
            val data = Intent()
            data.putExtra("Key", binding.etChange.text.toString())
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}
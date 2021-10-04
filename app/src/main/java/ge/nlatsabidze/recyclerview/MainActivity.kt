package ge.nlatsabidze.recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList: ArrayList<Data>
    private lateinit var secondList: ArrayList<Second>

    lateinit var imageId: ArrayList<Int>
    lateinit var heading: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageId = mutableListOf<Int>(R.mipmap.ic_launcher, R.drawable.ic_launcher_background, R.mipmap.ic_launcher, R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round ) as ArrayList<Int>
        heading = mutableListOf<String>("name", "surname", "lastname", "firstname", "secondname", "secondlast", "last").toTypedArray()

        newRecyclerView = binding.recyclerView
        newRecyclerView.layoutManager = GridLayoutManager(this, 3)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = ArrayList<Data>()
        secondList = ArrayList<Second>()

        getUserData()


    }

    private fun getUserData() {

        val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val changedTitle = result.data!!.getStringExtra("Key")
            }
        }

        val adapter = MyAdapter(newArrayList, secondList)

        for (i in heading.indices) {
            val news = Data(imageId[i], heading[i])
            adapter.add(news)
        }




        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                goToSecondActivity(position, result)
            }

            override fun onLongClick(position: Int) {
                adapter.remove(position)
            }
        })

        binding.btnAdd.setOnClickListener {
            val news = Data(R.drawable.ic_launcher_foreground, "New Image")
            adapter.add(news)
        }
    }



    private fun goToSecondActivity(position: Int, result: ActivityResultLauncher<Intent>) {

        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra("Key", newArrayList[position].heading)
        result.launch(intent)
    }
}
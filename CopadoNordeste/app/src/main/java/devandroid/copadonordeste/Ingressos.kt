package devandroid.copadonordeste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Ingressos : AppCompatActivity() {

    private lateinit var tcRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var torcedorList: ArrayList<dbTorcedor>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingressos)

        tcRecyclerView = findViewById(R.id.listTorcedores)
        tcRecyclerView.layoutManager = LinearLayoutManager(this)
        tcRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        torcedorList = arrayListOf<dbTorcedor>()

        getTorcedorData()
    }

    private fun getTorcedorData() {
        tcRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Torcedor")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                torcedorList.clear()
                if(snapshot.exists()) {
                    for(empSnap in snapshot.children) {
                        val dData =empSnap.getValue(dbTorcedor::class.java)
                        torcedorList.add(dData!!)
                    }
                    val vAdapter = appAdapter(torcedorList)
                    tcRecyclerView.adapter = vAdapter

                    tcRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
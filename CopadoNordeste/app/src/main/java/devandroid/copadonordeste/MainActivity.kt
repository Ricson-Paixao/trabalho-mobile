package devandroid.copadonordeste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import devandroid.copadonordeste.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nome = binding.FormNome
        var cpf = binding.FormCPF

        dbRef = FirebaseDatabase.getInstance().getReference("Torcedor")

        binding.btComprar.setOnClickListener {

            binding.btComprar.setOnClickListener {

                val fnome = binding.FormNome.text.toString()
                val fcpf = binding.FormCPF.text.toString()


                if(fnome.isNotEmpty() && fcpf.isNotEmpty()) {

                    val userId = dbRef.push().key!!

                    val dadosTorcedor = dbTorcedor(fnome,fcpf)


                    dbRef.child(userId).setValue(dadosTorcedor).addOnCompleteListener{
                        Toast.makeText(this, "INGRESSO COMPRADO", Toast.LENGTH_SHORT).show()

                        nome.text.clear()
                        cpf.text.clear()

                    }.addOnFailureListener{err ->
                        Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                    }
                }
                val intent = Intent(this, Ingressos::class.java)
                startActivity(intent)
            }
        }
    }
}
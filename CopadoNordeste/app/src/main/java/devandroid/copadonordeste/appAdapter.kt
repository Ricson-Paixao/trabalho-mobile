package devandroid.copadonordeste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class appAdapter(private val torcedorList: ArrayList<dbTorcedor>) :
    RecyclerView.Adapter<appAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val torcedorNome : TextView = itemView.findViewById(R.id.cardTorcedor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ingressos_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNome = torcedorList[position]
        holder.torcedorNome.text = currentNome.userNome
    }

    override fun getItemCount(): Int {
        return torcedorList.size
    }


}
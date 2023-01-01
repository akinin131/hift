package quiz.example.hift

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import quiz.example.hift.databinding.FragmentSecondBinding
import quiz.example.weather.adapter.Adapter
import quiz.example.weather.model.Model

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var bunding: FragmentSecondBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter
    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bunding = FragmentSecondBinding.inflate(inflater, container, false)
        return bunding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }
    private fun init() {
        var viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDataBase()
        recyclerView = bunding.rvNotes
        adapter = Adapter()
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->

            adapter.setList(listNotes.asReversed())
        }

    }

  /*  override fun onDestroyView() {
        super.onDestroyView()
        bunding = null
    }*/
    companion object{
        fun clickNote(noteModel: Model){
            val bundle = Bundle()
            bundle.putSerializable("note",noteModel)
        }
    }
}
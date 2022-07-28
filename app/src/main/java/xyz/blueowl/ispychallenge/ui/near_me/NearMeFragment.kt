package xyz.blueowl.ispychallenge.ui.near_me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.blueowl.ispychallenge.data.repository.DataRepository
import xyz.blueowl.ispychallenge.databinding.FragmentNearMeBinding
import xyz.blueowl.ispychallenge.extensions.requireISpyApplication
import xyz.blueowl.ispychallenge.ui.data_browser.DataBrowserFragment
import xyz.blueowl.ispychallenge.ui.data_browser.DataBrowserViewModel
import xyz.blueowl.ispychallenge.ui.data_browser.adapter_items.NewAdapter

class NearMeFragment : Fragment() {

    private var _binding: FragmentNearMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val newAdapter = NewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory =
            NearMeViewModelFactory(requireISpyApplication().dataRepository)
        val homeViewModel =
            ViewModelProvider(this,factory).get(NearMeViewModel::class.java)

        _binding = FragmentNearMeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.list.observe(viewLifecycleOwner) {
            newAdapter.setData(it)
        }

        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = newAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class NearMeViewModelFactory(
        private val dataRepository: DataRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NearMeViewModel::class.java)){
                return NearMeViewModel(dataRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
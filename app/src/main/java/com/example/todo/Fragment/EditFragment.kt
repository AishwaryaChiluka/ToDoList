package com.example.todo.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.MainActivity
import com.example.todo.Note
import com.example.todo.R
import com.example.todo.databinding.FragmentEditBinding
import com.example.todo.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {
    lateinit var notesViewModel: NoteViewModel
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private  val args by navArgs<EditFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.apply {
            title = ""
            setTitle("")
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        setViewModel()
        setValues()
    }

    private fun setValues() {
        val updatedTime: Date = Calendar.getInstance().getTime()
        val formatter = SimpleDateFormat("dd/M/yyyy");
        _binding!!.idEdtNoteName.setText(args.updateNote.noteTitle)
        _binding!!.idEdtNoteDesc.setText(args.updateNote.noteDescription)
        _binding!!.idupdateBtn.setOnClickListener {
            var note = Note(_binding!!.idEdtNoteName.text.toString(),
                _binding!!.idEdtNoteDesc.text.toString(),
                 args.updateNote.createdBy,
                formatter.format(updatedTime).toString(),
                args.updateNote.id)
            notesViewModel.updateNote(note)
            Toast.makeText(requireContext(),"updated Successfully ",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_notesFragment)
        }
    }

    private fun setViewModel() {
        notesViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
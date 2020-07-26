package ru.mkedonsky.myappbykotlin.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.mkedonsky.myappbykotlin.R
import ru.mkedonsky.myappbykotlin.common.getColorInt
import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.ui.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

    companion object {
        const val EXTRA_NOTE = "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) =
            Intent(context, NoteActivity::class.java).run {
                putExtra(EXTRA_NOTE, noteId)
                context.startActivity(this)
            }
    }

    private var note: Note? = null
    override val layoutRes: Int = R.layout.activity_note
    override val viewModel: NoteViewModel by viewModel()

    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let {
            if (savedInstanceState == null) {
                viewModel.loadNote(it)
            }
        } ?: let {
            supportActionBar?.title = getString(R.string.note_new)
            initView()
        }
    }

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) finish()

        this.note = data.note
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: let {
            getString(R.string.note_new)
        }

        initView()
    }

    private fun initView() {

        note?.let {
            et_title.removeTextChangedListener(textChangeListener)
            et_body.removeTextChangedListener(textChangeListener)

            if (et_title.text.toString() != it.title) et_title.setTextKeepState(it.title)
            if (et_body.text.toString() != it.text) et_body.setTextKeepState(it.text)

            et_title.addTextChangedListener(textChangeListener)
            et_body.addTextChangedListener(textChangeListener)

            toolbar.setBackgroundColor(it.color.getColorInt(this))
        }

        colorPicker.onColorClickListener = {
            note = note?.copy(color = it)
            toolbar.setBackgroundColor(it.getColorInt(this))
            saveNote()
        }
    }

    fun saveNote() {
        if (et_title.text.isNullOrBlank()) return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChanged = Date()
        ) ?: Note(
            UUID.randomUUID().toString(),
            title = et_title.text.toString(),
            text = et_body.text.toString()
        )

        note?.let { viewModel.save(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        MenuInflater(this).inflate(R.menu.note, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> onBackPressed().let { true }
        R.id.palette -> togglePallete().let { true }
        R.id.delete -> deleteNote().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    fun togglePallete() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    fun deleteNote() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.note_delete_message))
            .setNegativeButton(getString(R.string.note_delete_cancel)) { dialog, which -> dialog.dismiss() }
            .setPositiveButton(getString(R.string.note_delete_ok)) { dialog, which -> viewModel.deleteNote() }
            .show()
    }


}

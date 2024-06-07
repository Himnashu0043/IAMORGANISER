package com.example.iamorganiser.helper
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.iamorganiser.R

class ReadMoreTextView : AppCompatTextView {

    private var originalText: String = ""
    private val MAX_CHARS_THRESHOLD = 59
    private val readMoreColor = Color.RED
    private val readLessColor = Color.RED

    constructor(context: Context) : super(context) {
        setupTextView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setupTextView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupTextView()
    }

    private fun setupTextView() {
        movementMethod = LinkMovementMethod.getInstance()
    }

    fun setTextWithReadMore(mtext: String) {
        this.originalText = mtext

        if (originalText.length <= MAX_CHARS_THRESHOLD) {
            text = originalText
        } else {
            val truncatedText = originalText.substring(0, MAX_CHARS_THRESHOLD) + "..."
            val spannable = SpannableStringBuilder(truncatedText + " Read more")
            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: android.view.View) {
                    expandText()
                }
            }, truncatedText.length + 1, spannable.length, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(ForegroundColorSpan(readMoreColor), truncatedText.length + 1, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            this.text = spannable
        }
    }

    private fun expandText() {
        val spannable = SpannableStringBuilder(originalText + " Read less")
        spannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: android.view.View) {
                collapseText()
            }
        }, originalText.length + 1, spannable.length, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(readLessColor), originalText.length + 1, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = spannable
    }

    private fun collapseText() {
        if (originalText.length <= MAX_CHARS_THRESHOLD) {
            this.text = originalText
        } else {
            val truncatedText = originalText.substring(0, MAX_CHARS_THRESHOLD) + "..."
            val spannable = SpannableStringBuilder(truncatedText + " Read more")
            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: android.view.View) {
                    expandText()
                }
            }, truncatedText.length + 1, spannable.length, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(ForegroundColorSpan(readMoreColor), truncatedText.length + 1, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            this.text = spannable
        }
    }
}

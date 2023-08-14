package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    private var tvNow : String? = null
    var lastnumeric = false
    var lastdot = false
    var operatorAdd = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
    }
    fun  onDigit(view: View){
        tvInput?.append((view as Button).text)
            if(operatorAdd){
                onEqual(view = view)
            }

        if(operatorAdd){
            var tvValue = tvInput?.text.toString()

            var prefix = ""

            if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
            }
            if(tvValue.contains("-")){
                    val splitValu = tvValue.split("-")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                tvNow =(one.toDouble() - two.toDouble()).toString()
                Toast.makeText(this,"${tvNow}",Toast.LENGTH_SHORT).show()
            }else if(tvValue.contains("*")){
                    val splitValu = tvValue.split("*")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                tvNow = removedotafter((one.toDouble() * two.toDouble()).toString())
            }else if(tvValue.contains("/")){
                    val splitValu = tvValue.split("/")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                tvNow = removedotafter((one.toDouble() / two.toDouble()).toString())
            }else if(tvValue.contains("+")){
                    val splitValu = tvValue.split("+")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                tvNow = removedotafter((one.toDouble() + two.toDouble()).toString())
            }
        }
        lastnumeric = true
        lastdot = false
        operatorAdd = false
    }
    fun onClr(view: View){
        tvInput?.text = ""
        lastnumeric = false
        lastdot = false
        operatorAdd = false
    }
    fun dot(view: View){
        if (lastnumeric && !lastdot){
            tvInput?.append(".")
            lastnumeric = false
            lastdot = true
            operatorAdd = false
        }
    }

    fun onEqual(view: View){
        if(lastnumeric){
            var tvValue = tvInput?.text.toString()

            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValu = tvValue.split("-")
                    var one = splitValu[0]
                    if(tvNow!=null){
                        one = tvNow as String

                    }
                    val two = splitValu[1]
                    Toast.makeText(this,"${one} ${two}",Toast.LENGTH_SHORT).show()

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removedotafter((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValu = tvValue.split("*")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removedotafter((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValu = tvValue.split("/")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removedotafter((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValu = tvValue.split("+")
                    var one =  splitValu[0]
                    val two = splitValu[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removedotafter((one.toDouble() + two.toDouble()).toString())
                }



            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }


    //&& !isOperatorAdd(it.toString())
    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastnumeric  && !operatorAdd){
                tvInput?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
                operatorAdd = !it.startsWith("-")
            }
        }

    }
    fun backspace(view: View){
        val result2=tvInput?.text
        if (result2?.length!! > 0) {
            tvInput?.text = result2.substring(0,result2.length-1)

        }
        else{
            tvInput?.text = ""

        }


    }
    private fun removedotafter(value : String) :String {
        var result = value
        if (value.contains(".0")) {
            result = value.substring(0, value.length - 2)

        }
        return result
    }
//    private fun isOperatorAdd(value : String) : Boolean{
//        return if(value.startsWith("-")){
//            false
//        }
//        else{
//            value.contains("/")
//                    || value.contains("*")
//                    ||value.contains("-")
//                    || value.contains("+")
//        }
//
//    }
}
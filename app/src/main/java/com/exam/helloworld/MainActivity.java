package com.exam.helloworld;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

//定义实现监听接口的类MainActivity
public class MainActivity extends AppCompatActivity implements View.OnClickListener {//
    //声明19个按钮和一个文本编辑框,声明初始化控件view(Button)
    Button but1;
    Button but2;
    Button but3;
    Button but4;
    Button but5;
    Button but6;
    Button but7;
    Button but8;
    Button but9;
    Button but0;
    Button butAC;
    Button butDelete;
    Button butDiv;
    Button butRemain;
    Button butSub;
    Button butAdd;
    Button butMulti;
    Button butPoint;
    Button butEdu;
    Button butPing;
    private EditText butResult;//文本
    boolean clear;//判断是否为空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_main);//显示activity_main定义的用户界面
        intView(); //调用

    }

    //用于与用户界面程序中的组件建立关联，并分别注册监听接口
    public void intView() {

        //通过findViewById查找对应控件的id
        but1 = (Button) findViewById(R.id.but1);
        but1.setOnClickListener(this);//this是访问当前类的接口的一个对象，就是把当前OnClick事件绑定到控件view上面去
        //实例化对象
        but0 = (Button) findViewById(R.id.but0);
        //给按钮设置点击事件
        but0.setOnClickListener(this);

        but2 = findViewById(R.id.but2);
        but2.setOnClickListener(this);
        butPing = findViewById(R.id.butPing);
        butPing.setOnClickListener(this);
        but3 = findViewById(R.id.but3);
        but3.setOnClickListener(this);
        but4 = findViewById(R.id.but4);
        but4.setOnClickListener(this);
        but5 = findViewById(R.id.but5);
        but5.setOnClickListener(this);
        but6 = findViewById(R.id.but6);
        but6.setOnClickListener(this);
        but7 = findViewById(R.id.but7);
        but7.setOnClickListener(this);
        but8 = findViewById(R.id.but8);
        but8.setOnClickListener(this);
        but9 = findViewById(R.id.but9);
        but9.setOnClickListener(this);
        butAC = findViewById(R.id.butAC);
        butAC.setOnClickListener(this);
        butAdd = findViewById(R.id.butAdd);
        butAdd.setOnClickListener(this);
        butDelete = findViewById(R.id.butDelete);
        butDelete.setOnClickListener(this);
        butDiv = findViewById(R.id.butDiv);
        butDiv.setOnClickListener(this);
        butEdu = findViewById(R.id.butEdu);
        butEdu.setOnClickListener(this);
        butSub = findViewById(R.id.butSub);
        butSub.setOnClickListener(this);
        butMulti = findViewById(R.id.butMulti);
        butMulti.setOnClickListener(this);
        butPoint = findViewById(R.id.butPoint);
        butPoint.setOnClickListener(this);
        butRemain = findViewById(R.id.butRemain);
        butRemain.setOnClickListener(this);
        butResult = (EditText) findViewById(R.id.butResult);
        // butResult.setOnClickListener(this);

    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    //onClick()方法，触发OnClickListener接口
    public void onClick(View v) {//接口的方式实现监听
        String str = butResult.getText().toString();//获取文本框的内容
        switch (v.getId()) {
            case R.id.but0:
            case R.id.but1:
            case R.id.but2:
            case R.id.but3:
            case R.id.but4:
            case R.id.but5:
            case R.id.but6:
            case R.id.but7:
            case R.id.but8:
            case R.id.but9:
            case R.id.butPoint:
                if (clear) {
                    clear = false;
                    str = "";
                    butResult.setText("");
                }
                butResult.setText(str + ((Button) v).getText());
                break;

            case R.id.butAdd:
            case R.id.butMulti:
            case R.id.butSub:
            case R.id.butRemain:
            case R.id.butDiv:
            case R.id.butPing:
                if (clear) {
                    clear = false;
                    str = "";
                    butResult.setText("");
                }
                butResult.setText(str + " " + ((Button) v).getText());
                break;
            case R.id.butDelete:
                getOffSet();
                break;
            case R.id.butAC:
                if (clear) {
                    clear = false;
                    str = "";
                    butResult.setText("");
                } else if (!str.equals("")) {
                    butResult.setText(str.substring(0, str.length() - 1));

                }
                break;
            case R.id.butEdu://单独运算最后结果
                getResult();
                break;
        }

    }

    @SuppressLint({"SetTextI18n", "WrongConstant"})
    private void getOffSet() { //正负互换的功能
        String oss = butResult.getText().toString();
        if (clear) {
            clear = false;
            return;
        }
        clear = true;

        double d2 = Double.parseDouble(oss);
        if (oss.equals("0")) {
            Toast.makeText(this, "0不是正负数", 1).show();//默认toast
        } else if (oss.contains("-")) {
            String s1 = oss.substring(oss.indexOf(" ") + 2);
            double d1 = Double.parseDouble(s1);
            butResult.setText("" + d1);
        } else {
            butResult.setText("-" + d2);
        }
    }

    @SuppressLint({"WrongConstant", "SetTextI18n", "ShowToast"})
    private void getResult() {
        String result = butResult.getText().toString();
        if (result.equals("")) {
            return;
        }
        if (!result.contains(" ")) {
            return;
        }
        if (clear) {
            clear = false;
            return;
        }
        clear = true;
//截取运算符前面的字符串
        String s1 = result.substring(0, result.indexOf(" "));
//截取的运算符
        String op = result.substring(result.indexOf(" ") + 1, result.indexOf(" ") + 2);
//截取运算符后面的字符串
        String s2 = result.substring(result.indexOf(" ") + 2);
        double cnt = 0;
        int Num1 = 0;
        int Num2 = 0;
        int Num3 = 0, Num4 = 0, Num5 = 0, Num6 = 0, Num7 = 0;
        String str1 = "+";
        String str2 = "-";
        String str3 = "/";
        String str4 = "%";
        String str5 = "*";
        String str6 = "√";
        //String str7 = ".";

        char ch1 = str1.charAt(0);
        char ch2 = str2.charAt(0);
        char ch3 = str3.charAt(0);
        char ch4 = str4.charAt(0);
        char ch5 = str5.charAt(0);
        char ch6 = str6.charAt(0);
       // char ch7 = str7.charAt(0);
        // char ch8=str8.charAt(0);

        String starch1 = String.valueOf(ch1);
        String starch2 = String.valueOf(ch2);
        String starch3 = String.valueOf(ch3);
        String starch4 = String.valueOf(ch4);
        String starch5 = String.valueOf(ch5);
        String starch6 = String.valueOf(ch6);
      //  String starch7 = String.valueOf(ch7);

        for (int i = 0; i < result.length(); i++) {
            if (starch1.equals(result.substring(i, i + 1)))
                Num1++;
            if (starch2.equals(result.substring(i, i + 1)))
                Num2++;
            if (starch3.equals(result.substring(i, i + 1)))
                Num3++;
            if (starch4.equals(result.substring(i, i + 1)))
                Num4++;
            if (starch5.equals(result.substring(i, i + 1)))
                Num5++;
            if (starch6.equals(result.substring(i, i + 1)))
                Num6++;

        }
        int sum= Num1 + Num2 + Num3 + Num4 + Num5 + Num6;
        int sum1=Num3 + Num4 + Num5 + Num6;
        if (sum == 0) {
            butResult.setText(result);
        }
        else if (sum>2) {
            Toast.makeText(this, "输入法不对 ,重来", 1).show();//默认toast
            // butResult.setText(0);
        }

//两个数都不是空
        else if (sum == 1) {

            if (!s1.equals("") && !s2.equals("")) {
                double d1 = Double.parseDouble(s1);//用Double.parseDouble()是把括号里面内容变成double类型的
                double d2 = Double.parseDouble(s2);
                switch (op) {
                    case "+":
                        cnt = d1 + d2;
                        break;
                    case "-":
                        cnt = d1 - d2;
                        break;
                    case "*":
                        cnt = d1 * d2;
                        break;
                    case "%":
                        cnt = d1 % d2;
                        break;
                    case "√": {
                        cnt = d1 * (Math.sqrt(d2));
                        butResult.setText(cnt + "");
                    }
                    case "/": {
                        if (d2 == 0) {
                            Toast.makeText(this, "非法输入！！除数不能为0", 1).show();//默认toast
                        } else {
                            cnt = d1 / d2;
                        }
                        break;
                    }
                }
                butResult.setText(cnt + "");

            }
            //如果s1是空 s2不是空 就执行下一步
            if (s1.equals("")&&!s2.equals("")) {
                // double d1 = Double.parseDouble(s1);//用Double.parseDouble()是把括号里面内容变成double类型的
                double d2 = Double.parseDouble(s2);
                switch (op) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        butResult.setText(result);
                        break;
                    case "√": {
                        if(d2<0){
                            Toast.makeText(this, "不正确", Toast.LENGTH_SHORT).show();
                        }
                        else
                        cnt = Math.sqrt(d2);
                        butResult.setText(cnt + "");
                    }
                    break;
                }
            }
            else if(!s1.equals("")&&s2.equals("")){
                //Toast.makeText(this, "你真是个小机灵鬼！！！重试", Toast.LENGTH_SHORT).show();
                butResult.setText(0);
            }
        } else if (sum == 2&&sum1!=2) {

            int i = result.indexOf(" ", result.indexOf(" ") + 1);
            //String s = result.substring(0,result.indexOf(" "));
            String a2 = result.substring(i + 2);
            //String a=result.substring(result.indexOf(" ")+2,result.indexOf(" ")+3);
            String op2 = result.substring(i + 1, i + 2);
            String a1 = result.substring(result.indexOf(" ") + 2, i);
            double d1 = Double.parseDouble(a1);//用Double.parseDouble()是把括号里面内容变成double类型的
            double d2 = Double.parseDouble(a2);
                if (a1.equals("")) {
                    if (op.equals("√")) {
                        switch (op2) {
                            case "-":
                                Toast.makeText(this, "非法输入！！不能负数", 1).show();//默认toast
                                break;
                            //cnt = Math.sqrt(d2);
                            case "+":
                                cnt = Math.sqrt(d2);
                                butResult.setText(cnt + "");
                                break;
                            case "√":
                            case "*":
                            case "/":
                            case "%": {
                                Toast.makeText(this, "输入不正确!!! ", 1).show();
                                butResult.setText("0");
                            }
                            break;


                        }
                    } else if (op.equals("*") || op.equals("/") || op.equals("%") || op.equals("+") || op.equals("-")) {

                        Toast.makeText(this, "输入不正确!!! ", 1).show();
                        butResult.setText("0");
                    }
                }
                else if (!a2.equals("")) {
                    switch (op) {
                        case "+": {
                            switch (op2) {
                                case "+":
                                    cnt = d1 + d2;
                                    break;
                                case "-":
                                    cnt = d1 - d2;
                                    break;
                                case "*":
                                    cnt = d1 * d2;
                                    break;

                                case "√": {
                                    cnt = d1 * Math.sqrt(d2);
                                    butResult.setText(cnt + "");
                                }
                                break;
                                case "/":
                                    if (d2 == 0) {
                                        Toast.makeText(this, "非法输入！！除数不能为0", 1).show();//默认toast
                                    } else {
                                        cnt = d1 / d2;
                                    }
                                    break;
                            }
                        }
                        break;
                        case "-": {
                            switch (op2) {
                                case "+":
                                    cnt = d2 - d1;
                                    break;
                                case "-": {
                                    cnt =-d1-d2;
                                   // butResult.setText("-" + cnt);
                                }
                                break;
                                case "*": {
                                    cnt = -d1 * d2;

                                }
                                break;
                               case "√": {
                                    cnt = -d1 * Math.sqrt(d2);
                                    butResult.setText("-" + cnt);
                                    }
                                break;
                                case "/": {
                                    if (d2 == 0) {
                                        Toast.makeText(this, "非法输入！！除数不能为0", 1).show();//默认toast
                                    } else {
                                        cnt = -d1 / d2;
                                        // butResult.setText("-" + cnt);
                                    }
                                }
                                break;
                            }
                            butResult.setText(cnt + "");
                        }
                        break;
                        case "√": {
                            if (op2.equals("-"))
                                //Toast.makeText(this, "非法输入！！不能负数", 1).show();//默认toast
                                cnt = Math.sqrt(d1) - d2;
                            else if (op2.equals("+"))
                                cnt = Math.sqrt(d1) + d2;
                            else if (op2.equals("*"))
                                cnt = Math.sqrt(d1) * d2;
                            else if (op2.equals("/"))
                                cnt = Math.sqrt(d1) / d2;
                            else if (op2.equals("%"))
                                cnt = Math.sqrt(d1) % d2;

                            butResult.setText(cnt + "");
                        }
                        break;
                        case "*":
                        case "%":
                        case "/": {
                            Toast.makeText(this, "输入不正确!!! ", 1).show();
                        }
                        break;
                    }
                    if (a1.contains(".") && a2.contains(".")) {
                        butResult.setText((double) cnt + "");
                    } else {
                        butResult.setText(cnt + "");
                    }
                } else  {
                    Toast.makeText(this, "你真是个小机灵鬼!!! 不正确", 1).show();
                    butResult.setText("0");

                }


            }
        else {
            Toast.makeText(this, "你真是个小机灵鬼!!! 不正确", 1).show();
            butResult.setText("0");

        }

        }
        }

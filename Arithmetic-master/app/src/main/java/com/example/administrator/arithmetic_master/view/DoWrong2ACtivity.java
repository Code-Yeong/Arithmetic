package com.example.administrator.arithmetic_master.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.base.BaseGenerator;
import com.example.administrator.arithmetic_master.baseimpl.BaseGeneratorImpl;
import com.example.administrator.arithmetic_master.http.HttpUtil;
import com.example.administrator.arithmetic_master.utils.Convert;
import com.example.administrator.arithmetic_master.utils.DisplayMsg;
import com.example.administrator.arithmetic_master.utils.EncodeAndDecode;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DoWrong2ACtivity extends AppCompatActivity {

    //计时相关变量
    private Handler timeHandler;
    private TextView exe_time;
    private int totalTime = 0;

    //题目相关变量
    private ArrayList<String> contentContainer = new ArrayList<>(); //题目内容保存的容器
    private ArrayList<String> answerContainer = new ArrayList<>(); //答案保存的容器
    private ArrayList<String> correctAnswerContainer = new ArrayList<>();//正确答案的容器
    private int currentProblemIndex = 0;//当前题目索引

    private TextView problem_content;
    private TextView problem_answer;
    private ImageView finishProblem;

    private String errorid;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_wrong2_activity);

        exe_time = (TextView) findViewById(R.id.wrong_time);
        problem_content = (TextView) findViewById(R.id.wrong_problem_content);
        problem_answer = (TextView) findViewById(R.id.wrong_problem_answer);
        finishProblem = (ImageView) findViewById(R.id.wrong_img_finishProblem);
        timeHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x1233) {
                    totalTime++;
                    String hour = (totalTime / 3600) + "";
                    String minute = (totalTime - Integer.parseInt(hour) * 3600) / 60 + "";
                    String second = (totalTime % 60) + "";
                    String time = hour + ":" + minute + ":" + second;
                    exe_time.setText(time);
                }
            }
        };

        Intent intent = getIntent();
        errorid = intent.getStringExtra("errorid");
        content = intent.getStringExtra("content");
        Log.i("content", content);
        init();
    }

    public void NumClick(View view) {
        switch (view.getId()) {
            case R.id.wrong_img_numOne:
                AlterAnswer("1");
                break;
            case R.id.wrong_img_numTwo:
                AlterAnswer("2");
                break;
            case R.id.wrong_img_numThree:
                AlterAnswer("3");
                break;
            case R.id.wrong_img_numFour:
                AlterAnswer("4");
                break;
            case R.id.wrong_img_numFive:
                AlterAnswer("5");
                break;
            case R.id.wrong_img_numSix:
                AlterAnswer("6");
                break;
            case R.id.wrong_img_numSeven:
                AlterAnswer("7");
                break;
            case R.id.wrong_img_numEight:
                AlterAnswer("8");
                break;
            case R.id.wrong_img_numNine:
                AlterAnswer("9");
                break;
            case R.id.wrong_img_numZero:
                AlterAnswer("0");
                break;
            case R.id.wrong_img_clean:
                AlterAnswer("Clean");
                break;
            case R.id.wrong_next_problem:
                //防止数组越界
                if (currentProblemIndex < contentContainer.size() - 1) {
                    currentProblemIndex++;
                }

                if (currentProblemIndex == contentContainer.size() - 1) {
                    finishProblem.setVisibility(View.VISIBLE);
                }
                UpdateProblem(currentProblemIndex);
                break;
            case R.id.wrong_last_problem:
                if (currentProblemIndex > 0) {
                    currentProblemIndex--;
                }
                UpdateProblem(currentProblemIndex);
                break;
            case R.id.wrong_img_divide:
                AlterAnswer("/");
                break;
            case R.id.wrong_img_minus:
                AlterAnswer("-");
                break;
            case R.id.wrong_img_point:
                AlterAnswer(".");
                break;
            case R.id.wrong_return:
                finish();
                break;
            case R.id.wrong_img_finishProblem:
                SendResult();
                finish();
                break;
            default:
                break;
        }
    }

    //修改答案
    public void AlterAnswer(String singleChar) {
        String answer = answerContainer.get(currentProblemIndex);
        if (singleChar.equals("Clean")) {
            if (answer.equals("")) {

            } else {
                int length = answer.length();
                answer = answer.substring(0, length - 1);
            }
        } else {
            answer = answer + singleChar;
        }
        answerContainer.set(currentProblemIndex, answer);
        UpdateProblem(currentProblemIndex);
    }

    //刷新问题或答案
    private void UpdateProblem(int index) {
        Log.i("info", "Index:" + index);
        problem_content.setText(contentContainer.get(index));
        problem_answer.setText(answerContainer.get(index));
    }

    public void GenerateProblem(String content) {
        String[] pros = EncodeAndDecode.Decode(content).split("\\|");
        for (int i = 0; i < pros.length; i++) {
            contentContainer.add(pros[i]);
        }
    }

    private void SendResult() {
        String userId = User.getInstance().getUserId();
        String totalCount = contentContainer.size() + "";
        String useTime = totalTime + "";
        int rightCount = 0;
        for (int i = 0; i < contentContainer.size(); i++) {
            if (answerContainer.get(i).equals(correctAnswerContainer.get(i))) //增加正确的题目数量
            {
                rightCount++;
            }
        }
        String str_rightCount = rightCount + "";
        //计算分数
        float float_rightCount = rightCount;
        float float_totalCount = contentContainer.size();

//        DisplayMsg.Show(this, "您答对了" + str_rightCount + "题");
        showDialgog("您答对了" + str_rightCount + "题");
//        //将答题结果发送到服务端记录
//        String url = "recordsAction_saveRecord.action?userid=" + userId + "&rightcount=" + str_rightCount + "&totalcount=" + totalCount + "&time=" + useTime;
//        new HttpUtil(new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//            }
//        }, url).start();
    }

    public void init() {
        GenerateProblem(content);
        int count = contentContainer.size();
        //根据对话框内容取得相对应的题目、正确答案及用户默认计算结果，默认结果为空字符串
        for (int i = 0; i < count; i++) {
            //生成对应题目的正确答案。
            BaseGenerator baseGenerator = new BaseGeneratorImpl();
            String correctAsn = baseGenerator.calculate(Convert.infix2postfixMdf(contentContainer.get(i)));
            correctAnswerContainer.add(correctAsn);
            //初始化用户计算结棍容器
            answerContainer.add("");
        }
        UpdateProblem(currentProblemIndex);

        //开始计时
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                timeHandler.sendEmptyMessage(0x1233);
            }
        }, 0, 1000);
    }

    public void showDialgog(String msg) {
        try {
//             Get the layout inflater:获取自定义的Layout
            LayoutInflater inflater = getLayoutInflater();
            View layout_casSetting = inflater.inflate(R.layout.wrong2setting, null);
            TextView tv = (TextView) layout_casSetting.findViewById(R.id.wrong_tv);
            tv.setText(msg);
            new AlertDialog.Builder(this)
//                    .setView(layout_casSetting)
                    .setNeutralButton("下次再战", null)
                    .setNegativeButton("无需再练", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new HttpUtil(new Handler() {
                                @Override
                                public void handleMessage(Message msg) {
                                    super.handleMessage(msg);
                                    if (msg.obj.toString().equals("0x01")) {
//                                        init();
                                        DisplayMsg.Show(DoWrong2ACtivity.this, "删除成功");
                                    }
                                }
                            }, "errorsAction_deleteErrors.action?userid=" + User.getInstance().getUserId() + "&errorid=" + errorid).start();
                            dialog.dismiss();
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
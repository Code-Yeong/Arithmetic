package com.example.administrator.arithmetic_master.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.grade.GradeFive;
import com.example.administrator.arithmetic_master.grade.GradeFour;
import com.example.administrator.arithmetic_master.grade.GradeOne;
import com.example.administrator.arithmetic_master.grade.GradeSix;
import com.example.administrator.arithmetic_master.grade.GradeThree;
import com.example.administrator.arithmetic_master.grade.GradeTwo;
import com.example.administrator.arithmetic_master.http.HttpUtil;
import com.example.administrator.arithmetic_master.utils.DisplayMsg;

import java.util.ArrayList;

public class DoWrongActivity extends AppCompatActivity {

    //计时相关变量
    private Handler timeHandler;
    private TextView exe_time;
    private int totalTime=0;

    //题目相关变量
    private ArrayList<String> contentContainer=new ArrayList<>(); //题目内容保存的容器
    private ArrayList<String> answerContainer=new ArrayList<>(); //答案保存的容器
    private ArrayList<String> correctAnswerContainer=new ArrayList<>();//正确答案的容器
    private int currentProblemIndex=0;//当前题目索引

    private TextView problem_content;
    private TextView problem_answer;
    private ImageView finishProblem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_wrong);

        exe_time=(TextView) findViewById(R.id.exe_time);
        problem_content=(TextView)findViewById(R.id.problem_content);
        problem_answer=(TextView)findViewById(R.id.problem_answer) ;
        finishProblem=(ImageView)findViewById(R.id.img_finishProblem);
        timeHandler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==0x1233)
                {
                    totalTime++;
                    String hour=(totalTime/3600)+"";
                    String minute=(totalTime-Integer.parseInt(hour)*3600)/60+"";
                    String second=(totalTime%60)+"";
                    String time=hour+":"+minute+":"+second;
                    exe_time.setText(time);
                }
            }
        };
    }

    public void NumClick(View view)
    {
        switch (view.getId())
        {
            case R.id.img_numOne:
                AlterAnswer("1");
                break;
            case R.id.img_numTwo:
                AlterAnswer("2");
                break;
            case R.id.img_numThree:
                AlterAnswer("3");
                break;
            case R.id.img_numFour:
                AlterAnswer("4");
                break;
            case R.id.img_numFive:
                AlterAnswer("5");
                break;
            case R.id.img_numSix:
                AlterAnswer("6");
                break;
            case R.id.img_numSeven:
                AlterAnswer("7");
                break;
            case R.id.img_numEight:
                AlterAnswer("8");
                break;
            case R.id.img_numNine:
                AlterAnswer("9");
                break;
            case R.id.img_numZero:
                AlterAnswer("0");
                break;
            case R.id.img_clean:
                AlterAnswer("Clean");
                break;
            case R.id.next_problem:
                //防止数组越界
                if(currentProblemIndex< User.getInstance().currentExeNum-1)
                {
                    currentProblemIndex++;
                }

                if(currentProblemIndex==User.getInstance().currentExeNum-1)
                {
                    finishProblem.setVisibility(View.VISIBLE);
                }
                UpdateProblem(currentProblemIndex);
                break;
            case R.id.last_problem:
                if(currentProblemIndex>0)
                {
                    currentProblemIndex--;
                }
                UpdateProblem(currentProblemIndex);
                break;
            case R.id.img_divide:
                AlterAnswer("/");
                break;
            case R.id.img_minus:
                AlterAnswer("-");
                break;
            case R.id.img_point:
                AlterAnswer(".");
                break;
            case R.id.img_return:
                finish();
                break;
            case R.id.img_finishProblem:
                SendResult();
                finish();
                break;
            default:
                break;
        }
    }

    //修改答案
    public void AlterAnswer(String singleChar)
    {
        String answer=answerContainer.get(currentProblemIndex);
        if(singleChar.equals("Clean"))
        {
            if(answer.equals("")){

            }
            else{
                int length=answer.length();
                answer=answer.substring(0,length-1);
            }
        }
        else
        {
            answer=answer+singleChar;
        }
        answerContainer.set(currentProblemIndex,answer);
        UpdateProblem(currentProblemIndex);
    }

    //刷新问题或答案
    private void UpdateProblem(int index)
    {
        problem_content.setText(contentContainer.get(index));
        problem_answer.setText(answerContainer.get(index));
    }

    public String GenerateProblem(int grade)
    {
        String problem="";
        switch (grade)
        {
            case 1:
                GradeOne gradeOne =new GradeOne();
                problem=gradeOne.getIntergralExpression();
                break;
            case 2:
                GradeTwo gradeTwo =new GradeTwo();
                problem=gradeTwo.getIntergralExpression();
                break;
            case 3:
                GradeThree gradeThree=new GradeThree();
                problem=gradeThree.getIntergralExpression();
                break;
            case 4:
                GradeFour gradeFour=new GradeFour();
                problem=gradeFour.getIntergralExpression();
                break;
            case 5:
                GradeFive gradeFive=new GradeFive();
                problem=gradeFive.getIntergralExpression();
                break;
            case 6:
                GradeSix gradeSix=new GradeSix();
                problem=gradeSix.getIntergralExpression();
                break;
            default:
                DisplayMsg.Show(this,"年级错误");
                break;
        }
        return problem;
    }

    private void SendResult()
    {
        String userId=User.getInstance().getUserId();
        String totalCount=User.getInstance().currentExeNum+"";
        String useTime= totalTime+"";
        int rightCount=0;
        for(int i=0;i<User.getInstance().currentExeNum;i++)
        {
            if(answerContainer.get(i).equals(correctAnswerContainer.get(i)))
            {
                rightCount++;
            }
        }
        String str_rightCount=rightCount+"";

        //计算分数
        float score=0;
        float float_rightCount=rightCount;
        float float_totalCount=User.getInstance().currentExeNum;

        if(User.getInstance().getUserGrade()<4)
        {
            score=(float_rightCount/float_totalCount)*(5*float_totalCount/totalTime);
        }
        else
        {
            score=(float_rightCount/float_totalCount)*(10*float_totalCount/totalTime);
        }
        DisplayMsg.Show(this,"您答对了"+str_rightCount+",所得积分为："+score);

        //将记录发送到服务端记录
        String url="recordsAction_saveRecord.action?userid="+userId+"&rightcount="+str_rightCount+"&totalcount="+totalCount+"&time="+useTime;
        new HttpUtil(new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        },url).start();
    }
}

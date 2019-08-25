package me.meodinger.cat;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * 请求的类型
     */
    private interface RequestMethod {
        int GET = 1;
        int POST = 2;
    }

    private EditText activity_main_url_et;
    private EditText activity_main_name_et;
    private EditText activity_main_age_et;
    private EditText activity_main_breed_et;
    private RadioButton activity_main_male_rb;
    private RadioButton activity_main_female_rb;
    private TextView activity_main_log_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        activity_main_url_et = findViewById(R.id.activity_main_url_et);
        activity_main_name_et = findViewById(R.id.activity_main_name_et);
        activity_main_age_et = findViewById(R.id.activity_main_age_et);
        activity_main_breed_et = findViewById(R.id.activity_main_breed_et);
        activity_main_male_rb = findViewById(R.id.activity_main_male_rb);
        activity_main_female_rb = findViewById(R.id.activity_main_female_rb);
        activity_main_log_tv = findViewById(R.id.activity_main_log_tv);

        //软键盘管理
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final View view = getWindow().peekDecorView();
        Objects.requireNonNull(imm);

        findViewById(R.id.activity_main_get_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        request(MainActivity.RequestMethod.GET);
                    }
                });

        findViewById(R.id.activity_main_post_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        request(MainActivity.RequestMethod.POST);
                    }
                });

        activity_main_log_tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    /**
     * 数据请求
     *
     * @param requestType
     */
    private void request(final int requestType) {
        String URL = "http://" + activity_main_url_et.getText().toString() + "/api/cat";
        //String URL = "http://192.168.0.110:8080/api/cat";
        String name = activity_main_name_et.getText().toString();
        String gender = (activity_main_male_rb.isChecked() ? "m" : (activity_main_female_rb.isChecked() ? "f" : "x"));
        String age = activity_main_age_et.getText().toString();
        String breed = activity_main_breed_et.getText().toString().isEmpty() ?
                "" : activity_main_breed_et.getText().toString();

        if (requestType == MainActivity.RequestMethod.GET) {
            activity_main_log_tv.append("GET请求：\n");
            OkGo.<String>get(URL)
                    .params("name", name)
                    .params("gender", gender)
                    .params("age", age)
                    .params("breed", breed)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            activity_main_log_tv.append(response.body() + "\n");
                        }
                    });
        } else if (requestType == MainActivity.RequestMethod.POST) {
            activity_main_log_tv.append("POST请求：\n");
            OkGo.<String>post(URL)
                    .params("name", name)
                    .params("gender", gender)
                    .params("age", age)
                    .params("breed", breed)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            activity_main_log_tv.append(response.body() + "\n");
                        }
                    });
        }
    }

}

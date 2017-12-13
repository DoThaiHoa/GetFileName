package com.example.hoadt.getfilename;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.util.ArrayList;
import com.example.hoadt.getfilename.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();
    private ListView view;
int c=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (ListView) findViewById(R.id.view);

        //getting SDcard root path
        root = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/data/");
        getfile(root);
//
//        for (int i = 0; i < fileList.size(); i++) {
//            TextView textView = new TextView(this);
//            textView.setText(fileList.get(i).getName());
//            textView.setPadding(5, 5, 5, 5);
//
//            System.out.println(fileList.get(i).getName());
//
//            if (fileList.get(i).isDirectory()) {
//                textView.setTextColor(Color.parseColor("#ff0000"));
//            }
//            view.addView(textView);
//        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,fileList);
       view.setAdapter(arrayAdapter);

    }

    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
//
                if (listFile[i].isDirectory()) {
//                    fileList.add(listFile[i]);
                    getfile(listFile[i]);

                } else {
                    if ( listFile[i].getName().endsWith(".apk") )

                    {
                        fileList.add(listFile[i]);
                    }
                    if(listFile[i].getName().endsWith(".apk")){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() +"/data/"+ listFile[i].getName())), "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                }


            }
        }
        return fileList;
    }

}


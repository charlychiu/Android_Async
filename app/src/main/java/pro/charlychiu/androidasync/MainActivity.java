package pro.charlychiu.androidasync;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TestTask task = new TestTask();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task.execute();

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                task.testing("OAO");
            }
        });
    }
    class TestTask extends AsyncTask<Void,Void,Void> {

        ArrayList<String> test = new ArrayList<String>();
        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetSocketAddress isa = new InetSocketAddress("10.1.1.5",8885);
                SocketChannel sc = SocketChannel.open(isa);

                while(true) {

                    test.add("test1");
                    test.add("test2");
                    test.add("test3");
                    test.add("getInfo");
                    test.add("Crunchify");

                    for (String testele : test) {
                        if (testele.equals("getInfo")) {
                            byte[] message = new String(testele).getBytes();
                            ByteBuffer buffer = ByteBuffer.wrap(message);
                            sc.write(buffer);
                            buffer.clear();
                            Thread.sleep(2000);
                            sc.read(buffer);
                            String res = new String(buffer.array());
                            Log.d("****Test", res);
                            buffer.clear();
                        } else {
                            byte[] message = new String(testele).getBytes();
                            ByteBuffer buffer = ByteBuffer.wrap(message);
                            sc.write(buffer);
                            buffer.clear();
                        }

                        Thread.sleep(2000);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        private void connect() throws IOException, InterruptedException {

            //sc.close();
        }
        public void testing(String testStr) {
            Log.d("***TestSendingStr","testing");
        }
    }
}

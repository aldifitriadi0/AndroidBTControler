
package com.example.androidbtcontrol;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends ActionBarActivity {

    TextView textView ;
    TextView textView2 ;
    TextView textView3 ;
    Button reset ;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    long MillisecondTime2, StartTime2, TimeBuff2, UpdateTime2 = 0L ;
    long MillisecondTime3, StartTime3, TimeBuff3, UpdateTime3 = 0L ;


    Handler handler;
    int Seconds, MilliSeconds ;
    ListView listView ;
    String[] ListElements = new String[] {  };
    List<String> ListElementsArrayList ;
    ArrayAdapter<String> adapter ;

    private static final int REQUEST_ENABLE_BT = 1;

    BluetoothAdapter bluetoothAdapter;
    DatabaseHelper myDb;

    ArrayList<BluetoothDevice> pairedDeviceArrayList;

    TextView textInfo, textStatus;
    ListView listViewPairedDevice;
    LinearLayout inputPane;
    EditText inputField;
    Button btnSend;

    private final int Req_code_speech_output = 143;
    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    private UUID myUUID;
    private final String UUID_STRING_WELL_KNOWN_SPP =
        "00001101-0000-1000-8000-00805F9B34FB";

    ThreadConnectBTdevice myThreadConnectBTdevice;
    ThreadConnected myThreadConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        reset = (Button)findViewById(R.id.button3);

        myDb = new DatabaseHelper(this);
        isiSample();

        textInfo = (TextView)findViewById(R.id.info);
        textStatus = (TextView)findViewById(R.id.status);
        listViewPairedDevice = (ListView)findViewById(R.id.pairedlist);

        inputPane = (LinearLayout)findViewById(R.id.inputpane);
        inputField = (EditText)findViewById(R.id.input);
        btnSend = (Button)findViewById(R.id.send);


        handler = new Handler() ;

        btnSend.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(myThreadConnected!=null){
                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    btnToOpenmic();
                }
            }});

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            Toast.makeText(this,
                    "FEATURE_BLUETOOTH NOT support",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        //using the well-known SPP UUID
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this,
                    "Bluetootmu ganok",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String stInfo = bluetoothAdapter.getName() + "\n" +
                bluetoothAdapter.getAddress();
        textInfo.setText(stInfo);
    }

    void isiSample() {
        // CLASS 1 MAIN MOVEMENT
        myDb.insertData("TERBANG",  "1");
        myDb.insertData("TAKE",  "1");

        myDb.insertData("LANDING",  "2");
        myDb.insertData("MENDARAT",  "2");

        //main Movement Class
        myDb.insertData("NAIKNYA",     "3");
        myDb.insertData("NAIK",     "3");
        myDb.insertData("ATAS",     "3");
        myDb.insertData("KEATAS",     "3");

        myDb.insertData("TURUNNYA",    "4");
        myDb.insertData("TURUN",    "4");
        myDb.insertData("KEBAWAH",    "4");
        myDb.insertData("BAWAH",    "4");
        myDb.insertData("SAWAH",    "4");
        myDb.insertData("KAWAH",    "4");

        myDb.insertData("KIRI",     "5");
        myDb.insertData("KEKIRI",     "5");
        myDb.insertData("KIRINYA",     "5");
        myDb.insertData("DIRI",     "5");


        myDb.insertData("KANAN",    "6");
        myDb.insertData("KEKANAN",    "6");
        myDb.insertData("KANANNYA",    "6");

        myDb.insertData("MAJU",     "7");
        myDb.insertData("DEPAN",     "7");
        myDb.insertData("KEDEPAN",     "7");
        myDb.insertData("DEPANNYA",     "7");
        myDb.insertData("MAJUNYA",     "7");

        myDb.insertData("MUNDUR",   "8");
        myDb.insertData("BELAKANG",   "8");
        myDb.insertData("KEBELAKANG",   "8");
        myDb.insertData("MUNDURNYA",   "8");
        myDb.insertData("BUDUR",   "8");

        myDb.insertData("BERPUTAR",     "9");
        myDb.insertData("PUTAR",     "9");
        myDb.insertData("PEMUTAR",     "9");
        myDb.insertData("MUTER",     "9");
        myDb.insertData("MUTAR",     "9");
        //CLASS 2 NEGASI
        myDb.insertData("TANGAN",   "J");
        myDb.insertData("JANGAN",   "J");
        //MOVE TAMBAHAN
        myDb.insertData("FLIP",     "A");
        myDb.insertData("JOGET",    "B");
        //CLASS 3 IMBUHAN
        myDb.insertData("SEDIKIT",  "C");
        myDb.insertData("DIKIT",  "C");
        myDb.insertData("AGAK",     "C");

        myDb.insertData("BANYAK",   "D");
        myDb.insertData("LAMA",     "D");
        //class 4 Detikan
        myDb.insertData("SATU",     "E");
        myDb.insertData("1",        "E");
        myDb.insertData("DUA",      "F");
        myDb.insertData("2",        "F");
        myDb.insertData("TIGA",     "G");
        myDb.insertData("3",        "G");
        myDb.insertData("EMPAT",    "H");
        myDb.insertData("4",        "H");
        myDb.insertData("LIMA",     "I");
        myDb.insertData("5",        "I");
        //Jenis Jenis Kata Hubung

        myDb.insertData("ATAU",     "K");
        myDb.insertData("ATAUKAH",     "K");
        myDb.insertData("ATAUPUN",     "K");

        myDb.insertData("SEBELUM",     "L");
        myDb.insertData("SAKDURUNGE",     "L");

        myDb.insertData("LALU",     "Z");
        myDb.insertData("LANJUT",   "Z");
        myDb.insertData("DAN",      "Z");
        myDb.insertData("SETELAH",   "Z");
        myDb.insertData("KEMUDIAN",   "Z");
        myDb.insertData("SELANJUTNYA",   "Z");
        myDb.insertData("SEKALIAN",   "Z");
    }

    public void btnToOpenmic(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Bicara Sekarang . . .");

        try{
            startActivityForResult(intent, Req_code_speech_output);
        }

        catch(ActivityNotFoundException tim){

        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        //permintaan Nyalakan Bluetooth
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        setup();
    }

    private void setup() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
            }

            pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this,
                    android.R.layout.simple_list_item_1, pairedDeviceArrayList);
            listViewPairedDevice.setAdapter(pairedDeviceAdapter);

            listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    BluetoothDevice device =
                            (BluetoothDevice) parent.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this,
                            "Name: " + device.getName() + "\n"
                                    + "Address: " + device.getAddress() + "\n"
                                    + "BondState: " + device.getBondState() + "\n"
                                    + "BluetoothClass: " + device.getBluetoothClass() + "\n"
                                    + "Class: " + device.getClass(),
                            Toast.LENGTH_LONG).show();

                    textStatus.setText("start ThreadConnectBTdevice");
                    myThreadConnectBTdevice = new ThreadConnectBTdevice(device);
                    myThreadConnectBTdevice.start();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(myThreadConnectBTdevice!=null){
            myThreadConnectBTdevice.cancel();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TimeBuff += MillisecondTime;
        handler.removeCallbacks(runnable);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                setup();
            } else {
                Toast.makeText(this,
                        "BlueTooth NOT enabled",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (requestCode == Req_code_speech_output) {
            if (resultCode == RESULT_OK && null != data){
                StartTime2 = System.nanoTime();

                MillisecondTime2 = System.nanoTime() - StartTime2;
                Log.d("hitung:", String.valueOf(MillisecondTime2));
                UpdateTime2 = TimeBuff2 + MillisecondTime2;

                Seconds = (int) (UpdateTime2 / 1000);

                Seconds = Seconds % 60;

                MilliSeconds = (int) (UpdateTime2 % 1000);

                textView2.setText(""
                        + String.format("%02d", Seconds)
                        + String.format("%03d", MilliSeconds));



                ArrayList<String> voiceText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                inputField.setText(voiceText.get(0));
                String tmp = voiceText.get(0);
                Log.d("Input Suara", voiceText.get(0));
                String parser[] = tmp.split(" ");

                for (String x : parser) {
                    Log.d("Hasil split:", x);
                    String Command = myDb.getCommand(x.toUpperCase());

                    byte[] bytesToSend = Command.getBytes();
                    myThreadConnected.write(bytesToSend);
                    Log.d("Hasil pencocokan DB", Command);
                }
                TimeBuff2 += MillisecondTime2;
                handler.removeCallbacks(runnable2);
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                MillisecondTime2 = 0L ;
                MillisecondTime3 = 0L ;
                StartTime = 0L ;
                StartTime2 = 0L ;
                StartTime3 = 0L ;
                TimeBuff = 0L ;
                TimeBuff2 = 0L ;
                TimeBuff3 = 0L ;
                UpdateTime = 0L ;
                UpdateTime2 = 0L ;
                UpdateTime3 = 0L ;

                Seconds = 0 ;
                MilliSeconds = 0 ;

                textView.setText("000");
                textView2.setText("000");
                textView3.setText("000");

            }
        });

    }

    //Called in ThreadConnectBTdevice once connect successed
    //to start ThreadConnected
    private void startThreadConnected(BluetoothSocket socket){

        myThreadConnected = new ThreadConnected(socket);
        myThreadConnected.start();
    }

    /*
    ThreadConnectBTdevice:
    Background Thread to handle BlueTooth connecting
    */
    private class ThreadConnectBTdevice extends Thread {

        private BluetoothSocket bluetoothSocket = null;
        private final BluetoothDevice bluetoothDevice;


        private ThreadConnectBTdevice(BluetoothDevice device) {
            bluetoothDevice = device;

            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);
                textStatus.setText("bluetoothSocket: \n" + bluetoothSocket);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean success = false;
            try {
                bluetoothSocket.connect();
                success = true;
            } catch (IOException e) {
                e.printStackTrace();

                final String eMessage = e.getMessage();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        textStatus.setText("Gagal Konek Cek Bluetoothnya \n" + eMessage);
                    }
                });

                try {
                    bluetoothSocket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if(success){
                //connect successful
                final String msgconnected = "Successful-"
                        + "Socket: " + bluetoothSocket + "\n"
                        + "Device: " + bluetoothDevice;

                runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        textStatus.setText(msgconnected);

                        listViewPairedDevice.setVisibility(View.GONE);
                        inputPane.setVisibility(View.VISIBLE);
                    }});

                startThreadConnected(bluetoothSocket);
            }else{
                //fail
            }
        }

        public void cancel() {

            Toast.makeText(getApplicationContext(),
                    "close bluetoothSocket",
                    Toast.LENGTH_LONG).show();

            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    /*
    ThreadConnected:
    Background Thread to handle Bluetooth data communication
    after connected
     */

    private class ThreadConnected extends Thread {
        private final BluetoothSocket connectedBluetoothSocket;
        private final InputStream connectedInputStream;
        private final OutputStream connectedOutputStream;

        public ThreadConnected(BluetoothSocket socket) {
            connectedBluetoothSocket = socket;
            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            connectedInputStream = in;
            connectedOutputStream = out;
        }

        @Override

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = connectedInputStream.read(buffer);
                    String strReceived = new String(buffer, 0, bytes);
                    final String msgReceived = String.valueOf(bytes) +
                            " bytes received:\n"
                            + strReceived;

                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            textStatus.setText(msgReceived);
                        }});

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    final String msgConnectionLost = "Connection lost:\n"
                            + e.getMessage();
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            textStatus.setText(msgConnectionLost);
                        }});
                }
            }
        }

        public void write(byte[] buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                connectedBluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText(""
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    public Runnable runnable2 = new Runnable() {

        public void run() {

            MillisecondTime2 = System.nanoTime() - StartTime2;
            Log.d("hitung:", String.valueOf(MillisecondTime2));
            UpdateTime2 = TimeBuff2 + MillisecondTime2;

            Seconds = (int) (UpdateTime2 / 1000);

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime2 % 1000);

            textView2.setText(""
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    public Runnable runnable3 = new Runnable() {

        public void run() {

            MillisecondTime3 = SystemClock.uptimeMillis() - StartTime3;

            UpdateTime3 = TimeBuff3 + MillisecondTime3;

            Seconds = (int) (UpdateTime3 / 1000);

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime3 % 1000);

            textView3.setText(""
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}

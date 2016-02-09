package atomar94.chupaclawbra;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    BluetoothAdapter mBluetoothAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public enum REQUEST_CODES {
        REQUEST_ENABLE_BT(1);

        private final int value;

        //set all enum values to ints
        private REQUEST_CODES(int value) {
            this.value = value;
        }
        public final int getInt() { //getter
            return value;
        }
    }

    /*   returns a set of all bonded bt devices on this device.
         if no bonded devices then return null.
     */
    public Set<BluetoothDevice> getBondedDevices() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            return pairedDevices;
        }
        else return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODES.REQUEST_ENABLE_BT.getInt()) {
            //bluetooth was enabled
            if(resultCode == RESULT_OK) {
                Log.v("Bluetooth", "Bluetooth enabled.");
            }
            else {
                Log.v("Bluetooth", "Bluetooth not enabled.");
                Toast tempToast = Toast.makeText(this, "Please Enable Bluetooth.", Toast.LENGTH_LONG);
                tempToast.show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast no_bt_toast = Toast.makeText(this, "Bluetooth is not supported on this device.", Toast.LENGTH_LONG);
            no_bt_toast.show();
        }
        else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_CODES.REQUEST_ENABLE_BT.getInt());
        }

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    private BluetoothAdapter getBTAdapter() {
        return mBluetoothAdapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void upButtonClick(View view) {
        Log.v("Control Flow", "Up button");

        bondedDeviceFragment fragment = new bondedDeviceFragment().newInstance();
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();


    }

    void downButtonClick(View view) {


    }

    void leftButtonClick(View view) {


    }

    void rightButtonClick(View view) {


    }
}



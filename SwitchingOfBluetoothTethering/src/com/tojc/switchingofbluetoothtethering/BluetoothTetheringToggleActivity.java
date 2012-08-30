package com.tojc.switchingofbluetoothtethering;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;

public class BluetoothTetheringToggleActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_tethering_toggle);

		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if(adapter != null)
		{
			adapter.getProfileProxy(this, new BluetoothProfile.ServiceListener()
			{
				@Override
				public void onServiceConnected(int arg0, BluetoothProfile arg1)
				{
					BluetoothPan pan = (BluetoothPan)arg1;

					boolean toggle = !pan.isTetheringOn();
					
					pan.setBluetoothTethering(toggle);
					showToast("Bluetooth Tethering: " + (toggle ? "On" : "Off"));

					finish();
				}

				@Override
				public void onServiceDisconnected(int profile)
				{
				}

			}, BluetoothProfile.PAN);
		}
		else
		{
			showToast("Bluetooth not supported.");
    		finish();
		}
	}

	private void showToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_LONG).show();
	}
}

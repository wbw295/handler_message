package com.example.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	/* ��UI�߳����view������ */
	private Button getSubID;
	private TextView mainThreadID, subThreadID;

	// handler�Ķ���
	class MyHandler extends Handler {

		public MyHandler(Looper looper) {
			super(looper);
			// TODO �Զ����ɵĹ��캯�����
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO �Զ����ɵķ������
			subThreadID.setText((String) msg.obj);
		}
	}

	// ����handler�����̵߳Ĺ���
	class MySubThread extends Thread {

		private Handler myHandler;

		@Override
		public void run() {
			// TODO �Զ����ɵķ������

			long subId = Thread.currentThread().getId();
			String obj = "I am sended by sub Thread~ID : " + subId;

			// Looper.prepare();
			Looper mainLooper = Looper.getMainLooper();
			// Looper subLooper = Looper.myLooper();

			myHandler = new MyHandler(mainLooper);
			// myHandler = new MyHandler(subLooper);

			Message m = myHandler.obtainMessage(1, 1, 1, obj);

			myHandler.sendMessage(m);
			// Looper.loop();

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupView();

	}

	private void setupView() {
		// TODO �Զ����ɵķ������
		getSubID = (Button) findViewById(R.id.btn_get_sub_thread_id);
		mainThreadID = (TextView) findViewById(R.id.label_main_thread_id);
		subThreadID = (TextView) findViewById(R.id.label_sub_thread_id);

		mainThreadID.setText("main thread ID : "
				+ Thread.currentThread().getId());

		getSubID.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO �Զ����ɵķ������
		if (view == getSubID) {

			new MySubThread().start();
		}

	}

}

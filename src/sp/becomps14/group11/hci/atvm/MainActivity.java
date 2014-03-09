package sp.becomps14.group11.hci.atvm;

import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	private int station_no;
	private int adults;
	private int adult_rate;
	private int children;
	private int child_rate;
	private int journey_type;
	private int ticket_fare;
	private int current_bal;
	private int final_bal;

	private Button stationNo[];
	private Button adultBtn[];
	private Button childBtn[];

	private Button printBtn;
	private Button resetBtn;
	private Button singleBtn;
	private Button returnBtn;

	private TextView curr_bal_disp;
	private TextView final_bal_disp;
	private TextView fare_disp;

	Random randomGenerator = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_list);
		init();
	}

	public void init()
	{
		station_no=1;
		adults=1;
		children=0;
		journey_type= 1;
		current_bal=100;
		current_bal = randomGenerator.nextInt(200);
		final_bal=0;

		if(current_bal < 7)
		{
			// show dialauge box
			AlertDialog.Builder curr_bal_init= new AlertDialog.Builder(this);
			curr_bal_init.setTitle("Not enough balance");
			curr_bal_init.setMessage("Your card does not have minimum balance required for a ticket. Please recharge or use another card. \nPress Okay to insert another card.");
			curr_bal_init.setPositiveButton("Okay", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
					dialog.cancel(); // nothing here.. this step has to be there.. otherwise it gives error.. (-_-)
				}
			});
			curr_bal_init.show();
			current_bal = randomGenerator.nextInt(200)+7; //generating new current balance
		}
		stationNo= new Button[12];
		stationNo[0]= (Button)findViewById(R.id.button1);
		stationNo[1]= (Button)findViewById(R.id.button2);
		stationNo[2]= (Button)findViewById(R.id.button3);
		stationNo[3]= (Button)findViewById(R.id.button4);
		stationNo[4]= (Button)findViewById(R.id.button5);
		stationNo[5]= (Button)findViewById(R.id.button6);
		stationNo[6]= (Button)findViewById(R.id.button7);
		stationNo[7]= (Button)findViewById(R.id.button8);
		stationNo[8]= (Button)findViewById(R.id.button9);
		stationNo[9]= (Button)findViewById(R.id.button10);
		stationNo[10]= (Button)findViewById(R.id.button11);
		stationNo[11]= (Button)findViewById(R.id.button12);

		for(int i=0;i<12; i++)
		{
			stationNo[i].setOnClickListener(this);
		}

		adultBtn= new Button[5];
		adultBtn[0]= (Button)findViewById(R.id.adult1);
		adultBtn[1]= (Button)findViewById(R.id.adult2);
		adultBtn[2]= (Button)findViewById(R.id.adult3);
		adultBtn[3]= (Button)findViewById(R.id.adult4);
		adultBtn[4]= (Button)findViewById(R.id.adult5);

		childBtn= new Button[5];
		childBtn[0]= (Button)findViewById(R.id.child0);
		childBtn[1]= (Button)findViewById(R.id.child1);
		childBtn[2]= (Button)findViewById(R.id.child2);
		childBtn[3]= (Button)findViewById(R.id.child3);
		childBtn[4]= (Button)findViewById(R.id.child4);

		for(int i=0;i<5; i++)
		{
			adultBtn[i].setOnClickListener(this);
			childBtn[i].setOnClickListener(this);
		}

		printBtn= (Button)findViewById(R.id.print_btn);
		printBtn.setOnClickListener(this);
		resetBtn= (Button)findViewById(R.id.reset_btn);
		resetBtn.setOnClickListener(this);
		singleBtn= (Button)findViewById(R.id.single_btn);
		singleBtn.setOnClickListener(this);
		returnBtn= (Button)findViewById(R.id.return_btn);
		returnBtn.setOnClickListener(this);

		curr_bal_disp= (TextView)findViewById(R.id.curr_bal_box);
		fare_disp= (TextView)findViewById(R.id.fareBox);
		final_bal_disp= (TextView)findViewById(R.id.final_bal_box);
		resetAll();
	}//end of init()

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
		case R.id.button1: setStation(1);
		break;
		case R.id.button2: setStation(2);
		break;
		case R.id.button3: setStation(3);
		break;
		case R.id.button4: setStation(4);
		break;
		case R.id.button5: setStation(5);
		break;
		case R.id.button6: setStation(6);
		break;
		case R.id.button7: setStation(7);
		break;
		case R.id.button8: setStation(8);
		break;
		case R.id.button9: setStation(9);
		break;
		case R.id.button10: setStation(10);
		break;
		case R.id.button11: setStation(11);
		break;
		case R.id.button12: setStation(12);
		break;

		case R.id.adult1: setAdult(1);
		break;
		case R.id.adult2: setAdult(2);
		break;
		case R.id.adult3: setAdult(3);
		break;
		case R.id.adult4: setAdult(4);
		break;
		case R.id.adult5: setAdult(5);
		break;

		case R.id.child0: setChild(0);
		break;
		case R.id.child1: setChild(1);
		break;
		case R.id.child2: setChild(2);
		break;
		case R.id.child3: setChild(3);
		break;
		case R.id.child4: setChild(4);
		break;

		case R.id.single_btn: setJourneyType(1);
		break;
		case R.id.return_btn: setJourneyType(2);
		break;

		case R.id.reset_btn: resetFunc();
		break;
		case R.id.print_btn: print();
		break;
		}
	}

	public void setStation(int no)
	{
		station_no= no;
		for(int i=0; i<12; i++)
		{
			setUnclicked(stationNo[i]);
		}
		setClicked(stationNo[no-1]);
		calculateFare();
	}

	public void setAdult(int no)
	{
		adults=no;
		for(int i=0; i<5; i++)
		{
			setUnclicked(adultBtn[i]);
		}
		setClicked(adultBtn[no-1]);

		calculateFare();
	}

	public void setChild(int no)
	{
		children=no;
		for(int i=0; i<5; i++)
		{
			setUnclicked(childBtn[i]);
		}
		setClicked(childBtn[no]);

		calculateFare();
	}

	public void setJourneyType(int no)
	{
		journey_type=no;
		setUnclicked(singleBtn);
		setUnclicked(returnBtn);
		if(no==1)
			setClicked(singleBtn);
		else
			setClicked(returnBtn);

		calculateFare();
	}

	public void resetAll()
	{
		setAdult(1);
		setChild(0);
		setStation(1);
		setJourneyType(1);
		calculateFare();
	}

	public void resetFunc()
	{
		AlertDialog.Builder negative_bal= new AlertDialog.Builder(this);

		negative_bal.setTitle("Reset");
		negative_bal.setMessage("Press 'Reset' to reset the chosen options or 'Change Card' to use another card.");
		negative_bal.setPositiveButton("Reset", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				resetAll();
				Toast reset_toast = Toast.makeText(getApplicationContext(),"Options reset.", Toast.LENGTH_SHORT);
				reset_toast.show();
			}
		});
		negative_bal.setNegativeButton("Change Card", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				current_bal = randomGenerator.nextInt(300)+7;
				resetAll();
			}
		});
		negative_bal.show();
	}

	public void calculateFare()
	{
		curr_bal_disp.setText(Integer.toString(current_bal));
		if(station_no<4)
		{
			adult_rate=7;
			child_rate=4;
		}
		else if(station_no > 9)
		{
			adult_rate=11;
			child_rate=6;
		}
		else
		{
			adult_rate=9;
			child_rate=5;
		}

		int temp_sum=0;
		temp_sum+= adult_rate*adults + child_rate*children;

		ticket_fare= temp_sum*journey_type;
		final_bal= current_bal- ticket_fare;

		if(final_bal < 0)
		{
			AlertDialog.Builder negative_bal= new AlertDialog.Builder(this);
			negative_bal.setTitle("Not enough balance");
			negative_bal.setMessage("Your card does not have enough balance for this transaction.\nPress 'Ok' to insert another card or 'Reset' to reset your chosen options.");
			negative_bal.setPositiveButton("Ok", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
					current_bal = randomGenerator.nextInt(200);
					resetAll();
				}
			});
			negative_bal.setNegativeButton("Reset", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
					resetAll();
					Toast reset_toast = Toast.makeText(getApplicationContext(),"Options reset.", Toast.LENGTH_SHORT);
					reset_toast.show();
				}
			});
			negative_bal.show();
		}
		fare_disp.setText(Integer.toString(ticket_fare));
		final_bal_disp.setText(Integer.toString(final_bal));
	}//end calculateFare

	public void print()
	{
		//show dialogue box
		AlertDialog.Builder print_dialog = new AlertDialog.Builder(this);
		print_dialog.setMessage("Print the ticket?");
		print_dialog.setCancelable(true);
		print_dialog.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				Toast print_toast = Toast.makeText(getApplicationContext(),"Collect your ticket.", Toast.LENGTH_SHORT);
				print_toast.show();
			}
		});
		print_dialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		print_dialog.setNeutralButton("Reset",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		print_dialog.show();
	}

	//color change on click
	public void setClicked(Button btn)
	{
		btn.setBackground(getResources().getDrawable(R.drawable.select40x40));
		btn.setTextColor(Color.WHITE);
	}
	public void setUnclicked(Button btn)
	{
		btn.setBackground(getResources().getDrawable(R.drawable.btn_40x40));
		btn.setTextColor(Color.BLACK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
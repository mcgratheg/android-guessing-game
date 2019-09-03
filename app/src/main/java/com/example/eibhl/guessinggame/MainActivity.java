package com.example.eibhl.guessinggame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editNumber;
    private Button buttonGuess;
    private TextView textOutput;
    private int theNumber;

    public void newGame() {
        theNumber = (int) (Math.random()*100 + 1);
    }

    public void checkGuess() {

        // get the number the user entered
        String theGuess = editNumber.getText().toString();

        try {
            int guess = Integer.parseInt(theGuess);
            String message = "";
            if (guess >= 1 && guess <= 100) {
                if (guess > theNumber) {
                    // too high
                    message = guess + " was too high, guess again";
                    textOutput.setText(message);
                } else if (guess < theNumber) {
                    // too low
                    message = guess + " was too low, guess again";
                    textOutput.setText(message);
                } else {
                    // guess correct
                    message = guess + " was right! You win! Play again.";
                    textOutput.setText(message);
                    newGame();
                }
            } else {
                message = "Please enter whole number between 1 and 100";
                textOutput.setText(message);
            }
        } catch (Exception e) {
            String message = "Please enter whole number between 1 and 100";
            textOutput.setText(message);
        } finally {
            // highlight the editNumber field and focus
            editNumber.requestFocus();
            editNumber.selectAll();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = (EditText) findViewById(R.id.editNumber);
        buttonGuess = (Button) findViewById(R.id.buttonGuess);
        textOutput = (TextView) findViewById(R.id.textOutput);

        newGame();

        // set up event listener for buttonGuess
        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        // set up event listener for input field
        editNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}

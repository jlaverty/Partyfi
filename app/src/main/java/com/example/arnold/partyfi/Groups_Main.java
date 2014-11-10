package com.example.arnold.partyfi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Arnold on 11/9/2014.
 */
public class Groups_Main extends ActionBarActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_group_options);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.groups:
                groupsGo();
                return true;
            case R.id.parties:
                partiesGo();
                return true;
            case R.id.friends:
                friendsGo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void friendsGo()
    {
        Intent intent = new Intent(this, Friends_Main.class);
        startActivity(intent);
    }

    public void groupsGo()
    {
        Intent intent = new Intent(this, Groups_Main.class);
        startActivity(intent);
    }

    public void partiesGo()
    {
        Intent intent = new Intent(this, Parties_Main.class);
        startActivity(intent);
    }
}

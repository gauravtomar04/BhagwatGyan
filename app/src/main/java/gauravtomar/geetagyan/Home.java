package gauravtomar.geetagyan;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;///

public class Home extends AppCompatActivity {

    private DatabaseReference mref;
    ArrayList <String> arrayList = new ArrayList <>();
    ArrayList <String> mkey = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        // final TextView textView2 =(TextView)findViewById(R.id.textView2);
        final ListView lists = (ListView) findViewById( R.id.lists );

        //final FirebaseDatabase database= FirebaseDatabase.getInstance();
        // final DatabaseReference mref = database.getReference("Gaurav");
        mref = FirebaseDatabase.getInstance().getReference();
        final ArrayAdapter <String> arrayAdapter = new ArrayAdapter <String>( this, android.R.layout.simple_list_item_1, arrayList );
        lists.setAdapter( arrayAdapter );

        mref.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue( String.class );

                String key = dataSnapshot.getKey();
                mkey.add( key );
                /// listner
                arrayList.add( value );
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue( String.class );
                String key = dataSnapshot.getKey();
                int index = mkey.indexOf( key );

                arrayList.set( index, value );
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
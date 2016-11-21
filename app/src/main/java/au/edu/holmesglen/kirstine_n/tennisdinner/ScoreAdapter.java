package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Kirsti on 21/11/2016.
 */

public class ScoreAdapter extends BaseAdapter {
    // this references the array passed through the constructor
    private Score[] mScores;  // an array of the Item class - our Item class represents color images
    private Context mContext;  // the current state of an app or object or Activity


    /**
     * Constructor
     * @param context
     * @param scoreArray
     */
    public ScoreAdapter(Context context, Score[] scoreArray) {
        this.mContext = context;
        this.mScores = scoreArray;
    }


    /**
     * Gets the size of the array mScores.
     * @return      integer representing array size.
     */
    public int getCount() {
        return mScores.length;
    }


    /**
     * Gets the current Item (color) in a grid on GridView
     * @param position
     * @return      Item object
     */
    public Score getItem(int position) {
        return null;
    }


    /**
     * Gets the position in GridView of where the color can be found
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }


    /**
     * Gets an ImageView to display the correct color image for the position passed in.     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(mScores[position].display());
        return textView;
    }
}

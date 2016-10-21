package au.edu.holmesglen.kirstine_n.tennisdinner;

/**
 * Created by Kirsti on 21/10/2016.
 */

public class TennisDBSchema {
    public static final class TennisTable {
        public static final String NAME = "scores";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String HYDRO_SCORE = "hydro_score";
            public static final String DYNAMITE_SCORE = "dynamite_score";
        }
    }

}

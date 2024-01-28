package es.agilcentros.pmdm.recyclerview22;


import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

// Patrones de diseño:
// Singleton
public class DataProvider {

    private static DataProvider instance;
    private final Context context;
    private List<User> users;

    private DataProvider(Context context) {
       this.context = context;
   }

   public static DataProvider getInstance(Context context) {
       if (instance == null) {
           instance = new DataProvider(context);
       }
       return instance;
   }

   public List<User> getUsersList() {
       String[] names = context.getResources().getStringArray(R.array.names);
       String[] genders = context.getResources().getStringArray(R.array.genders);
       String[] streets = context.getResources().getStringArray(R.array.streets);
       String[] locations = context.getResources().getStringArray(R.array.locations);
       String[] emails = context.getResources().getStringArray(R.array.emails);
       String[] phones = context.getResources().getStringArray(R.array.phones);
       TypedArray pictureResIds = context.getResources().obtainTypedArray(R.array.pictures);

       users = new ArrayList<>();
       for (int i = 0; i < names.length; i++) {
           User user = new User(
                   names[i],
                   genders[i],
                   streets[i],
                   locations[i],
                   emails[i],
                   phones[i],
                   pictureResIds.getResourceId(i, 0)
                   );
           users.add(user);
       }
       return users;
   }

}

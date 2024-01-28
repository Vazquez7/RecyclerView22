package es.agilcentros.pmdm.recyclerview22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvUsers = findViewById(R.id.rv_users);

        rvUsers.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(layoutManager);

        UsersAdapter usersAdapter = new UsersAdapter(DataProvider.getInstance(this));
        rvUsers.setAdapter(usersAdapter);

        usersAdapter.setOnUserSelectionListener(new OnUserSelectionListener() {
            @Override
            public void onUserSelection(User user) {
                setUserSelected(user);
            }
        });

    }

    private void setUserSelected(User user) {
        selectedUser = user;
        if (user != null) {
            Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

interface OnUserSelectionListener {

    void onUserSelection(User user);

}


class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEWTYPE_FEMALE = 0;
    public static final int VIEWTYPE_MALE = 1;
    private final List<User> users;
    private User userSelected;
    private RecyclerView.ViewHolder selectedViewHolder;
    private OnUserSelectionListener onUserSelectionListener;

    public UsersAdapter(DataProvider dataProvider) {
        users = dataProvider.getUsersList();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        if (viewType == VIEWTYPE_FEMALE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.female_user_item, parent, false);
            final FemaleViewHolder femaleViewHolder = new FemaleViewHolder(itemView);
            View.OnClickListener userClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserClick(femaleViewHolder);
                }
            };
            femaleViewHolder.itemView.setOnClickListener(userClickListener);
            return femaleViewHolder;
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.male_user_item, parent, false);
            final MaleViewHolder maleViewHolder = new MaleViewHolder(itemView);
            View.OnClickListener userClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserClick(maleViewHolder);
                }
            };
            maleViewHolder.itemView.setOnClickListener(userClickListener);
            return maleViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        if (getItemViewType(position) == VIEWTYPE_FEMALE) {
            FemaleViewHolder myholder = ((FemaleViewHolder) holder);
            myholder.ivFemalePicture.setImageResource(user.getPictureRes());
            myholder.tvFemaleName.setText(user.getName());
            myholder.tvFemaleEmail.setText(user.getEmail());
            myholder.tvFemalePhone.setText(user.getPhone());


            if (user == userSelected) {
                myholder.itemView.setBackgroundResource(R.color.itemBackgroundSelected);
            } else {
                myholder.itemView.setBackgroundResource(R.color.itemBackground);
            }

        } else {
            MaleViewHolder myholder = ((MaleViewHolder) holder);
            myholder.ivMalePicture.setImageResource(user.getPictureRes());
            myholder.tvMaleName.setText(user.getName());
            myholder.tvMaleStreet.setText(user.getStreet());
            myholder.tvMaleLocation.setText(user.getLocation());


            if (user == userSelected) {
                myholder.itemView.setBackgroundResource(R.color.itemBackgroundSelected);
            } else {
                myholder.itemView.setBackgroundResource(R.color.itemBackground);
            }
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public int getItemViewType(int position) {
        User user = users.get(position);
        if (user.getGender().equals("female")) {
            return VIEWTYPE_FEMALE;
        }
        return VIEWTYPE_MALE;
    }

    public void onUserClick(RecyclerView.ViewHolder viewHolder) {
        User clickedUser = users.get(viewHolder.getAdapterPosition());
        if (selectedViewHolder != null) {
            selectedViewHolder.itemView.setBackgroundResource(R.color.itemBackground);
        }
        if (clickedUser == userSelected) {
            viewHolder.itemView.setBackgroundResource(R.color.itemBackground);
            userSelected = null;
        } else {
            viewHolder.itemView.setBackgroundResource(R.color.itemBackgroundSelected);
            userSelected = clickedUser;
            selectedViewHolder = viewHolder;
        }
        Log.d("UsersAdapter", clickedUser.toString());

        if (onUserSelectionListener != null) {
            onUserSelectionListener.onUserSelection(userSelected);
        }
    }

    public void setOnUserSelectionListener(OnUserSelectionListener onUserSelectionListener) {
        this.onUserSelectionListener = onUserSelectionListener;
    }


    public static class FemaleViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFemalePicture;
        private TextView tvFemaleName;
        private TextView tvFemaleEmail;
        private TextView tvFemalePhone;

        public FemaleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFemalePicture = itemView.findViewById(R.id.iv_female_picture);
            tvFemaleName = itemView.findViewById(R.id.tv_female_name);
            tvFemaleEmail = itemView.findViewById(R.id.tv_female_email);
            tvFemalePhone = itemView.findViewById(R.id.tv_female_phone);
        }
    }

    public static class MaleViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivMalePicture;
        private TextView tvMaleName;
        private TextView tvMaleStreet;
        private TextView tvMaleLocation;

        public MaleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMalePicture = itemView.findViewById(R.id.iv_male_picture);
            tvMaleName = itemView.findViewById(R.id.tv_male_name);
            tvMaleStreet = itemView.findViewById(R.id.tv_male_street);
            tvMaleLocation = itemView.findViewById(R.id.tv_male_location);
        }
    }
}


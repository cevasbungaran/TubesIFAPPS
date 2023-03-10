package com.example.tugasifapps2.View;
import com.example.tugasifapps2.Presenter.PresenterUser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tugasifapps2.FragmentListener;
import com.example.tugasifapps2.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements View.OnClickListener, PresenterUser.PresenterCallback {
    private FragmentLoginBinding binding;
    private FragmentManager fragmentManager;
    private FragmentListener fragmentListener;

    //must-have empty constructor
    public LoginFragment(){}

    //singleton
    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();


        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the Layout for this fragment
        this.binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        this.binding.btnLogin.setOnClickListener(this::onClick);

        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()+"Must implement fragment listener");
        }
    }

    @Override
    public void onClick(View v) {
        PresenterUser.login(
                this.binding.etEmailLogin.getText().toString(),
                this.binding.etPassword.getText().toString(),
                this
        );
    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(getActivity().getApplicationContext(),
                "login success " +
                        result,
                Toast.LENGTH_LONG).show();
        this.fragmentListener.changePage(2);
    }

    @Override
    public void loginError(String result) {
        Toast.makeText(getActivity().getApplicationContext(),
                "login error ",
                Toast.LENGTH_LONG).show();
    }
}

//&& this.binding.radioGroup.getCheckedRadioButtonId() == -1

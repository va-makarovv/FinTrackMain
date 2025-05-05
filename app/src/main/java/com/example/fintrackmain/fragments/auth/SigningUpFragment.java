package com.example.fintrackmain.fragments.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fintrackmain.R;
import com.example.fintrackmain.databinding.FragmentSigningUpBinding;


public class SigningUpFragment extends Fragment {

    FragmentSigningUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSigningUpBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int colorFocus = ContextCompat.getColor(requireContext(),R.color.picked);
        int colorWrong = ContextCompat.getColor(requireContext(),R.color.wrongData);
        int colorWrongFont = ContextCompat.getColor(requireContext(),R.color.wrongDataFont);
        int colorEt = ContextCompat.getColor(requireContext(),R.color.et);


        binding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SigningUpFragment.this).navigate(R.id.action_signingUp_to_welcomeFragment);
            }
        });

        binding.btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();

            }
        });
        binding.tietNickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    binding.tilNickname.setBoxStrokeColor(colorFocus);
                    binding.tvCaptNick.setVisibility(View.VISIBLE);
                    binding.tvCaptNick.setText("6 symbols at least. Latin, numbers ans special characters");
                    binding.tvCaptNick.setTextColor(colorEt);
                }else if (!hasFocus && binding.tilNickname.getBoxStrokeColor() == colorWrong){
                    binding.tvCaptNick.setVisibility(View.GONE);
                } else if (hasFocus && binding.tilNickname.getBoxStrokeColor() != colorWrong){
                    binding.tvCaptNick.setVisibility(View.GONE);
                }

            }
        });

        binding.tietPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    binding.tilPass.setBoxStrokeColor(colorFocus);
                    binding.tvCaptPass.setVisibility(View.VISIBLE);
                    binding.tvCaptPass.setText("8 symbols at least. Latin, numbers ans special characters");
                }else {
                    binding.tvCaptPass.setVisibility(View.GONE);
                }
            }
        });

        binding.tietMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    binding.tilMail.setBoxStrokeColor(colorFocus);
                    binding.tvCaptMail.setVisibility(View.VISIBLE);
                    binding.tvCaptMail.setText("example@samsung.com");
                }else {
                    binding.tvCaptMail.setVisibility(View.GONE);
                }
            }
        });

        binding.tietMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!isValidEmail(s.toString())) {
                    binding.tvCaptMail.setVisibility(View.VISIBLE);
                    binding.tvCaptMail.setText("Invalid email format");
                    binding.tvCaptMail.setTextColor(colorWrong);

                    binding.tilMail.setBoxStrokeColor(colorWrong);
                    binding.tilMail.setBoxBackgroundColor(colorWrongFont);
                } else {
                    binding.tvCaptMail.setVisibility(View.GONE);
                    binding.tvCaptMail.setTextColor(colorEt);
                    binding.tilMail.setBoxStrokeColor(colorEt);
                    binding.tilMail.setBoxBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white));
                }
            }
        });
    }

    private void validateInputs() {
        String nick = binding.tietNickname.getText().toString().trim();
        String password = binding.tietPass.getText().toString().trim();
        String mail = binding.tietMail.getText().toString().trim();

        int colorWrong = ContextCompat.getColor(requireContext(),R.color.wrongData);
        int colorWrongFont = ContextCompat.getColor(requireContext(),R.color.wrongDataFont);
        int colorEt = ContextCompat.getColor(requireContext(),R.color.et);

        resetErrorState();

        boolean isValid = true;

        if (nick.length() < 3) {
            showErrorState("nick");
            binding.tvCaptNick.setVisibility(View.VISIBLE);
            binding.tvCaptNick.setText("3 symbols at least. Latin, numbers ans special characters");
            binding.tvCaptNick.setTextColor(colorWrong);
            isValid = false;
        } else if (TextUtils.isEmpty(nick)) {
            showErrorState("nick");
            binding.tvCaptNick.setText("This field is required");
            binding.tvCaptNick.setTextColor(colorWrong);
            isValid = false;
        } else {
            resetErrorState();
            binding.tvCaptNick.setVisibility(View.GONE);
        }

        if (!isPasswordValid(password)) {
            showErrorState("pass");
            binding.tvCaptPass.setVisibility(View.VISIBLE);
            binding.tvCaptPass.setText("8 symbols at least. Latin, numbers ans special characters");
            binding.tvCaptPass.setTextColor(colorWrong);
            isValid = false;
        } else {
            resetErrorState();
            binding.tvCaptPass.setVisibility(View.GONE);
        }

        if (!isValidEmail(mail)) {
            showErrorState("mail");
            binding.tvCaptMail.setVisibility(View.VISIBLE);
            binding.tvCaptMail.setText("Please enter a valid email address");
            binding.tvCaptMail.setTextColor(colorWrong);
            isValid = false;
        } else {
            resetErrorState();
            binding.tvCaptMail.setVisibility(View.GONE);
        }

        if (isValid) {
            NavHostFragment.findNavController(SigningUpFragment.this)
                    .navigate(R.id.action_signingUp_to_transactionSyncFragment);

        }

        if (TextUtils.isEmpty(nick)) {
            showErrorState("nick");
            binding.tvCaptNick.setText("This field is required");
            binding.tvCaptNick.setTextColor(colorWrong);
            isValid = false;
        } else {
            resetErrorState();
            binding.tvCaptNick.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(password)) {
            showErrorState("pass");
            binding.tvCaptPass.setText("This field is required");
            binding.tvCaptPass.setTextColor(colorWrong);
            isValid = false;
        } else {
            resetErrorState();
            binding.tvCaptPass.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(mail)) {
            showErrorState("mail");
            binding.tvCaptMail.setText("This field is required");
            binding.tvCaptMail.setTextColor(colorWrong);
            isValid = false;
        } else {
            resetErrorState();
            binding.tvCaptMail.setVisibility(View.GONE);
        }

    }

    private void showErrorState(String point) {

        int colorWrong = ContextCompat.getColor(requireContext(),R.color.wrongData);
        int colorWrongFont = ContextCompat.getColor(requireContext(),R.color.wrongDataFont);


        switch (point){
            case "nick":
                binding.tilNickname.setBoxStrokeColor(colorWrong);
                binding.tilNickname.setBoxBackgroundColor(colorWrongFont);
                break;
            case "pass":
                binding.tilPass.setBoxStrokeColor(colorWrong);
                binding.tilPass.setBoxBackgroundColor(colorWrongFont);
                break;
            case "mail":
                binding.tilMail.setBoxStrokeColor(colorWrong);
                binding.tilMail.setBoxBackgroundColor(colorWrongFont);
                break;
        }

    }

    private void resetErrorState() {


        int colorEt = ContextCompat.getColor(requireContext(),R.color.et);
        int colorWhite = ContextCompat.getColor(requireContext(),R.color.white);

        binding.tilNickname.setBoxStrokeColor(colorEt);
        binding.tilNickname.setBoxBackgroundColor(colorWhite);

        binding.tilPass.setBoxStrokeColor(colorEt);
        binding.tilPass.setBoxBackgroundColor(colorWhite);

        binding.tilMail.setBoxStrokeColor(colorEt);
        binding.tilMail.setBoxBackgroundColor(colorWhite);
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isPasswordValid(String password) {
        // Минимум 8 символов, хотя бы одна цифра и одна буква
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";
        return password.matches(passwordPattern);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
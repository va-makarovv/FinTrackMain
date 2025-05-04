package com.example.fintrackmain.fragments.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fintrackmain.R;
import com.example.fintrackmain.databinding.FragmentWelcomeBinding;


public class WelcomeFragment extends Fragment {


    private FragmentWelcomeBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int colorFocus = ContextCompat.getColor(requireContext(),R.color.picked);


        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeFragment.this).navigate(R.id.action_welcomeFragment_to_signingUp);

            }
        });
        binding.btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btSignin.setOnClickListener(v -> validateInputs());

            }
        });

        binding.tvForgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.tietLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.tietLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    binding.cvLogPass.setStrokeColor(colorFocus);
                    binding.line.setBackgroundColor(colorFocus);

                }
            }
        });

    }

    private void validateInputs() {
        String login = binding.tietLogin.getText().toString().trim();
        String password = binding.tietPass.getText().toString().trim();

        if (login.length() <= 6 || password.length() <= 6) {
            showErrorState();
            binding.tvError.setVisibility(View.VISIBLE);
            binding.tvError.setText("Login and password must be longer than 6 characters");
        } else {
            resetErrorState();
            binding.tvError.setVisibility(View.GONE);
            // Proceed with sign in
        }
    }

    private void showErrorState() {
        // Change stroke color
        binding.cvLogPass.setStrokeColor(ContextCompat.getColor(requireContext(), R.color.wrongData));

        // Change background color inside stroke
        binding.cvLogPass.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.wrongDataFont));

        // Change line color
        binding.line.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.wrongData));
    }

    private void resetErrorState() {
        // Reset to default colors
        binding.cvLogPass.setStrokeColor(ContextCompat.getColor(requireContext(), R.color.et));
        binding.cvLogPass.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
        binding.line.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.et));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
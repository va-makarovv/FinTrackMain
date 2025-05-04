package com.example.fintrackmain.fragments.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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


        binding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SigningUpFragment.this).navigate(R.id.action_signingUp_to_welcomeFragment);
            }
        });

        binding.btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.tietNickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    binding.tilNickname.setBoxStrokeColor(colorFocus);
                    binding.tvCaptNick.setVisibility(View.VISIBLE);
                    binding.tvCaptNick.setText("6 symbols at least. Latin, numbers ans special characters");
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
                }
            }
        });
    }

    private void validateInputs() {
        String nick = binding.tietNickname.getText().toString().trim();
        String password = binding.tietPass.getText().toString().trim();
        String mail = binding.tietMail.getText().toString().trim();


        if (nick.length() < 6) {
            showErrorState(nick);
            binding.tvCaptNick.setVisibility(View.VISIBLE);
            binding.tvCaptNick.setText("6 symbols at least. Latin, numbers ans special characters");
        } else if (password.length() < 8) {
            showErrorState(password);
            binding.tvCaptPass.setVisibility(View.VISIBLE);
            binding.tvCaptPass.setText("8 symbols at least. Latin, numbers ans special characters");
        } else if (e) {
            
        }

    }

    private void showErrorState(String point) {
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
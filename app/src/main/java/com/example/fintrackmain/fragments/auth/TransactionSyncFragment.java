package com.example.fintrackmain.fragments.auth;

import android.content.res.ColorStateList;
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
import com.example.fintrackmain.databinding.FragmentTransactionSyncBinding;
import com.example.fintrackmain.databinding.FragmentWelcomeBinding;


public class TransactionSyncFragment extends Fragment {


    FragmentTransactionSyncBinding binding;
    private String selectedOption = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTransactionSyncBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivSmsCheck.setImageResource(R.drawable.ic_unchecked);
        binding.ivManualCheck.setImageResource(R.drawable.ic_unchecked);

        setupChoiceButtons();

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(TransactionSyncFragment.this).navigate(R.id.action_transactionSyncFragment_to_allowSMSFragment);
            }
        });
    }

    private void setupChoiceButtons() {



        binding.llSms.setOnClickListener(v -> {
            updateSelection("sms");
        });
        binding.tvSms.setOnClickListener(v -> {
            updateSelection("sms");
        });
        binding.ivSmsCheck.setOnClickListener(v -> {
            updateSelection("sms");
        });


        binding.llManual.setOnClickListener(v -> {
            updateSelection("manual");
        });
        binding.tvManual.setOnClickListener(v -> {
            updateSelection("manual");
        });
        binding.ivManualCheck.setOnClickListener(v -> {
            updateSelection("manual");
        });
    }

    private void updateSelection(String option) {
        selectedOption = option;

        int colorPicked = ContextCompat.getColor(requireContext(),R.color.picked);
        int colorWhite = ContextCompat.getColor(requireContext(),R.color.white);

        // Обновляем иконки
        if (option.equals("sms")) {
            binding.ivSmsCheck.setImageTintList(ColorStateList.valueOf(colorPicked));
            binding.ivManualCheck.setImageTintList(ColorStateList.valueOf(colorWhite));
            binding.tvDesc1.setText("Recording your expenses might be time-consumin type of activity. Doesn’t exclude manual input.");
            binding.tvDesc.setVisibility(View.VISIBLE);
        } else {
            binding.tvDesc1.setText("The good old manual input might be harder way of accounting than any other, but it is the most reliable one if done consistently.");
            binding.tvDesc.setVisibility(View.INVISIBLE);
            binding.ivSmsCheck.setImageTintList(ColorStateList.valueOf(colorWhite));
            binding.ivManualCheck.setImageTintList(ColorStateList.valueOf(colorPicked));
        }
        binding.btNext.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}
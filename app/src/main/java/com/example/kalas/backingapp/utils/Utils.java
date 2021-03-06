package com.example.kalas.backingapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kalas.backingapp.R;
import com.example.kalas.backingapp.adapter.RecipeAdapter;
import com.example.kalas.backingapp.adapter.StepAdapter;
import com.example.kalas.backingapp.databinding.FragmentListMainBinding;
import com.example.kalas.backingapp.databinding.FragmentListStepsBinding;
import com.example.kalas.backingapp.fragments.MainListFragment;
import com.example.kalas.backingapp.fragments.StepsListFragment;
import com.example.kalas.backingapp.model.Ingredient;
import com.example.kalas.backingapp.model.Recipe;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.kalas.backingapp.utils.BuildConfig.DASH;

public class Utils {

    /**
     * Method to show the Toast messages
     * @param context current context
     * @param message text message from string resources
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to show the Custom Toast messages
     *
     * @param context  current context
     * @param activity current activity
     */
    public static void showCustomToast(Context context, Activity activity) {
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        toast.setView(inflater.inflate(R.layout.toast_message, (ViewGroup) activity.findViewById(R.id.toast_msg_container)));
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Method to set the View for the MainListFragment
     * @param mainListFragment to get the current Context
     * @param recipeAdapter RecipeAdapter for the RecyclerView (recyclerView)
     * @param binding to get the recyclerView
     * @param spanCount get number of span from the integers.xml recourse
     */
    public static void configViews(MainListFragment mainListFragment, RecipeAdapter recipeAdapter, FragmentListMainBinding binding, int spanCount) {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(mainListFragment.getContext(), spanCount));
        binding.recyclerView.setAdapter(recipeAdapter);
    }

    /**
     * Method to set the View for the MainListFragment
     * @param stepFragment to get the current Context
     * @param adapter StepAdapter for the RecyclerView (recyclerViewStep)
     * @param binding  to get the recyclerView
     */
    public static void configViews(StepsListFragment stepFragment, StepAdapter adapter, FragmentListStepsBinding binding) {
        binding.recyclerViewStep.setHasFixedSize(true);
        binding.recyclerViewStep.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        binding.recyclerViewStep.setLayoutManager(new GridLayoutManager(stepFragment.getContext(), 1));
        binding.recyclerViewStep.setAdapter(adapter);
    }

    /**
     * Method expand or collapse ExpendableLayout
     * @param id of the expandableLayout
     * @param binding to get the expandable layout
     */
    public static void expendableLayoutSettings(int id, FragmentListStepsBinding binding) {
        switch (id) {
            case R.id.expand_layout:
                if (!binding.expandableLayoutStepDescription.isExpanded()) {
                    binding.expandableLayoutStepDescription.expand();
                    binding.expandLayout.arrowDown.setVisibility(View.GONE);
                    binding.expandLayout.arrowUp.setVisibility(View.VISIBLE);
                } else {
                    binding.expandableLayoutStepDescription.collapse();
                    binding.expandLayout.arrowDown.setVisibility(View.VISIBLE);
                    binding.expandLayout.arrowUp.setVisibility(View.GONE);
                }
                break;
        }
    }

    /**
     * Method to set Recipe from passed Recipes
     * @param recipes received list of recipes
     * @return recipe
     */
    public static Recipe setRecipe(ArrayList<Recipe> recipes) {
        Recipe recipe = null;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
        }
        return recipe;
    }

    public static void displayIngredientData(Recipe recipe, SpannableStringBuilder builder) {
        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {

            builder.append(StringUtils.capitalize(ingredient.getIngredient())).append(DASH)
                    .append(String.valueOf(ingredient.getQuantity())).append(StringUtils.SPACE)
                    .append(ingredient.getMeasure().toLowerCase())
                    .append(StringUtils.LF);
        }
    }
}

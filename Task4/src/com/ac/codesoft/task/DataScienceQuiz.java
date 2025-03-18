package com.ac.codesoft.task;

import java.util.*;

public class DataScienceQuiz {
    HashMap<String, List<String>> questions = new HashMap<>();

    public DataScienceQuiz() {
        questions.put("What is the main goal of data science?", Arrays.asList(
                "Extract insights from data", "Store large amounts of data", "Optimize network performance", "Create databases"));

        questions.put("Which programming language is most commonly used in data science?", Arrays.asList(
                "Python", "Java", "C++", "Swift"));

        questions.put("What is supervised learning?", Arrays.asList(
                "A model that learns from labeled data",
                "A learning method without labels",
                "A method for unsupervised learning",
                "A way to store data"));

        questions.put("Which library is commonly used for data manipulation in Python?", Arrays.asList(
                "Pandas", "TensorFlow", "Matplotlib", "PyTorch"));

        questions.put("What does a confusion matrix help with?", Arrays.asList(
                "Evaluating classification models", "Cleaning data",
                "Generating random numbers", "Storing large datasets"));

        questions.put("Which type of variable is used for categorical data?", Arrays.asList(
                "Nominal", "Integer", "Float", "Boolean"));

        questions.put("What does 'overfitting' mean in machine learning?", Arrays.asList(
                "A model that performs well on training data but poorly on new data",
                "A model that generalizes well to new data",
                "A model that uses too few parameters",
                "A model that is too simple"));

        questions.put("What is the purpose of principal component analysis (PCA)?", Arrays.asList(
                "Dimensionality reduction", "Data cleaning", "Model evaluation", "Hyperparameter tuning"));

        questions.put("Which metric is commonly used to evaluate regression models?", Arrays.asList(
                "Mean Squared Error (MSE)", "Accuracy", "F1 Score", "Confusion Matrix"));

        questions.put("Which SQL clause is used to group rows based on a specific column?", Arrays.asList(
                "GROUP BY", "ORDER BY", "HAVING", "WHERE"));
    }

    public HashMap<String, List<String>> getQuestions() {
        return questions;
    }
}




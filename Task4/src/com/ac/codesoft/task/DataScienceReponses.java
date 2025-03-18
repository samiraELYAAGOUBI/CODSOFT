package com.ac.codesoft.task;

import java.util.HashMap;

public class DataScienceReponses {
    HashMap<String, String> reponses = new HashMap<>();

    public DataScienceReponses() {
        reponses.put("What is the main goal of data science?", "Extract insights from data");
        reponses.put("Which programming language is most commonly used in data science?", "Python");
        reponses.put("What is supervised learning?", "A model that learns from labeled data");
        reponses.put("Which library is commonly used for data manipulation in Python?", "Pandas");
        reponses.put("What does a confusion matrix help with?", "Evaluating classification models");
        reponses.put("Which type of variable is used for categorical data?", "Nominal");
        reponses.put("What does 'overfitting' mean in machine learning?", "A model that performs well on training data but poorly on new data");
        reponses.put("What is the purpose of principal component analysis (PCA)?", "Dimensionality reduction");
        reponses.put("Which metric is commonly used to evaluate regression models?", "Mean Squared Error (MSE)");
        reponses.put("Which SQL clause is used to group rows based on a specific column?", "GROUP BY");
    }

    public String getCorrectAnswer(String question) {
        return reponses.get(question);
    }
    public HashMap<String, String> getReponses() {
        return reponses;
    }
}

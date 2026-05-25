package com.example.calculatrice1; // mon package

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1. Déclaration des variables pour l'interface graphique
    private EditText etNumber1, etNumber2;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Lier au fichier XML

        // 2. Initialisation des composants en les reliant avec leurs ID du fichier XML
        etNumber1 = findViewById(R.id.et_number1);
        etNumber2 = findViewById(R.id.et_number2);
        btnAdd = findViewById(R.id.btn_add);
        btnSubtract = findViewById(R.id.btn_subtract);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);
        tvResult = findViewById(R.id.tv_result);

        // 3. Définition des écouteurs de clics pour chaque bouton
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer("+");
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer("-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer("*");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer("/");
            }
        });
    }

    // 4. Méthode unique pour gérer toute la logique de calcul
    private void calculer(String operation) {
        // Récupérer le texte saisi par l'utilisateur
        String strN1 = etNumber1.getText().toString().trim();
        String strN2 = etNumber2.getText().toString().trim();

        // Sécurité : Vérifier si l'un des champs est vide
        if (strN1.isEmpty() || strN2.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir les deux nombres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir les textes en nombres décimaux (double)
        double num1 = Double.parseDouble(strN1);
        double num2 = Double.parseDouble(strN2);
        double resultat = 0;

        // Structure conditionnelle pour appliquer la bonne opération
        switch (operation) {
            case "+":
                resultat = num1 + num2;
                break;
            case "-":
                resultat = num1 - num2;
                break;
            case "*":
                resultat = num1 * num2;
                break;
            case "/":
                // Sécurité cruciale : Empêcher la division par zéro !
                if (num2 == 0) {
                    Toast.makeText(this, "Erreur : Division par zéro impossible !", Toast.LENGTH_LONG).show();
                    tvResult.setText("Résultat : Erreur");
                    return;
                }
                resultat = num1 / num2;
                break;
        }

        // Afficher le résultat final directement sur l'écran
        tvResult.setText("Résultat : " + resultat);
    }
}
package com.example.pirates_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ImageView im1;
    private ImageView im2;
    private ImageView im3;
    private ImageView im4;
    private ImageView im5;
    private ImageView im6;
    private Button button_get_cards;
    private Button button_new_game;
    private Deck game;
    private int img_link1;
    private int img_link2;
    private int img_link3;
    private TextView tv1;
    private TextView tv2;

    private String card_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_get_cards = findViewById(R.id.button);
        button_get_cards.setOnClickListener(onClickListener);

        button_new_game = findViewById(R.id.button2);
        button_new_game.setOnClickListener(onClickListener);

        im1 = findViewById(R.id.imageView1);
        im2 = findViewById(R.id.imageView2);
        im3 = findViewById(R.id.imageView3);
        im4 = findViewById(R.id.imageView4);
        im5 = findViewById(R.id.imageView5);
        im6 = findViewById(R.id.imageView6);

        im1.setOnClickListener(onClickListener);
        im2.setOnClickListener(onClickListener);
        im3.setOnClickListener(onClickListener);

        im1.setImageResource(0);
        im2.setImageResource(0);
        im3.setImageResource(0);
        im4.setImageResource(0);
        im5.setImageResource(0);
        im6.setImageResource(0);

        tv1 = findViewById(R.id.textView); // "Игровое поле"
        tv1.setText("");
        tv2 = findViewById(R.id.textView2); // "Отложенные карты"
        tv2.setText("");

        //создаем объект колода выбранного цвета
        card_color = getIntent().getExtras().getString("color");
        game = new Deck(this, card_color);

        button_get_cards.callOnClick(); // вывод карт на игровое поле после первого старта
    }

    //обработчик нажатия на кнопки _________________________________________________________________
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button: { //РАЗДАТЬ КАРТЫ
                    tv1.setText("Игровые карты:");
                    tv2.setText("");
                    im1.setImageResource(getResources().getIdentifier(card_color + "_back", "drawable", getPackageName())); im1.setTag("fon");
                    im2.setImageResource(getResources().getIdentifier(card_color + "_back", "drawable", getPackageName())); im2.setTag("fon");
                    im3.setImageResource(getResources().getIdentifier(card_color + "_back", "drawable", getPackageName())); im3.setTag("fon");
                    im4.setImageResource(0);
                    im5.setImageResource(0);
                    im6.setImageResource(0);

                    game.pileToDeck();
                    game.shuffle();

                    img_link1 = game.getCardFromDeck(0).getImage();
                    img_link2 = game.getCardFromDeck(1).getImage();
                    img_link3 = game.getCardFromDeck(2).getImage();

                    game.deckToPile();

                    //отображение карт с черепками отдельно
                    for(int i = 0; i < game.getPileSize(); ++i){
                        switch (i){
                            case (0): {
                                im4.setImageResource(game.getCardFromPile(0).getImage());
                                tv2.setText("Отложенные карты с черепками:");
                                break;
                            }
                            case (1): im5.setImageResource(game.getCardFromPile(1).getImage()); break;
                            case (2): im6.setImageResource(game.getCardFromPile(2).getImage());
                        }
                    }
                    button_get_cards.setEnabled(false);
                    break;
                }
                case R.id.imageView1: { //нажатие на карту №1
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, im1, ViewCompat.getTransitionName(im1));
                    Intent intent = new Intent(MainActivity.this, Big_card.class);
                    intent.putExtra("id_image", img_link1);

                    im1.setImageResource(img_link1);
                    im1.setTag("card");
                    startActivity(intent, options.toBundle());
                    if ((im1.getTag().equals("card")) && (im2.getTag().equals("card")) && (im3.getTag().equals("card"))) {button_get_cards.setEnabled(true);}
                    break;
                }
                case R.id.imageView2: {//нажатие на карту №2
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, im2, ViewCompat.getTransitionName(im2));
                    Intent intent = new Intent(MainActivity.this, Big_card.class);
                    intent.putExtra("id_image", img_link2);

                    im2.setImageResource(img_link2);
                    im2.setTag("card");
                    startActivity(intent, options.toBundle());
                    if ((im1.getTag().equals("card")) && (im2.getTag().equals("card")) && (im3.getTag().equals("card"))) {button_get_cards.setEnabled(true);}
                    break;
                }
                case R.id.imageView3: {//нажатие на карту №3
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, im3, ViewCompat.getTransitionName(im3));
                    Intent intent = new Intent(MainActivity.this, Big_card.class);
                    intent.putExtra("id_image", img_link3);

                    im3.setImageResource(img_link3);
                    im3.setTag("card");
                    startActivity(intent, options.toBundle());
                    if ((im1.getTag().equals("card")) && (im2.getTag().equals("card")) && (im3.getTag().equals("card"))) {button_get_cards.setEnabled(true);}
                    break;
                }
                case R.id.button2: {  // НАЧАТЬ ЗАНОВО
                    /*tv1.setText("");
                    tv2.setText("");
                    im1.setImageResource(0);
                    im2.setImageResource(0);
                    im3.setImageResource(0);
                    im4.setImageResource(0);
                    im5.setImageResource(0);
                    im6.setImageResource(0);

                    game = new Deck();
                    button_get_cards.setEnabled(true);*/

                    onBackPressed();
                    break;
                }
            }
        }
    };
}
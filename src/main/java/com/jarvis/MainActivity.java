package com.jarvis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private JarvisMemory memory;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memory = new JarvisMemory(this);
        setContentView(createUi());
    }

    private View createUi() {
        HologramLayout root = new HologramLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(32, 48, 32, 32);

        TextView title = label("JARVIS", 42, Color.WHITE, true);
        TextView subtitle = label("Sistema operacional pessoal de IA", 16, Color.CYAN, false);
        answer = label("JARVIS inicializado. Comande por texto; voz em segundo plano está pronta para ativação autorizada.", 17, Color.WHITE, false);
        EditText input = new EditText(this);
        input.setHint("Comando para o JARVIS");
        input.setSingleLine(false);
        input.setTextColor(Color.WHITE);
        input.setHintTextColor(0x99FFFFFF);
        input.setBackgroundColor(0x22FFFFFF);

        Button run = new Button(this);
        run.setText("Executar comando");
        run.setOnClickListener(v -> {
            String command = input.getText().toString().trim();
            if (!command.isEmpty()) {
                String response = JarvisEngine.respond(command);
                memory.save(command, response);
                answer.setText(response);
                startService(new Intent(this, JarvisVoiceService.class).putExtra("say", response));
            }
        });

        root.addView(title);
        root.addView(subtitle);
        root.addView(card(answer));
        root.addView(input, new LinearLayout.LayoutParams(-1, -2));
        root.addView(run, new LinearLayout.LayoutParams(-1, -2));
        root.addView(card(label("Módulos: Memória • Voz • Overlay autorizado • Visão de tela • Automações • Casa inteligente", 15, Color.WHITE, false)));

        ScrollView scroll = new ScrollView(this);
        scroll.addView(root);
        return scroll;
    }

    private TextView label(String text, int sp, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(sp);
        view.setTextColor(color);
        view.setPadding(0, 14, 0, 14);
        if (bold) view.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
        return view;
    }

    private View card(View child) {
        LinearLayout card = new LinearLayout(this);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundColor(0x2200E5FF);
        card.addView(child);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(0, 20, 0, 20);
        card.setLayoutParams(lp);
        return card;
    }

    public static final class HologramLayout extends LinearLayout {
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public HologramLayout(android.content.Context context) { super(context); setWillNotDraw(false); }
        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float cx = getWidth() / 2f;
            paint.setShader(new RadialGradient(cx, 240, Math.max(getWidth(), getHeight()), 0x66304488, 0xFF050814, Shader.TileMode.CLAMP));
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
            paint.setShader(null);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(0x6600E5FF);
            canvas.drawCircle(cx, 260, 90, paint);
            canvas.drawCircle(cx, 260, 155, paint);
        }
    }
}

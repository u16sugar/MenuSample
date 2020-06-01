package com.websarva.wings.android.menusample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // リストビューを表すフィールド
    private ListView _lvMenu;
    // リストビューに表示するリストデータ
    private List<Map<String, Object>> _menuList;
    // SimpleAdapterの第４引数fromに使用する定数フィールド
    private static final String[] FROM = {"name", "price"};
    // SimpleAdapterの第５引数toに使用する定数フィールド
    private static final int[] TO = {R.id.tvMenuName, R.id.tvMenuPrice};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 画面部品ListViewを取得
//        ListView lvMenu = findViewById(R.id.lvMenu);
//        // SimpleAdapterで使用するListオブジェクトを用意
//        List<Map<String, String>> menuList = new ArrayList<>();
//        // から揚げ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
//        Map<String, String> menu = new HashMap<>();
//        menu.put("name", "から揚げ定食");
//        menu.put("price", "800円");
//        menuList.add(menu);
//        // ハンバーグ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
//        menu = new HashMap<>();
//        menu.put("name", "ハンバーグ定食");
//        menu.put("price", "850円");
//        menuList.add(menu);

//        // SimpleAdapter 第４引数
//        String[] from = {"name", "price"};
//        // SimpleAdapter 第５引数to用データの用意
//        int[] to = {android.R.id.text1, android.R.id.text2};
//        // SimpleAdapterを生成
//        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, menuList, android.R.layout.simple_list_item_2, from, to);
//        // アダプタの登録
//        lvMenu.setAdapter(adapter);
//
//        // リストタップのリスナクラス登録
//        lvMenu.setOnItemClickListener(new ListItemClickListener());

        // 画面部品ListViewを取得し、フィールドに格納
        _lvMenu = findViewById(R.id.lvMenu);
        // 定食メニューはListオブジェクトをprivateメソッドを利用して用意し、フィールドに格納
        _menuList = createTeishokuList();
        // SimpleAdapterを生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        // アダプタの登録
        _lvMenu.setAdapter(adapter);
        // リストタップのリスナクラス登録
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        registerForContextMenu(_lvMenu);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // タップされた行のデータ取得。SimpleAdapterでは１行分のデータはMap型！
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
//            // 定食名と金額を取得。
//            String menuName = (String) item.get("name");
//            Integer menuPrice = (Integer) item.get("price");
//            // インテントオブジェクトを生成
//            Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);
//            // 第２画面に送るデータを格納
//            intent.putExtra("menuName", menuName);
//            intent.putExtra("menuPrice", menuPrice + "円");
//            // 第２画面の起動
//            startActivity(intent);
            order(item);
        }
    }

    private List<Map<String, Object>> createTeishokuList() {
        // 定食メニュー用のリストオブジェクトを用意
        List<Map<String, Object>> menuList = new ArrayList<>();
        // から揚げ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鶏のから揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);
        // ハンバーグ定食
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューインフレーターの取得
        MenuInflater inflater = getMenuInflater();
        // オプションメニュー用.xmlファイルをインフレート
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        // 親クラスの同名メソッドを呼び出し。その戻り値を返却
        return super.onCreateOptionsMenu(menu);
    }

    private List<Map<String, Object>> createCurryList() {
        // カレーメニューリスト用のListオブジェクトを用意
        List<Map<String, Object>> menuList = new ArrayList<>();
        // 「ビーフカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選スパイスをきかせた国産ビーフ100%のカレーです。");
        menuList.add(menu);
        // ポークカレー
        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選スパイスをきかせた国産ポーク100%のカレーです。");
        menuList.add(menu);

        return menuList;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 選択されたメニューのIDを取得
        int itemId = item.getItemId();
        // IDのR値による処理の分岐
        switch (itemId) {
            // 定食メニューが選択された場合の処理
            case R.id.menuListOptionTeishoku:
                // 定食メニューリストデータの生成
                _menuList = createTeishokuList();
                break;
            // カレーメニューが選択された場合の処理
            case R.id.menuListOptionCurry:
                // カレーメニューリストデータの生成
                _menuList = createCurryList();
                break;
        }
        // Simple Adapterを選択されたメニューデータで生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        // アダプタの登録
        _lvMenu.setAdapter(adapter);
        // 親クラスの同名メソッドを呼び出し、その戻り値を返却
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 親クラスの同名メソッドの呼び出し
        super.onCreateContextMenu(menu, v, menuInfo);
        // メニューインフレーターの取得
        MenuInflater inflater = getMenuInflater();
        // コンテキストメニュー用.xmlファイルをインフレーと
        inflater.inflate(R.menu.menu_context_menu_list, menu);
        // コンテキストメニューのヘッダタイトルを設定
        menu.setHeaderTitle(R.string.menu_list_context_header);
    }

    private void order(Map<String, Object> menu) {
        // 定食名と金額を取得。Mapの値部分がObject型なのでキャストが必要
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");
        // インテントオブジェクトを生成
        Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);
        // 第２画面に送るデータを格納
        intent.putExtra("menuName", menuName);
        intent.putExtra("menuPrice", menuPrice + "円");
        // 第２画面の起動
        startActivity(intent);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // 長押しされたビューに関する情報が格納されたオブジェクトを取得
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // 長押しされたリストのポジションを取得
        int listPosition = info.position;
        // ポジションから長押しされたメニュー情報Mapオブジェクトを取得
        Map<String, Object> menu = _menuList.get(listPosition);

        // 選択されたメニューのIDを取得
        int itemId = item.getItemId();
        // IDのR値による処理の分岐
        switch (itemId) {
            // [説明を表示]メニューが選択されたときの処理
            case R.id.menuListContextDesc:
                // メニューの説明文字列を取得
                String desc = (String) menu.get("desc");
                // トーストを表示
                Toast.makeText(MainActivity.this, desc, Toast.LENGTH_LONG).show();
                break;
            // [ご注文]メニューが選択されたときの処理
            case R.id.menuListContextOrder:
                // 注文処理
                order(menu);
                break;
        }

        return super.onContextItemSelected(item);
    }
}

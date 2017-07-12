package com.buyoldgood.gucheng.myvolleytwo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gucheng on 2017/7/11.
 */

public class Goods {
    /**
     * buy_id : 0
     * sale_id : 0
     * thing_id : 1
     * thing_intro : 重庆大学计算机学院官方教材
     * thing_name : 数据结构
     * thing_num : 0
     * thing_picture : http://118.113.9.65:8080/PlayTogether/image/thing_picture/1.jpg
     * thing_price_before : 80
     * thing_price_now : 20
     * thing_sale_date : 2017-07-07
     * thing_type_id : 1
     */

    private int buy_id;
    private int sale_id;
    private int thing_id;
    private String thing_intro;
    private String thing_name;
    private int thing_num;
    private String thing_picture;
    private String thing_price_before;
    private String thing_price_now;
    private String thing_sale_date;
    private int thing_type_id;

    public static Goods objectFromData(String str) {

        return new Gson().fromJson(str, Goods.class);
    }

    public static Goods objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Goods.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Goods> arrayGoodsFromData(String str) {

        Type listType = new TypeToken<ArrayList<Goods>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Goods> arrayGoodsFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Goods>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getThing_id() {
        return thing_id;
    }

    public void setThing_id(int thing_id) {
        this.thing_id = thing_id;
    }

    public String getThing_intro() {
        return thing_intro;
    }

    public void setThing_intro(String thing_intro) {
        this.thing_intro = thing_intro;
    }

    public String getThing_name() {
        return thing_name;
    }

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public int getThing_num() {
        return thing_num;
    }

    public void setThing_num(int thing_num) {
        this.thing_num = thing_num;
    }

    public String getThing_picture() {
        return thing_picture;
    }

    public void setThing_picture(String thing_picture) {
        this.thing_picture = thing_picture;
    }

    public String getThing_price_before() {
        return thing_price_before;
    }

    public void setThing_price_before(String thing_price_before) {
        this.thing_price_before = thing_price_before;
    }

    public String getThing_price_now() {
        return thing_price_now;
    }

    public void setThing_price_now(String thing_price_now) {
        this.thing_price_now = thing_price_now;
    }

    public String getThing_sale_date() {
        return thing_sale_date;
    }

    public void setThing_sale_date(String thing_sale_date) {
        this.thing_sale_date = thing_sale_date;
    }

    public int getThing_type_id() {
        return thing_type_id;
    }

    public void setThing_type_id(int thing_type_id) {
        this.thing_type_id = thing_type_id;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "buy_id=" + buy_id +
                ", sale_id=" + sale_id +
                ", thing_id=" + thing_id +
                ", thing_intro='" + thing_intro + '\'' +
                ", thing_name='" + thing_name + '\'' +
                ", thing_num=" + thing_num +
                ", thing_picture='" + thing_picture + '\'' +
                ", thing_price_before='" + thing_price_before + '\'' +
                ", thing_price_now='" + thing_price_now + '\'' +
                ", thing_sale_date='" + thing_sale_date + '\'' +
                ", thing_type_id=" + thing_type_id +
                '}';
    }
}

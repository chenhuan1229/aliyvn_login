package com.aliqin.mytel;

public class Constant {
    public static final String[] TYPES = {"全屏（竖屏）", "全屏（横屏）", "弹窗（竖屏）",
            "弹窗（横屏）", "底部弹窗", "自定义View", "自定义View（Xml）"};
    public enum UI_TYPE {
        /**
         * 全屏（竖屏）
         */
        FULL_PORT,
        /**
         * 全屏（横屏）
         */


        FULL_LAND,
        /**
         * 弹窗（竖屏）
         */
        DIALOG_PORT,
        /**
         * "弹窗（横屏）
         */
        DIALOG_LAND,
        /**
         * 底部弹窗
         */
        DIALOG_BOTTOM,
        /**
         * 自定义View
         */
        CUSTOM_VIEW,
        /**
         * 自定义View（Xml）
         */
        CUSTOM_XML
    }

    public static final String THEME_KEY = "theme";

    public static final String LOGIN_TYPE = "login_type";
    public static final int LOGIN = 1;
    public static final int LOGIN_DELAY = 2;
}

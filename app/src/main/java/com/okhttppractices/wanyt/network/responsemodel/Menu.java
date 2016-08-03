package com.okhttppractices.wanyt.network.responsemodel;

import java.util.List;

/**
 * Created on 2016/8/3 17:14
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class Menu {

    private String resultcode;//请求结果的返回值
    private String reason;//是否成功
    private ResultBean result;
    private int error_code;//错误码

    public static class ResultBean {
        private String totalNum;//当前搜索结果的总数
        private String pn;
        private String rn;
        private List<Cook> data;

        public static class Cook {
            private String id;
            private String title;//菜名
            private String tags;//事宜人群
            private String imtro;
            private String ingredients;//配料
            private String burden;//佐料
            private List<String> albums;//图片
            private List<StepsBean> steps;
            public static class StepsBean {
                private String img;//图片
                private String step;//具体做法
            }
        }
    }
}

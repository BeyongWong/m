package com.example.admin.mysimplenews;

import java.util.List;

/**
 * Created by admin on 2016/11/10.
 */

public class TianQiBean  {

    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"5","ganmao":"澶╂皵杞噳锛岀┖姘旀箍搴﹁緝澶э紝杈冩槗鍙戠敓鎰熷啋锛屼綋璐ㄨ緝寮辩殑鏈嬪弸璇锋敞鎰忛\u20ac傚綋闃叉姢銆�","forecast":[{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 9鈩�","type":"闇�","low":"浣庢俯 1鈩�","date":"10鏃ユ槦鏈熷洓"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 11鈩�","type":"澶氫簯","low":"浣庢俯 1鈩�","date":"11鏃ユ槦鏈熶簲"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 12鈩�","type":"鏅�","low":"浣庢俯 2鈩�","date":"12鏃ユ槦鏈熷叚"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 13鈩�","type":"闇�","low":"浣庢俯 3鈩�","date":"13鏃ユ槦鏈熷ぉ"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 14鈩�","type":"鏅�","low":"浣庢俯 2鈩�","date":"14鏃ユ槦鏈熶竴"}],"yesterday":{"fl":"寰","fx":"鏃犳寔缁鍚�","high":"楂樻俯 10鈩�","type":"闇�","low":"浣庢俯 2鈩�","date":"9鏃ユ槦鏈熶笁"},"aqi":"275","city":"鍖椾含"}
     */

    private String desc;
    private int status;
    /**
     * wendu : 5
     * ganmao : 澶╂皵杞噳锛岀┖姘旀箍搴﹁緝澶э紝杈冩槗鍙戠敓鎰熷啋锛屼綋璐ㄨ緝寮辩殑鏈嬪弸璇锋敞鎰忛€傚綋闃叉姢銆�
     * forecast : [{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 9鈩�","type":"闇�","low":"浣庢俯 1鈩�","date":"10鏃ユ槦鏈熷洓"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 11鈩�","type":"澶氫簯","low":"浣庢俯 1鈩�","date":"11鏃ユ槦鏈熶簲"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 12鈩�","type":"鏅�","low":"浣庢俯 2鈩�","date":"12鏃ユ槦鏈熷叚"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 13鈩�","type":"闇�","low":"浣庢俯 3鈩�","date":"13鏃ユ槦鏈熷ぉ"},{"fengxiang":"鏃犳寔缁鍚�","fengli":"寰绾�","high":"楂樻俯 14鈩�","type":"鏅�","low":"浣庢俯 2鈩�","date":"14鏃ユ槦鏈熶竴"}]
     * yesterday : {"fl":"寰","fx":"鏃犳寔缁鍚�","high":"楂樻俯 10鈩�","type":"闇�","low":"浣庢俯 2鈩�","date":"9鏃ユ槦鏈熶笁"}
     * aqi : 275
     * city : 鍖椾含
     */


    private DataBean data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String wendu;
        private String ganmao;
        /**
         * fl : 寰
         * fx : 鏃犳寔缁鍚�
         * high : 楂樻俯 10鈩�
         * type : 闇�
         * low : 浣庢俯 2鈩�
         * date : 9鏃ユ槦鏈熶笁
         */

        private YesterdayBean yesterday;
        private String aqi;
        private String city;
        /**
         * fengxiang : 鏃犳寔缁鍚�
         * fengli : 寰绾�
         * high : 楂樻俯 9鈩�
         * type : 闇�
         * low : 浣庢俯 1鈩�
         * date : 10鏃ユ槦鏈熷洓
         */

        private List<ForecastBean> forecast;

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class ForecastBean {
            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
